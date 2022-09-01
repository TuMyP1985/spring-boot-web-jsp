package ru.java.springbootwebjsp.repositiry.datajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.java.springbootwebjsp.model.BankStatements;

@Transactional(readOnly = true)
public interface CrudBankStatementRepository extends JpaRepository<BankStatements,Integer>
{

}
