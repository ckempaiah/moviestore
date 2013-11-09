package com.sjsu.movie.web;
import com.sjsu.movie.domain.MemberType;
import com.sjsu.movie.domain.MembershipInfo;
import com.sjsu.movie.service.MovieUserService;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/membershipinfoes")
@Controller
public class MembershipInfoController {

	@Autowired
    MovieUserService movieUserService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid MembershipInfo membershipInfo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, membershipInfo);
            return "membershipinfoes/create";
        }
        uiModel.asMap().clear();
        membershipInfo.persist();
        return "redirect:/membershipinfoes/" + encodeUrlPathSegment(membershipInfo.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new MembershipInfo());
        return "membershipinfoes/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("membershipinfo", MembershipInfo.findMembershipInfo(id));
        uiModel.addAttribute("itemId", id);
        return "membershipinfoes/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("membershipinfoes", MembershipInfo.findMembershipInfoEntries(firstResult, sizeNo));
            float nrOfPages = (float) MembershipInfo.countMembershipInfoes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("membershipinfoes", MembershipInfo.findAllMembershipInfoes());
        }
        addDateTimeFormatPatterns(uiModel);
        return "membershipinfoes/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid MembershipInfo membershipInfo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, membershipInfo);
            return "membershipinfoes/update";
        }
        uiModel.asMap().clear();
        membershipInfo.merge();
        return "redirect:/membershipinfoes/" + encodeUrlPathSegment(membershipInfo.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, MembershipInfo.findMembershipInfo(id));
        return "membershipinfoes/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        MembershipInfo membershipInfo = MembershipInfo.findMembershipInfo(id);
        membershipInfo.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/membershipinfoes";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("membershipInfo_createddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("membershipInfo_updateddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, MembershipInfo membershipInfo) {
        uiModel.addAttribute("membershipInfo", membershipInfo);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("membertypes", MemberType.findAllMemberTypes());
        uiModel.addAttribute("movieusers", movieUserService.findAllMovieUsers());
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
}
