package com.springapp.mvc.service;

import com.springapp.mvc.domain.UserVo;

/**
 * Created by jin on 15. 10. 21..
 */
public interface LoginService {

    public UserVo select() throws Exception;

    public UserVo selectByUserId(String userId) throws Exception;

}
