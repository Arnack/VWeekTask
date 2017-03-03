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
<h2>Login now</h2>
<c:if test="${not empty requestScope.message}">
    <div class="alert alert-warning">
            ${message}
    </div>
</c:if>
<div class="row">
<div class="col-md-6 col-xs-12">

    <form action="/login" method="post" >

        <input type="text" id="login-field" name="login" placeholder="login" class="form-control"><br>

        <input type="password" name="password" placeholder="password" class="form-control"><br>
        <input type="submit" value="Send" class="btn btn-primary">

    </form>
</div>

    <div class="col-md-6 col-sm-12 list bs-callout bs-callout-info">
        <p>If you have no account yet, you can register now</p><br>
        <a href="/register" class="btn btn-success">Sign Up</a>
    </div>
</div>

<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>

