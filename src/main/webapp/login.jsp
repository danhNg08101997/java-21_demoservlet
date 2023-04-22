<%@ page import="model.Users" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<%--
    <%! %>: thẻ dùng để khai báo biến
    <% %>: thẻ xử lý logic, thẻ đa năng
    <%= %>: thẻ xuất giá trị
--%>

<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<%--<%!--%>
<%--int count = 0;--%>
<%--Users users = new Users();--%>
<%--%>--%>

<%--<%--%>
<%--    int kq = (int) request.getAttribute("kq");--%>
<%--    count++;--%>
<%--    users.setUsername("Nguyễn Văn A");--%>
<%--    users.setPassword("123456");--%>
<%--%>--%>

<%--<%--%>
<%--if(count % 2 == 0){--%>
<%--%>--%>
<%--<b style="padding: 50px"> <%= users.getUsername()%> </b>--%>
<%--<%}%>--%>

<%--<b style="padding: 50px"> <%= kq%> </b>--%>
<%--<b style="padding: 50px"> <%= count%> </b>--%>
<%--<b style="padding: 50px"> <%= users.getUsername()%> </b>--%>
<%--<b style="padding: 50px"> <%= users.getPassword()%> </b>--%>

<div class="container">
    <div class="row mt-5">
        <div class="col-md-5 m-auto mt-5">
            <h3 class="text-center">ĐĂNG NHẬP HỆ THỐNG</h3>
            <div class="p-4 border mt-4">

            <%--
            action: đường dẫn mà tham số sẽ được gửi về
            method: phương thức gửi tham số cho đường dẫn khai báo ở action
            --%>


<%--                <% String message = (String) request.getAttribute("msg"); %>--%>
<%--                <b style="padding: 50px"> <%= message%> </b>--%>
<%--                <h1><c:out value="${msg}"/></h1>--%>
<%--                <span>${users.username}</span>--%>
<%--                <c:if test="${users.password == '123'}">--%>
<%--                    Mật khẩu của bạn là: ${users.password}--%>
<%--                </c:if>--%>
<%--                <span>${users.password}</span>--%>

<%--                <c:forEach items="${list}" var = "item">
                        Từng phần tử trong list hoặc mảng
                        <b>${item} </b>
                        </c:forEach>
--%>

<%--                <p>${users.getUsername()}</p>--%>
<%--                <p>${users.getPassword()}</p>--%>


                <% String contextPath = request.getContextPath(); %>
                <form action="<%=contextPath%>/login" method="post">
                    <div class="form-group">
                        <label>Email</label>
                        <%-- name phải trùng với parameter mới lấy được giá trị--%>
                        <input type="email" class="form-control" name="email">
                    </div>
                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <input type="password" class="form-control" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary">Đăng nhập</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
