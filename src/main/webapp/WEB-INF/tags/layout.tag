<%@tag description="Layout" pageEncoding="UTF-8"%>
<%@attribute login="header" fragment="true" %>
<%@attribute login="footer" fragment="true" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <style>
        html,body{
            min-height: 100%;
            display: flex;
            flex-direction: column;
        }
        header{
            border-bottom: 2px solid steelblue;
        }
        footer{
            padding: 20px 0px 20px 0px;
            margin-top: 20px;
            color: snow;
            background-color: #2b669a;
            border-top: 1px solid steelblue;
            flex: 0 0 auto;
        }

        div#body{
            padding-top: 30px;
        }


    </style>

</head>
<body>
<header>
    <div class="container"> <div class="navbar-header"> <button aria-controls="bs-navbar" aria-expanded="false" class="collapsed navbar-toggle" data-target="#bs-navbar" data-toggle="collapse" type="button"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button> <a href="../" class="navbar-brand">IS Self Development</a> </div>
        <nav class="collapse navbar-collapse" id="bs-navbar">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="/index">Home</a>
                </li>
                <li>
                    <a href="/subjects">Subjects</a>
                </li>
                <li>
                    <a href="/components/">Tasks</a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/login">Log In</a></li>
                <li><a href="/register">Sign Up</a></li>
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
<div id="pagefooter">
    <div class="container"><jsp:invoke fragment="footer"/></div>

</div>
    <footer>
        <div class="container">
            <p id="copyright">&copy; At Once has been created</p>
        </div>

    </footer>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>
