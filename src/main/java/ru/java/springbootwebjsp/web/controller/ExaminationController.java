package ru.java.springbootwebjsp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.java.springbootwebjsp.model.Exam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/examination")
public class ExaminationController {

    private static final Logger log = LoggerFactory.getLogger(ExaminationController.class);
    private List<Exam> examList = new ArrayList<>();


    @GetMapping()
    public String getAll(HttpServletRequest request, Model model)  {
        return redirectExaminations(model, request);
    }

    @PostMapping()
    public String update(HttpServletRequest request, Model model)  {
        return redirectExaminations(model, request);
    }

    private int gerInt(){
        return 1+(int) (Math.random()*9);
    }

    private String getPicture(){
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

        return pictureList.get(gerInt());
    }

    public String redirectExaminations(Model model, HttpServletRequest request){
        String result = "";
        boolean goodJobBn = (request.getParameter("goodjob_bn") != null);
        boolean checkBn = (request.getParameter("check_bn") != null || goodJobBn) && examList.size() >= 10;
        if (!checkBn)
            examList.clear();
        for (int i = 0; i < 10; i++)
            if (checkBn) {
                Exam e = examList.get(i);
                e.setResult(String.valueOf(Integer.parseInt(request.getParameter(String.valueOf(i)))));
//                e.setError(e.getVal1()*e.getVal2()!=e.getResult());
            }
//        else
//                examList.add(new Exam(gerInt(), gerInt(), 0, false));

        int countJob = (int) examList.stream().filter(n->!n.isError()).count();

        if (checkBn)
            result = "Правильных ответов: " + countJob + ".";
        log.info("getAll examinations...");
        model.addAttribute("exams", examList);
        model.addAttribute("result", result);
        model.addAttribute("goodJobBn", goodJobBn);
        model.addAttribute("jobgood", (checkBn && (countJob==10)) );
//        model.addAttribute("jobgood", (checkBn && (countJob>2)) ); //DEBUG
        model.addAttribute("resultpicture", getPicture());
        return "/examination";
    }


}