package eg.edu.alexu.csd.datastructure.hangman.cs5.Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HangmanClass
{
	Integer wordCount;
	String randomWord;
	Integer maxWrongGuesses;
	String[] dictionary;
	ArrayList<Character> discovered;
	
	public HangmanClass(Integer max, String path) throws Exception
	{
		this.wordCount = 0;
		this.discovered = new ArrayList<Character>();
		setMaxWrongGuesses(max);		
		setDictionary(this.loadFile(path));
		if(dictionary.length == 0)
			throw new Exception("File is Empty");
		this.randomWord = selectRandomSecretWord();
	}
	
	public boolean isAlpha(String word)
	{
		for(int i = 0; i < word.length();i++)
		{
			Character x = word.charAt(i);
			if(!((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')))
				return false;
		}
		return true;
			
	}
	
	public void setDictionary(String[] words)
	{
		this.dictionary = words;
	}
	
	private String[] loadFile(String path) throws Exception
	{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		ArrayList<String> Words = new ArrayList<String>();
			
		String word = reader.readLine();
		
		while(word != null && word.length() != 0)
		{
			Words.add(word.toLowerCase());
			this.wordCount++;
			word = reader.readLine();
		}
		reader.close();
		return Words.toArray(new String[Words.size()]);
	}
	
	
	
	public void setMaxWrongGuesses(Integer max)
	{
		this.maxWrongGuesses = max;
	}
	
	public void gameLoop() throws Exception
	{
		Scanner cin = new Scanner(System.in);
		System.out.println("Game Started!");
		while(true)
		{
			String c = cin.nextLine().toLowerCase();
			if(c.length() == 0)
				continue;
			String ret = this.guess(c.charAt(0));
			System.out.println(ret);
			if(ret.equals(this.randomWord))
			{
				System.out.println("Congrats! you win!");
				break;
			}
			if(ret.equals("You Lost!"))
				break;
		}
		cin.close();

	}
	
	public String guess(Character c) throws Exception
	{
		if(!isAlpha(this.randomWord))
			throw new Exception("The word is buggy!");
		if(this.randomWord.indexOf(c) == -1)
		{
			this.maxWrongGuesses--;
			if(this.maxWrongGuesses == 0)
				return "You Lost!";
			return "Wrong Guess! " + maxWrongGuesses + " remaining!" ;
		}else
		{
			char []placeholder = new char[this.randomWord.length()];
			Arrays.fill(placeholder, '*');
			this.discovered.add(c);
			for(Character x: discovered)
			{
				for(int i = 0; i < this.randomWord.length(); i++)
					if(x == this.randomWord.charAt(i))
						placeholder[i] = x;
			}
			if(new String(placeholder) == this.randomWord)
				return (new String(placeholder)) + "\nCongrats! Gameover";
			return new String(placeholder);
		}
	}
	
	
	public String selectRandomSecretWord()
	{
		Random randNumGenerator = new Random();
		return this.dictionary[randNumGenerator.nextInt(this.wordCount)];
	}
	
	
}
