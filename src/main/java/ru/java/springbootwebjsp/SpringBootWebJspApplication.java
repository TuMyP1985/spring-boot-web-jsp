package ru.java.springbootwebjsp;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.java.springbootwebjsp.model.BankStatements;
import ru.java.springbootwebjsp.model.enums.TypeCurrency;
import ru.java.springbootwebjsp.service.BankStatementService;
import ru.java.springbootwebjsp.service.UserService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static ru.java.springbootwebjsp.util.Util.utileInstance;


@SpringBootApplication
@EnableSwagger2
public class SpringBootWebJspApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringBootWebJspApplication.class);

	@Autowired
	BankStatementService bankStatementService;

	@Override
	public void run(String... strings) throws Exception {

//		//only for debug
//		bankStatementService.save(new BankStatements("Иванов поступление руб.11", TypeCurrency.RUB));
//		log.info(bankStatementService.getAll().size()+"!!!!!!!!!!!!!");
//		for (BankStatements u : bankStatementService.getAll()){
// 			log.info(u.getDescription());
//		}

	}

	public static void main(String[] args) throws Exception {

		utileInstance();
		SpringApplication.run(SpringBootWebJspApplication.class, args);

	}
}

