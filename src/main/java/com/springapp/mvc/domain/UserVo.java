package com.springapp.mvc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by jin on 15. 10. 21..
 */

@Entity
@Table(name="User", catalog = "hornetdb")
public class UserVo implements Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="userNo", unique=true, nullable=false)
    private int userNo;

    @Column(name="userId")
    private String userId;

    @Column(name="userPassword")
    private String userPassword;

    @Column(name="userName")
    private String userName;

    @Column(name="nickName")
    private String nickName;

    @Column(name="authFlag")
    private boolean authFlag;

    @Column(name="authKey")
    private String authKey;

    @Column(name="passwordKey")
    private String passwordKey;

    @Column(name="joinedDate")
    private Date joinedDate;

    @Column(name="authenticatedDate")
    private Date authenticatedDate;

    @Column(name="lastLoginDate")
    private Date lastLoginDate;

    @Column(name="memberYn")
    private String memberYn;

    @Column(name="userFileName")
    private String userFileName;

    @Column(name="userFilePath")
    private String userFilePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "signInProvider", length = 20)
    private SocialMediaService signInProvider;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isAuthFlag() {
        return authFlag;
    }

    public void setAuthFlag(boolean authFlag) {
        this.authFlag = authFlag;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getPasswordKey() {
        return passwordKey;
    }

    public void setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Date getAuthenticatedDate() {
        return authenticatedDate;
    }

    public void setAuthenticatedDate(Date authenticatedDate) {
        this.authenticatedDate = authenticatedDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public SocialMediaService getSignInProvider() {
        return signInProvider;
    }

    public void setSignInProvider(SocialMediaService signInProvider) {
        this.signInProvider = signInProvider;
    }

    public String getMemberYn() {
        return memberYn;
    }

    public void setMemberYn(String memberYn) {
        this.memberYn = memberYn;
    }

    public String getUserFileName() {
        return userFileName;
    }

    public void setUserFileName(String userFileName) {
        this.userFileName = userFileName;
    }

    public String getUserFilePath() {
        return userFilePath;
    }

    public void setUserFilePath(String userFilePath) {
        this.userFilePath = userFilePath;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", authFlag=" + authFlag +
                ", authKey='" + authKey + '\'' +
                ", passwordKey='" + passwordKey + '\'' +
                ", joinedDate=" + joinedDate +
                ", authenticatedDate=" + authenticatedDate +
                ", lastLoginDate=" + lastLoginDate +
                ", memberYn='" + memberYn + '\'' +
                ", userFileName='" + userFileName + '\'' +
                ", userFilePath='" + userFilePath + '\'' +
                ", signInProvider=" + signInProvider +
                ", userRole=" + userRole +
                '}';
    }
}
