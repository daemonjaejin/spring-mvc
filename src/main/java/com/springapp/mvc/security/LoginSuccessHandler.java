package com.springapp.mvc.security;

import com.springapp.mvc.domain.UserVo;
import com.springapp.mvc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler
{
    @Autowired
    private LoginService loginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException
    {
        try {
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            UserVo user = loginService.selectByUserId(userId);
            HttpSession session  =  request.getSession();
            session.setAttribute("userId",userId);
            session.setAttribute("userNo",user.getUserNo());
            session.setAttribute("memberYn",user.getMemberYn());
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(getReturnUrl(request, response));
        /*로그 남기는 부분도 필요*/

    }

    /**
     * 로그인 하기 전의 요청했던 URL을 알아낸다.
     *
     * @param request
     * @param response
     * @return
     */
    private String getReturnUrl(HttpServletRequest request, HttpServletResponse response) {
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest == null) {
            return request.getSession().getServletContext().getContextPath();
        }
        return savedRequest.getRedirectUrl();
    }
}

