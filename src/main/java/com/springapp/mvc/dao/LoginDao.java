package com.springapp.mvc.dao;

import com.springapp.mvc.domain.UserVo;

/**
 * Created by jin on 15. 10. 21..
 */

public interface LoginDao {
    UserVo findByUserId(String userId);
    public UserVo select() throws Exception;
    public UserVo selectByUserId(String userId) throws Exception;
}
