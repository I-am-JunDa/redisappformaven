<%--
  Created by IntelliJ IDEA.
  User: 军大
  Date: 2021/1/26
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>修改学生</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>
<form action="/redisapp/updateStudentServlet" method="post"  id="myform">
    <table border="1px solid" align="center">
        <tr>
            <td>姓名</td>
            <td>
                <input name="name" id="user_name" value="${requestScope.student.name}">
                <span id="username"  style="display: none;">提示信息</span>
            </td>
        </tr>
        <tr>
            <td>生日</td>
            <td>
                <input  name="birthday" id="birthday" placeholder="格式:1999-01-15" value="${requestScope.sBirthday}" >
                <span id="birthday"  style="display: none;">提示信息</span>
            </td>
        </tr>
        <tr>
            <td>描述</td>
            <td>
                <input name="description" id="description" value="${requestScope.student.description}">
                <span id="description"  style="display: none;">提示信息</span>
            </td>
        </tr>
        <tr>
            <td>平均分</td>
            <td>
                <input name="avgscore" id="avgscore"  value="${requestScope.student.avgscore}">
                <span id="avgscore"  style="display: none;">提示信息</span>
            </td>
        </tr>
        <tr>
        <tr>
            <input type="hidden" name="id" value="${requestScope.student.id}">
            <td colspan="2"><input type="submit" id="submit"></td>
        </tr>
        </tr>
    </table>
</form>

</body>
<script>
    $(function () {
        // 定义开关变量
        var flagUser=true
        var flagbirthday=true
        var flagdescription=true
        var flagavgscore=true

        // 找到所在标签元素
        var $user_name = $('#user_name')
        var $birthday=$('#birthday')
        var $description=$('#description')
        var $avgscore=$('#avgscore')

        var $submit=$('#myform')
        //姓名
        $user_name.blur(function () {
            $("#username").show();
            fnCheckUser()
        })
        function fnCheckUser() {
            var vals = $user_name.val()
            var re = /^[\u4e00-\u9fa5]{0,40}$/
            if (vals == '') {
                $user_name.next().show().html('姓名不能为空')
                flagUser=false
                return
            }
            if (re.test(vals)) {
                $user_name.next().hide()
                flagUser=true
            } else {
                $user_name.next().show().html('用户名必须的中文，限制长度40')
                flagUser=false
            }
        }
        // 生日
        $birthday.blur(function () {
            $("#birthday").show();
            fnCheckBirthday()
        })
        function fnCheckBirthday() {
            var vals = $birthday.val()
            var re = /^\d{4}-\d{1,2}-\d{1,2}$/
            if (vals == '') {
                $birthday.next().show().html('生日不能为空')
                flagbirthday=false
                return
            }
            if (re.test(vals)) {
                $birthday.next().hide()
                flagbirthday=true
            } else {
                $birthday.next().show().html('格式必须是1999-02-11')
                flagbirthdayr=false
            }
        }
        //备注
        $description.blur(function(){
            fnCheckdes()
        })
        function fnCheckdes(){
            var vals=$description.val()
            var reMail = /^[\u4e00-\u9fa5A-Za-z0-9]{0,255}$/
            if (vals==''){
                $description.next().show().html('描述不能为空')
                flagdescription=false
                return
            }
            if(reMail.test(vals))
            {
                $description.next().hide()
                flagdescription=true
            }
            else
            {
                $description.next().show().html('输入长度过长，限制255')
                flagdescription=false
            }
        }
        //平均分完成
        $avgscore.blur(function(){
            fnCheckscore()
        })
        function fnCheckscore(){
            var vals=$avgscore.val()
            var score = /^(0|[1-9][0-9]?|100)$/
            if (vals==''){
                $avgscore.next().show().html('平均分不能为空')
                flagavgscore=false
                return
            }
            if(score.test(vals))
            {
                $avgscore.next().hide()
                flagavgscore=true
            }
            else
            {
                $avgscore.next().show().html('平均分必须为0-100')
                flagavgscore=false
            }
        }

        //所有输入框验证通过再提交注册
        $submit.submit(function(){
            if(flagUser && flagbirthday && flagdescription && flagavgscore){

            }
            else{
                alert("验证失败，请修改你输入的参数")
                return false
            }
        })
    })

</script>
</html>
