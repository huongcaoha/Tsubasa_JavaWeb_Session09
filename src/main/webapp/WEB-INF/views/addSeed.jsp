<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/25/2025
  Time: 7:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Mới Hạt Giống</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Thêm Mới Hạt Giống</h1>
    <form action="/seeds/add" method="post">
        <div class="form-group">
            <label for="seedName">Tên Hạt Giống</label>
            <input type="text" class="form-control" value="${seed.seedName}" id="seedName" name="seedName" required>
        </div>
        <div class="form-group">
            <label for="quantity">Số Lượng</label>
            <input type="number" class="form-control" value="${seed.quantity}" id="quantity" name="quantity" required>
        </div>
        <div class="form-group">
            <label for="category">Danh Mục</label>
            <select class="form-control" id="category" name="categoryId" required>
                <option value="">Chọn danh mục</option>

                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}" <c:if test="${category.id == seed.category.id}">selected</c:if>>${category.cateName}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Thêm Mới</button>
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </form>

    <c:if test="${not empty message}" >
        <script>
            alert("${message}")
        </script>
    </c:if>
</div>
</body>
</html>