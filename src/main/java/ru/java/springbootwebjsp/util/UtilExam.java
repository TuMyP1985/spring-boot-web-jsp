package ru.java.springbootwebjsp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import ru.java.springbootwebjsp.model.Exam;
import ru.java.springbootwebjsp.model.enums.TypeExam;
import ru.java.springbootwebjsp.web.controller.ExamRussianController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UtilExam {

    private static final Logger log = LoggerFactory.getLogger(ExamRussianController.class);
    public final static int SIZE_QUASTION = 10;
    private final static String PASSWORD = "123";

    public static List<Exam> examListMathematic(List<Exam> examList) {
        examList.clear();
        for (int i = 0; i < SIZE_QUASTION; i++)
        {
            int val1 = gerInt(SIZE_QUASTION - 1);
            int val2 = gerInt(SIZE_QUASTION - 1);
            examList.add(new Exam(
                    String.valueOf(val1),
                    String.valueOf(val2),
                    "0",
                    String.valueOf(val1*val2),
                    false));
        }
        return examList;
    }

    public static int gerInt(int size){
        return 1+(int) (Math.random()*size);
    }

    private static void getListFromData(List<Exam> examList, List<Exam> examListTemp){
        examList.clear();
        HashSet<Integer> rand = new HashSet<>();
        //1.рандомно заполним SIZE_QUASTION вопросов
        while (examList.size()<SIZE_QUASTION){
            int r1 = gerInt(examListTemp.size()-1);
            if (rand.contains(r1))
                continue;
            rand.add(r1);
            Exam e = examListTemp.get(r1);
            //2.рандомно поменяем 1 и 2 значения местами
            if ( (int) (Math.random() * 2) != 1) {
                String temp = e.getVal1();
                e.setVal1(e.getVal2());
                e.setVal2(temp);
            }
            //3.рандомно поменяем 2 и 3 значения местами
            if (!e.getVal3().isEmpty() && (int) (Math.random() * 2) != 1) {
                String temp = e.getVal2();
                e.setVal2(e.getVal3());
                e.setVal3(temp);
            }
            examList.add(e);
        }
    }

    public static void examListEnglish(List<Exam> examList){
        getListFromData(examList, DATAEnglish());
    }

    public static void examListRussian(List<Exam> examList) {
        getListFromData(examList, DATARussian());
    }

    public static String DATAPicture(){
        List<String> pictureList = new ArrayList<>();
        pictureList.add("https://funik.ru/wp-content/uploads/2019/06/7e556a85155ec5e99630-700x700.jpeg");
        pictureList.add("https://funik.ru/wp-content/uploads/2019/06/964329ce98e5e7118218.jpg");
        pictureList.add("https://klike.net/uploads/posts/2019-06/1560839416_1.png");
        pictureList.add("https://klike.net/uploads/posts/2019-06/medium/1560839429_2.jpg");
        pictureList.add("https://klike.net/uploads/posts/2019-06/1560839391_3.jpg");
        pictureList.add("https://klike.net/uploads/posts/2019-06/1560839402_4.jpg");
        pictureList.add("https://funik.ru/wp-content/uploads/2019/06/ea7482fd2eaeb3f8bc0c-2-700x700.jpg");
        pictureList.add("https://funik.ru/wp-content/uploads/2019/06/4ff365b2d15a8e1ceaf2-700x700.jpg");
        pictureList.add("https://funik.ru/wp-content/uploads/2019/06/4d6804e2c4524a1d2396-2-700x700.jpg");
        pictureList.add("https://funik.ru/wp-content/uploads/2019/06/976983cd1c0b07fbd5cb-700x700.jpg");

        return pictureList.get(gerInt(SIZE_QUASTION -1));
    }

    private static List<Exam> DATAEnglish(){

        List<Exam> examListTemp = new ArrayList<>();
        examListTemp.add(new Exam("кошка","cat","cot","kat","cat",false));
        examListTemp.add(new Exam("собака","sobaka","dog","pig","dog",false));
        examListTemp.add(new Exam("дом","son","dom","house","house",false));
        examListTemp.add(new Exam("книга","book","mook","took","book",false));
        examListTemp.add(new Exam("начинать","beg","begin","din","begin",false));
        examListTemp.add(new Exam("бежать","run","man","tank","run",false));
        examListTemp.add(new Exam("стол","mebel","table","stol","table",false));
        examListTemp.add(new Exam("машина","cal","mar","car","car",false));
        examListTemp.add(new Exam("ручка","hand","pencil","pen","pen",false));
        examListTemp.add(new Exam("вода","water","voda","soda","water",false));

        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));
        examListTemp.add(new Exam("море","mea","sea","dea","sea",false));
        examListTemp.add(new Exam("велосипед","mike","nike","bike","bike",false));
        examListTemp.add(new Exam("лошадь","horse","mors","kors","horse",false));
        examListTemp.add(new Exam("автобус","mus","bus","liaz","bus",false));
        examListTemp.add(new Exam("карандаш","sun","note","pencil","pencil",false));
        examListTemp.add(new Exam("дождь","rain","can","main","rain",false));
        examListTemp.add(new Exam("свинья","svin","pig","mig","pig",false));
        examListTemp.add(new Exam("холодно","fold","moda","cold","cold",false));
        examListTemp.add(new Exam("золото","gold","sold","dog","gold",false));

        examListTemp.add(new Exam("один","one","mom","two","one",false));
        examListTemp.add(new Exam("два","wow","two","foo","two",false));
        examListTemp.add(new Exam("три","mir","fir","three","three",false));
        examListTemp.add(new Exam("четыре","four","for","food","four",false));
        examListTemp.add(new Exam("пять","fofe","five","fif","five",false));
        examListTemp.add(new Exam("привет","melo","selo","hello","hello",false));
        examListTemp.add(new Exam("что","what","hot","pot","what",false));
        examListTemp.add(new Exam("как","now","how","sow","how",false));
        examListTemp.add(new Exam("цвет","molo","col","colour","colour",false));
        examListTemp.add(new Exam("это","this","is","miss","this",false));

        examListTemp.add(new Exam("я", "Y", "IA", "I", "I", false));
        examListTemp.add(new Exam("ты", "you", "mine", "your", "you", false));
        examListTemp.add(new Exam("мы", "us", "we", "nas", "we", false));
        examListTemp.add(new Exam("он", "wifi", "hi", "he", "he", false));
        examListTemp.add(new Exam("она", "she", "me", "see", "she", false));
        examListTemp.add(new Exam("это", "my", "it", "I", "it", false));
        examListTemp.add(new Exam("нас", "vas", "nas", "us", "us", false));
        examListTemp.add(new Exam("твой", "your", "mine", "my", "your", false));
        examListTemp.add(new Exam("мой", "nus", "my", "us", "my", false));
        examListTemp.add(new Exam("наш", "sorry", "mor", "our", "our", false));

//        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));
//        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));
//        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));
//        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));
//        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));
//        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));
//        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));
//        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));
//        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));
//        examListTemp.add(new Exam("солнце","sun","san","son","sun",false));

        return examListTemp;

    }

    private static List<Exam> DATARussian(){

        List<Exam> examListTemp = new ArrayList<>();

        examListTemp.add(new Exam("альбом","ольбом","","альбом",false));
        examListTemp.add(new Exam("аппетит","апетит","","аппетит",false));
        examListTemp.add(new Exam("аппарат","апарат","","аппарат",false));
        examListTemp.add(new Exam("автобус","овтобус","","автобус",false));
        examListTemp.add(new Exam("аптека","оптека","","аптека",false));
        examListTemp.add(new Exam("аккуратно","акуратно","","аккуратно",false));
        examListTemp.add(new Exam("аппликация","опликация","","аппликация",false));
        examListTemp.add(new Exam("автомобиль","автамабиль","","автомобиль",false));
        examListTemp.add(new Exam("аллея","алея","","аллея",false));
        examListTemp.add(new Exam("багаж","богаж","","багаж",false));
        examListTemp.add(new Exam("болото","балото","","болото",false));
        examListTemp.add(new Exam("беречь","биречь","","беречь",false));
        examListTemp.add(new Exam("беседа","биседа","","беседа",false));
        examListTemp.add(new Exam("берег","бериг","","берег",false));
        examListTemp.add(new Exam("библиотека","беблиотека","","библиотека",false));
        examListTemp.add(new Exam("ботинки","батинки","","ботинки",false));
        examListTemp.add(new Exam("благодарить","благадарить","","благодарить",false));
        examListTemp.add(new Exam("билет","белет","","билет",false));
        examListTemp.add(new Exam("бассейн","басейн","","бассейн",false));
        examListTemp.add(new Exam("богатство","багатство","","богатство",false));
        examListTemp.add(new Exam("блокнот","блакнот","","блокнот",false));
        examListTemp.add(new Exam("балкон","болкон","","балкон",false));
        examListTemp.add(new Exam("восемь","восим","","восемь",false));
        examListTemp.add(new Exam("вагон","вогон","","вагон",false));
        examListTemp.add(new Exam("веять","веить","","веять",false));
        examListTemp.add(new Exam("весенний","висений","","весенний",false));
        examListTemp.add(new Exam("велосипед","вилосипед","","велосипед",false));
        examListTemp.add(new Exam("впереди","впиреди","","впереди",false));
        examListTemp.add(new Exam("вторник","вторнек","","вторник",false));
        examListTemp.add(new Exam("ветер","ветир","","ветер",false));
        examListTemp.add(new Exam("вверх","веръ","","вверх",false));
        examListTemp.add(new Exam("валенки","валинки","","валенки",false));
        examListTemp.add(new Exam("вместе","вмести","","вместе",false));
        examListTemp.add(new Exam("видеть","видить","","видеть",false));
        examListTemp.add(new Exam("восхищение","васхищение","","восхищение",false));
        examListTemp.add(new Exam("везде","визде","","везде",false));
        examListTemp.add(new Exam("восток","васток","","восток",false));
        examListTemp.add(new Exam("вокруг","вакруг","","вокруг",false));
        examListTemp.add(new Exam("воскресенье","васкресенье","","воскресенье",false));
        examListTemp.add(new Exam("вперёд","впиред","","вперёд",false));
        examListTemp.add(new Exam("велосипедист","вилосипед","","велосипедист",false));
        examListTemp.add(new Exam("вниз","внез","","вниз",false));
        examListTemp.add(new Exam("воображение","ваабражение","","воображение",false));
        examListTemp.add(new Exam("варежки","варижки","","варежки",false));
        examListTemp.add(new Exam("винегрет","венергет","","винегрет",false));
        examListTemp.add(new Exam("вокзал","вакзал","","вокзал",false));
        examListTemp.add(new Exam("георгин","геаргин","","георгин",false));
        examListTemp.add(new Exam("гигант","гегант","","гигант",false));
        examListTemp.add(new Exam("гладить","гладеть","","гладить",false));
        examListTemp.add(new Exam("горячий","гарячий","","горячий",false));
        examListTemp.add(new Exam("гвоздика","гваздика","","гвоздика",false));
        examListTemp.add(new Exam("горох","гарох","","горох",false));
        examListTemp.add(new Exam("газета","гозета","","газета",false));
        examListTemp.add(new Exam("гладиолус","глодиолус","","гладиолус",false));
        examListTemp.add(new Exam("гараж","гораж","","гараж",false));
        examListTemp.add(new Exam("галерея","галлерея","","галерея",false));
        examListTemp.add(new Exam("герой","гирой","","герой",false));
        examListTemp.add(new Exam("грамм","грам","","грамм",false));
        examListTemp.add(new Exam("гречишный","гречичный","","гречишный",false));
        examListTemp.add(new Exam("гирлянда","герлянда","","гирлянда",false));
        examListTemp.add(new Exam("гимнастика","гемнастика","","гимнастика",false));
        examListTemp.add(new Exam("горизонт","газиронт","","горизонт",false));
        examListTemp.add(new Exam("готовить","гатовить","","готовить",false));
        examListTemp.add(new Exam("девочка","девачка","","девочка",false));
        examListTemp.add(new Exam("двадцать","двацать","","двадцать",false));
        examListTemp.add(new Exam("ещё","ище","","ещё",false));
        examListTemp.add(new Exam("животное","жевотнае","","животное",false));
        examListTemp.add(new Exam("жёлтый","жолтный","","жёлтый",false));
        examListTemp.add(new Exam("желать","жилать","","желать",false));
        examListTemp.add(new Exam("запад","запод","","запад",false));
        examListTemp.add(new Exam("завод","зовод","","завод",false));
        examListTemp.add(new Exam("заря","зоря","","заря",false));
        examListTemp.add(new Exam("здесь","сдесь","","здесь",false));
        examListTemp.add(new Exam("забота","зобота","","забота",false));
        examListTemp.add(new Exam("завтрак","завтрок","","завтрак",false));
        examListTemp.add(new Exam("здравствуй","здраствуй","","здравствуй",false));
        examListTemp.add(new Exam("здание","сдание","","здание",false));
        examListTemp.add(new Exam("здоровье","здаровье","","здоровье",false));
        examListTemp.add(new Exam("интересный","интерестный","","интересный",false));
        examListTemp.add(new Exam("инженер","инжинер","","инженер",false));
        examListTemp.add(new Exam("интерес","интирес","","интерес",false));
        examListTemp.add(new Exam("извинение","извенение","","извинение",false));
        examListTemp.add(new Exam("картофель","кортофель","","картофель",false));
        examListTemp.add(new Exam("костёр","кастер","","костёр",false));
        examListTemp.add(new Exam("компьютер","компютер","","компьютер",false));
        examListTemp.add(new Exam("коллектив","колектив","","коллектив",false));
        examListTemp.add(new Exam("календарь","колендарь","","календарь",false));
        examListTemp.add(new Exam("касса","каса","","касса",false));
        examListTemp.add(new Exam("килограмм","килограм","","килограмм",false));
        examListTemp.add(new Exam("корова","карова","","корова",false));
        examListTemp.add(new Exam("комната","комнота","","комната",false));
        examListTemp.add(new Exam("конечно","канечно","","конечно",false));
        examListTemp.add(new Exam("корабль","карабль","","корабль",false));
        examListTemp.add(new Exam("когда","кагда","","когда",false));
        examListTemp.add(new Exam("колыбель","калыбельная","","колыбель",false));
        examListTemp.add(new Exam("коллекция","колекция","","коллекция",false));
        examListTemp.add(new Exam("колокол","калокол","","колокол",false));
        examListTemp.add(new Exam("конверт","канверт","","конверт",false));
        examListTemp.add(new Exam("колокольчик","калакольчик","","колокольчик",false));
        examListTemp.add(new Exam("конфетти","канфети","","конфетти",false));
        examListTemp.add(new Exam("комбайн","камбайн","","комбайн",false));
        examListTemp.add(new Exam("лагерь","лагирь","","лагерь",false));
        examListTemp.add(new Exam("лучше","лучши","","лучше",false));
        examListTemp.add(new Exam("ландыш","ландиш","","ландыш",false));
        examListTemp.add(new Exam("лимон","лемон","","лимон",false));
        examListTemp.add(new Exam("лестница","леснитца","","лестница",false));
        examListTemp.add(new Exam("лаять","лаить","","лаять",false));
        examListTemp.add(new Exam("магазин","могозин","","магазин",false));
        examListTemp.add(new Exam("медленно","медлено","","медленно",false));
        examListTemp.add(new Exam("метро","митро","","метро",false));
        examListTemp.add(new Exam("молоко","малако","","молоко",false));
        examListTemp.add(new Exam("малина","молина","","малина",false));
        examListTemp.add(new Exam("молоток","малаток","","молоток",false));
        examListTemp.add(new Exam("морковь","марковь","","морковь",false));
        examListTemp.add(new Exam("металл","метал","","металл",false));
        examListTemp.add(new Exam("мишура","мешура","","мишура",false));
        examListTemp.add(new Exam("математика","матиматика","","математика",false));
        examListTemp.add(new Exam("минута","мениту","","минута",false));
        examListTemp.add(new Exam("назад","назат","","назад",false));
        examListTemp.add(new Exam("ноябрь","наябрь","","ноябрь",false));
        examListTemp.add(new Exam("овёс","авес","","овёс",false));
        examListTemp.add(new Exam("обычный","абычный","","обычный",false));
        examListTemp.add(new Exam("обед","абед","","обед",false));
        examListTemp.add(new Exam("огромный","агромный","","огромный",false));
        examListTemp.add(new Exam("оберегать","аберегать","","оберегать",false));
        examListTemp.add(new Exam("огород","агарод","","огород",false));
        examListTemp.add(new Exam("около","окола","","около",false));
        examListTemp.add(new Exam("один","адин","","один",false));
        examListTemp.add(new Exam("осенний","осений","","осенний",false));
        examListTemp.add(new Exam("огурец","агурец","","огурец",false));
        examListTemp.add(new Exam("организм","арганизм","","организм",false));
        examListTemp.add(new Exam("океан","акеан","","океан",false));
        examListTemp.add(new Exam("опушка","апушка","","опушка",false));
        examListTemp.add(new Exam("орех","арех","","орех",false));
        examListTemp.add(new Exam("окрестность","акресность","","окрестность",false));
        examListTemp.add(new Exam("осина","асина","","осина",false));
        examListTemp.add(new Exam("объектив","абъектив","","объектив",false));
        examListTemp.add(new Exam("остров","острав","","остров",false));
        examListTemp.add(new Exam("обидеть","абидеть","","обидеть",false));
        examListTemp.add(new Exam("очень","очинь","","очень",false));
        examListTemp.add(new Exam("охрана","ахрана","","охрана",false));
        examListTemp.add(new Exam("отдых","одых","","отдых",false));
        examListTemp.add(new Exam("однажды","аднажды","","однажды",false));
        examListTemp.add(new Exam("одуванчик","адуванчик","","одуванчик",false));
        examListTemp.add(new Exam("обедать","абедать","","обедать",false));
        examListTemp.add(new Exam("одиннадцать","адинадцать","","одиннадцать",false));
        examListTemp.add(new Exam("привет","превет","","привет",false));
        examListTemp.add(new Exam("петух","питух","","петух",false));
        examListTemp.add(new Exam("победа","пабеда","","победа",false));
        examListTemp.add(new Exam("песок","писок","","песок",false));
        examListTemp.add(new Exam("пирог","перог","","пирог",false));
        examListTemp.add(new Exam("погода","пагода","","погода",false));
        examListTemp.add(new Exam("петрушка","питрушка","","петрушка",false));
        examListTemp.add(new Exam("пожалуйста","пажалуйста","","пожалуйста",false));
        examListTemp.add(new Exam("прекрасный","прикрасный","","прекрасный",false));
        examListTemp.add(new Exam("прыгать","прыготь","","прыгать",false));
        examListTemp.add(new Exam("программа","праграма","","программа",false));
        examListTemp.add(new Exam("природа","прерода","","природа",false));
        examListTemp.add(new Exam("поэт","паэт","","поэт",false));
        examListTemp.add(new Exam("потом","патом","","потом",false));
        examListTemp.add(new Exam("помидор","памидор","","помидор",false));
        examListTemp.add(new Exam("песчаный","писченый","","песчаный",false));
        examListTemp.add(new Exam("профессия","професия","","профессия",false));
        examListTemp.add(new Exam("пшеница","пшиница","","пшеница",false));
        examListTemp.add(new Exam("полотенце","палатенце","","полотенце",false));
        examListTemp.add(new Exam("пловец","плавец","","пловец",false));
        examListTemp.add(new Exam("пороша","пароша","","пороша",false));
        examListTemp.add(new Exam("приветливо","преветливо","","приветливо",false));
        examListTemp.add(new Exam("прощай","пращай","","прощай",false));
        examListTemp.add(new Exam("победить","пабедить","","победить",false));
        examListTemp.add(new Exam("позади","пазади","","позади",false));
        examListTemp.add(new Exam("пингвин","пенгвин","","пингвин",false));
        examListTemp.add(new Exam("падать","подать","","падать",false));
        examListTemp.add(new Exam("пятьдесят","питьдесят","","пятьдесят",false));
        examListTemp.add(new Exam("пирожное","пероженое","","пирожное",false));
        examListTemp.add(new Exam("расстояние","растаяние","","расстояние",false));
        examListTemp.add(new Exam("ракета","рокета","","ракета",false));
        examListTemp.add(new Exam("растение","ростение","","растение",false));
        examListTemp.add(new Exam("рекорд","рикорд","","рекорд",false));
        examListTemp.add(new Exam("сначала","сначало","","сначала",false));
        examListTemp.add(new Exam("салют","солют","","салют",false));
        examListTemp.add(new Exam("солома","салома","","солома",false));
        examListTemp.add(new Exam("сеять","сеить","","сеять",false));
        examListTemp.add(new Exam("сверкать","свиркать","","сверкать",false));
        examListTemp.add(new Exam("север","севир","","север",false));
        examListTemp.add(new Exam("строить","строеть","","строить",false));
        examListTemp.add(new Exam("солдат","салдат","","солдат",false));
        examListTemp.add(new Exam("счастье","счасте","","счастье",false));
        examListTemp.add(new Exam("самолет","сомолет","","самолет",false));
        examListTemp.add(new Exam("слышать","слышить","","слышать",false));
        examListTemp.add(new Exam("соловей","салавей","","соловей",false));
        examListTemp.add(new Exam("сирень","серень","","сирень",false));
        examListTemp.add(new Exam("столица","сталица","","столица",false));
        examListTemp.add(new Exam("ставить","ставеть","","ставить",false));
        examListTemp.add(new Exam("серпантин","сирпантин","","серпантин",false));
        examListTemp.add(new Exam("специальный","спициальный","","специальный",false));
        examListTemp.add(new Exam("сарай","сорай","","сарай",false));
        examListTemp.add(new Exam("слушать","слушеть","","слушать",false));
        examListTemp.add(new Exam("сорок","сорак","","сорок",false));
        examListTemp.add(new Exam("территория","теретория","","территория",false));
        examListTemp.add(new Exam("трактор","трактар","","трактор",false));
        examListTemp.add(new Exam("тяжелый","тежелый","","тяжелый",false));
        examListTemp.add(new Exam("трамвай","тромвай","","трамвай",false));
        examListTemp.add(new Exam("топор","тапор","","топор",false));
        examListTemp.add(new Exam("теперь","типерь","","теперь",false));
        examListTemp.add(new Exam("тренировка","тренеровка","","тренировка",false));
        examListTemp.add(new Exam("таять","таить","","таять",false));
        examListTemp.add(new Exam("товарищ","таварищ","","товарищ",false));
        examListTemp.add(new Exam("учитель","учитиль","","учитель",false));
        examListTemp.add(new Exam("ужин","ужен","","ужин",false));
        examListTemp.add(new Exam("улица","улеца","","улица",false));
        examListTemp.add(new Exam("фотограф","фатограф","","фотограф",false));
        examListTemp.add(new Exam("фамилия","фомилия","","фамилия",false));
        examListTemp.add(new Exam("фотоаппарат","фатаапарат","","фотоаппарат",false));
        examListTemp.add(new Exam("футбол","фубол","","футбол",false));
        examListTemp.add(new Exam("фиолетовый","феолетовый","","фиолетовый",false));
        examListTemp.add(new Exam("фотография","фатаграфия","","фотография",false));
        examListTemp.add(new Exam("фломастер","фламастер","","фломастер",false));
        examListTemp.add(new Exam("февраль","фивраль","","февраль",false));
        examListTemp.add(new Exam("цыплёнок","ципленок","","цыплёнок",false));
        examListTemp.add(new Exam("четыре","читыре","","четыре",false));
        examListTemp.add(new Exam("чёрный","чорный","","чёрный",false));
        examListTemp.add(new Exam("чувство","чуство","","чувство",false));
        examListTemp.add(new Exam("человек","чиловек","","человек",false));
        examListTemp.add(new Exam("чемпион","чемпеон","","чемпион",false));
        examListTemp.add(new Exam("шофёр","шафер","","шофёр",false));
        examListTemp.add(new Exam("шоколад","шакалад","","шоколад",false));
        examListTemp.add(new Exam("шелест","шелист","","шелест",false));
        examListTemp.add(new Exam("шоссе","шассе","","шоссе",false));
        examListTemp.add(new Exam("шёлк","шолк","","шёлк",false));
        examListTemp.add(new Exam("экскурсия","эскурсия","","экскурсия",false));
        examListTemp.add(new Exam("экскурсовод","эскурсовод","","экскурсовод",false));
        examListTemp.add(new Exam("яблоко","яблако","","яблоко",false));
        examListTemp.add(new Exam("язык","езык","","язык",false));

        return examListTemp;

    }

    public static String redirectExaminations(Model model, HttpServletRequest request, TypeExam typeExam, String path, List<Exam> examList){

        boolean goodJobBn = (request.getParameter("goodjob_bn") != null );
        boolean helper = (request.getParameter("lie_bn") != null);
        boolean checkBn = (request.getParameter("check_bn") != null || goodJobBn || helper) && examList.size() >= SIZE_QUASTION;

        log.info("get All examinations...{}", path);

        if (!checkBn) {
            examList.clear();
            if (typeExam.equals(TypeExam.MATH))
                examListMathematic(examList);
            else if (typeExam.equals(TypeExam.ENGL))
                examListEnglish(examList);
            else if (typeExam.equals(TypeExam.RUSS))
                examListRussian(examList);
        } else
            for (int i = 0; i < SIZE_QUASTION; i++) {
                Exam e = examList.get(i);
                if (helper && PASSWORD.equals(request.getParameter("pwd")))
                    e.setResult(e.getResultTrue());
                else {
                    String res = request.getParameter(String.valueOf(i));
                    e.setResult(res);
                }
                e.setError(!e.getResultTrue().equals(e.getResult()));
            }

        int countJob = (int) examList.stream().filter(n -> !n.isError()).count();

        model.addAttribute("exams", examList);
        model.addAttribute("result", checkBn ? "Правильных ответов: " + countJob + "." : "");
        model.addAttribute("goodJobBn", goodJobBn);
        model.addAttribute("jobgood", (checkBn && (countJob==SIZE_QUASTION)) );
        model.addAttribute("resultpicture", DATAPicture());
        return path;
    }

}
