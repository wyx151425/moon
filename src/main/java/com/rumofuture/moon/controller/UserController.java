package com.rumofuture.moon.controller;

import com.rumofuture.moon.domain.User;
import com.rumofuture.moon.service.UserService;
import com.rumofuture.moon.util.DataValidationUtil;
import com.rumofuture.moon.util.constant.KeyConstant;
import com.rumofuture.moon.util.dto.user.UserLoginDTO;
import com.rumofuture.moon.util.dto.user.UserPasswordUpdateDTO;
import com.rumofuture.moon.util.dto.user.UserSignUpDTO;
import com.rumofuture.moon.util.exception.MoonException;
import com.rumofuture.moon.util.exception.MoonJSRRuntimeException;
import com.sun.javafx.css.StyleCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signUp")
    public User signUp(
            HttpServletRequest request,
            @Valid @RequestBody UserSignUpDTO dto,
            BindingResult bindingResult
    ) {
        RequestContext requestContext = new RequestContext(request);
        DataValidationUtil.execute(bindingResult, requestContext);

        User validUser = userService.getUserByMobilePhoneNumber(dto.getMobilePhoneNumber());

        if (null == validUser) {
            User targetUser = new User();
            targetUser.setName(dto.getName());
            targetUser.setMobilePhoneNumber(dto.getMobilePhoneNumber());
            targetUser.setPassword(dto.getPassword());
            User responseUser = userService.signUp(targetUser);
            if (null == responseUser) {
                throw new MoonException(requestContext.getMessage(""));
            } else {
                return responseUser;
            }
        } else {
            throw new MoonException(requestContext.getMessage(""));
        }
    }

    @PostMapping(value = "/login")
    public User login(
            HttpServletRequest request,
            @Valid @RequestBody UserLoginDTO dto,
            BindingResult bindingResult
    ) {
        RequestContext requestContext = new RequestContext(request);
        DataValidationUtil.execute(bindingResult, requestContext);

        User responseUser = userService.login(dto.getMobilePhoneNumber());

        if (null == responseUser) {
            throw new MoonException(requestContext.getMessage(""));
        } else {
            if (responseUser.getPassword().equals(dto.getPassword())) {
                request.getSession().setAttribute(KeyConstant.CURRENT_USER, responseUser);
                return responseUser;
            } else {
                throw new MoonException(requestContext.getMessage(""));
            }
        }
    }

    @PostMapping(value = "/password/update")
    public User updateUserPassword(
            HttpServletRequest request,
            @Valid @RequestBody UserPasswordUpdateDTO dto,
            BindingResult bindingResult
    ) {
        RequestContext requestContext = new RequestContext(request);
        DataValidationUtil.execute(bindingResult, requestContext);

        User targetUser = userService.getUserById(dto.getId());
        if (null == targetUser) {
            throw new MoonException(requestContext.getMessage("user.password.updateFailed"));
        } else {
            if (targetUser.getPassword().equals(dto.getOldPassword())) {
                targetUser.setPassword(dto.getNewPassword());
                User responseUser = userService.updateUserPassword(targetUser);
                if (null == responseUser) {
                    throw new MoonException(requestContext.getMessage("user.password.updateFailed"));
                } else {
                    return responseUser;
                }
            } else {
                throw new MoonException(requestContext.getMessage("user.password.error"));
            }
        }
    }

    @PostMapping(value = "/info/update")
    public User updateUserInfo(
            HttpServletRequest request,
            @RequestBody User user
    ) {
        RequestContext requestContext = new RequestContext(request);
        if (null == user.getId()) {
            throw new MoonJSRRuntimeException(requestContext.getMessage(""));
        }

        User responseUser = userService.updateUserInfo(user);

        if (null == responseUser) {
            throw new MoonJSRRuntimeException(requestContext.getMessage(""));
        } else {
            return responseUser;
        }
    }
}