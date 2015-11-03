package com.springapp.mvc.service;

import com.springapp.mvc.dao.LoginDao;
import com.springapp.mvc.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jin on 15. 10. 21..
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    public UserVo select() throws Exception{
        return loginDao.select();
    }

    public UserVo selectByUserId(String userId) throws Exception{
        return loginDao.selectByUserId(userId);
    }

}
