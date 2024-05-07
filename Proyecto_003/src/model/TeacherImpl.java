package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaceDAO.DAO;

public class TeacherImpl implements DAO<Teacher>{
	 private Connection connection;

	    public TeacherImpl(Connection connection) {
	        this.connection = connection;
	    }

	    @Override
	    public Teacher get(int id) {
	    	Teacher profe = new Teacher();
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM profesor WHERE id = ?");
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                profe.setId(resultSet.getInt("id"));
	                profe.setName(resultSet.getString("name"));
	                profe.setLastname(resultSet.getString("lastname"));
	                profe.setAge(resultSet.getInt("age"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return profe;
	    }

	    @Override
	    public List<Teacher> getAll() {
	        List<Teacher> profes = new ArrayList<>();
	        try {
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM profesor");

	            while (resultSet.next()) {
	                Teacher profe = new Teacher();
	                profe.setId(resultSet.getInt("id"));
	                profe.setName(resultSet.getString("name"));
	                profe.setLastname(resultSet.getString("lastname"));
	                profe.setAge(resultSet.getInt("age"));
	                profes.add(profe);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return profes;
	    }

	    @Override
	    public void create(Teacher profe) {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO profe (name, lastname, age) VALUES (?, ?, ?)");
	            preparedStatement.setString(1, profe.getName());
	            preparedStatement.setString(2, profe.getLastname());
	            preparedStatement.setInt(3, profe.getAge());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void update(Teacher profe) {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE profesor SET name = ?, lastname = ?, age = ? WHERE id = ?");
	            preparedStatement.setString(1, profe.getName());
	            preparedStatement.setString(2, profe.getLastname());
	            preparedStatement.setInt(3, profe.getAge());
	            preparedStatement.setInt(4, profe.getId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void delete(Teacher profe) {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM profesor WHERE id = ?");
	            preparedStatement.setInt(1, profe.getId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
