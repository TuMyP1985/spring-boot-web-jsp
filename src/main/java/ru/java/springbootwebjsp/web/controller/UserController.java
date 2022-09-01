package ru.java.springbootwebjsp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.web.SecurityUtil;
import ru.java.springbootwebjsp.service.UserService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

import static ru.java.springbootwebjsp.util.Util.getID;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping()
    public String getAll(HttpServletRequest request, Model model)  {

        String action = request.getParameter("action");
        if (action!=null && (action.equals("delete") || (action.equals("update")))){
            int userId = getID(request.getParameter("id"));
            if (action.equals("delete")) {
                log.info("delete user{}", userId);
                userService.delete(userId);
                //при удалении пользователя, меняем текущего на следующего
                Optional<User> user = userService.getAll().stream().findFirst();
                if (!user.equals(Optional.empty()))
                    SecurityUtil.setAuthUserId(user.get().getId());
                else
                    SecurityUtil.setAuthUserId(0);

                return redirectUsers(model,"redirect:" + request.getHeader("referer"));

            }
            else{
                User user = userId==0?new User():userService.get(userId);
                model.addAttribute("user",user);
                return "/userForm";
            }
        }
        return redirectUsers(model, "users");
    }

    @PostMapping()
    public String createUserAndGetAll(HttpServletRequest request, Model model) {

        int userId = getID(request.getParameter("id"));
        String name = request.getParameter("name");
        User user;
        if (userId!=0) {
            log.info("update user{}", userId);
            user = new User(userId, name);
        }
        else{
            log.info("create user");
            user = new User(name);
        }

        userService.save(user);

        return redirectUsers(model, "users");
    }

    public String redirectUsers(Model model, String returned){
        log.info("getAll users");
        model.addAttribute("users",userService.getAll());
        return returned;
    }

}