<%@ page import="com.bean.Category" %>
<%@ page import="com.service.itf.CategoryService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.service.impl.CategoryServiceImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: phapk
  Date: 24-May-23
  Time: 2:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="/asset/bootstrap.min.css">
    <link rel="stylesheet" href="/asset/library_style.css">
</head>
<body>
<%! CategoryService categoryService = new CategoryServiceImpl();
    List<Category> categories = categoryService.display(); %>

<div class="container">
    <fmt:setLocale value="vi_VN"/>

    <!--TẠO THÂN TRANG-->
    <div id="body-page">

        <!--PHẦN NỘI DUNG CỦA PRODUCT-->
        <div class="content">

            <!--NỘI DUNG CHÍNH CỦA PRODUCT-->
            <div class="main-content">
                <div class="popup">
                    <c:if test="${error!=null}">
                        <h3>${error}</h3>
                    </c:if>
                </div>

                <!--FORM TÌM KIẾM PRODUCT-->
                <table class="table">
                    <tbody>
                    <tr class="col">
                        <td>
                            <div>
                                <form action="/exams" method="post">
                                    <input type="hidden" name="action" value="initCreate">
                                    <input type="submit" class="btn btn-primary" value="+ Add new product">
                                </form>
                            </div>
                        </td>
                        <td>
                            <div>
                                <form class="d-flex" action="/exams" method="post">
                                    <input type="hidden" name="action" value="search">
                                    <input class="form-control me-2" type="search"
                                           placeholder="Search by name of product"
                                           aria-label="Search"
                                           name="searchByName">
                                    <button class="btn btn-outline-success" type="submit">Search</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <!--PHẦN HIỂN THỊ BẢNG PRODUCT-->
                <h4><a href="/exams">Product List</a></h4>
                <form action="/exams" method="post">
                    <input type="hidden" name="action" value="delMany">
                    <table class="table">
                        <thread>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col">Product ID</th>
                                <th scope="col">Product Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Color</th>
                                <th scope="col">Description</th>
                                <th scope="col">Category</th>
                                <th scope="col">Action</th>
                                <th scope="col"></th>
                            </tr>
                        </thread>
                        <tbody>
                        <c:forEach items="${products}" var="product">

                            <tr class="col">
                                <td>
                                    <input type="checkbox" name="choose" value=${product.id}>
                                </td>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td><fmt:formatNumber value="${product.price}" type="currency"/></td>
                                <td>${product.quantity}</td>
                                <td>${product.color}</td>
                                <td>${product.description}</td>
                                <td>${product.category.getCName()}</td>
                                <td>
                                    <button
                                            type="button"
                                            class="btn btn-primary"
                                            data-bs-toggle="modal"
                                            data-bs-target="#delProductModal"
                                            data-bs-id="${product.id}">
                                        Del
                                    </button>
                                </td>
                                <td>
                                    <button
                                            id="testbt"
                                            type="button"
                                            class="btn btn-primary"
                                            data-bs-toggle="modal"
                                            data-bs-target="#editProductModal"
                                            data-bs-id="${product.id}"
                                            data-bs-name="${product.name}"
                                            data-bs-price="${product.price}"
                                            data-bs-quantity="${product.quantity}"
                                            data-bs-color="${product.color}"
                                            data-bs-description="${product.description}"
                                            data-bs-cId="${product.category.getCId()}">
                                        Edit
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <input type="submit" class="btn btn-secondary" value="OK">
                </form>

                <!--MODAL XÓA SẢN PHẨM-->
                <div class="modal fade"
                     id="delProductModal"
                     tabindex="-1"
                     aria-labelledby="delProductModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="delProductModalLabel">Are you sure delete this product?</h5>
                                <button type="button"
                                        class="btn-close"
                                        data-bs-dismiss="modal"
                                        aria-label="Close">
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/exams" method="post">
                                    <input type="hidden" name="action" value="delete">
                                    <div class="mb-3">
                                        <input type="hidden" class="form-control" id="id" name="id"/>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                            Close
                                        </button>
                                        <input type="submit" class="btn btn-secondary" value="OK">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!--MODAL SỬA SẢN PHẨM-->
                <div class="modal fade"
                     id="editProductModal"
                     tabindex="-1"
                     aria-labelledby="editProductModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="editProductModalLabel">Edit information of product</h5>
                                <button type="button"
                                        class="btn-close"
                                        data-bs-dismiss="modal"
                                        aria-label="Close">
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/exams" method="post">
                                    <input type="hidden" name="action" value="edit">
                                    <div class="mb-3">
                                        <input type="hidden" class="form-control" id="newId" name="newId"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="newName" class="col-form-label">Tên sản phẩm:</label>
                                        <input type="text" class="form-control" id="newName" name="newName" required/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="newPrice" class="col-form-label">Giá sản phẩm:</label>
                                        <input type="number" class="form-control" id="newPrice" name="newPrice"
                                               required/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="newQuantity" class="col-form-label">Số lượng:</label>
                                        <input type="nuber" class="form-control" id="newQuantity" name="newQuantity"
                                               required/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="newColor" class="col-form-label">Màu sắc:</label>
                                        <input type="text" class="form-control" id="newColor" name="newColor" required/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="newDescription" class="col-form-label">Mô tả:</label>
                                        <input type="text" class="form-control" id="newDescription"
                                               name="newDescription" required/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="newCategory" class="col-form-label">Loại hàng:</label>
                                        <select class="form-control" name="newCategory" id="newCategory">
                                            <c:forEach items="<%=categories%>" var="category">
                                                <option value="${category.CId}">${category.CName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                            Close
                                        </button>
                                        <input type="submit" class="btn btn-secondary" value="OK">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/asset/bootstrap.bundle.min.js"></script>
<script src="/asset/book.js"></script>
<script src="/asset/product.js"></script>
<script src="/asset/cards.js"></script>
</html>
