<%@ page import="ru.java.springbootwebjsp.model.paper.DataRate" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.springbootwebjsp.ru/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body onload="onload()">

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="resources/js/common.js" defer></script>
<script src="resources/js/drawgraph.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>
<H3 class="p-5" style="color:#0f6674">Ценные бумаги (всего ${countvalue} шт.)
    <a href="https://iss.moex.com/iss/history/engines/stock/markets/shares/boards/TQBR/securities/SBER.json?iss.meta=off&iss.only=history&history.columns=SECID,TRADEDATE,CLOSE&limit=1&from=2021-08-10">API</a>
</H3>



<div class="container">
    <form method="get" action="${pageContext.request.contextPath}/richvalues" class="form-inline">
        <table class="container">
            <td nowrap>
                Анализируемый период (дней):
                <input type="number" value=${updatedayselect} name="dayselect">
                <button class="btn btn-outline-secondary" type="submit">Изменить</button>
            </td>
            <td style="color:#35a22d">
                <h6>Отобразить:</h6>
                <input style="color:#2d3da2" type="checkbox" id="seltable" name="seltable" ${selectedtable ? 'checked' : ''}
                       onchange="onChangeCheckBoxVisible()"> (таблица)
                <input input style="color:#2d3da2" type="checkbox" id="selgraph" name="selgraph" ${selectedgraph ? 'checked' : ''}
                       onchange="onChangeCheckBoxVisible()"> (график)
            </td>
        </table>
        <br>

        <div class="alineGorizont">
            <table style="border-spacing:2px">
                <td>бумага(тек.):</td>
                <td><select id="selectrich" onchange="this.form.submit()" name="listname" class="form-control mx-3">
                    <c:forEach items="${lists}" var="list">
                        <option value=${list.name} ${list.name == selectedpaper ? 'selected' : ''}>${list.description} (${list.name})</option>
                    </c:forEach>
                </select>
                </td>
                <td>
                    <button name="analytics" class="btn btn-info mr-1" type="submit">Аналитика</button>
                </td>

                <td nowrap>
                    <input type="checkbox" id="selanalitictable"
                           name="selanalitictable" ${selectedanalitictable ? 'checked' : ''}
                           onchange="onChangeCheckBoxVisible()"> (аналитика по всем бумагам)
                </td>

            </table>
        </div>
    </form>
    <p style="color:#35a22d">${analyticsmax}</p>
    <p></p>
    <p></p>
    <p style="color:#ff003b">${analyticsmin}</p>
    <p></p>
    <p></p>
    <div class="container">
        <div hidden="true" id="datatableanalitics">
            <table class="table table-bordered">
                <thead title="123123">
                <h4 class="text-info">Аналитика за ${DAY_FOR_ANALITIC_TABLE} дн.</h4>
                <tr>
                    <th>Бумага</th>
                    <th>Изм.всего (курс падение)</th>
                    <th>Изм.всего (курс рост)</th>
                    <th>Посмотреть</th>
                </tr>
                </thead>
                <c:forEach items="${requestScope.analiticpaperlistdata}" var="dataanalitic">
                    <tr data-minus-excess="${(dataanalitic.minusPercentForSort)<0}" align="right">
                        <td>${dataanalitic.richPaper.name}</td>
                        <td>${fn:MyFormatDigital(dataanalitic.minusPercentForSort, 2)}%
                        <td>${fn:MyFormatDigital(dataanalitic.plusPercentForSort, 2)}%
                        <td>
                            <a href="${pageContext.request.contextPath}/richvalues?anotherselectedpaper=${dataanalitic.richPaper.name}"><span
                                    class="fa fa-address-card"></span></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div hidden="true" id="datatable">
            <table border="true" class="table-info">
<%--                <table class="table table-bordered">--%>
                <thead>
                <h4 class="text-info">Данные курсов</h4>
                <tr>
                    <th>Дата</th>
                    <th>Цена (руб.)</th>
                    <th>Изменение (руб.)</th>
                    <th>Изменение (%)</th>
                </tr>
                </thead>
                <c:forEach items="${requestScope.paperlistdata}" var="datarate">
                    <jsp:useBean id="datarate" type="ru.java.springbootwebjsp.model.paper.DataRate"/>
                    <tr align="right">
                        <td>${datarate.date}</td>
                        <td>${fn:MyFormatDigital(datarate.value, 2)}</td>
                        <td>${fn:MyFormatDigital(datarate.delta, 2)}</td>
                        <td>${fn:MyFormatDigital(datarate.deltaPerсent, 2)}%</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<div id="curve_chart" style="width: 1500px; height: 500px"></div>


<jsp:include page="fragments/footer.jsp"/>
</body>
</html>