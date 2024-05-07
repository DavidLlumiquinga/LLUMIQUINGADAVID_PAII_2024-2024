package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaceDAO.DAO;

public class ScheduleImpl implements DAO<Schedule> {

	private Connection connection;

	public ScheduleImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Schedule get(int id) {
		Schedule horario = new Schedule();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM horario WHERE id_mat = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				horario.setId_mat(resultSet.getInt("id_mat"));
				horario.setId_alum(resultSet.getInt("id_alum"));
				horario.setId_profesor(resultSet.getInt("id_profesor"));
				horario.setHora_inicio(resultSet.getTime("hora_inicio"));
				horario.setHora_fin(resultSet.getTime("hora_fin"));
				horario.setDia(resultSet.getString("dia"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horario;
	}

	@Override
	public List<Schedule> getAll() {
		List<Schedule> horarios = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM horario");

			while (resultSet.next()) {
				Schedule horario = new Schedule();
				horario.setId_mat(resultSet.getInt("id_mat"));
				horario.setId_alum(resultSet.getInt("id_alum"));
				horario.setId_profesor(resultSet.getInt("id_profesor"));
				horario.setHora_inicio(resultSet.getTime("hora_inicio"));
				horario.setHora_fin(resultSet.getTime("hora_fin"));
				horario.setDia(resultSet.getString("dia"));
				horarios.add(horario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarios;
	}

	@Override
	public void create(Schedule horario) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO horario (id_mat, id_alum, id_profesor, hora_inicio, hora_fin, dia) VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, horario.getId_mat());
			preparedStatement.setInt(2, horario.getId_alum());
			preparedStatement.setInt(3, horario.getId_profesor());
			preparedStatement.setTime(4, horario.getHora_inicio());
			preparedStatement.setTime(5, horario.getHora_fin());
			preparedStatement.setString(6, horario.isDia());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Schedule horario) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE horario SET id_alum = ?, id_profesor = ?, hora_inicio = ?, hora_fin = ?, dia = ? WHERE id_mat = ?");
			preparedStatement.setInt(1, horario.getId_alum());
			preparedStatement.setInt(2, horario.getId_profesor());
			preparedStatement.setTime(3, horario.getHora_inicio());
			preparedStatement.setTime(4, horario.getHora_fin());
			preparedStatement.setString(5, horario.isDia());
			preparedStatement.setInt(6, horario.getId_mat());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Schedule horario) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM horario WHERE id_mat = ?");
			preparedStatement.setInt(1, horario.getId_mat());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
