<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 23.02.17
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:uplayout>
    <jsp:attribute name="header">    </jsp:attribute>
</t:uplayout>
<!-- write your code here -->

<% HttpSession sess = request.getSession(false);

%>

<% if (session.getAttribute("name") != null){ %>
<h1>Hello, <%= session.getAttribute("name")%>!</h1>
    <% }else{ %>
 <h1>
     Hi, there!<br>
    <small>It's an Information System for self developing</small>
 </h1>
<% } %>

<% if (session.getAttribute("name") == null){ %>
<div class="jumbotron">
    <p class="lead">
        If you already have an account you can
        <a class="btn btn-primary" href="/login" role="button">Log in</a>
    </p>
    <hr class="my-4">
    <p class="lead">
        Otherwise you can <a class="btn btn-success" href="/register" role="button">Register</a>
        It's free!
    </p>
</div>
<% }else{ %>
<div class="well">
<h4>Some useful links for you:</h4>

<ul class="">

    <li class="">
        <a href="/users">List of users</a>
    </li>
    <li>
        <a href="/subjects">List of subjects</a>
    </li>
    <li>
        <a href="/tasks">List of tasks</a>
    </li>
</ul>
</div>
<% } %>

<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>

