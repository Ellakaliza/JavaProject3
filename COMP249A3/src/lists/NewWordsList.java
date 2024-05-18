package lists;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class NewWordsList {
	private class Node implements Comparable<Node>{
		String word;
		Node next;
		Node(String word){
			this.word=word;
		}
		void displayNode() {
			System.out.println(word);
		}
		@Override
		public int compareTo(Node o) {
			int result = this.word.compareToIgnoreCase(o.word);
	        if(result==0)
	            result = this.word.compareTo(o.word);
	        return result;
		}
	}
	private Node head;
	public NewWordsList() {
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
    public void sortList()
    {
 
        // Node current will point to head
        Node current = head, index = null;
 
        String temp;
 
        if (head == null) {
            return;
        }
        else {
            while (current != null) {
                // Node index will point to node next to
                // current
                index = current.next;
 
                while (index != null) {
                    
                    if (current.compareTo(index)<0) {
                        temp = current.word;
                        current.word = index.word;
                        index.word = temp;
                    }
 
                    index = index.next;
                }
                current = current.next;
            }
        }
    }
	public void display() {
		this.sortList();
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
