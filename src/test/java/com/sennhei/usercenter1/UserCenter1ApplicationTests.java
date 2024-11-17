package com.sennhei.usercenter1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import static org.junit.Assert.assertNotNull;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = UserCenter1Application.class)
@RunWith(SpringRunner.class)
public class UserCenter1ApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {
        // 确保Spring上下文成功加载
        assertNotNull("Application context failed to load", context);
    }
}
