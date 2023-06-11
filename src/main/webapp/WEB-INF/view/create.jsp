<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: phapk
  Date: 25-May-23
  Time: 12:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Borrow</title>
    <link rel="stylesheet" href="/asset/library_style.css">
    <link rel="stylesheet" href="/asset/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Thêm sản phẩm</h2>
    <c:if test="${error!=null}">
        <h3 id="popup">${error}</h3>
    </c:if>

    <c:if test="${error==null}">
        <form action="/exams" method="post">
            <table class="table">
                <tbody>
                <input type="hidden" name="action" value="create">
                <tr class="col">
                    <td>Product ID</td>
                    <td><input type="text" name="id" value="${id}" required/></td>
                </tr>
                <tr class="col">
                    <td>Product Name</td>
                    <td><input type="text" name="name" required/></td>
                </tr>
                <tr class="col">
                    <td>Price</td>
                    <td><input type="number" name="price" required/></td>
                </tr>
                <tr class="col">
                    <td>Quantity</td>
                    <td><input type="number" name="quantity" required></td>
                </tr>
                <tr class="col">
                    <td>Color</td>
                    <td><input type="text" name="color" required></td>
                </tr>
                <tr class="col">
                    <td>Description</td>
                    <td><input type="text" name="description" required></td>
                </tr>
                <tr class="col">
                    <td>Category</td>
                    <td>
                        <select class="form-control" name="category">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.CId}">${category.CName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
            <div>
                <a href="/exams" class="btn btn-secondary">Cancel</a>
                <input type="submit" class="btn btn-secondary" value="OK">
            </div>
        </form>
    </c:if>
</div>
</body>
<script src="/asset/bootstrap.bundle.min.js"></script>
<script src="/asset/book.js"></script>
<script src="/asset/cards.js"></script>
<script src="/asset/product.js"></script>
</html>
