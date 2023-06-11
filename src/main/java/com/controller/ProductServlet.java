package com.controller;

import com.bean.Category;
import com.bean.Product;
import com.exception.ExistException;
import com.exception.ValidateException;
import com.regex.MyRegex;
import com.service.impl.CategoryServiceImpl;
import com.service.impl.ProductServiceImpl;
import com.service.itf.CategoryService;
import com.service.itf.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/exams")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showList(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.display();
        req.setAttribute("products", products);
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "search":
                doSearch(req, resp);
                break;
            case "initCreate":
                doInitCreate(req, resp);
                break;
            case "create":
                doCreate(req, resp);
                break;
            case "delete":
                doDel(req, resp);
                break;
            case "delMany":
                doDelMany(req, resp);
                break;
            case "edit":
                doEdit(req, resp);
                break;
            default:
                showList(req, resp);
                break;
        }
    }

    private void doSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("searchByName");
        List<Product> products = productService.search(str);
        req.setAttribute("products", products);
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
    }

    private void doInitCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.display();
        int maxId = 0;
        if (products.size() == 0) {
            maxId = 1;
        } else {
            for (Product p : products) {
                int pId = Integer.parseInt(p.getId().substring(3));
                if (pId + 1 > maxId) {
                    maxId = pId + 1;
                }
            }
        }

        String id;
        if (maxId < 10) {
            id = "SP-000" + maxId;
        } else if (maxId < 100) {
            id = "SP-00" + maxId;
        } else if (maxId < 1000) {
            id = "SP-0" + maxId;
        } else {
            id = "SP-" + maxId;
        }

        List<Category> categories = categoryService.display();
        req.setAttribute("id", id);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("WEB-INF/view/create.jsp").forward(req, resp);
    }

    private void doCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            if (!id.matches(MyRegex.REGEX_PRODUCT_ID)) {
                throw new ValidateException();
            }

            List<Product> products = productService.display();
            for (Product p : products) {
                if (p.getId().equals(id)) {
                    throw new ExistException();
                }
            }

            String name = req.getParameter("name");

            int price = Integer.parseInt(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String color = req.getParameter("color");
            String description = req.getParameter("description");

            String cId = req.getParameter("category");
            Category category = categoryService.search(cId);

            Product product = new Product(id, name, price, quantity, color, description, category);
            productService.create(product);
            resp.sendRedirect("/exams");
        } catch (ValidateException e) {
            String errorMsg = "Mã sản phẩm chưa đúng định dạng";
            req.setAttribute("error", errorMsg);
            req.getRequestDispatcher("WEB-INF/view/create.jsp").forward(req, resp);
        } catch (ExistException e) {
            String errorMsg = "ID sản phẩm đã tồn tại";
            req.setAttribute("error", errorMsg);
            req.getRequestDispatcher("WEB-INF/view/create.jsp").forward(req, resp);
        }
    }

    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        productService.delete(id);
        resp.sendRedirect("/exams");
    }

    private void doDelMany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] id = req.getParameterValues("choose");
        for (String str : id) {
            productService.delete(str);
        }
        resp.sendRedirect("/exams");
    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("newId");
            String name = req.getParameter("newName");
            int price = Integer.parseInt(req.getParameter("newPrice"));
            int quantity = Integer.parseInt(req.getParameter("newQuantity"));
            String color = req.getParameter("newColor");
            String description = req.getParameter("newDescription");

            String cId = req.getParameter("newCategory");
            Category category = categoryService.search(cId);

            Product product = new Product(id, name, price, quantity, color, description, category);
            productService.edit(product);
            resp.sendRedirect("/exams");
        } catch (ValidateException e) {
            String errorMsg = "Mã sản phẩm chưa đúng định dạng";
            req.setAttribute("error", errorMsg);
            req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, resp);
        }
    }
}
