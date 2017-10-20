package com.rumofuture.moon.util.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MoonExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(ex));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();

//        // FastJson处理方式，此方式按success返回，并非error
//        ModelAndView mv = new ModelAndView();
//        FastJsonJsonView view = new FastJsonJsonView();
//        Map<String, Object> params = new HashMap<>();
//        params.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        params.put("msg", ex.getMessage());
//        view.setAttributesMap(params);
//        mv.setView(view);
//        return mv;
    }
}
