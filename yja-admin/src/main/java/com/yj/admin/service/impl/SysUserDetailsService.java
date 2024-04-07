package com.yj.admin.service.impl;

import com.yj.admin.service.SysUserService;
import com.yj.core.model.dto.UserAuthInfo;
import com.yj.core.security.model.SysUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Yang Jian
 * @date 2024/4/5 10:17
 * @description
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserDetailsService implements UserDetailsService {

    private final SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAuthInfo userAuthInfo = sysUserService.getUserAuthInfo(username);
        System.out.println("loadUserByUsername-userAuthInfo===="+userAuthInfo);
        if (userAuthInfo == null) {
            throw new UsernameNotFoundException(username);
        }
        log.info("loadUserByUsername-userAuthInfo11:{}", userAuthInfo);
        return new SysUserDetails(userAuthInfo);
    }
}
