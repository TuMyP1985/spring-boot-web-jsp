package ru.java.springbootwebjsp.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.java.springbootwebjsp.model.Account;
import ru.java.springbootwebjsp.model.enums.TypeCurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static javax.print.DocFlavor.URL.PDF;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static ru.java.springbootwebjsp.util.Util.savePdf;

class UtilTest {

    List<String> mockList = new ArrayList<>();

    @Test
    void getRate() {
        final String API_RATES = "https://api.coingate.com/v2/rates/merchant/USD/RUB";
        String rateTemp = resultGetQuery(API_RATES).get("value");
        double rate = 0;

        try {
            rate = Double.parseDouble(rateTemp);
        }catch (Exception ignored){}
        assertNotEquals(rate, 0);
    }

    @Test
    void savePdfTest() throws IOException {
        List<Account> accounts = new LinkedList<>();
        final String destFileName = "src/test/ru/java/springbootwebjsp/reportForTest.pdf";
        accounts.add(new Account(111111122, "rub1", TypeCurrency.RUB, 173000.12));
        accounts.add(new Account(333333344, "NOT rub3", TypeCurrency.EUR, 23000.22));
        savePdf(destFileName, accounts);
        byte[] data1 = Files.readAllBytes(Paths.get(destFileName));

        final String testFileName = "src/test/ru/java/springbootwebjsp/reportTestData.pdf";
        byte[] data2 = Files.readAllBytes(Paths.get(testFileName));
        Assertions.assertEquals(data1.length, data2.length);
    }

    public static HashMap<String,String> resultGetQuery(String query){

        String value = "";
        try {
            final URL url = new URL(query);
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                final StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null)
                    content.append(inputLine);
                value = content.toString();

            } catch (final Exception ex) {
                ex.printStackTrace();
                value = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("error","");
        hashMap.put("value",value);
        return hashMap;

    }

    /*
    public static void main(String[] args) {

        List<Account> accounts = new LinkedList<>();
        final String destFileName = "src/main/resources/report/report.pdf";
        Account a1 = new Account(111111122, "rub1", TypeCurrency.RUB, 173000.12);
        a1.setRegistered(new Date());
        accounts.add(a1);
        accounts.add(new Account(333333344, "NOT rub3", TypeCurrency.EUR, 23000.22));

        savePdf(destFileName, accounts);

//        UtilTest u = new UtilTest();
//        u.whenMockAnnotation();


    }
    //пример работы с Mock
    void whenMockAnnotation () {
        Mockito.doReturn(10).when(mockList).size();
        int s = mockList.size(); //должно быть 10;
    }
    */
}