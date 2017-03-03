<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 24.02.17
  Time: 23:56
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



<h2>List of Subjects</h2><br>
<c:if test="${not empty requestScope.message}">
    <div class="alert alert-warning">
            ${message}
    </div>
</c:if><br>
<div class="row">

    <div class="col-md-6 col-sm-12 list">
        <h2>List of my Subjects</h2><br>

        <table class="table-striped table-condensed table-hover">
            <c:forEach items="${userSubjects}" var="usubject">
                <tr>
                    <% if (isAdmin){ %>
                    <td ><c:out value="${usubject.id}"></c:out></td>
                    <% } %>
                    <td class="info"><c:out value="${usubject.name}"></c:out></td>
                    <td><c:out value="${usubject.description}"></c:out></td>
                    <td><c:out value="${usubject.sphere}"></c:out></td>
                    <td  class="warning"><a href="/finishsubject?id=<c:out value="${usubject.id}"></c:out>">Finish learning</a></td>
                    <% if (isAdmin){ %>
                    <td class="danger"><a href="/deletesubject?id=<c:out value="${usubject.id}"/>">Delete</a> </td>
                    <td class="warning"><a href="/editsubject?id=<c:out value="${usubject.id}"/>">Edit</a> </td>
                    <% } %>
                </tr>
            </c:forEach>
        </table>
    </div>


<div class="col-md-6 col-sm-12 list">

    <h2>List of other Subjects</h2><br>

    <table class="table-striped table-condensed table-hover">
        <c:forEach items="${subjects}" var="subject">
            <tr>
                <% if (isAdmin){ %>
                <td ><c:out value="${subject.id}"></c:out></td>
                <% } %>
                <td class="info"><c:out value="${subject.name}"></c:out></td>
                <td><c:out value="${subject.description}"></c:out></td>
                <td><c:out value="${subject.sphere}"></c:out></td>
                <td class="success"><a href="/startsubject?id=<c:out value="${subject.id}"></c:out>">Start learning</a></td>
                <% if (isAdmin){ %>
                <td class="danger"><a href="/deletesubject?id=<c:out value="${subject.id}"/>">Delete</a> </td>
                <td class="warning"><a href="/editsubject?id=<c:out value="${subject.id}"/>">Edit</a> </td>
                <% } %>
            </tr>
        </c:forEach>
    </table>
</div>



</div>

<hr>
<% if (isAdmin){ %>
    <a href="/newsubject" class="btn btn-default btn-lg">Add new subject</a>
<% }%>

<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>
