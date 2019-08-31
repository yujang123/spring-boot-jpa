package top.yujiangtao.springbootjpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yujiangtao.springbootjpa.dao.UserRepository;
import top.yujiangtao.springbootjpa.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 10; i++) {
            userRepository.save(new User("test" + i, i));
        }

        Assert.assertEquals(10, userRepository.findAll().size());

        Assert.assertEquals(3, userRepository.findByName("test3").getAge().longValue());

        Assert.assertEquals(4, userRepository.findByHQL("test4").getAge().longValue());

        Assert.assertEquals("test1", userRepository.findByNameAndAge("test1", 1).getName());

        Assert.assertEquals("test1", userRepository.findBySQL("test1", 1).getName());

        userRepository.delete(userRepository.findByName("test7"));

        Assert.assertEquals(9, userRepository.findAll().size());

        Assert.assertEquals(9, userRepository.findByNameLike("%t%").size());
    }

}
