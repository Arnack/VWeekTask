<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 23.02.17
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>


<t:uplayout>
    <jsp:attribute name="header">    </jsp:attribute>
</t:uplayout>
<!-- write your code here -->

<%
    boolean isAdmin = (session.getAttribute("role").toString().intern() == "admin".intern());
%>

<h2>List of Users</h2><br>
<c:if test="${not empty requestScope.message}">
    <div class="alert alert-warning">
            ${message}
    </div>
</c:if><br>
<table class="table-striped table-condensed table-hover" style="margin-left: 20%;">
<c:forEach items="${users}" var="user">
    <tr>
        <% if (isAdmin){ %>
        <td ><c:out value="${user.id}"></c:out></td>
        <% } %>
        <td class="info"><c:out value="${user.login}"></c:out></td>
        <% if (isAdmin){ %> <td><c:out value="${user.description}"></c:out></td> <% } %>
        <td><c:out value="${user.role}"></c:out></td>

        <% if (isAdmin){ %>
        <td class="danger"><a href="/deleteuser?id=<c:out value="${user.id}"/>">Delete</a> </td>
        <td class="warning"><a href="/edituser?id=<c:out value="${user.id}"/>">Edit</a> </td>
        <% } %>
    </tr>
</c:forEach>
</table>

<hr>
<% if (isAdmin){ %>
<a href="/newuser" class="btn btn-default btn-lg">Add new user</a>
<% } %>

<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>

