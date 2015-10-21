package com.springapp.mvc.service;

import com.springapp.mvc.dao.TestDao;
import com.springapp.mvc.domain.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jin on 15. 10. 21..
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public TestVo select() throws Exception{
        return testDao.select();
    }

}
