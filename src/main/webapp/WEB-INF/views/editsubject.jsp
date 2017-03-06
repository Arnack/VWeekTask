<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 24.02.17
  Time: 23:58
  To change this template use File | Settings | File Templates.

--%>
<%@ page import="ru.third.inno.task.models.pojo.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:uplayout>
    <jsp:attribute name="header">    </jsp:attribute>
</t:uplayout>
<!-- write your code here -->


<h2>Edit subject</h2>

<c:if test="${not empty requestScope.message}">
    <div class="alert alert-warning">
            ${message}
    </div>
</c:if>

<hr>
<div class="col-md-6 col-xs-12">
    <form action="/editsubject" method="post">

        <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">

        <label for="name">New Name: </label>
        <input class="form-control" type="text" name="name" id="name" value="<%=request.getAttribute("name")%>" placeholder="new name" required>
        <br>
        <label for="description">New Description: </label>
        <input class="form-control" type="text" name="description" id="description" placeholder="description" value="<%=request.getAttribute("description")%>">
        <br>
        <label for="sphere">Subject Sphere</label>
        <input class="form-control" type="text" name="sphere" id="sphere" placeholder="sphere" value="<%=request.getAttribute("sphere")%>">
        <br>

        <input type="submit" value="Send" class="btn btn-md btn-primary">
    </form>
</div>
<hr>



<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>

