package ru.java.springbootwebjsp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.java.springbootwebjsp.model.paper.DataRate;
import ru.java.springbootwebjsp.model.paper.RichPaper;
import ru.java.springbootwebjsp.model.enums.TypeRichPaper;
import ru.java.springbootwebjsp.util.ExecutValueUtil;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static ru.java.springbootwebjsp.util.ExecutValueUtil.*;
import static ru.java.springbootwebjsp.util.Util.formatDigital;

@Controller
@RequestMapping(value = "/richvalues")
public class RishValuesController {

    @GetMapping()
    public String getrichvalues(HttpServletRequest request, Model model) {

        String analyticsmax = "";
        String analyticsmin = "";

        String listname = request.getParameter("listname");
        String selectedtable = request.getParameter("seltable");
        String selectedgraph = request.getParameter("selgraph");
        String selectedanalitictable = request.getParameter("selanalitictable");

        String dayselect = request.getParameter("dayselect");

        if (dayselect!=null) {
            int day = 0;
            try {
                day = Integer.parseInt(request.getParameter("dayselect"));
            }catch (Exception e){}
            if (day != getDay_select() && day != 0) {
                setDay_select(day);
                clearDataRates();
            }
        }

        //нажимаем на "Посмотреть" - меняем "бумаг (тек.)" на то, что указано и показываем таблицу и график
        if (request.getParameter("anotherselectedpaper")!=null) {
            listname = request.getParameter("anotherselectedpaper");
            selectedtable = "on";
            selectedgraph = "on";
        }

        //ищем минимум/максимум по дельте и выводим на экран (по кнопке Аналитика)
        if (request.getParameter("analytics")!=null) {
            Map<TypeRichPaper, RichPaper> result = findBestRichPaper();
            RichPaper richPaperMaxTemplate = result.get(TypeRichPaper.MAX);
            DataRate dataMax = richPaperMaxTemplate.getDataRates().get(0);
            listname = richPaperMaxTemplate.getName();


            analyticsmax = "Рост (максимум) % за один из дней: " + richPaperMaxTemplate.getName() + " (" + richPaperMaxTemplate.getDescription() + "); " +
                    formatDigital(dataMax.getDelta(),2) + "(руб); " + dataMax.getDeltaPerсent() + "(%); " + dataMax.getDate() + "(дата);";

            RichPaper richPaperMinTemplate = result.get(TypeRichPaper.MIN);
            DataRate dataMin = richPaperMinTemplate.getDataRates().get(0);
            analyticsmin = "Падение (максимум) % за один из дней: " + richPaperMinTemplate.getName() + " (" + richPaperMinTemplate.getDescription() + "); " +
                    formatDigital(dataMin.getDelta(),2) + "(руб); " + dataMin.getDeltaPerсent() + "(%); " + dataMin.getDate() + "(дата);";
        }

        //таблицу с курсами для текущей ЦБ
        if (listname!=null){
            RichPaper paper = papermap.get(listname);
            fillIfEmpty(paper);

            model.addAttribute("paperlistdata", paper.getDataRates());
        }

        model.addAttribute("analiticpaperlistdata", analiticpaperlistdata());
        model.addAttribute("DAY_FOR_ANALITIC_TABLE", getDAY_FOR_ANALITIC_TABLE());
        model.addAttribute("selectedpaper", listname);
        model.addAttribute("updatedayselect", getDay_select());
        model.addAttribute("selectedtable", "on".equals(selectedtable));
        model.addAttribute("selectedgraph", "on".equals(selectedgraph));
        model.addAttribute("selectedanalitictable", "on".equals(selectedanalitictable));
        model.addAttribute("countvalue", papermap.values().size());
        model.addAttribute("lists", papermap.values());
        model.addAttribute("analyticsmax", analyticsmax);
        model.addAttribute("analyticsmin", analyticsmin);
        return "richvalues";
    }

}
