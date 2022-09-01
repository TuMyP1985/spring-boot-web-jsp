package ru.java.springbootwebjsp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.java.springbootwebjsp.model.Exam;
import ru.java.springbootwebjsp.model.enums.TypeExam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ru.java.springbootwebjsp.util.UtilExam.redirectExaminations;

@Controller
@RequestMapping(value = "/exammathematic")
public class ExamMathematicController {

    private List<Exam> examList = new ArrayList<>();

    @GetMapping()
    public String getAll(HttpServletRequest request, Model model)  {
        return redirectExaminations(model, request, TypeExam.MATH, "/exammathematic", examList);
    }

    @PostMapping()
    public String update(HttpServletRequest request, Model model)  {
        return redirectExaminations(model, request, TypeExam.MATH, "/exammathematic", examList);
    }

}