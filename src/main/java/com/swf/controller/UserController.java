package com.swf.controller;

import com.swf.common.base.PageResult;
import com.swf.entity.User;
import com.swf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
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
    @ResponseBody
    @GetMapping(value = "/{id}")
    public User selectOne(@PathVariable(name = "id") Long id) {
        return this.userService.queryById(id);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})
    public User save(@RequestBody User user) {
//        名字唯一性校验
        User byName = userService.findByName(user.getName());
        Assert.isTrue(byName == null || (byName.getId() != null && byName.getId().equals(user.getId())),"名称重复请修改！");
        return userService.insert(user);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public PageResult<User> findList(@RequestParam(name = "limit",required = false, defaultValue = "10") int pageSize,
                               @RequestParam(required = false, defaultValue = "0") int offset,
                               @RequestParam(required = false, defaultValue = "id") String sort,
                               @RequestParam(required = false) String order,
                               @RequestParam(required = false) String search
                         ) {
        Page<User> pages = userService.queryAllByLimit(Math.floorDiv(offset,pageSize), pageSize, order,sort);
        PageResult<User> userPageResult = PageResult.create(pages.getTotalElements(), pages.getContent());
        return userPageResult;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody List<User> users) {
        userService.deleteByEntitys(users);
    }







    @RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
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




}
