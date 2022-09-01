package ru.java.springbootwebjsp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.service.UserService;
import ru.java.springbootwebjsp.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/admin/usersajax", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControllerAJAX {

    private static final Logger log = LoggerFactory.getLogger(UserControllerAJAX.class);

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAll() {
        log.info("users getAll");
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("users delete");
        userService.delete(id);
    }

/*


    //Если для ajax, то возвращаем List<User> return userService.getAllUser(); и он обрабатывается в сначала в common.js, а потом в users.js. Иначе возвращаем String return "users";
    @GetMapping("NOT")
    public List<User> getAllForAjax()  {
        return userService.getAll();

    }

    @GetMapping()
    public String getAll(HttpServletRequest request, Model model)  {

        String action = request.getParameter("action");
        if (action!=null && (action.equals("delete") || (action.equals("update")))){
            int id = getID(request.getParameter("id"));
            if (action.equals("delete")) {
                log.info("delete");
                userService.delete(id);
                //при удалении пользователя, меняем текущего на следующего
                Optional<User> user = userService.getAll().stream().findFirst();
                if (!user.equals(Optional.empty()))
                    SecurityUtil.setAuthUserId(user.get().getId());
                else
                    SecurityUtil.setAuthUserId(0);

                return redirectUsers(model,"redirect:" + request.getHeader("referer"));

            }
            else{
                User user = id==0?new User():userService.get(id);
                model.addAttribute("user",user);
                return "/userForm";
            }
        }
        return redirectUsers(model,"/users");
    }

    @GetMapping("/update")
    public String updateUser(HttpServletRequest request, Model model) {
        log.info("update");
        int id = getID(request.getParameter("id"));
        User user = id==0?new User():userService.get(id);
        model.addAttribute("user",user);
        return "/userForm";
    }

    @PostMapping("/create")
    public String createUser(HttpServletRequest request, Model model) {
        log.info("create");
        int id = getID(request.getParameter("id"));
        String name = request.getParameter("name");
        User user;
        if (id!=0)
            user = new User(id, name);
        else
            user = new User(name);
        userService.save(user);

        return redirectUsers(model,"redirect:" + "users");


    }

    public String redirectUsers( Model model,String returned){
        log.info("getAll");
        model.addAttribute("users",userService.getAll());
        return returned;
    }

    @DeleteMapping("/{id}")//not use
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(HttpServletRequest request, Model model, @PathVariable int id) {
        userService.delete(id);
        return redirectUsers(model,"redirect:" + "/users");
    }

*/


}