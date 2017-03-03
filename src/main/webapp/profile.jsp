<%@ page import="ru.third.inno.task.models.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 25.02.17
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:uplayout>
    <jsp:attribute name="header">    </jsp:attribute>
</t:uplayout>
<!-- write your code here -->

<h2>Hello, <%=session.getAttribute("name") %>! </h2>

<%
    boolean isAdmin = (session.getAttribute("role").toString().intern() == "admin".intern());
%>

<hr>
<div class="row">
<div class="col-md-6 col-xs-12">
    <ul>
        <li>Name: <%=session.getAttribute("name") %> </li>
        <li>Contact Information: <%=request.getAttribute("description") %></li>
    </ul>

<hr>

    <h3>You can update you profile</h3>

    <form action="/profile" method="post">

        <input type="hidden" name="id" value="<%=session.getAttribute("id")%>">

        <label for="description">e-mail:</label>
        <input class="form-control" type="text" name="description" id="description" placeholder="password" value="<%=request.getAttribute("description")%>">
<br>
        <label for="password">password: </label>
        <input class="form-control" type="password" name="password" id="password" placeholder="password" required>
<br>
        <input type="submit" value="Send" class="btn btn-md btn-primary">
    </form>

</div>

    <% if (isAdmin){ %>
    <div class="col-md-6 col-sm-12 list">

        <div id="profile-collapse" class="child-center btn-default" data-toggle="collapse" data-target="#demo">Settings</div>
        <div id="demo" class="collapse">
            <h4>Set email notifications</h4>
            <div class="profile-button-wrapper">

            </div>
        </div>

    </div>

    <% } %>


</div>


<hr>



<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>
