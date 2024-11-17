package com.sennhei.usercenter1.service;
import java.util.Date;

import com.sennhei.usercenter1.UserCenter1Application;
import com.sennhei.usercenter1.model.User;
// import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserCenter1Application.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("sennhei");
        user.setUserAccount("123");
        user.setAvatarUrl("https://cn.bing.com/images/search?view=detailV2&ccid=5sAZBJJ5&id=7832E2EEF3959EDA8ADC15F0CF9E785A96DF0DFF&thid=OIP.5sAZBJJ5b3vyW-K6oCBQCQAAAA&mediaurl=https://c-ssl.duitang.com/uploads/item/201209/26/20120926214307_hTQtB.thumb.400_0.jpeg&q=%E5%A4%B4%E5%83%8F&ck=2890EA60BC4B51888249222233FA273A&idpp=rc&idpview=singleimage&form=rc2idp");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("123");
        user.setEmail("123");


        boolean result=  userService.save(user);
        System.out.println(user.getId());
        assertTrue(result);
    }

    @Test
    public void userRegister() {
        String userAccount = "sennhei";
        String userPassword = "";
        String checkPassword = "123456";
        String planetCode="`1";
        long result=userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1,result);
        userAccount = "we";
        result=userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1,result);
    }
}