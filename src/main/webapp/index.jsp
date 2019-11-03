<%--
  Created by IntelliJ IDEA.
  User: 34759
  Date: 2019/11/1
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<%--    直接跳转到main.jsp--%>
    <jsp:forward page="/pages/main.jsp"/>
<%--    <a href="${pageContext.request.contextPath}/product/findAll.do">点击查询所有产品信息</a>--%>
</body>
</html>
