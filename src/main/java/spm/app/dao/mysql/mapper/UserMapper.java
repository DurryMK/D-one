package spm.app.dao.mysql.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import spm.app.dao.mysql.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查询所有用户信息
     */
    @Select("select id,name,phone,sex from done_user")
    public List<User> selectAllUser();

    /**
     *根据用户名和密码查询用户  没有则返回null
     */
    @Select("select id,name,pwd,phone,sex from done_user where (phone = #{phone} or name = #{name}) and pwd = #{pwd}")
    public User selectUser(User user);

    /**
     *根据用户ID查询用户  没有则返回null
     */
    @Select("select id,name,phone,sex from done_user where id = #{id}")
    public User selectUserWithID(@Param("id") String id);
}
