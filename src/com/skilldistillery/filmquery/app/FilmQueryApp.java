package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
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
		System.out.println("Film Query App started.");
		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

		System.out.print("1. Select a film Id: ");
		int filmId = input.nextInt();
		Film film = db.findFilmById(filmId);
		System.out.print(film);

		Film film2 = db.findFilmByKeyword(filmId);
		System.out.println(film2);
		
//		System.out.println("\n Exiting...");
//		System.exit(1);
//		System.out.print("Select an actor Id:");
//		int actorId = input.nextInt();
//		db.findActorById(actorId);

	}

}
