package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Using wrong Drivers.");
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String user = "student";
		String pass = "student";

//		String sqltxt = "SELECT id, title, description FROM film WHERE id = ?";
		String sqltxt = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, length,"
				+ " replacement_cost, rating, special_features FROM film WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film();

				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString(3));
				film.setReleaseYear(rs.getInt(4));
				film.setLanguageId(rs.getInt(5));
				film.setRentalDuration(rs.getInt(6));
				film.setRentalRate(rs.getInt(7));
				film.setLength(rs.getInt(8));
				film.setReplacementCost(rs.getInt(9));
				film.setRating(rs.getString(10));
				film.setSpecialFeatures(rs.getString(11));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
		return film;
	}

	@Override
	public Film findFilmByKeyword(int filmId) {
		Film film = null;
		String user = "student";
		String pass = "student";
		String sqltxt = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, length,"
				+ " replacement_cost, rating, special_features FROM film WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film();

				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString(3));
				film.setReleaseYear(rs.getInt(4));
				film.setLanguageId(rs.getInt(5));
				film.setRentalDuration(rs.getInt(6));
				film.setRentalRate(rs.getInt(7));
				film.setLength(rs.getInt(8));
				film.setReplacementCost(rs.getInt(9));
				film.setRating(rs.getString(10));
				film.setSpecialFeatures(rs.getString(11));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
		return film;
	}

//	@Override
//	public Actor findActorById(int actorId) {
//		return null;
//	}

//		Actor actor = null;
//		String user = "student";
//		String pass = "student";
//		String sqltxt = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
//		try (Connection conn = DriverManager.getConnection(URL, user, pass);
//				PreparedStatement stmt = conn.prepareStatement(sqltxt);
//				ResultSet rs = stmt.executeQuery(sqltxt);) {
//			stmt.setInt(1, actorId);
////			int counter = 1;
//			if (rs.next()) {
//				actor = new Actor();
//
//				actor.setId(rs.getInt(1));
//				actor.setFirstName(rs.getString(2));
//				actor.setLastName(rs.getString(3));
////				actor.setFilms(findFilmsByActorId(actorId));
//
////				System.out.println(counter + " " + rs.getString("id") + " " + rs.getString("first_name") + " "
////						+ rs.getString("last_name"));
////				counter++;
//			}
//		} catch (SQLException sqle) {
//			System.err.println(sqle);
//		}
//		return actor;
//	}

//	@Override
//	public List findActorsByFilmId(int ResultSet) {
//		return null;
//	}

//		 List<Film> films = new ArrayList<>();
//		  try {
//		    Connection conn = DriverManager.getConnection(url, user, pass);
//		    String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
//		                sql += " rental_rate, length, replacement_cost, rating, special_features "
//		               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
//		               + " WHERE actor_id = ?";
//		    PreparedStatement stmt = conn.prepareStatement(sql);
//		    stmt.setInt(1, actorId);
//		    ResultSet rs = stmt.executeQuery();
//		    while (rs.next()) {
//		      int filmId = rs.getInt(1);
//		      String title = rs.getString(2);
//		      String desc = rs.getString(3);
//		      short releaseYear = rs.getShort(4);
//		      int langId = rs.getInt(5);
//		      int rentDur = rs.getInt(6);
//		      double rate = rs.getDouble(7);
//		      int length = rs.getInt(8);
//		      double repCost = rs.getDouble(9);
//		      String rating = rs.getString(10);
//		      String features = rs.getString(11);
//		      Film film = new Film(filmId, title, desc, releaseYear, langId,
//		                           rentDur, rate, length, repCost, rating, features);
//		      films.add(film);
//		    }
//		    rs.close();
//		    stmt.close();
//		    conn.close();
//		  } catch (SQLException e) {
//		    e.printStackTrace();
//		  }
//		  return films;
//		}
//	}

}