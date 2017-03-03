<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 24.02.17
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>


<t:uplayout>
    <jsp:attribute name="header">    </jsp:attribute>
</t:uplayout>
<!-- write your code here -->
<h2>Create new Subject</h2>
<c:if test="${not empty requestScope.message}">
    <div class="alert alert-warning">
            ${message}
    </div>
</c:if>
<div class="col-md-6 col-xs-12">
    <form action="/newsubject" method="post" >
        <input type="text" name="name" placeholder="name" class="form-control" required><br>
        <input type="description" name="description" placeholder="description" class="form-control" required><br>
        <input type="sphere" name="sphere" placeholder="sphere" class="form-control" required><br>
        <input type="submit" value="Send" class=" btn btn-primary">

    </form>
</div>


<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>
