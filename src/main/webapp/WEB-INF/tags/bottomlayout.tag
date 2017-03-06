<%@tag description="bottomayout" pageEncoding="UTF-8"%>
<%@attribute name="footer" fragment="true" %>
</div>
<div id="pagefooter">
    <div class="container"><jsp:invoke fragment="footer"/></div>

</div>
<footer>
    <div class="container">
        <p id="copyright">&copy; At Once has been created</p>
    </div>

</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {

        $(document).on('input', '#login-reg', function () {
            var login = $('#login-reg').val();

            $.ajax({
                url : 'loginchecker',
                data : {
                    login : $('#login-reg').val()
                },
                success : function(responseText) {
                    $('#login-info').html(responseText);
                }
            });
        });

        $(document).on('click', '.day-night', function () {
            $.ajax({
                url : 'togglenight',
                data : {
                    mode : "day"
                },
                success : function(responseText) {
                    //$('#login-info').html(responseText);
                }
            });
        });

        $(document).on('click', '.night-day', function () {
            $.ajax({
                url : 'togglenight',
                data : {
                    mode : "night"
                },
                success : function(responseText) {
                }
            });
        });

        //$(document).on('load', '.profile-button-wrapper', function () {
        $('#profile-collapse').click( function () {

            var enableText = "Mail notifications are disabled " +
                "<button id='enable-notifications' class='btn btn-success' onclick='enc()'>Enable</button>";
            var disableText = "Mail notifications are enabled " +
                "<button id='disable-notifications' class='btn btn-danger' onclick='dnc()'>Disable</button>";
            $.ajax({
                url : 'mailchecker',
                type: "GET",
                data : {
                    send_mail : "val"
                },
                success : function(responseText) {

                    if(responseText == "0"){
                        $(".profile-button-wrapper").html(enableText);
                    } else {
                        $(".profile-button-wrapper").html(disableText);
                    }
                }
            });
        });

        $('[data-toggle="tooltip"]').tooltip();

        $('#profile-form').submit(
            function(e) {
                e.preventDefault();
                e.stopImmediatePropagation();

                var enableText = "<div class='alert alert-success  alert-dismissable'>"
                    + "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
                    + "Profile succeessfully changed, <br> please relogin to see them" + "</div>";

                var disableText = "<div class='alert alert-danger  alert-dismissable'>"
                    + "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
                    + "Something gone wrong <br> Have fun!!!" + "</div>";
                $.ajax({
                    url : 'profile',
                    type: "POST",
                    data : {
                        id : $('#id').val(),
                        namex: $('#namex').val(),
                        description: $('#description').val(),
                        password: $('#password').val(),
                    },
                    success : function(responseText) {
                        if(responseText.toString() == "1"){
                            $("#alert-handler").html(enableText);
                        } else {
                            $("#alert-handler").html(disableText);
                        }
                    }
                });

            }
        );

    });

    function dnc() {
        var sText = "<div class='alert alert-success'> " + "Successfully disabled" + "</div>" +
            "<button id='enable-notifications' class='btn btn-success' onclick='enc()'>Enable</button>";
        var wText = "<div class='alert alert-danger'> " + "Something wrong" + "</div>";
        //
        $.ajax({
            url : 'mailchecker',
            type: "POST",
            data : {
                value : "0"
            },
            success : function(responseText) {

                if(responseText == "1"){
                    $(".profile-button-wrapper").html(sText);
                } else {
                    $(".profile-button-wrapper").html(wText);
                }
            }
        });
    }

    function enc() {

        var sText = "<div class='alert alert-success'> " + "Successfully enabled" + "</div>" +
            "<button id='disable-notifications' class='btn btn-danger' onclick='dnc()'>Disable</button>";
        var wText = "<div class='alert alert-danger'> " + "Something wrong" + "</div>";
        //"<button id='enable-notifications' class='btn btn-success'>Enable</button>";
        $.ajax({
            url : 'mailchecker',
            type: "POST",
            data : {
                value : "1"
            },
            success : function(responseText) {

                if(responseText == "1"){
                    $(".profile-button-wrapper").html(sText);
                } else {
                    $(".profile-button-wrapper").html(wText);
                }
            }
        });
    }


</script>
</body>
</html>
