package com.springapp.mvc.controller;

import com.springapp.mvc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jin on 15. 10. 21..
 */
@Controller
@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
public class LoginController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/beforeLogin", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "로그인");
        return "login";
    }

}
