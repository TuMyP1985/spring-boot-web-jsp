const userAjaxUrl = "admin/usersajax/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {"data": "id"},
                {"data": "name"},
                {
                    // "defaultContent": "<a className='delete'><span className='fa fa-remove'></span></a>"
                    // "defaultContent": "<button className='delete'><span className='fa fa-remove'></span></button>"

                     // "defaultContent": "Delete"
                    "defaultContent": "<button className='delete'>Удалить</button>"
                    ,
                    "orderable": false

                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});