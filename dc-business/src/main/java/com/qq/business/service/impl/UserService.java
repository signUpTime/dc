package com.qq.business.service.impl;

import com.qq.business.service.IUserService;
import com.qq.common.data.mapper.UserMapper;
import com.qq.common.domain.ResultDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyangyang on 2017/6/9.
 */
@Service
public class UserService implements IUserService {
    @Resource
    private UserMapper userMapper;

    @SuppressWarnings("rawtypes")
    @Override
    public ResultDO addDestinationId(int userId, int destinationId){
        ResultDO resultDO = new ResultDO();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("destinationId",destinationId);
        map.put("userId",userId);
        userMapper.updateDestinationId(map);
        resultDO.setResult(true);
        return resultDO;
    }

    @Override
    public ResultDO updateDepartment(int userId, String department){
        ResultDO resultDO = new ResultDO();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("department", department);
        map.put("userId", userId);
        userMapper.updateDepartment(map);
        resultDO.setResult(true);
        return resultDO;
    }
}
