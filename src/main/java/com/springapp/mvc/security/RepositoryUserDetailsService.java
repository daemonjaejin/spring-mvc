package com.springapp.mvc.security;

import com.springapp.mvc.dao.LoginDao;
import com.springapp.mvc.domain.SocialUserDetails;
import com.springapp.mvc.domain.UserRole;
import com.springapp.mvc.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class RepositoryUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginDao loginDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String userId)
            throws UsernameNotFoundException {
        UserVo user = loginDao.findByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + userId);
        }
        SocialUserDetails userDetails = SocialUserDetails.getBuilder()
                .userId(user.getUserId())
                .userNo(user.getUserNo())
                .userName(user.getUserName())
                .password(user.getUserPassword())
                .userRole(user.getUserRole())
                .socialSignInProvider(user.getSignInProvider())
                .build();
        return userDetails;

    }


    private org.springframework.security.core.userdetails.User buildUserForAuthentication(UserVo user,
                                                                                          List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getUserPassword(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}