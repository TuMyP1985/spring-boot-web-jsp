package ru.java.springbootwebjsp.repositiry.datajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.java.springbootwebjsp.model.Account;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudAccountRepository extends JpaRepository<Account,Integer>
{
    @Modifying
    @Transactional
    @Query("DELETE FROM Account m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Account m WHERE m.user.id=:userId")
    List<Account> getAll(@Param("userId") int userId);

    @Query("SELECT m FROM Account m JOIN FETCH m.user WHERE m.id = ?1 and m.user.id = ?2")
    Account getWithUser(int id, int userId);
}
