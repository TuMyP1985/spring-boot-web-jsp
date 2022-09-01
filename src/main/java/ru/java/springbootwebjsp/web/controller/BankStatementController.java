package ru.java.springbootwebjsp.web.controller;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.java.springbootwebjsp.model.Account;
import ru.java.springbootwebjsp.model.BankStatements;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.model.enums.TypeCurrency;
import ru.java.springbootwebjsp.service.AccountService;
import ru.java.springbootwebjsp.service.BankStatementService;
import ru.java.springbootwebjsp.service.UserService;
import ru.java.springbootwebjsp.util.DataFromFile;
import ru.java.springbootwebjsp.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.java.springbootwebjsp.util.Util.*;
import static ru.java.springbootwebjsp.web.SecurityUtil.selectedUserId;

@Controller
@RequestMapping(value = "/bankstatements")
public class BankStatementController {

    private static final Logger log = LoggerFactory.getLogger(BankStatementController.class);

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    BankStatementService bankStatementService;

    @GetMapping()
    public String getAll(HttpServletRequest request, Model model)  {

        String action = request.getParameter("action");
        if (action!=null && (action.equals("delete") || (action.equals("update")))){
            int id = getID(request.getParameter("id"));
            if (action.equals("delete")) {
                bankStatementService.delete(id);
                model.addAttribute("bankstatements",  bankStatementService.getAll());
                return "redirect:" + request.getHeader("referer");
            }
            else{
                model.addAttribute("listAccount",  accountService.getAll(0,""));
                return "/bankstatementForm";
            }
        }

        model.addAttribute("bankstatements",  bankStatementService.getAll());
        return "/bankstatements";


    }

    @PostMapping()
    public String createAndGetAll(HttpServletRequest request, Model model) {

        int accountId = getID(request.getParameter("accountid"));
        String description = request.getParameter("description");
        double value = getDouble(request.getParameter("value"));
        Account account = accountService.getByID(accountId);
        int userId = accountService.getUserID(accountId);

        BankStatements bankStatements = new BankStatements(
                description, account.getTypeCurrency(),
                value, userService.get(userId), account);

        bankStatementService.save(bankStatements, userId, accountId);

        model.addAttribute("bankstatements",  bankStatementService.getAll());
        return "/bankstatements";
    }

    @PostMapping(value = "/bankstatementForm")
    public String dataFromFile( @RequestParam MultipartFile file, Model model) {
        DataFromFile dataFromFile = new DataFromFile();
        try {
            String text_json = new String(file.getBytes(), StandardCharsets.UTF_8);
            dataFromFile = DataFromJson(text_json);

            //check account
            int id = dataFromFile.getAccountid();
            if (accountService.getByID(id)==null)
                dataFromFile.setError(dataFromFile.getError() + " Account not found by ID:" + id + "; " + "<BR/>");

        }catch (Exception e) {
            dataFromFile.setError(dataFromFile.getError() + " Bad file: "+e.getMessage() + "; " + "<BR/>");}
        model.addAttribute("dataFromFile",  dataFromFile);
        model.addAttribute("listAccount",  accountService.getAll(0,""));
        return "/bankstatementForm";
    }

    public String redirectAccounts(HttpServletRequest request, Model model, int userId, String returned){
        List<User> users = new ArrayList<>();
        users.add(new User(0,"(всё)")); //чтобы показывал по всем пользователям
        users.addAll(userService.getAll());

        String currencyString = currentcurrency(request);

        model.addAttribute("selectedCurrency",  currencyString);
        model.addAttribute("selectedUserId", selectedUserId());
        model.addAttribute("listCurrency", listCurrency(true));
        model.addAttribute("users", users);
        model.addAttribute("accounts",accountService.getAll(userId, currencyString));
        return returned;
    }

}