package com.springapp.mvc.domain;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="UserRole", catalog="hornetdb",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "role", "userNo" }))
public class UserRole {
    private Integer userRoleId;
    private UserVo userVo;
    private String role;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "userRoleId", unique = true, nullable = false)
    public Integer getUserRoleId() {
        return userRoleId;
    }
    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo", nullable = false)
    public UserVo getUser() {
        return userVo;
    }
    public void setUser(UserVo UserVo) {
        this.userVo = UserVo;
    }

    @Column(name="role", length=45)
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}