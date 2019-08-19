package org.zpp.security.core.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author zpp
 * @date 2019/8/14 17:25
 */
@UtilityClass
public class SecurityUtils {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    private User getSecurityUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    /**
     * 获取当前用户
     * @return
     */
    public User getSecurityUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getSecurityUser(authentication);
    }
}
