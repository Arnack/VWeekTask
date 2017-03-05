<%@ page import="ru.third.inno.task.models.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 24.02.17
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:uplayout>
    <jsp:attribute name="header">    </jsp:attribute>
</t:uplayout>
<!-- write your code here -->
<%
    User user = (User)request.getAttribute("user");
%>



<h2>Edit user <%=user.getLogin() %> <small>with id <%= request.getParameter("id") %></small></h2>

<hr>

<div class="col-md-6 col-xs-12">
<form action="/edituser" method="post">

    <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">

    <%--<label for="login">New Name: </label>--%>
    <input class="form-control" type="hidden" name="login" id="login" value="<%=request.getAttribute("login")%>" placeholder="new name" required>
    <br>

    <input class="form-control" type="hidden" value="<%=request.getAttribute("password")%>" name="password" id="password">
    <br>
    <label for="description">New Description: </label>
    <input class="form-control" type="text" name="description" id="description" placeholder="password" value="<%=request.getAttribute("description")%>">
<br>
    <label for="role">User Status</label>
    <select class="form-control" name="role" id="role">
        <option value="user">user</option>
        <option value="admin">admin</option>
    </select><br>

    <input type="submit" value="Send" class="btn btn-md btn-primary">
</form>
</div>
<hr>



<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>
