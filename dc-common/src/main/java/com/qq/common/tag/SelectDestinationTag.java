package com.qq.common.tag;

import com.qq.common.data.mapper.DestinationMapper;
import com.qq.common.data.mapper.UserMapper;
import com.qq.common.domain.Destination;
import com.qq.common.domain.User;
import com.qq.common.util.SpringContextHolder;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangyangyang on 2017/6/15.
 */
public class SelectDestinationTag extends AbstractSelectTagSupport{

    private static final long serialVersionUID=1L;

    @Override
    public String setOptions(){
        DestinationMapper destinationMapper = SpringContextHolder.getBean("destinationMapper");
        List<Destination> destinations = destinationMapper.selectAllDestinations();
        StringBuffer sb = new StringBuffer();
        for(Destination destination:destinations){
            if(!StringUtils.isEmpty(selectedValue) && selectedValue.equals(String.valueOf(destination.getId()))) {
                sb.append("<option value='"+destination.getId()+"' selected>").append(destination.getName()).append("</option>");
            } else {
                sb.append("<option value='"+destination.getId()+"' >").append(destination.getName()).append("</option>");
            }
        }
        return sb.toString();
    }
}
