google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    var nameSelect = (document.getElementById('selectrich'));
    var selectedName = nameSelect.options[nameSelect.selectedIndex].value;

    $.get("jsonResultToJson/", {text: selectedName}, function (dataList) {
        /*
        //a)
        dataList1 = <%=dataList1%>;
        //b
        dataList2 = [['Дата', 'Цена'], ['2022-08-04', 241.07], ['2022-08-05', 241.99], ['2022-08-06', 308.0], ['2022-08-07', 198.0], ['2022-08-08', 177.86]];

        //c
        dataList3 = [];
        dataList3[0] = ['Дата', 'Цена'];
        dataList3[1] = ['2022-08-04', 12.25];
        dataList3[2] = ['2022-08-05', 8.00];
        */

        //d
        dataList4 = [];
        dataList4[0] = ['Дата', 'Цена'];
        for (var i = 0; i < dataList.length; i++) {
            dataList4[i+1] = [dataList[i].date, dataList[i].value];
        }

        if (dataList.length > 0) {
            var data = google.visualization.arrayToDataTable(dataList4);
            var options = {title: 'Цена ценных бумаг за период', curveType: 'function', legend: {position: 'bottom'}};
            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
            chart.draw(data, options);
        }
    });

}

function onload(){
    onChangeCheckBoxVisible();
}