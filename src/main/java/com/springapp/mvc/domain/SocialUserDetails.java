package com.springapp.mvc.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Petri Kainulainen
 */
public class SocialUserDetails extends SocialUser {

    private int userNo;

    private String userId;

    private String password;

    private String userName;

    private Set<UserRole> userRole;

    private SocialMediaService socialSignInProvider;

    private Set<GrantedAuthority> authorities;

    public SocialUserDetails(String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userId, password, authorities);
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public int getUserNo() {
        return userNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public SocialMediaService getSocialSignInProvider() {
        return socialSignInProvider;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userNo", userNo)
                .append("userId", userId)
                .append("password", password)
                .append("userName", userName)
                .append("userRole", userRole)
                .append("socialSignInProvider", socialSignInProvider)
                .toString();
    }

    public static class Builder {

        private int userNo;

        private String userId;

        private String userName;

        private String password;

        private Set<UserRole> userRole;

        private SocialMediaService socialSignInProvider;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet();
        }


        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userNo(int userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }

            this.password = password;
            return this;
        }

        public Builder userRole(Set<UserRole> userRole) {
            this.userRole = userRole;
            // Build user's authorities
            for (UserRole userRoles : userRole) {
                this.authorities.add(new SimpleGrantedAuthority(userRoles.getRole()));
            }
            return this;
        }

        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }


        public SocialUserDetails build() {
            SocialUserDetails user = new SocialUserDetails(userId,password, authorities);
            user.userNo= userNo;
            user.userId= userId;
            user.userRole = userRole;
            user.password = password;
            user.authorities = authorities;
            user.socialSignInProvider = socialSignInProvider;
            return user;
        }
    }
}
