package com.example.demo.controller;


import com.example.demo.config.SessionListener;
import com.example.demo.user.SessionDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping(value = "/login")
public class LoginController {


    @RequestMapping(value = "/user")
    public ModelAndView login(HttpServletRequest request , HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/index")
    public String index( String userName , String userPwd, HttpServletRequest request){
        String result = "";
//        customUserService.loadUserByUsername(userName);
        System.out.println("start....");
        //得到用户信息
        String id = "sadda";
        //得到对象
        List<SessionDto> list = SessionListener.sessionList;
        if(list != null && list.size() > 0){
            for (SessionDto sessionDto: list  ) {
                if(id.equals(sessionDto.getUserid())){
                   return  "用户已登录了";
                }
            }
        }
        String sessionid = request.getSession().getId();
        SessionDto sessionDto = new SessionDto();
        sessionDto.setUserid(id);
        sessionDto.setSessionid(sessionid);
        sessionDto.setCurrDate(new Date());
        SessionListener.sessionList.add(sessionDto);
        request.getSession().setMaxInactiveInterval(30);
        return sessionid;
    }

    @RequestMapping(value = "/index1")
    public String index1(String userName , String userPwd, HttpServletRequest request){
        String result = "";
//        customUserService.loadUserByUsername(userName);
        String sessionid = request.getSession().getId();
        return sessionid;
    }

}
