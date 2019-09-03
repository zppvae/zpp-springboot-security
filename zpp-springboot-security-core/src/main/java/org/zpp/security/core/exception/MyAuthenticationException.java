package org.zpp.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author zpp
 * @date 2019/9/3 17:01
 */
public class MyAuthenticationException extends AuthenticationException {

    public MyAuthenticationException(String msg){
        super(msg);
    }
}
