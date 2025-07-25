<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/25/2025
  Time: 7:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Hạt Giống</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        a {
            color: black;
        }
        .active {
            color: blue;
        }

    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">Danh sách Hạt Giống</h1>

    <form action="/seeds" method="get" class="mb-3">
        <div class="form-group">
            <input type="text" class="form-control" name="searchName" placeholder="Tìm kiếm theo tên hạt giống" value="${searchName}">
        </div>
        <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
    </form>

    <a href="/seeds/add" class="btn btn-success mb-3">Thêm Hạt Giống</a>

    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Tên Hạt Giống</th>
            <th>Số Lượng</th>
            <th>Danh Mục</th>
            <th>Hành Động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="seed" items="${seeds}" varStatus="status">
            <tr>
                <td>${(page - 1) * size + status.index + 1}</td>
                <td>${seed.seedName}</td>
                <td>${seed.quantity}</td>
                <td>${seed.category.cateName}</td>
                <td>
                    <a href="/seeds/edit/${seed.id}" class="btn btn-warning">Sửa</a>
                    <button type="button" onclick="return confirm('Bạn có chắc chắn muốn xóa hạt giống này chứ ?')" class="btn btn-danger">
                        <a href="/seeds/delete/${seed.id}" style="color: white;">Xóa</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagination" style="display: flex ; justify-content: center ; align-items: center ; gap: 20px">
        <c:forEach var="p" items="${pages}">
            <a class="<c:if test="${page == p}">active</c:if> " href="/seeds?page=${p}&size=${size}&searchName=${param.searchName}" class="btn btn-light">${p}</a>
        </c:forEach>
    </div>

    <c:if test="${not empty message}">
        <script>
            alert("${message}");
        </script>
    </c:if>
</div>
</body>
</html>