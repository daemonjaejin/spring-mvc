package com.springapp.mvc.dao;

import com.springapp.mvc.domain.TestVo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by jin on 15. 10. 21..
 */
@Repository
public class TestDaoImpl implements TestDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TestVo select() throws Exception{
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(TestVo.class);
        return (TestVo)criteria.list().get(0);
    }

}
