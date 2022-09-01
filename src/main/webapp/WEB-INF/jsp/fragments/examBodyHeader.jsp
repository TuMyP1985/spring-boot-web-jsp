<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<p><a class="fa" href="${pageContext.request.contextPath}/examination">Назад к проверке</a></p>
<p><span class="text-body">Тестирование ученика 3 класса, на знание предметной области.</span></p>
<b style="color:#336b2f">Матвей,</b>
давай попробуем решить 10 примеров...
<button name="update_bn" class="btn btn-outline-secondary" type="submit">Обновить данные</button>
<button name="check_bn" class="btn btn-outline-secondary" type="submit">Проверить ответы</button>
<p><input style="color:#2d3da2" type="checkbox" id="examcheck" onchange="visibleElem()">Активировать чит режим
    <u><i>(pass = 27*26-3+56/2-604)</i></u>
<div id="exempassword" hidden="true">
    Password: <input type="password" name="pwd">
    <button name="lie_bn" class="btn btn-outline-secondary" type="submit">Попробовать</button>
</div>
</p>
<p style="color:#35a22d">${result}</p>
<div style=${jobgood?'':'visibility:hidden'}>
    <button name="goodjob_bn" class="btn btn-info mr-1" type="submit">ВСЁ ВЕРНО, ОТПРАВИТЬ ЗАПРОС НА
        ПОЛУЧЕНИЕ ШОКОЛАДКИ!!!
    </button>
</div>
<p><a target="_blank" href=${resultpicture} style=${goodJobBn?'':'visibility:hidden'}>ВОТ ШОКОЛАДКА
    (реальную получишь у папы):</a></p>