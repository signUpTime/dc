package com.qq.business.controller;

import com.qq.business.service.IUserService;
import com.qq.common.constants.CommonConstants;
import com.qq.common.domain.ResultDO;
import com.qq.common.domain.User;
import com.qq.common.util.RequestExtract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangyangyang on 2017/6/9.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping("/addDestinationId.do")
    @ResponseBody
    public ResultDO addDestinationId(ModelMap modelMap,HttpServletRequest request, @RequestParam int destinationId){
        User user = (User) request.getSession().getAttribute(CommonConstants.LOGINED_USER);
        ResultDO resultDO = new ResultDO();
        resultDO = userService.addDestinationId(user.getId(),destinationId);
        user.setDestinationId(destinationId);
        User userNew = (User) RequestExtract.getAdminInfo(request);
        modelMap.addAttribute("user", userNew);
        return resultDO;
    }

    @RequestMapping("/toAddDestinationId.do")
    public ModelAndView toAddDestinationId() {
        return new ModelAndView("/WEB-INF/user/toAddDestinationId.jsp");
    }

    @RequestMapping("/updateDepartment.do")
    @ResponseBody
    public ModelAndView updateDepartment(ModelMap modelMap,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(CommonConstants.LOGINED_USER);
        ResultDO resultDO = new ResultDO();
        resultDO = userService.updateDepartment(user.getId(), user.getDepartment());
        modelMap.addAttribute("user", user);
        return new ModelAndView("/WEB-INF/main.jsp");
    }
}
