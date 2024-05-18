package mainpackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import lists.*;


public class Driver {
	
	public static Scanner scan = new Scanner(System.in);
	public static Vocab vocablist=new Vocab();
	public static void main(String[] args) {
		int choice=1;
		while(choice!=0) {
			choice=primaryMenu();
			switch(choice) {
			case 1:browse_aTopic();break;
			case 2:insertNewTopic(2);break;
			case 3:insertNewTopic(3);break;
			case 4:removeTopic();break;
			case 5:modifyTopic();break;
			case 6:containsWord();break;
			case 7:loadfrom_aFile("Assignment3_inputfiles.txt");break;
			case 8:allWordStartingwLetter();break;
			case 9:vocablist.writeToFile();break;
			}
		}
		scan.close();
	}
	public static int primaryMenu() {
		
		System.out.print(" -----------------------------\r\n"
				+ "  Vocabulary Control Center\r\n"
				+ " -----------------------------\r\n"
				+ " 1 browse a topic\r\n"
				+ " 2 insert a new topic before another one\r\n"
				+ " 3 insert a new topic after another one\r\n"
				+ " 4 remove a topic\r\n"
				+ " 5 modify a topic\r\n"
				+ " 6 search topics for a word\r\n"
				+ " 7 load from a file\r\n"
				+ " 8 show all words starting with a given letter\r\n"
				+ " 9 save to file\r\n"
				+ " 0 exit\r\n"
				+ " -----------------------------\r\n"
				+ "Enter Your Choice: ");
		int choice=scan.nextInt();
		choice=choiceValidation(choice,0,9);
		System.out.println();
		
		return choice;
	}
	public static int choiceValidation(int choice,int lowerbound, int upperbound) {
		while(!(choice>=lowerbound&&choice<=upperbound)) {
			System.out.println("option out of range, try again!");
			choice=scan.nextInt();
		}
		return choice;
	}
	public static int pick_aTopicMenu() {
		System.out.println(" ------------------------------\r\n"
				+ "	Pick a topic\r\n"
				+ " ------------------------------");
		vocablist.displayTopicName();
		System.out.print(" 0 Exit\r\n"
				+ " ------------------------------\r\n"
				+ "Enter Your Choice:");
		int choice=scan.nextInt();
		choice=choiceValidation(choice,0,vocablist.size());
		//System.out.println();
		return choice;
	}
	public static void browse_aTopic() {
		int choice=1;
		do{
			choice=pick_aTopicMenu();
			if(choice!=0)
			vocablist.displayWords(vocablist.findByPosition(choice));
		}
		while(choice!=0);
	}
 	public static void insertNewTopic(int aOb) {
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		Wordslist words=new Wordslist();
		int choice_topic=pick_aTopicMenu();
		if (choice_topic!=0) {
			System.out.println("Enter a topic name:");
			String topic_name=scan.next();
			System.out.println("Enter a word - to quit press Enter:");
			while(true) {
				String word;
				try {
					word = br.readLine();
					if (word.isEmpty()) {
	                break;
	            }
	            words.Insert(word);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(aOb==2) {

				String name=vocablist.GetTopic(vocablist.findByPosition(choice_topic-1));
				vocablist.InsertAfterKey(topic_name, words, name);
			}
			if(aOb==3) {
				String name=vocablist.GetTopic(vocablist.findByPosition(choice_topic));
				vocablist.InsertAfterKey(topic_name, words, name);
			}
		}
	}
	public static void removeTopic() {
		int choice=pick_aTopicMenu();
		vocablist.removeNode(vocablist.findByPosition(choice));
	}
	public static String modifyTopicMenu() {
		System.out.print(" -----------------------------\r\n"
				+ "		Modify Topics Menu\r\n"
				+ " -----------------------------\r\n"
				+ " a add a word\r\n"
				+ " r remove a word\r\n"
				+ " c change a word\r\n"
				+ " 0 Exit\r\n"
				+ " -----------------------------\r\n"
				+ "Enter Your Choice:");
		String choice=scan.next();
		return choice;
	}
	public static void modifyTopic() {
		int topic_choice=pick_aTopicMenu();
		String choice=modifyTopicMenu();
		System.out.println("Enter a word");
		String word=scan.next();
		System.out.println();
		switch(choice) {
		case "a": vocablist.addword(vocablist.findByPosition(topic_choice), word); break;
		case "r": vocablist.removeWord(vocablist.findByPosition(topic_choice), word); break;
		case "c":
			System.out.println("Enter the word you would like to put instead ");
			String newword=scan.next();
			vocablist.changeWord(vocablist.findByPosition(topic_choice), word, newword);
			break;
		}
	}
	public static void loadfrom_aFile(String filename) {
		BufferedReader read1;
		Wordslist[] words;
		try {
			read1=new BufferedReader(new FileReader(filename));
			String line1;
			int array_length=0;
			while((line1=read1.readLine())!=null) {
				if(line1.contains("#")) {
					array_length++;
				}
			}
			words=new Wordslist[array_length];
			read1.close();
			read1=new BufferedReader(new FileReader(filename));
			String line0;
			String topic=null;
			int index=-1;
			while((line0=read1.readLine())!=null) {
				if(line0.contains("#")) {
					String temp=line0;
					topic=temp.substring(1,temp.length());
					index++;
					words[index]=new Wordslist();
				}
				if(!(line0.contains("#"))&&words[index]!=null) {
					words[index].Insert(line0);
				}
				if(topic!=null&&line0.contains("#")) {
					vocablist.InsertLastPosition(topic, words[index]);
				}
			}
			read1.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void allWordStartingwLetter() {
		ArrayList<String> arrlist= new ArrayList<String>();
		System.out.print("Enter a letter ");
		String letter=scan.next();
		arrlist=vocablist.findWord(letter);
		for(String value:arrlist) {
			System.out.println(value);
		}
	}
	public static void containsWord() {
		System.out.print("Enter a word: ");
		String word=scan.next();
		System.out.println(word+" is in "+vocablist.containsWord(word));
	}
}
