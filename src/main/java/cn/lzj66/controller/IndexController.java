package cn.lzj66.controller;


import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    private String show(){
        return "/show";
    }
    @Value("${goeasy.key.super}")
    private String superKey;
    @Value("${goeasy.regionHost}")
    private String regionHost;

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }
    @RequestMapping("/register")
    public String register(){
        return "/register";
    }
    @RequestMapping("/teacherLogin")
    public String teacherLogin(){
        return  "/teacherLogin";
    }
    @RequestMapping("/teacherRegister")
    public String teacherRegister(){
        return "/teacherRegister";
    }
    @RequestMapping(value = "/goeasy/index")
    public String goeasyIndex(){
        return "/goeasy/index";
    }
    @ResponseBody
    @RequestMapping(value = "/goeasy/push")
    public String goeasyPush(){
        GoEasy goEasy = new GoEasy(regionHost,superKey);
        goEasy.publish("class6","你们好啊");
        return "ok";
    }
}
