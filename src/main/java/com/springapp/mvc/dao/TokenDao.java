package com.springapp.mvc.dao;

import com.springapp.mvc.domain.Token;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class TokenDao {

    @Resource
    private  SessionFactory sessionFactory;

    public void createNewToken(Token token) {
        sessionFactory.getCurrentSession().save(token);
    }

    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {

        Token existingToken = (Token) sessionFactory.getCurrentSession().get(Token.class, seriesId);
        existingToken.setTokenValue(tokenValue);
        existingToken.setLastUsed(lastUsed);
        sessionFactory.getCurrentSession().merge(existingToken);
    }

    public Token getTokenForSeries(String seriesId) {
        return (Token) sessionFactory.getCurrentSession().get(Token.class, seriesId);
    }

    public void removeUserTokens(final String userId) {

        List<Token> tokens = sessionFactory.getCurrentSession().createCriteria(Token.class)
                .add(Restrictions.eq("userId", userId)).list();
        if (tokens.size() > 0) {
            for (Token token : tokens) {
                sessionFactory.getCurrentSession().delete(token);
            }
        }
    }
}