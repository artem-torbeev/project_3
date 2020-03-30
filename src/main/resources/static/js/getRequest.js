 $(document).ready(function ($) {

        $("#getALL").click(function (event) {

            event.preventDefault();

            console.log("answer: ", 1);

            ajaxGet();

        });

        // DO GET
        function ajaxGet() {
            $.ajax({
                type: "GET",
                url: "/all",

                success: function (result) {

                    console.log("answer: ", 2);
                    $.each(result.data, function (i, user) {
                        var user = "User id  "
                            + user.id
                            + ", Name  = " + user.username
                            + ", Email  = " + user.email
                            + ", Password  = " + user.password
                            + ", Role  = " + user.role.map(role => role.role)
                            + "<br>";
                        $("#getList").append(user)
                    });
                    console.log("answer: ", 3);
                },

            });
        }
    });