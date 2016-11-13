package com.evgo.repository;

import com.evgo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by root_pc on 11/13/2016.
 */


@Repository
public class PersonalRepoImpl implements  PersonalRepo {

    @Autowired
    DataSource dataSource;

    public User getUser(LoginUser user) {

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "select * from users where username = ?";
        User customer = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
            rs.close();
            ps.close();
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public Boolean addTask(AddTask task,int userId) {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "insert into Task (userId,name,description,status,isFinish,deadline) values (?,?,?,?,?,?)";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//
//            java.sql.Date startDate = new java.sql.Date()

            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, task.getName());
            ps.setString(3, task.getDescription());
            ps.setString(4, String.valueOf(task.getStatus()));
            ps.setBoolean(5, task.isFinish());
            System.out.println(task.getDeadline());
            ps.setDate(6, new java.sql.Date(format.parse(task.getDeadline()).getTime()));
            int rs = ps.executeUpdate();
            ps.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return true;
    }

    public List<Task> getAllTask(int userId) {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "select * from Task where userId = ?";
        List<Task> list = new ArrayList<Task>();
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("description"),
                        Status.valueOf(rs.getString("status")),
                        rs.getBoolean("isFinish"),
                        rs.getDate("deadline")
                );
                list.add(task);
            }
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }
}
