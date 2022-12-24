package com.vdoichev.db;

import java.sql.*;
import java.util.Objects;

public class DBCalculator {

    private static final String SELECT_FROM_EXPRESSIONS = "SELECT * FROM expressions";
    private static final String SELECT_FROM_EXPRESSIONS_BY_ID = "SELECT * FROM expressions where id = ?";
    private static final String SELECT_FROM_EXPRESSIONS_BY_RESULT = "SELECT * FROM expressions where result = ?";
    public static final String INSERT_INTO_EXPRESSIONS = "INSERT INTO expressions(expression, result) values (?, ?)";
    public static final String UPDATE_EXPRESSIONS = "UPDATE expressions SET expression = ?, result = ? WHERE ID =?";

    public void findAll() {
        try (Connection con = MySQLConnection.getConnection();
             Statement statement = Objects.requireNonNull(con).createStatement();
             ResultSet rs = statement.executeQuery(SELECT_FROM_EXPRESSIONS)) {
            while (rs.next()) {
                String output = String.format("ID = %s  EXPRESSOIN = %s  RESULT = %s",
                        rs.getInt("id"),
                        rs.getString("expression"),
                        rs.getDouble("result"));
                System.out.println(output);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean findById(int id) {
        try (Connection con = MySQLConnection.getConnection();
             PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(
                     SELECT_FROM_EXPRESSIONS_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String output = String.format("ID = %s  EXPRESSOIN = %s  RESULT = %s",
                        rs.getInt("id"),
                        rs.getString("expression"),
                        rs.getDouble("result"));
                System.out.println(output);
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean findByResult(String condition) {
        try (Connection con = MySQLConnection.getConnection();
             PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(
                     SELECT_FROM_EXPRESSIONS_BY_RESULT)) {
            statement.setString(1, condition);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String output = String.format("ID = %s  EXPRESSOIN = %s  RESULT = %s",
                        rs.getInt("id"),
                        rs.getString("expression"),
                        rs.getDouble("result"));
                System.out.println(output);
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int addExpression(String expression, double result) {
        try (Connection con = MySQLConnection.getConnection();
             PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(
                     INSERT_INTO_EXPRESSIONS,
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, expression);
            statement.setDouble(2, result);
            int execute = statement.executeUpdate();
            if (execute > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public boolean updateExpression(int id, String expression, double result) {
        try (Connection con = MySQLConnection.getConnection();
             PreparedStatement statement =
                     Objects.requireNonNull(con).prepareStatement(UPDATE_EXPRESSIONS,
                             Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, expression);
            statement.setDouble(2, result);
            statement.setInt(3, id);
            int execute = statement.executeUpdate();
            if (execute > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
