<%--
  Created by IntelliJ IDEA.
  User: 军大
  Date: 2021/1/25
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>redis的增删改查</title>
</head>
<body>
    <table border="1px solid" align="center">
        <tr>
            <td colspan="6"><a href="${pageContext.request.contextPath}/add.jsp">添加学生</a></td>
        </tr>
        <tr>
            <td style="width: 250px;">id</td>
            <td style="width: 50px;">姓名</td>
            <td style="width: 150px;">生日</td>
            <td style="width: 100px;">描述</td>
            <td style="width: 50px;">平均分</td>
            <td style="width: 100px;">操作</td>
        </tr>
     <c:forEach items="${requestScope.studentHashSet}" var="student">
         <tr>
             <td style="width: 250px;">${student.id}</td>
             <td style="width: 50px;">${student.name}</td>
             <td style="width: 150px;">${student.birthdayDate}</td>
             <td style="width: 100px;">${student.description}</td>
             <td style="width: 50px;">${student.avgscore}</td>
             <td style="width: 100px;">
                 <a href="${pageContext.request.contextPath}/findByidServlet?id=${student.id}">修改</a>
                 <a href="${pageContext.request.contextPath}/deleteStudentServlet?id=${student.id}">删除</a>
             </td>
         </tr>
     </c:forEach>
        <tr>
            <td colspan="6">
            <c:forEach  items="${requestScope.totalPage}" var="number">
                    <a href="${pageContext.request.contextPath}/listStudentServlet?number=${number}">${number}</a>
            </c:forEach>
            </td>
        </tr>
    </table>

</body>
<script>

</script>
</html>
