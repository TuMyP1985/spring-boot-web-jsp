let form;


function delelerow(stringget, stringredirect) {

    if (confirm("Точно удаляем запись?")) {
        $.get(stringget, async function (data) {
            successNoty("Удалено.");
            await new Promise(resolve => setTimeout(resolve, 1000));
            window.location.replace(stringredirect);
        })
    }
}



var f_nm = 1; // flag to toggle the sorting order
function getSort() {
    f_nm *= -1; // toggle the sorting order
    var n = $(this).prevAll().length;
    sortTable(f_nm, n);
    // //почему то эта сортировка не работает для <th id="sl", прописывал напрямую <th onClick="getSort()">
    // $("#sl").click(function(){
    //     f_sl *= -1; // toggle the sorting order
    //     var n = $(this).prevAll().length;
    //     sortTable(f_sl,n);
    // });
}
    // sortTable(f,n)
    // f : 1 ascending order, -1 descending order
    // n : n-th child(    <td>) of <tr>
function sortTable(f, n) {
    var rows = $('#datatableforsort tbody  tr').get();

    rows.sort(function (a, b) {

        var A = getVal(a);
        var B = getVal(b);

        if (A < B) {
            return -1 * f;
        }
        if (A > B) {
            return 1 * f;
        }
        return 0;
    });

    function getVal(elm) {
        var v = $(elm).children('td').eq(n).text().toUpperCase();
        if ($.isNumeric(v)) {
            v = parseInt(v, 10);
        }
        return v;
    }

    $.each(rows, function (index, row) {
        $('#datatableforsort').children('tbody').append(row);
    });
}

function onChangeCheckBoxVisible(){
    document.getElementById("datatable").hidden = !(document.getElementById("seltable").checked);
    document.getElementById("curve_chart").hidden = !(document.getElementById("selgraph").checked);
    document.getElementById("datatableanalitics").hidden = !(document.getElementById("selanalitictable").checked);
}

function onChangeVisible_Accounts(){
    var check = (document.getElementById("outcheck").checked);
    document.getElementById("output_bn").hidden = !check;
    document.getElementById("output_th").hidden = !check;

    let blocks = document.getElementsByClassName( "output_td" );
    for( let i = 0; i < blocks.length; i++){
        blocks[i].hidden = !check;
    }
}

function outJson_Accounts(itIsJson) {
    var dataout = "";
    var table = document.getElementById("datatable");
    // document.getElementById("outcheck").checked = false;
    for (var i = 1; i < table.rows.length; i++) {
        var tableRow = table.rows[i];
        if (!itIsJson || tableRow.cells[0].childNodes[0].checked) {
            dataout = dataout + tableRow.id + ";";
        }
    }
    if (itIsJson)
        $.get("outJsonInFile/", {text: dataout}, function (data) {
            document.write(data);
        })
    else
        $.get("reportpdf/", {text: dataout}, function (data) {
            window.write(data);
        })

};


function makeEditable(datatableApi) {
    ctx.datatableApi = datatableApi;
    form = $('#detailsForm');
    $(".delete").click(function () {
        if (confirm('Are you sure?')) {
            deleteRow($(this).closest('tr').attr("iddel"));
        }
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function add() {
    form.find(":input").val("");
    $("#editRow").modal();
}

function deleteRow(id) {

    $.ajax({
        url: ctx.ajaxUrl + id,
        type: "DELETE"
    }).done(function () {
        updateTable();
        successNoty("Deleted");
    });
}

function updateTable() {
    ctx.datatableApi.clear();
    $.get(ctx.ajaxUrl, function (data) {
        ctx.datatableApi.clear().rows.add(data).draw();
    });
}

function save() {
    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
        successNoty("Saved");
    });
}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + text,
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;Error status: " + jqXHR.status,
        type: "error",
        layout: "bottomRight"
    });
    failedNote.show()
}