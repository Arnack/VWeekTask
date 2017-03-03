<%@tag description="uplayout" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<html>
<head>
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    -->
    <script src="https://use.fontawesome.com/7add4cc9c2.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <title><%request.getPathInfo(); %></title>

    <link rel="stylesheet" href="../resources/static/css/bootstrap.css">

    <style>
        <% if (session.getAttribute("night") == null || session.getAttribute("night").equals("day")){ %>
        <%@include file='../resources/static/css/bootstrap.css'%>
        header{
            border-bottom: 2px solid steelblue;
            position: fixed;
            box-shadow: 0 5px 2px -2px lightgray;
            background-color: white;
            width: 100%;
        }

        .icon-bar{
            background: steelblue;
        }

        footer{
            padding: 20px 0px 20px 0px;
            margin-top: 20px;
            color: snow;
            background-color: #2b669a;
            border-top: 1px solid steelblue;
            flex: 0 0 auto;
        }

        div.list{
            border-left: 3px solid forestgreen;
            padding-bottom: 14px;
            background: #fbfbfb;
        }


        <% }else{ %>
        <%@include file='../resources/static/css/night-theme.css'%>
        header{
            position: fixed;
            width: 100%;
            border-bottom: 2px solid gainsboro;
            background-color: #272b30;
        }

        footer{
            padding: 20px 0px 20px 0px;
            margin-top: 20px;
            border-top: 2px solid gainsboro;
        }

        .form-control{
            color: #c1ccd4 !important;
            background-color: #272b30 !important;
        }

        tr:hover>td.info{
            background-color: #1b6d85 !important;
        }

        .icon-bar{
            background: gray;
        }
        <% } %>

        html,body{
            min-height: 100%;
            display: flex;
            flex-direction: column;
            /*font-family: 'Roboto', sans-serif;*/
        }

        div#body{
            padding-top: 75px;
        }

        div#main-content{
            min-height: 482px;
        }

        .bold{
            font-weight: bold;
        }

        .semi-bold{
            font-weight: 400;
            font-family: 'Roboto', sans-serif;
        }

        .right{
            float: right;
        }

        .left{
            float: left;
        }

        h1, h2, h3{
            text-align: center;
        }


        .child-center{
            text-align: center;
        }

        #profile-collapse{
            height: 50px;
            width: 100%;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            line-height: 50px;
            border: 1px solid gray;
        }
        #profile-collapse:hover{
            opacity: .7;
        }


    </style>

</head>
<body>
<header>
    <div class="container"> <div class="navbar-header">
    <button aria-controls="bs-navbar" aria-expanded="false" class="collapsed navbar-toggle" data-target="#bs-navbar" data-toggle="collapse" type="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button> <a href="../" class="navbar-brand">IS Self Development</a>
    </div>


        <nav class="collapse navbar-collapse" id="bs-navbar">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="/index">Home</a>
                </li>
                <li class="active">
                    <a href="/users">Users</a>
                </li>
                <li>
                    <a href="/subjects">Subjects</a>
                </li>
                <li>
                    <a href="/tasks">Tasks</a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <% if (session.getAttribute("name") != null){ %>
                <li>
                        <a class="semi-bold" href="/profile">
                            Hi, <%= session.getAttribute("name") %>
                        </a>
                </li>
                <li><a href="/logout">Log out</a></li>
                <% } else { %>
                <li><a href="/login">Log In</a></li>
                <li><a href="/register">Sign Up</a></li>
                <% } %>

                    <li>
                        <% if (session.getAttribute("night") == null || session.getAttribute("night").equals("day")){ %>
                        <a href="" class="night-day">
                            <i class="fa fa-moon-o" aria-hidden="true"></i>
                        </a>
                        <% } else { %>
                        <!--
                        /togglenight?mode=night
                        /togglenight?mode=day -->
                            <a href="" class="day-night">
                                <i class="fa fa-sun-o" aria-hidden="true"></i>
                            </a>
                        <% } %>
                    </li>
            </ul>
        </nav>
    </div>
</header>
<div id="pageheader">
    <jsp:invoke fragment="header"/>
</div>
<div id="body">
    <div class="container">
        <jsp:doBody/>
    </div>
</div>
<div id="main-content" class="container">