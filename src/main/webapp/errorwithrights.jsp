<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 25.02.17
  Time: 17:35
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
<h2>Something wrong, sorry</h2>
<div class="col-md-6 col-xs-12">

        <div class="alert alert-danger">
                You have insufficient rights
        </div>
    <a href="/login.jsp" class="btn btn-primary">Log in</a>
    <a href="/register.jsp" class="btn btn-success right">Sign Up</a>
</div>


<t:bottomlayout>
    <jsp:attribute name="footer">    </jsp:attribute>
</t:bottomlayout>



