package eg.edu.alexu.csd.datastructure.hangman.cs5.Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import Classes.HangmanClass;


class gameTester {

	@Test
	public static void main(String[] args) throws Exception
	{
		
		Scanner cin = new Scanner(System.in);
		System.out.println("Enter max number of guesses: ");
		int numGuess = cin.nextInt();
		String path = Paths.get(".").toAbsolutePath().normalize().toString() + "\\words.txt";
		HangmanClass game = new HangmanClass(numGuess, path);
		game.gameLoop();
		cin.close();
	}

}