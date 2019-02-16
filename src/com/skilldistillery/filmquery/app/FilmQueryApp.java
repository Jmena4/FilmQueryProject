package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
//		System.out.println("1. Film Query App started.");
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() {
//		Film film = db.findFilmById(0);
//		System.out.println(film);
//		Actor actor = db.findActorById(1);
//		System.out.println(actor);

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
					"Choose one of the following options: \n1) Select film by id \n2) Select film by keyword \n3) Exit program \n");
			choice = input.nextInt();
			if (choice == 1) {
				System.out.print("Enter film id to search: ");
				int filmId = input.nextInt();
				Film film = db.findFilmById(filmId);
				System.out.print(film);
			} else if (choice == 2) {
				System.out.print("Enter film keyword to search: ");
				String filmId = input.next();
				List<Film> films = db.findFilmByKeyword(filmId);
				System.out.println(films);
			} else {
				System.out.println("\n Exiting...");
				System.exit(1);
			}
		} while (choice != 3);
	}
}