package top.yujiangtao.springbootjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import top.yujiangtao.springbootjpa.model.User;

import java.util.List;

/**
 * @author yujan
 */
public interface UserRepository extends JpaRepository<User, Long>//JpaRepository<实体类型，主键类型>
{
    /**
     * 通过名称找到用户
     *
     * @param name 姓名
     * @return 用户信息
     */
    User findByName(String name);

    /**
     * 通过名称和年龄找到用户
     *
     * @param name 姓名
     * @param age  年龄
     * @return 用户信息
     */
    User findByNameAndAge(String name, Integer age);

    /**
     * 通过名称模糊查询
     *
     * @param name 模糊用户名
     * @return 符合条件的所有用户集合
     */
    List<User> findByNameLike(String name);

    /**
     * 使用hql查询 :name对应@Param里的name
     *
     * @param name 姓名
     * @return 用户信息
     */
    @Query("from User u where u.name=:name")
    User findByHQL(@Param("name") String name);

    /**
     * 使用sql查询 ?1表示第一个参数，?2表示第二个参数
     *
     * @param name 用户名
     * @param age  年龄
     * @return 用户信息
     */
    @Query(value = "select * from jpa.user where user.name = ?1 and user.age = ?2", nativeQuery = true)
    User findBySQL(String name, Integer age);
}