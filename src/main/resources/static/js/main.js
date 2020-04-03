jQuery(document).ready(function ($) {

    //заполнение таблицы
    function ajaxGet() {
        $.ajax({
            type: "GET",
            url: "/admin/all",
            cache: false,
            success: function (result) {

                var tr = [];
                for (var i = 0; i < result.length; i++) {
                    tr.push('<tr>');
                    tr.push('<td>' + result[i].id + '</td>');
                    tr.push('<td>' + result[i].role.map(role => role.role) + '</td>');
                    tr.push('<td>' + result[i].username + '</td>');
                    tr.push('<td>' + result[i].password + '</td>');
                    tr.push('<td>' + result[i].email + '</td>');
                    tr.push('<td><button data-id=' + result[i].id + ' type="button" class="btn btn-primary" data-toggle="modal"  ' +
                        'data-target="#editModal">Edit</button></td>');
                    tr.push('</tr>');
                }
                //заменит содержимое тэга
                $("#table-user .userList").html($(tr.join('')));
            },

        });
    }

    ajaxGet();

    //добавления User
    jQuery("#addBtn").click(function (e) {

        e.preventDefault();

        var user = {
            "email": $("#addEmail").val(),
            "username": $("#addLogin").val(),
            "password": $("#addPassword").val(),
            "role": $("#addRole").val()
        };
        $.ajax({
            type: 'POST',
            url: "/admin/create",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(user),
            dataType: 'json'
        });
        // setTimeout(() => alert('ОТправка...'), 1000);
        $("#creat")[0].reset();

        setTimeout(() => ajaxGet(), 1000);
    });

    //изменения user
    jQuery("#editBtn").click(function (e) {

        e.preventDefault();

        var user = {
            "id": $("#editId").val(),
            "email": $("#editEmail").val(),
            "username": $("#editLogin").val(),
            "password": $("#editPassword").val(),
            "role": $("#editRole").val()
        };
        $.ajax({
            type: 'PUT',
            url: "/admin/edit",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(user),
            dataType: 'json'
        });

        $("#edit")[0].reset();

        setTimeout(() => ajaxGet(), 1000);
    });

     //заполнения модального окна
    jQuery('#editModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('id');
        // console.log(id);
        $.ajax({
            type: 'GET',
            url: '/admin/' + id,
            success: function (data) {
                $('#editModal #editId').val(data.id);
                $('#editModal #editEmail').val(data.email);
                $('#editModal #editLogin').val(data.username);
                $('#editModal #editPassword').val(data.password);
                $('#editModal #editRole').val(data.role.map(role => role.role));
            }
        })
    });

});
