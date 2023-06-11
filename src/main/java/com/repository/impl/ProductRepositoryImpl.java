package com.repository.impl;

import com.bean.Category;
import com.bean.Product;
import com.database.DBConnection;
import com.database.MyQuery;
import com.repository.itf.ProductRepository;
import com.util.MyUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<Product> display() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Product> productList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SELECT_PRODUCT);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    int price = resultSet.getInt("price");
                    int quantity = resultSet.getInt("quantity");
                    String color = resultSet.getString("color");
                    String description = resultSet.getString("description");
                    String cId = resultSet.getString("cId");
                    String cName = resultSet.getString("cName");

                    Category category = new Category(cId, cName);

                    Product product = new Product(id, name, price, quantity, color, description, category);

                    productList.add(product);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
        return productList;
    }

    @Override
    public void delete(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.DELETE_PRODUCT);
                statement.setString(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
    }

    @Override
    public void create(Product product) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.CREATE_PRODUCT);

                statement.setString(1, product.getId());
                statement.setString(2, product.getName());
                statement.setInt(3, product.getPrice());
                statement.setInt(4, product.getQuantity());
                statement.setString(5, product.getColor());
                statement.setString(6, product.getDescription());
                statement.setString(7, product.getCategory().getCId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
    }

    @Override
    public List<Product> search(String str) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Product> productList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SEARCH_PRODUCT);
                statement.setString(1, '%' + str + '%');

                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    int price = resultSet.getInt("price");
                    int quantity = resultSet.getInt("quantity");
                    String color = resultSet.getString("color");
                    String description = resultSet.getString("description");
                    String cId = resultSet.getString("cId");
                    String cName = resultSet.getString("cName");

                    Category category = new Category(cId, cName);

                    Product product = new Product(id, name, price, quantity, color, description, category);

                    productList.add(product);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
        return productList;
    }

    @Override
    public void edit(Product product) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.UPDATE_PRODUCT);

                statement.setString(1, product.getName());
                statement.setInt(2, product.getPrice());
                statement.setInt(3, product.getQuantity());
                statement.setString(4, product.getColor());
                statement.setString(5, product.getDescription());
                statement.setString(6, product.getCategory().getCId());
                statement.setString(7, product.getId());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBConnection.close();
            }
        }
    }
}
