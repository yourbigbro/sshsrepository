package com.itheima.day49.user.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
    /**
     * 根据指定的名称查找cookie
     * 
     * @param string 指定的cookie名称
     * @return
     */
    public static Cookie getCookieByName(Cookie[] cookies, String name) {
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

}
