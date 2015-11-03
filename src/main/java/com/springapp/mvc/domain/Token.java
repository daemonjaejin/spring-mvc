package com.springapp.mvc.domain;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="RememberMeToken", catalog="hornetdb")
public class Token {
    private String seriesId;
    private String userId;
    private String tokenValue;
    private Date lastUsed;

    public Token() {
    }
    public Token(PersistentRememberMeToken persistentRememberMeToken) {
        this.userId = persistentRememberMeToken.getUsername();
        this.seriesId = persistentRememberMeToken.getSeries();
        this.lastUsed = persistentRememberMeToken.getDate();
        this.tokenValue = persistentRememberMeToken.getTokenValue();
    }

    @Id
    @Column(name="seriesId", unique=true, nullable=false,length=64)
    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    @Column(name="userId", unique=false, nullable=false, length=64)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name="tokenValue", unique=false, nullable=false, length=64)
    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    @Column(name="lastUsed")
    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}