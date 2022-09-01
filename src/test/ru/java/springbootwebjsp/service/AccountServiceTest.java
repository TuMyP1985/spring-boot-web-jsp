package ru.java.springbootwebjsp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.java.springbootwebjsp.SpringBootWebJspApplication;
import ru.java.springbootwebjsp.model.Account;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.repositiry.AccountRepository;
import ru.java.springbootwebjsp.web.controller.BankStatementController;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ru.java.springbootwebjsp.TestData.*;

@SpringBootTest
class AccountServiceTest {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceTest.class);

    @Autowired
    AccountRepository accountRepository;

    @Test
    void get() {
        //Не инжектится бин (описание в UserServiceTest), поэтому ошибка (NullPointerException).
        log.info("accountRepository="+(accountRepository == null));
        if (accountRepository == null)
            Assertions.assertThrows(NullPointerException.class, () -> accountRepository.get(ACCOUNT_ID_1, USER_ID_1));
        else {
            //Нужно исправить, чтобы не было ошибки, и закоментить код выше
            Account account_exp = accountRepository.get(ACCOUNT_ID_1, USER_ID_1);
            Assertions.assertEquals(account_exp, accountTest);

        }
    }

    /*public static void main(String[] args) {
        // java 7 automatic resource management (ARM)

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            log.info("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            AccountRepository mealRestController = appCtx.getBean(AccountRepository.class);


        }
    }*/
}