package com.skilldistillery.filmquery.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	DatabaseAccessor db = new DatabaseAccessorObject();

	public FilmQueryApp() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Film Query App started.");
		FilmQueryApp app = new FilmQueryApp();
		app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sqltxt;
		sqltxt = "SELECT id, first_name, last_name FROM staff order by first_name";
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		ResultSet rs = stmt.executeQuery(sqltxt);

		int counter = 1;

		while (rs.next()) {
			System.out.println(counter + " " + rs.getString("id") + " " + rs.getString("first_name") + " "
					+ rs.getString("last_name"));
			counter++;
		}
		rs.close();
		stmt.close();
		conn.close();

	}

}
