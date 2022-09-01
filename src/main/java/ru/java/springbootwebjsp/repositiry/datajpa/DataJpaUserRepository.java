package ru.java.springbootwebjsp.repositiry.datajpa;

import org.springframework.stereotype.Repository;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.repositiry.UserRepository;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private final CrudUserRepository crudRepository;

    public DataJpaUserRepository(CrudUserRepository crudUserRepository) {
        this.crudRepository = crudUserRepository;
    }

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll();
    }
}
