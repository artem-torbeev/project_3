$(document).ready(function ($) {

    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/admin",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
            $.each(data, function (key, value) {
                data += '<tr>';
                data += '<td>' + value.id+'</td>';
                data += '<td>' + value.role.map(role=>role.role)+'</td>';
                data += '<td>' + value.username+'</td>';
                data += '<td>' + value.password+'</td>';
                data += '<td>' + value.email+'</td>';
                data += '<td><button type="button" class="btn btn-primary"data-toggle="modal" data-target="#myModal"> Edit </button></td>';
                data += '<tr>';
            });
            $('#table-user .userList').append(data);
            console.log("Success: ", data);
            // setTimeout(() => alert('Прием...'), 1000);
        }

    });


    $("#addBtn").click(function (e) {

        e.preventDefault();

        var user = {
            "email": $("#addEmail").val(),
            "login": $("#addLogin").val(),
            "password": $("#addPassword").val(),
            "role": $("#addRole").val()
        };
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/admin/create",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(user),
            dataType: 'json'
        });
        // setTimeout(() => alert('ОТправка...'), 1000);
        $("#creat")[0].reset();
    });

    $("#editBtn").click(function (e) {

        e.preventDefault();

        var user = {
            "id": $("#editId").val(),
            "email": $("#editEmail").val(),
            "login": $("#editLogin").val(),
            "password": $("#editPassword").val(),
            "role": $("#editRole").val()
        };
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/admin/edit",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(user),
            dataType: 'json'
        });
        // setTimeout(() => alert('ОТправка...'), 1000);
        $("#creat")[0].reset();
    });
});

