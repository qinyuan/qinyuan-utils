package com.qinyuan15.utils.mvc.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import java.lang.Exception;import java.lang.Override;import java.lang.String;

/**
 * Controller to output identity code
 * Created by qinyuan on 15-6-28.
 */
public class IdentityCodeController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("image/jpeg");
        String str = IdentifyCode.getPicture(response);
        request.getSession().setAttribute("identityCode", str);
        return null;
    }
}
