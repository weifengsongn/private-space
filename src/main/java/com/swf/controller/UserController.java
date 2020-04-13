package com.swf.controller;

import com.swf.entity.User;
import com.swf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author swf
 * @since 2020-04-02 22:10:37
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Long id) {
        return this.userService.queryById(id);
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    public String save(User user, ModelAndView view) {
        userService.insert(user);
//        TODO :编辑要显示的页面。实现登录功能；
        return "";
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(User user, Map<String,Object> map, HttpSession httpSession) {
//        TODO: 对密码进行加密存储
        if (StringUtils.isEmpty(user)) {
            map.put("errorTip","请输入用户名和密码！");
            return "index";
        }
        User user1 = userService.findByName(user.getName());
        if (StringUtils.isEmpty(user1)) {
            map.put("errorTip","请检查输入是否正确！");
            return "index";
        }
        if (user1.getPassword().equals(user.getPassword())) {
            map.put("successTip","登陆成功，请稍后");
            httpSession.setAttribute("username",user1.getRealName());
            return "redirect:/main";
        }
        return "index";
    }


//    @RequestMapping(value = "/main",method = {RequestMethod.POST,RequestMethod.GET})
////    public String test(){
////        return "test";
////    }


}
