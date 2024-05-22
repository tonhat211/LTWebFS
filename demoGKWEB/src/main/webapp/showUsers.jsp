<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/24/2024
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/2.0.5/css/dataTables.dataTables.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/3.0.1/css/buttons.dataTables.min.css">
    <script src="https://cdn.datatables.net/2.0.5/js/dataTables.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.0.2/js/dataTables.buttons.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.0.1/js/buttons.jqueryui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.html5.js"></script>
</head>
<body>
<h1>Danh sách Users</h1>
<table id="users" class="display" style="width:100%">
    <thead>
    <tr>
        <th>Username</th>
        <th>Password</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Username</th>
        <th>Password</th>
    </tr>
    </tfoot>
</table>
<script>
    $(document).ready(function () {
        var table = $('#users').DataTable({
            "ajax": {
                "url": "showUserServlet",
                "dataSrc": "",
            },
            layout: {
                topStart: {
                    buttons: [
                        {
                            extend: 'excelHtml5',
                            customize: function (xlsx) {
                                var sheet = xlsx.xl.worksheets['sheet1.xml'];

                                sheet.querySelectorAll('row c[r^="C"]').forEach((el) => {
                                    el.setAttribute('s', '2');
                                });
                            }
                        }
                    ]
                }
            },
            "columns": [
                {"data": "username"},
                {"data": "password"},
                {
                    "data": null,
                    "defaultContent": "<button>Delete</button>"
                }
            ],
        });

        // Sự kiện khi nút "Xóa" được nhấn
        $('#users tbody').on('click', 'button', function () {
            var data = table.row($(this).parents('tr')).data();
            var username = data.username;

            $.ajax({
                type: "POST",
                url: "deleteServlet",
                data: {username: username},
                success: function (response) {
                    // Hiển thị thông báo thành công
                    alert(response);
                    table.ajax.reload();
                },
                error: function (xhr, status, error) {
                    // Hiển thị thông báo lỗi nếu có
                    alert("Error: " + error);
                }
            });
        });
    });
</script>
</body>
</html>
