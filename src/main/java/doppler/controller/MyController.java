package doppler.controller;

import doppler.service.CalService;
import doppler.service.LoginService;
import doppler.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by doppler on 2016/5/8.
 */
@Controller
public class MyController {

    private String[] loginInfo;
    private String text;
    private List[] lists;
    private String[] results;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ProcessService processService;
    @Autowired
    private CalService calService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    String home() {
        return "view/home.html";
    }
    @RequestMapping(value = "jade",method = RequestMethod.GET)
    String jade(){

        return "home";
    }
    @RequestMapping(value = "test",method = RequestMethod.GET)
    String test(){
        return "test";
    }
    @RequestMapping("/gpa")
    @ResponseBody
    String[] run(@RequestParam("user") String user, @RequestParam("sign") String sign,
                  @RequestParam("signedpwd") String signedpwd){
        loginInfo = new String[]{user,signedpwd,sign};
        text = loginService.loginAndGetText(loginInfo);
        if (text == null)
            return new String[]{"login Error"};
        lists = processService.process(text);
        results = calService.calculateGPA(lists);
        return results;
    }

}
