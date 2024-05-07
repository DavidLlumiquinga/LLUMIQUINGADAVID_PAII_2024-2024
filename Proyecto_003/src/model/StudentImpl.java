package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaceDAO.DAO;

public class StudentImpl implements DAO<Student>{
	  private Connection connection;

	    public StudentImpl(Connection connection) {
	        this.connection = connection;
	    }

	    @Override
	    public Student get(int id) {
	        Student alumno = new Student();
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM alumno WHERE id = ?");
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                alumno.setId(resultSet.getInt("id"));
	                alumno.setName(resultSet.getString("name"));
	                alumno.setLastname(resultSet.getString("lastname"));
	                alumno.setAge(resultSet.getInt("age"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return alumno;
	    }

	    @Override
	    public List<Student> getAll() {
	        List<Student> alumnos = new ArrayList<>();
	        try {
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM alumno");

	            while (resultSet.next()) {
	                Student alumno = new Student();
	                alumno.setId(resultSet.getInt("id"));
	                alumno.setName(resultSet.getString("name"));
	                alumno.setLastname(resultSet.getString("lastname"));
	                alumno.setAge(resultSet.getInt("age"));
	                alumnos.add(alumno);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return alumnos;
	    }

	    @Override
	    public void create(Student alumno) {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO alumno (name, lastname, age) VALUES (?, ?, ?)");
	            preparedStatement.setString(1, alumno.getName());
	            preparedStatement.setString(2, alumno.getLastname());
	            preparedStatement.setInt(3, alumno.getAge());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void update(Student alumno) {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE alumno SET name = ?, lastname = ?, age = ? WHERE id = ?");
	            preparedStatement.setString(1, alumno.getName());
	            preparedStatement.setString(2, alumno.getLastname());
	            preparedStatement.setInt(3, alumno.getAge());
	            preparedStatement.setInt(4, alumno.getId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void delete(Student alumno) {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM alumno WHERE id = ?");
	            preparedStatement.setInt(1, alumno.getId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
