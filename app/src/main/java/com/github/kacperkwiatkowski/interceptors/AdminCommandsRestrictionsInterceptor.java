package com.github.kacperkwiatkowski.interceptors;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static java.lang.Integer.parseInt;

class AdminCommandsRestrictionsInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String theMethod = request.getMethod();
        String admin = request.getHeader("admin");
        final Map<String, String> pathVariables = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if ((HttpMethod.PATCH.matches(theMethod) || HttpMethod.DELETE.matches(theMethod))
                        && parseInt(pathVariables.get("id")) == parseInt(admin))
        {

            //FIXME Throw appropriate error
            throw new Exception("Admin cannot influence his own account.");
        } else return true;
    }
}