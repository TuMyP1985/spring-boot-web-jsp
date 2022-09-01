package ru.java.springbootwebjsp.util;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import ru.java.springbootwebjsp.model.paper.RichPaper;

import java.awt.print.Paper;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static ru.java.springbootwebjsp.TestData.paperTest;
import static ru.java.springbootwebjsp.util.ExecutValueUtil.*;

class ExecutValueUtilTest {


    @BeforeEach
    public void setUp() {
        papermap.clear();
        instanceExecutValue();
    }

    @Test
    void instanceExecutValueTest() {
        assertEquals(papermap.size(), 9);
    }

    @Test
    void fillDataRatesTest() {
        fillDataRates(paperTest);
        //убедимся что после заполнения в последнем элементе будет текущая дата минус 1 день
        assertTrue(paperTest.getDataRates().size()!=0 &&
                paperTest.getDataRates().get(paperTest.getDataRates().size()-1)
                        .getDate().equals(LocalDate.now().minusDays(1)));
    }

    @Test
    void priceOnDateTest() {
        assertEquals(priceOnDate("SBER", LocalDate.of(2021, 8, 10)), 330.11, 0);
    }
}