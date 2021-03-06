/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.Lecturer;
import com.entities.Student;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

/**
 *
 * @author gautamverma
 */
public class UserControl {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    @EJB
    StudentEjb studentEjb;
    @Resource
    UserTransaction userTransaction;

    public boolean saveStudent(Student student) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/ExamMgmtSystem?"
                    + "user=root&password=root");

            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("insert into users(userid,password) values(?,?)");

            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(1, student.getUserName());

            preparedStatement.executeUpdate();
            saveStudentInGroup(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    public boolean saveLecturer(Lecturer lecturer) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/ExamMgmtSystem?"
                    + "user=root&password=root");

            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("insert into users(userid,password) values(?,?)");

            preparedStatement.setString(2, lecturer.getPassword());
            preparedStatement.setString(1, lecturer.getUserName());

            preparedStatement.executeUpdate();
            saveLecturerInGroup(lecturer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    
    
    private boolean saveStudentInGroup(Student student)
    {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/ExamMgmtSystem?"
                    + "user=root&password=root");

            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("insert into users_groups(groupid,userid) values(?,?)");

            preparedStatement.setString(1, "student");
            preparedStatement.setString(2, student.getUserName());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
        
    }
    
    
    private boolean saveLecturerInGroup(Lecturer lecturer)
    {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/ExamMgmtSystem?"
                    + "user=root&password=root");

            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("insert into users_groups(groupid,userid) values(?,?)");

            preparedStatement.setString(1, "lecturer");
            preparedStatement.setString(2, lecturer.getUserName());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
        
    }
    
    public boolean passwordChange(Student student) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/ExamMgmtSystem?"
                    + "user=root&password=root");

            statement = connect.createStatement();

            resultSet = statement
                    .executeQuery("select * from users");
            while (resultSet.next()) {
                System.out.println("RS:1:" + resultSet.getString(1));
            }
            System.out.println(student.getPassword());
            preparedStatement = connect
                    .prepareStatement("update users set password=? where userid=?");

            preparedStatement.setString(1, student.getPassword());
            preparedStatement.setString(2, student.getUserName());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean passwordChangeLecturer(Lecturer lecturer) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/ExamMgmtSystem?"
                    + "user=root&password=root");

            statement = connect.createStatement();

            resultSet = statement
                    .executeQuery("select * from users");
           
            System.out.println(lecturer.getPassword());
            preparedStatement = connect
                    .prepareStatement("update users set password=? where userid=?");

            preparedStatement.setString(1, lecturer.getPassword());
            preparedStatement.setString(2, lecturer.getUserName());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    

}
