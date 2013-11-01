package com.sjsu.movie.web;
import com.sjsu.movie.domain.MemberType;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/membertypes")
@Controller
@RooWebScaffold(path = "membertypes", formBackingObject = MemberType.class)
public class MemberTypeController {
}
