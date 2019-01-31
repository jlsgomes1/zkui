package org.grails.plugins.zkui.util

import javax.servlet.http.HttpServletRequest
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.zkoss.zk.ui.*
import org.zkoss.zul.*
import org.zkoss.zk.ui.util.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.ext.*
import org.zkoss.zk.au.*
import org.zkoss.zk.au.out.*
import org.zkoss.zul.theme.*


/**
 *
 * @author jlsgomes1@gmail.com
 */
class ZkThemeManager {
    private switchTheme(String themeName){
        if (themeName.equals("") || themeName == null){
           themeName = "iceblue"  // default ZK Theme
        }
        Themes.setTheme(Executions.getCurrent(), themeName)
        Executions.sendRedirect("");
    }
    
    String getCurrentThemeName(){
        def cookieThemeResolver = new CookieThemeResolver()
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        return cookieThemeResolver.getTheme(request)
    }
}


