package spm.app.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spm.app.Service.UserService.LoginService;
import spm.app.bean.AccessBean.StatusMapping;
import spm.app.bean.Const;
import spm.app.bean.ResModel;
import spm.app.dao.mysql.entity.User;
import spm.app.tools.DESUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 处理用户的登录注册验证等操作
 */
@Controller
public class AccessController {

    @Autowired
    private LoginService ls;

    /**
     * 验证是否有用户登录
     */
    @RequestMapping(value = "/verifyLogin", method = RequestMethod.GET)
    public @ResponseBody
    ResModel
    verifyLogin(HttpSession session, String token, ResModel rm) {
        //根据token提取用户信息
        User user = (User) session.getAttribute(token);
        if (user != null) {
            //用户已登录
            return rm.ResModel(StatusMapping.OK, token, user);
        } else {
            //用户未登录
            return rm.ResModel(StatusMapping.ERROR_INFO);
        }
    }

    /**
     * 执行登录操作
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public @ResponseBody
    ResModel
    doLogin(HttpSession session, User user, String auto, String token, ResModel rm) {
        //自动登录
        if ("AUTO".equals(auto)) {
            //从token中解析出用户名和密码
            token = DESUtils.decrypt(token);
            //用户与密码
            String[] userInfo = token.split(":");
            user.setName(userInfo[0]);
            user.setPhone(userInfo[0]);
            user.setPwd(userInfo[1]);
        }
        //使用传递过来的用户密码登录
        else {
            //用户名与手机号都可以用于登录
            user.setPhone(user.getName());
        }
        System.out.println(user);
        //验证用户名密码是否正确
        user = ls.loginVerify(user);

        //用户不为空 说明可以登录 执行登录操作
        if (user != null) {
            //获取生成的token并返回到客户端  保存在cookie中
            token = ls.doLogin(session, user);
            //返回到客户端的用户信息  密码设置为空
            user.setPwd(null);
            return rm.ResModel(StatusMapping.OK, token, user);
        }
        return rm.ResModel(StatusMapping.ERROR_INFO);
    }

    /**
     * 执行退出登录操作
     */
    @RequestMapping(value = "/exitOp", method = RequestMethod.POST)
    public @ResponseBody
    ResModel
    exit(HttpSession session, String id, ResModel rm) {
        session.removeAttribute(Const.UserKey);
        return rm.ResModel(StatusMapping.OK);
    }
}
