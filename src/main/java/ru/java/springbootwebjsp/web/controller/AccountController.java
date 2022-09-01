package ru.java.springbootwebjsp.web.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.service.AccountService;
import ru.java.springbootwebjsp.model.Account;
import ru.java.springbootwebjsp.model.enums.TypeCurrency;
import ru.java.springbootwebjsp.service.UserService;
import ru.java.springbootwebjsp.web.SecurityUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.java.springbootwebjsp.util.Util.*;
import static ru.java.springbootwebjsp.web.SecurityUtil.*;

@Controller
@RequestMapping(value = "/accounts")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @GetMapping()
    public String getAll(HttpServletRequest request, Model model)  {

        int userId = selectedUserId();
        String action = request.getParameter("action");
        if (action!=null && (action.equals("delete") || (action.equals("update")))){
            int id = getID(request.getParameter("id"));
            if (action.equals("delete")) {
                Account account = accountService.getByID(id);
                if (account != null)
                    accountService.delete(id, userId);

                return redirectAccounts(request, model, true);

            }
            else{
                User selectedUser = new User();
                if (userId!=0)
                    selectedUser = userService.get(selectedUserId());

                Account account = id==0?new Account():accountService.get(id, userId);
                String selectedCurrency = SelectedCurrencyInAccountjsp();
                if (!account.isNew())
                    selectedCurrency = account.getTypeCurrency().name();

                model.addAttribute("selectedCurrency", selectedCurrency);
                model.addAttribute("account",account);
                model.addAttribute("users",userService.getAll());
                model.addAttribute("listCurrency", listCurrency(false));
                model.addAttribute("selectedUser", selectedUser);
                return "/accountForm";
            }
        }

        if (action!=null && action.equals("gerRate")){
            int id = getID(request.getParameter("id"));
            Account account = accountService.getByID(id);
            if (account != null) {
                String val = account.getTypeCurrency().name();
                double rate = getRate(val);
                String result = "Для счета \"" + account.getNumber() + "\" рублевая сумма составляет: " +
                        formatDigital(rate * account.getValue(),2) + " руб.;"  + "<BR/>" +
                        "(текущий курс: " + val + " = " + rate + ")"+
                        "<a href=\"https://api.coingate.com/v2/rates/merchant/\"> API курсов </a>"
                        ;


                model.addAttribute("rate", result);
            }
        }
        return redirectAccounts(request, model, false);
    }

    @PostMapping()
    public String createAccountAndGetAll(HttpServletRequest request, Model model) {
        int idaccount = getID(request.getParameter("idaccount"));

        int userId = getID(request.getParameter("selectedUserId"));
        if (userId!=SecurityUtil.selectedUserId()) {
//            log.info("setUser {}", userId);
            SecurityUtil.setAuthUserId(userId);
        }

        int number = getID(request.getParameter("number"));
        String description = request.getParameter("description");
        String typeCurrencyString = request.getParameter("typeCurrency");
        double value = getDouble(request.getParameter("value"));

        TypeCurrency typeCurrency = TypeCurrency.valueOf(typeCurrencyString);

        Account account;
        if (idaccount!=0)
            account = new Account(idaccount, number, description,typeCurrency,value);
        else
            account = new Account(number, description,typeCurrency,value);
        accountService.save(account, selectedUserId());

        return redirectAccounts(request, model, false);

    }

    @PostMapping("setusercurrentcurrency")
    public String setUser(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        if (userId!=SecurityUtil.selectedUserId()) {
            log.info("setUser {}", userId);
            SecurityUtil.setAuthUserId(userId);
        }
        String currencyString = currentcurrency(request);
        if (currencyString!=SecurityUtil.SelectedCurrencyInAccountjsp()) {
            SecurityUtil.setCurrencyInAccountjsp(currencyString);
        }

        return redirectAccounts(request, model, true);


    }

    public String redirectAccounts(HttpServletRequest request, Model model, boolean redirect){
        int userId =SecurityUtil.selectedUserId();
        List<User> users = new ArrayList<>();
        String returned = "/accounts";
        users.add(new User(0,"(всё)")); //чтобы показывал по всем пользователям
        users.addAll(userService.getAll());

        String currencyString = SelectedCurrencyInAccountjsp();
        if (redirect)
            returned = "redirect:" + request.getHeader("referer");

        model.addAttribute("selectedCurrency",  currencyString);
        model.addAttribute("selectedUserId", selectedUserId());
        model.addAttribute("listCurrency", listCurrency(true));
        model.addAttribute("users", users);
        model.addAttribute("accounts",accountService.getAll(userId, currencyString));
        return returned;
    }


}