package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() {
//		Film film = db.findFilmById(0);
//		System.out.println(film);
		List<Actor> film = db.findActorsByFilmId(30);
		System.out.println(film);

	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		System.out.println("Film Query App started... \n");
		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		int choice = 0;
		do {
			System.out.print(
					"\nChoose one of the following options: \n1) Select film by id \n2) Select film by keyword \n3) Exit program \n");
			choice = input.nextInt();
			if (choice == 1) {
				System.out.print("Enter film id to search: ");

				int filmId;
				try {
					filmId = input.nextInt();
					Film film = db.findFilmById(filmId);
					printFindFilmByIdDisplay(film);
					System.out.println();
				} catch (Exception e) {
					System.err.println("Entry not found.");
				}

			} else if (choice == 2) {
				System.out.print("Enter film keyword to search: ");
				String keyword;
				try {
					keyword = input.next();
					List<Film> film = db.findFilmByKeyword(keyword);

					printFindFilmByKeywordDisplay(film);

				} catch (Exception e) {
					System.err.println("Keyword not found, try again.");
				}
			} else {
				System.out.println("\n Exiting...");
				System.exit(1);
			}
		} while (choice != 3);

	}

//	private void printFindActorByIdDisplay(List<Actor> actors) {
//		for (Actor actor : actors) {
//			System.out.println("First_name: '" + actor.getFirstName() + "\nLast_name: " + actor.getLastName());
//		}
//	}

	private void printFindFilmByIdDisplay(Film film) {
		System.out.println("\n************ Film ************");
		System.out.println("Title: '" + film.getTitle() + "' \nYear: '" + film.getReleaseYear() + "' \nRating: '"
				+ film.getRating() + "' \nDescription: '" + film.getDescription() + "'\nLanguage: "
				+ film.getLanguageName() + "\n");
	}

	private void printFindFilmByKeywordDisplay(List<Film> films) {
		for (Film film : films) {
			System.out.println("\n************ Film ************");
			System.out.println("Id; " + film.getId() + " Title: '" + film.getTitle() + "' \nYear: '"
					+ film.getReleaseYear() + "' \nRating: '" + film.getRating() + "' \nDescription: '"
					+ film.getDescription() + "'\nLanguage: " + film.getLanguageName() + "\n");
//			int filmId = film.getId();
//			System.out.println(film.getFilmActors());
			System.out.println("\n************ Actors ************");
//////			printFindActorByIdDisplay(listFilmId);
			for (Actor actors : film.getFilmActors()) {
				System.out.println("Actor Id: " + actors.getId() + " First_name: '" + actors.getFirstName()
						+ "\nLast_name: " + actors.getLastName());
			}
		}
	}
}
