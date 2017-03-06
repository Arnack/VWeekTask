<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 25.02.17
  Time: 21:20
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

<c:if test="${not empty requestScope.message}">
    <div class="alert alert-success">
            ${message}
    </div>
</c:if>

<div class="row">
    <div class="col-md-6 col-sm-12 list">
        <h2>List of Current Tasks</h2><br>
        <table class="table-striped table-condensed table-hover" style="width: 100%">

            <c:forEach items="${tasks}" var="task">
                <c:if test="${task.isDone.toString().intern() == '0'.toString().intern() }">
                    <tr>
                        <% if (isAdmin){ %>
                        <td ><c:out value="${task.id}"></c:out></td>
                        <% } %>
                        <td class="info"><c:out value="${task.name}"></c:out></td>
                        <td><c:out value="${task.description}"></c:out></td>
                        <td class="success"><a href="/donetask?id=${task.id}">Done</a> </td>
                        <td class="danger"><a href="/deletetask?id=${task.id}">Delete</a> </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>


    <div class="col-md-6 col-sm-12">
        <h2>List of Finished Tasks</h2><br>
        <table class="table-striped table-condensed table-hover" style="width: 100%">

            <c:forEach items="${tasks}" var="task">
                <c:if test="${task.isDone.toString().intern() != '0'.toString().intern() }">
                    <tr>
                        <% if (isAdmin){ %>
                        <td ><c:out value="${task.id}"></c:out></td>
                        <% } %>
                        <td class="info"><c:out value="${task.name}"></c:out></td>
                        <td><c:out value="${task.description}"></c:out></td>
                        <td class="danger"><a href="/deletetask?id=${task.id}">Delete</a> </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>



<hr>
<a href="/newtask" class="btn btn-default">Add new task</a>

<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>
