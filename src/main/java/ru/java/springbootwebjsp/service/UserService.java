package ru.java.springbootwebjsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.repositiry.UserRepository;
import ru.java.springbootwebjsp.util.ValidationUtil;

import java.util.List;

import static ru.java.springbootwebjsp.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public User get(int id){
        return ValidationUtil.checkNotFoundWithId(userRepository.get(id), id);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User save(User user){
        Assert.notNull(user,"user must not null");
        return ValidationUtil.checkNotFoundWithId(userRepository.save(user), user.getId());
    }

}
