package com.springapp.mvc.dao;

import com.springapp.mvc.domain.TestVo;

/**
 * Created by jin on 15. 10. 21..
 */

public interface TestDao {
    public TestVo select() throws Exception;
}
