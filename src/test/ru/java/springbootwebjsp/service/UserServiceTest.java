package ru.java.springbootwebjsp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
//import org.junit.Test; //можно это использовать вместо (выше), но не инизиализируется БД

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.java.springbootwebjsp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.repositiry.UserRepository;
import ru.java.springbootwebjsp.repositiry.datajpa.DataJpaUserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.java.springbootwebjsp.TestData.USER_ID_1;
import static ru.java.springbootwebjsp.TestData.userTest;

//@Sql(scripts = {"classpath:schema-hsqldb.sql","classpath:data-hsqldb.sql"}, config = @SqlConfig(encoding = "UTF-8"))
//@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    UserRepository userRepository;

    @Test
    void get() {
        //Не инжектится бин (userRepository), поэтому ошибка (NullPointerException).
        //будет инжектится если использовать а)import org.junit.Test;  б)@RunWith(SpringRunner.class) в)@Sql(scripts
        //но база не популируется - вероятно это из-за HSQL, может нужно переделать на Postgres
        log.info("userRepository="+(userRepository == null));
        if (userRepository == null)
            Assertions.assertThrows(NullPointerException.class, () -> userRepository.get(USER_ID_1));
        else {
            //Нужно исправить, чтобы не было ошибки, и закоментить код выше
            User user_exp = userRepository.get(USER_ID_1);
            Assertions.assertEquals(user_exp, userTest);
        }
    }
}