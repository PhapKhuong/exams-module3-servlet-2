package com.repository.impl;

import com.bean.Category;
import com.database.DBConnection;
import com.database.MyQuery;
import com.repository.itf.CategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public List<Category> display() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Category> categoryList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SELECT_CATEGORY);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String cId = resultSet.getString("cId");
                    String cName = resultSet.getString("cName");
                    Category category = new Category(cId, cName);
                    categoryList.add(category);
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
        return categoryList;
    }

    @Override
    public Category search(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Category category = new Category();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(MyQuery.SEARCH_CATEGORY);
                statement.setString(1, id);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String cId = resultSet.getString("cId");
                    String cName = resultSet.getString("cName");
                    category = new Category(cId, cName);
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
        return category;
    }
}
