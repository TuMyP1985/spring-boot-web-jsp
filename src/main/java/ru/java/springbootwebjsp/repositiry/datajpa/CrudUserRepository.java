package ru.java.springbootwebjsp.repositiry.datajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.java.springbootwebjsp.model.User;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User,Integer> {
    @Transactional
    @Modifying
    @Query("Delete From User u where u.id=:id")
    int delete(@Param("id") int id);
}
