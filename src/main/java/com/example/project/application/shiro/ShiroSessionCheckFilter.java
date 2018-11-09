package com.example.project.application.shiro;

import com.example.project.model.response.JSONResponse;
import com.example.project.model.shiro.ShiroUser;
import com.google.gson.Gson;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xuhan on 2018/11/9.
 */
public class ShiroSessionCheckFilter extends FormAuthenticationFilter {
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = this.getSubject(request, response);
        ShiroUser shiroUser;
        if (subject != null && subject.isAuthenticated() && (shiroUser = (ShiroUser) subject.getPrincipal()) != null && shiroUser.getUid() != null) {
            return true;
        } else {
            this.sessionOut(request, response);
            return false;
        }
    }

    private void sessionOut(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        WebUtils.saveRequest(request);
        httpResponse.setStatus(200, "redirect");
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;

        try {
            out = httpResponse.getWriter();
            JSONResponse jsonResponse = JSONResponse.sessionOut("sessionOut", "sessionOut");

            out.append((new Gson()).toJson(jsonResponse));
        } catch (IOException var9) {
            var9.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }

        }

    }
}
