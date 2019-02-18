package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
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

		String sqltxt = "SELECT  film.id, film.title, film.description, film.release_year, film.language_id, film.rental_duration, film.rental_rate, film.length,  film.replacement_cost, film.rating, film.special_features, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";
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
				film.setLanguageName(rs.getString(12));
				film.setFilmActors(findActorsByFilmId(film.getId()));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
		return film;
	}

	@Override
	public List<Film> findFilmByKeyword(String filmKeyword) {
		List<Film> filmKeywords = new ArrayList<>();
		Film film = null;
		String user = "student";
		String pass = "student";
		String sqltxt = "SELECT film.id, film.title, film.description, film.release_year, film.language_id, film.rental_duration, film.rental_rate, film.length,  film.replacement_cost, film.rating,  film.special_features, language.name FROM film JOIN language ON film.language_id = language.id WHERE description LIKE ? OR title LIKE ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sqltxt);) {
			stmt.setString(1, "%" + filmKeyword + "%");
			stmt.setString(2, "%" + filmKeyword + "%");
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
				film.setLanguageName(rs.getString(12));
				film.setFilmActors(findActorsByFilmId(film.getId()));
				filmKeywords.add(film);

			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}

		return filmKeywords;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String user = "student";
		String pass = "student";
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		String user = "student";
		String pass = "student";
		String sql = "SELECT actor.id, first_name, last_name "
				+ " FROM film JOIN film_actor ON film.id = film_actor.film_id "
				+ "JOIN actor ON film_actor.actor_id = actor.id " + "WHERE film.id = ?";
		/*
		 * Full Select from mysql FOR all film items, language, and Actor names String
		 * sql =
		 * "SELECT film.id, film.title, film.description, film.release_year, film.language_id, film.rental_duration, film.rental_rate, film.length,  film.replacement_cost, film.rating,  film.special_features, language.name, actor.first_name, actor.last_name FROM film JOIN language ON film.language_id = language.id JOIN film_actor ON film.id = film_actor.film_id JOIN actor ON film_actor.actor_id = actor.id WHERE description LIKE ? OR title LIKE ? ORDER by film.title \G"
		 * ;
		 */

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("actor.id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);
//				actor.setActor(actors);
				
//				System.out.println(actors);
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return actors;

	}

}