package spm.app.Service.UserService;

import org.springframework.stereotype.Service;
import spm.app.bean.Const;
import spm.app.dao.mysql.mapper.UserMapper;
import spm.app.dao.mysql.entity.User;
import spm.app.tools.DESUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@Service
public class LoginService {
    @Resource
    private UserMapper um;

    /**
     * 验证用户是否可以登录
     */
    public User loginVerify(User user) {
        try {
            user = um.selectUser(user);
            if (user != null)
                return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行登录操作
     */
    public String doLogin(HttpSession session, User user) {
        //使用用户手机号和密码拼接生成token
        String token = user.getPhone() + ":" + user.getPwd();
        //加密token后返回
        token = DESUtils.encrypt(token);
        //在session中保存用户信息
        session.setAttribute(token, user);
        return token;
    }
}
