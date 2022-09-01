package ru.java.springbootwebjsp.util;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.java.springbootwebjsp.model.Account;
import ru.java.springbootwebjsp.model.paper.DataRate;
import ru.java.springbootwebjsp.model.paper.PapersToAnaliticTable;
import ru.java.springbootwebjsp.model.paper.RichPaper;
import ru.java.springbootwebjsp.model.enums.TypeRichPaper;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static ru.java.springbootwebjsp.util.Util.resultGetQuery;

public class ExecutValueUtil {

    final static int DAY_FROM_DAY_NOW = 1;          //до какого дня грузим курсы бумаг = (текущий день минус это значение). По умолч. текущий день - 1 день
    private static int day_select = 7;              //количество дней выборки. По умолч. грузим за 7 дней курсы бумаг
    final static int STEP_OF_DELTA = 1000;          //округление DataRate.delta - кол. знаков после запятой
    final static int STEP_OF_PERCENT = 100;         //округление DataRate.deltaPerсent - кол. знаков после запятой
    final static int DAY_FOR_ANALITIC_TABLE = 5;    //строим итоговую аналит. таблицу - за какой период анализируем изменение курсов цб. По умолч. за 3 дня.

    public static Map<String, RichPaper> papermap = new HashMap<>();

    public static void instanceExecutValue(){

        addValues("GAZP", "Газпром");
        addValues("SBER", "Сбербанк ПАО");
        addValues("SBERP","Сбербанк (прив.)");
        addValues("MGNT", "ОАО Магнит");
        addValues("LKOH", "НК Лукойл ПАО");
        addValues("GMKN", "ГМК Норильский Никель ПАО");
        addValues("YNDX", "Яндекс Н.В.");
        addValues("VTBR", "ВТБ RTS ПАО");
        addValues("AFLT", "ОАО Аэрофлот");
    }

    public static void addValues(String name, String description){
        papermap.put(name,new RichPaper(name, description));
    }

    public static void doRepeat(){
        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        //ДЕЛАЕМ: на будущее, получать курсы бумаг, например раз в минуту

                        Thread.sleep(60*1000); //1000 - 1 сек
                    } catch (InterruptedException ex) {
                    }
                }
            }
        });
        run.start();
    }

    public static Map<TypeRichPaper, RichPaper> fillDataRates(RichPaper papers){
        List<RichPaper> richPaperList = new ArrayList<>();
        richPaperList.add(papers);
        return fillDataRates(richPaperList);

    }

    //заполняем историей курсов, за DAY_SELECT дней, от текущего минус DAY_FROM_CURRENT дня,
    //возвращает dataRateMax - оптимальная ЦБ
    public static Map<TypeRichPaper, RichPaper> fillDataRates(List<RichPaper> papers){

        Map<TypeRichPaper, RichPaper> result = new HashMap<>();

        RichPaper richPaperMax = new RichPaper();
        RichPaper richPaperMin = new RichPaper();
        DataRate dataRateMax = new DataRate();
        DataRate dataRateMin = new DataRate();

        result.put(TypeRichPaper.MAX, richPaperMax);
        result.put(TypeRichPaper.MIN, richPaperMin);

        LocalDate dateBegin = LocalDate.now().minusDays(DAY_FROM_DAY_NOW + day_select);

        for (RichPaper r : papers) {
            List<DataRate> datasList = new ArrayList<>();
            double prevDelta = 0;
            for (int i = 1; i <= day_select; i++) {
                DataRate dataRate = new DataRate(dateBegin.plusDays(i), priceOnDate(r.getName(), dateBegin.plusDays(i)));
                datasList.add(dataRate);
                if (dataRate.getValue()==0)
                    continue;
                if (prevDelta!=0){
                    dataRate.setDelta(Math.ceil((dataRate.getValue()-prevDelta)*STEP_OF_DELTA)/STEP_OF_DELTA);
                    double deltaPerсent = ((dataRate.getValue()/prevDelta-1)*100);
                    double perсent = Math.ceil(deltaPerсent*STEP_OF_PERCENT)/STEP_OF_PERCENT;
                    dataRate.setDeltaPerсent(perсent);
                }
                prevDelta = dataRate.getValue();
                if (dataRateMax.getDeltaPerсent()<dataRate.getDeltaPerсent()){
                    dataRateMax = new DataRate(dataRate);
                    richPaperMax.setName(r.getName());
                    richPaperMax.setDescription(r.getDescription());
                }
                if (dataRateMin.getDeltaPerсent()>dataRate.getDeltaPerсent()){
                    dataRateMin = new DataRate(dataRate);
                    richPaperMin.setName(r.getName());
                    richPaperMin.setDescription(r.getDescription());
                }
            }
            r.setDataRates(datasList);
        }

        richPaperMax.setDataRates(dataRateMax);
        richPaperMin.setDataRates(dataRateMin);

        return result;
    }

    public static DataRate CurrentData(String name){
        RichPaper r = papermap.get(name);
        return r.getDataRates().get(0);
    }

    //example:
    //http://iss.moex.com/iss/history/engines/stock/markets/shares/boards/TQBR/securities/SBER.json?iss.meta=off&iss.only=history&history.columns=SECID,TRADEDATE,CLOSE&limit=1&from=2015-01-10
    public static double priceOnDate(String currency, LocalDate date){

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        String datetext = date.format(formatters);

        final String API_PRICE_ON_DATE = "http://iss.moex.com/iss/history/engines/stock/markets/shares/boards/TQBR/securities/"+
                currency +".json?iss.meta=off&iss.only=history&history.columns=SECID,TRADEDATE,CLOSE&limit=1&from=" + datetext;;

        String text_json = resultGetQuery(API_PRICE_ON_DATE).get("value");
        JSONObject json = new JSONObject(text_json);
        double result = 0;

        try {
            String val = ((JSONArray) ((JSONArray)
                    ((JSONObject) json.get("history"))
                            .get("data"))
                    .get(0))
                    .get(2)
                    .toString();
            result = Double.parseDouble(val);
        } catch (Exception ignored) {
        }

        return result;

    }

    public static String lastPrice(String currency) {

        final String API_LAST_PRICE = "https://iss.moex.com/iss/engines/stock/markets/shares/boards/TQBR/securities/" +
                currency + ".json?iss.meta=off&iss.only=marketdata&marketdata.columns=SECID,LAST";
        String text_json = resultGetQuery(API_LAST_PRICE).get("value");

        JSONObject json = new JSONObject(text_json);
        String rate = "";

        try {
            rate = ((JSONArray) ((JSONArray)
                    ((JSONObject) json.get("marketdata"))
                            .get("data"))
                    .get(0))
                    .get(1)
                    .toString();
        } catch (Exception ignored) {
        }

        return rate;
    }

    public static List<PapersToAnaliticTable> analiticpaperlistdata(){

        List<PapersToAnaliticTable> listAnalitics = new ArrayList<>();

        for (RichPaper r : papermap.values()) {
            fillIfEmpty(r);
            List<DataRate> dataRateList = r.getDataRates();
            int sizeDalaList = dataRateList.size();
            if (sizeDalaList < DAY_FOR_ANALITIC_TABLE)
                throw new NotFoundException("Размер меньше бумаги:" + r.getName() + ", чем период анализа:" + DAY_FOR_ANALITIC_TABLE);
            PapersToAnaliticTable analitic = new PapersToAnaliticTable(r);
            for (int i = sizeDalaList - DAY_FOR_ANALITIC_TABLE; i < sizeDalaList; i++) {
                DataRate d = dataRateList.get(i);
                double delta = d.getDeltaPerсent();
                if (delta==0)
                    continue;
                if (delta < 0)
                    analitic.setMinusPercentForSort(analitic.getMinusPercentForSort() + delta);
                else
                    analitic.setPlusPercentForSort(analitic.getPlusPercentForSort() + delta);
            }

            analitic.setMinusPercentForSort(Math.ceil(
                    analitic.getMinusPercentForSort()*STEP_OF_PERCENT)
                    /STEP_OF_PERCENT);
            analitic.setPlusPercentForSort(Math.ceil(
                    analitic.getPlusPercentForSort()*STEP_OF_PERCENT)
                    /STEP_OF_PERCENT);
            listAnalitics.add(analitic);
        }

        listAnalitics.sort(PapersToAnaliticTable::compareTo);
//        Collections.sort(listAnalitics);
        return listAnalitics;

    }

    public static void fillIfEmpty(RichPaper paper){
        assert paper != null;
        List<DataRate> listData = paper.getDataRates();
        if (listData.size()==0)
            fillDataRates(paper);
    }

    public static void clearDataRates(){
        for (RichPaper p:papermap.values())
            p.setDataRates(new ArrayList<>());
    }

    public static Map<TypeRichPaper, RichPaper> findBestRichPaper(){
        return fillDataRates(new ArrayList<RichPaper>(papermap.values()));
    }

    public static int getDay_select() {
        return day_select;
    }

    public static void setDay_select(int day_select) {
        ExecutValueUtil.day_select = day_select;
    }

    public static int getDAY_FOR_ANALITIC_TABLE(){
        return DAY_FOR_ANALITIC_TABLE;
    }

    public static <T> JSONObject createJSON(T object, String...properties){
        StringBuilder error = new StringBuilder();
        JSONObject json = new JSONObject();
        for (String val:properties){
            try {
                Method getHorsepowerMethod = object.getClass().getMethod("get"+val.substring(0, 1).toUpperCase()+val.substring(1));

                json.put(val, getHorsepowerMethod.invoke(object));

            } catch (Exception e) {
                error.append(e.getMessage());
            }
        }
        if (!error.toString().isEmpty())
            json.put("error", error.toString());
        return json;
    }

    public static String outJson(List<Account> accounts) {
        String result = "";


        try {
            JSONArray jsonsArray = new JSONArray();
            for (Account account : accounts) {
                //тут формирую JSON и сохраняю на комп
                JSONObject json = createJSON(account, "number", "description", "typeCurrency", "value");
                json.put("userid", account.getUser().getId());
                json.put("username", account.getUser().getName());

                jsonsArray.put(json);
            }

                if (jsonsArray.length()>0) {
                    String jsonString = jsonsArray.toString();
                    File file = File.createTempFile("data", ".json");
                    result = file.getAbsolutePath();

                    FileWriter fileWriter = new FileWriter(result);
                    fileWriter.write(jsonString);
                    fileWriter.flush();
                    fileWriter.close();
                }

        } catch (Exception e) {
            result = e.getMessage();
        }
        ;
        return result;
    }
}
