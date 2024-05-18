package lists;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Wordslist {
	private class Node{
		String word;
		Node next;
		Node(String word){
			this.word=word;
		}
		void displayNode() {
			System.out.println(word);
		}
	}
	private Node head;
	public Wordslist() {
		head=null;
	}
	public boolean isEmpty() {
		if (head.next==null)
			return true;
		else
			return false;
	}
	public void Insert(String word) {
		Node newnode=new Node(word);
		newnode.next=head;
		head=newnode;
	}
	public void display() {
		Node node = head;
		while(node != null) {
			node.displayNode();
			node=node.next;
		}
	}
	public void removeWord(String word) {
        if (head == null) {
            return; 
        }
        
        if (head.word.equals(word)) {
            head = head.next; 
            return;
        }
        
        Node current = head;
        while (current.next != null) {
            if (current.next.word.equals(word)) {
                current.next = current.next.next; 
                return;
            }
            current = current.next;
        }
    }
	public void changeNodeWord(String oldword, String newword) {
        Node current = head;
        while (current != null) {
            if (current.word.equals(oldword)) {
                current.word = newword; // Update the data of the node
                return;
            }
            current = current.next;
        }
    }
	public ArrayList<String> matchWord(String letter) {
		ArrayList<String> minilist = new ArrayList<String>();
		Node node=head;
		while(node != null) {
			if(!(node.word.equals(""))) {
				if (node.word.substring(0,1).equalsIgnoreCase(letter)) {
				minilist.add(node.word);
				}
			}
			node=node.next;
		}
		return minilist;
	}
	public void writeToFile() {
		PrintWriter writer;
		try {
			writer=new PrintWriter(new FileWriter("Assignment3_outputfile.txt",true));
			Node node=head;
			while(node!=null) {
				writer.println(node.word);
				node=node.next;
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean containsWord(String word) {
		Node node=head;
		while(node!=null) {
			if(node.word.equals(word))
				return true;
			node=node.next;
		}
		return false;
	}
}

