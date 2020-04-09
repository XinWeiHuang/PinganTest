package com.test.pingan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;


public class Station {
	
	public static void sellSeats(int uid) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement statement = connection.createStatement();//
			Seat seat = ResultSetUtil.getOneResult(statement.executeQuery("select id, status from seat where name = '23D' and status = 0"), Seat.class);
			System.out.println(Thread.currentThread().getName() + ":\t" + seat);
			if (seat != null) {
				TimeUnit.MILLISECONDS.sleep(500);
				connection.setAutoCommit(false);
				PreparedStatement preparedStatement = connection.prepareStatement("update seat set status = 1 where id = ? and status = 0");
				int seatId = seat.getId();
				preparedStatement.setInt(1, seatId);
				if (preparedStatement.executeUpdate() > 0) {
					System.out.println(Thread.currentThread().getName() + "成功买到了票");
					preparedStatement = connection.prepareStatement("insert user_seat(user_id,seat_id) values(?,?)");
					preparedStatement.setInt(1, uid);
					preparedStatement.setInt(2, seatId);
					preparedStatement.execute();
				}
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			ConnectionFactory.realease(connection);
		}
	} 
	
}
