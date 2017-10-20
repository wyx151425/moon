package com.rumofuture.moon.util;

import com.rumofuture.moon.util.exception.MoonJSRRuntimeException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.support.RequestContext;

import java.util.regex.Pattern;

public class DataValidationUtil {

    private final static Pattern mobilePhoneNumberPattern = Pattern.compile("^(1[0-9])\\d{9}$");

    public static boolean isNullObject(Object object) {
        if (null == object) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyString(String string) {
        if (null == string || string.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNullDataList(Object... objectList) {
        for (int index = 0; index < objectList.length; index++) {
            if (null == objectList[index]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmptyStringList(String... stringList) {
        for (int index = 0; index < stringList.length; index++) {
            if (null == stringList[index] || stringList[index].equals("")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMobilePhoneNumber(String mobilePhoneNumber) {
        if (isEmptyString(mobilePhoneNumber) || 11 != mobilePhoneNumber.length()) {
            return false;
        }
        return mobilePhoneNumberPattern.matcher(mobilePhoneNumber).matches();
    }

    public static boolean isPassword(String password) {
        if (password.length() < 6) {
            return false;
        }
        return true;
    }

    public static void execute(BindingResult bindingResult, RequestContext requestContext) throws MoonJSRRuntimeException {
        if (bindingResult.hasErrors()) {
            String code = bindingResult.getAllErrors().get(0).getCodes()[0];
            throw new MoonJSRRuntimeException(requestContext.getMessage(code));
        }
    }
}
