package com.example.demo.config;

import com.example.demo.user.SessionDto;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;

@WebListener
public class SessionListener implements HttpSessionListener {

    //获取线程安全得list
    public static List<SessionDto> sessionList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("create------------------111111111111111");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("delete------------------2222222222222222");
        if(se != null && se.getSession() != null &&  se.getSession().getId() != null ){
            String sessionId = se.getSession().getId();
            //遍历
            if(sessionList != null && sessionList.size() >0){
                Iterator<SessionDto> iterator = sessionList.iterator();
                while (iterator.hasNext()){
                    SessionDto obj = iterator.next();
                    //删除
                    if(sessionId.equals(obj.getSessionid())){
                        iterator.remove();
                    }
                }
            }
        }
    }
}
