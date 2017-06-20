package com.qq.common.tag;

import com.qq.common.data.mapper.ShopMapper;
import com.qq.common.data.mapper.UserMapper;
import com.qq.common.domain.Shop;
import com.qq.common.domain.User;
import com.qq.common.util.SpringContextHolder;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangyangyang on 2017/6/14.
 */
public class SelectDepartmentTag  extends AbstractSelectTagSupport{

    private static final long serialVersionUID=1L;

    @Override
    public String setOptions(){
        UserMapper userMapper = SpringContextHolder.getBean("userMapper");
        List<User> users = userMapper.selectAllUsers();
        StringBuffer sb = new StringBuffer();
        Set sets = new HashSet();
        for(User user:users){
            sets.add(user.getDepartment());
        }
        for(Object department:sets){
            if(!StringUtils.isEmpty(selectedValue) && selectedValue.equals(String.valueOf(department))) {
                sb.append("<option value='"+department+"' selected>").append(department).append("</option>");
            } else {
                sb.append("<option value='"+department+"' >").append(department).append("</option>");
            }
        }
        return sb.toString();
    }
}
