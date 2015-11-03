package com.springapp.mvc.security;

import com.springapp.mvc.dao.TokenDao;
import com.springapp.mvc.domain.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Transactional
public class CustomTokenRepository implements PersistentTokenRepository {

    @Autowired
    private TokenDao tokenDao;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        tokenDao.createNewToken(new Token(token));
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        tokenDao.updateToken(seriesId, tokenValue, lastUsed);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        System.out.println("seriesId:"+seriesId);

        Token token = tokenDao.getTokenForSeries(seriesId);
        if (token == null) {
            return null;
        }
        System.out.println("Token:"+token.getUserId());
        return new PersistentRememberMeToken(token.getUserId(),
                token.getSeriesId(), token.getTokenValue(), token.getLastUsed());
    }

    @Override
    public void removeUserTokens(String userId) {
        tokenDao.removeUserTokens(userId);
    }
}