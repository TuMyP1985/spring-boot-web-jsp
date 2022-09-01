package ru.java.springbootwebjsp.web.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.java.springbootwebjsp.model.Account;
import ru.java.springbootwebjsp.model.enums.TypeCurrency;
import ru.java.springbootwebjsp.model.paper.DataRate;
import ru.java.springbootwebjsp.model.paper.ResultToJson;
import ru.java.springbootwebjsp.model.paper.RichPaper;
import ru.java.springbootwebjsp.service.AccountService;
import ru.java.springbootwebjsp.service.UserService;
import ru.java.springbootwebjsp.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.java.springbootwebjsp.util.ExecutValueUtil.*;
import static ru.java.springbootwebjsp.util.Util.*;
import static ru.java.springbootwebjsp.web.SecurityUtil.SelectedCurrencyInAccountjsp;

@RestController
public class RichUIController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/jsonResultToJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResultToJson> getResultToJson(HttpServletRequest request, Model model) {
        String description = request.getParameter("text");//"Газпром";
        RichPaper paper = papermap.get(description);
        //RichPaper paper = paperFromDescription(description); select by description

        fillIfEmpty(paper);

        List<ResultToJson> r = new ArrayList<>();
        double prev = 0;

        for (DataRate d:paper.getDataRates()) {
            if (d.getValue()==0 &&prev == 0)
                continue;
            r.add(new ResultToJson(d.getValue()==0?prev:d.getValue(), d.getDate().toString()));
            prev = d.getValue();
        }
        return r;
    }

    @RequestMapping(value = "/outJsonInFile")
    public String outJsonInFile(HttpServletRequest request, Model model) {
        String[] accountsID = request.getParameter("text").split(";");
        List<Account> accounts = new ArrayList<>();
        for (String accId:accountsID) {
            int accountId = Integer.parseInt(accId);
            Account account = accountService.getByID(accountId);
            account.setUser(
                    userService.get(
                            accountService.getUserID(accountId)));
            accounts.add(account);
        }


        return outJson(accounts);
    }

    @RequestMapping(value = "/reportpdf", produces="application/pdf")
    public @ResponseBody ResponseEntity reportpdf(HttpServletRequest request, HttpServletResponse response, Model model) {
        byte[] data = null;
        try {
            final String destFileName = "src/main/resources/report/report.pdf";

            String textParam = request.getParameter("text");
            List<Account> accounts = new ArrayList<>();
            if (textParam != null) {
                accounts = Arrays.stream(request.getParameter("text").split(";"))
                                .map(accId -> accountService.getByID(Integer.parseInt(accId))
                                ).collect(Collectors.toList());
            }else{
                int userId =SecurityUtil.selectedUserId();
                String currencyString = SelectedCurrencyInAccountjsp();
                accounts = accountService.getAll(userId, currencyString);
            }

            savePdf(destFileName, accounts);

            data = Files.readAllBytes(Paths.get(destFileName));//read PDF as byte stream

//            //1.
//            response.setContentType("application/pdf");
//            response.setHeader("Content-disposition", "attachment; filename=report");
//            response.setHeader("Content-Disposition", "inline");
//            response.setContentLength(data.length);
//            response.getOutputStream().write(data);
//            response.getOutputStream().flush();

//            //2
//            response.setContentType("application/pdf");
//            InputStream inputStream = null;
//            OutputStream outputStream = null;
//            try{
//                File initialFile = new File("src/main/resources/sample.txt");
//                InputStream targetStream = new FileInputStream(initialFile);
//                outputStream = response.getOutputStream();
//                IOUtils.copy(inputStream, outputStream);
//            }catch(IOException ioException){
//                //Do something or propagate up..
//            }finally{
//                IOUtils.closeQuietly(inputStream);
//                IOUtils.closeQuietly(outputStream);
//            }


        }catch (Exception e){
            return new ResponseEntity("bad file"+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(data, HttpStatus.OK);

    }

    //(+)NOT USE
    @GetMapping
    @RequestMapping(value = "/stringData")
    public String getStringData() {

        String dataList1 = "[ ['Дата', 'Цена' ]";
        for (DataRate datarate : papermap.get("GAZP").getDataRates())
            dataList1 = dataList1 + ", ['" + datarate.getDate() + "', " + datarate.getValue() + "]";
        return dataList1 + "]";

    }

    @RequestMapping(value = "/jsonData", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DataRate> getJsongData() {
        return papermap.get("GAZP").getDataRates();
    }

    @RequestMapping(value = "/jsonMap", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Object, Object> getMap() {
        Map<Object, Object> r = new HashMap<>();
        r.put("Dat","Pric");
        for (DataRate d:papermap.get("GAZP").getDataRates())
            r.put(d.getDate().toString(),d.getValue());
        return r;
    }
    //(-)
}
