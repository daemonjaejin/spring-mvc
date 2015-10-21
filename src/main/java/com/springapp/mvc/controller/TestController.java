package com.springapp.mvc.controller;

import com.springapp.mvc.domain.TestVo;
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
@RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        try {
            TestVo testVo = testService.select();
            System.out.println("toString() : " + testVo.toString());
        }catch (Exception e){
            System.out.println("message : "  + e.getMessage());
        }
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

}
