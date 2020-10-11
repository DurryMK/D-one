package spm.app;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;
import spm.app.Service.Analysis.AnalysisService;
import spm.app.bean.AccessBean.TokenPackage;
import spm.app.dao.mysql.mapper.UserMapper;
import spm.app.dao.mysql.entity.User;
import spm.app.dao.redis.RedisUtils;
import spm.app.tools.DESUtils;
import spm.app.tools.RSAUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@SpringBootTest(classes = {DemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoApplicationTests extends TestCase {

    @Qualifier("redisUtils")
    @Autowired
    private RedisUtils rt;

    @Autowired
    private AnalysisService as;

    @Resource
    private UserMapper um;

    @Test
    public void ff() {
    }

    @Test
    public void ee() {
        User u = new User();
        u.setName("done");
        u.setPwd("sys1123");
        User ru = um.selectUser(u);
        System.out.println(ru);
    }

    @Test
    public void aa() {
        //redisTemplate.boundHashOps("as").put("刘",2);
        Integer s = (Integer) rt.findHash("as", "张");
        System.out.println(s);
    }

    @Test
    public void cc() {

    }

    @Test
    public void bb() {
        String user = "15974076596";
        String pwd = "sys123";
        String token = user + ":" + pwd;
        System.out.println(DESUtils.decrypt("qlWVNRaNsj6DscAXEuaJuaSnlVUnYEGa"));
    }
}
