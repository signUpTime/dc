package com.qq.business.service;

import com.qq.common.domain.ResultDO;
import com.qq.common.domain.Shop;

/**
 * Created by wangyangyang on 2017/6/9.
 */
public interface IUserService {

    @SuppressWarnings("rawtypes")
    ResultDO addDestinationId(int userId,int destinationId);

    ResultDO updateDepartment(int userId,String department);
}
