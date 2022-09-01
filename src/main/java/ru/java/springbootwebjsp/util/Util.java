package ru.java.springbootwebjsp.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import ru.java.springbootwebjsp.model.Account;
import ru.java.springbootwebjsp.model.paper.RichPaper;
import ru.java.springbootwebjsp.model.enums.TypeCurrency;
import ru.java.springbootwebjsp.web.controller.BankStatementController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.java.springbootwebjsp.util.ExecutValueUtil.instanceExecutValue;
import static ru.java.springbootwebjsp.util.ExecutValueUtil.papermap;

public class Util {

    private static final Logger log = LoggerFactory.getLogger(Util.class);

    public static void utileInstance(){
        instanceExecutValue();
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

    public static double getRate(String currency) {

        final String API_RATES = "https://api.coingate.com/v2/rates/merchant/"+currency+"/RUB";
        String rateTemp = resultGetQuery(API_RATES).get("value");
        double rate = 0;

        try {
            rate = Double.parseDouble(rateTemp);
        }catch (Exception e){}

        return rate;
    }

    public static List<String> listCurrency(boolean addAllCurrency) {
        List<String> listCurrency = new ArrayList<>();
        if (addAllCurrency)
            listCurrency.add("(всё)");

        listCurrency.addAll(Stream.of(TypeCurrency.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
        return listCurrency;
    }

    public static String currentcurrency(HttpServletRequest request) {
        String currencyString = "";
        try {
            currencyString = TypeCurrency.valueOf(request.getParameter("typecurrencyselected")).name();
        } catch (Exception ignored) {}
        return currencyString;
    }

    public static String checkFile(Set<String> strings, String... keys) {
        StringBuilder error = new StringBuilder();
        for (String key : keys)
            if (!strings.contains(key))
                error.append("In file must be key: " + key + ";" + "<BR/>");
        return error.toString();
    }

    //need for Bank statement
    public static DataFromFile DataFromJson(String text_json){
        JSONObject json = new JSONObject(text_json); //jsonStr - мой json в видео строки
        StringBuilder error = new StringBuilder(checkFile(json.keySet(), "accountid", "description", "value"));

        DataFromFile data = new DataFromFile();
        try {
            data.setAccountid(Integer.parseInt((String) json.get("accountid")));
        } catch (Exception e) { error.append(e.getMessage());}

        try {
            data.setDescription((String) json.get("description"));
        } catch (Exception e) { error.append(e.getMessage() + ";" + "<BR/>");}

        try {
            data.setValue(Double.parseDouble((String) json.get("value")));
        } catch (Exception e) { error.append(e.getMessage() + ";" + "<BR/>");}

        data.setError(error.toString());
        return data;

    }

    public static int getID(String id){
        int i = 0;
        try {
            i = id==null?0:Integer.parseInt(id);
        }catch (Exception ignored){}
        return i;
    }

    public static double getDouble(String id){
        double i = 0;
        try {
            i = id==null?0:Double.parseDouble(id);
        }catch (Exception ignored){}
        return i;
    }

    public static String formatDigital(String input, int minDigit){
        BigDecimal decimal = new BigDecimal(input);
        DecimalFormat format = new DecimalFormat("###,###,###,###,###.####");
        format.setDecimalSeparatorAlwaysShown(true);
        format.setMinimumFractionDigits(minDigit);
        return format.format(decimal);
    }

    public static String formatDigital(double input, int minDigit){
        return formatDigital(String.valueOf(input), minDigit);
    }

    public static String nameFromFulNamePaper(double input, int minDigit){
        return formatDigital(String.valueOf(input), minDigit);
    }

    public static RichPaper paperFromDescription(String name) {
        for (RichPaper p:papermap.values())
            if (p.getDescription().equals(name))
                return p;
        return null;
    }

    public static void savePdf(String destFileName, List<Account> accounts){

        try {
            log.info("generating jasper report...");

            // 1. compile template ".jrxml" file
            File template = ResourceUtils.getFile("classpath:report/report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(template.getAbsolutePath());

            // 2. parameters "empty"
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "hmkcode");

            // 3. datasource "java object"
            JRDataSource dataSource = new JRBeanCollectionDataSource(accounts);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters,
                    dataSource);

            JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
            log.info("Документ pdf сформирован.");
        } catch (Exception e) {
            log.info("pdf не сформирован. " + e.getMessage());
        }
    }

}
