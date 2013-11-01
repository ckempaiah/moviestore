package com.sjsu.movie.web;
import com.sjsu.movie.domain.MembershipInfo;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/membershipinfoes")
@Controller
@RooWebScaffold(path = "membershipinfoes", formBackingObject = MembershipInfo.class)
public class MembershipInfoController {
}
