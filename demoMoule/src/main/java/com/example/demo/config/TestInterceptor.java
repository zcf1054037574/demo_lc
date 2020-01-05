package com.example.demo.config;


import com.example.demo.user.SessionDto;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service(value = "testInterceptor")
public class TestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        String sessionId = request.getSession().getId();
        //设置session时长
        List<SessionDto> list = SessionListener.sessionList;
        boolean flag = false;
        if(list != null && list.size() > 0){
            for (SessionDto sessionDto: list  ) {
                if (sessionId.equals(sessionDto.getUserid())) {
                    flag = true;
                    request.getSession().setMaxInactiveInterval(30);
                }
            }
        }
        System.out.println("执行拦截器 afterCompletion...."+sessionId);

    }

}