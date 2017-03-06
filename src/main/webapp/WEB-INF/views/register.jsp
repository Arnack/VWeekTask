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
<h2>Join our community</h2>
<div class="row">
<div class="col-md-6 col-xs-12">
    <form action="/register" method="post" >
        <c:if test="${not empty requestScope.message}">
            <div class="alert alert-warning">
                    ${message}
            </div>
        </c:if>
        <div id="login-info"></div>
        <!-- after login inf0 -->
        <input type="text" id="login-reg" name="login" placeholder="login" class="form-control" required><br>
        <input type="password" name="password" placeholder="password" class="form-control" minlength="3" maxlength="28" required><br>
        <input type="submit" value="Send" class=" btn btn-primary">

    </form>
</div>
<div class="col-md-6 col-sm-12 list">
    <p>If you already have an account, you can</p>
    <a href="/login" class="btn btn-success">Log in</a>
</div>
</div>

<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>

