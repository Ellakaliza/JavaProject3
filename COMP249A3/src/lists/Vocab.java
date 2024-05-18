package lists;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Vocab {
	private class Node{
		String topic;
		Wordslist wordslist;
		Node next;
		Node previous;
		int index;
		Node(String topic,Wordslist w){
			this.topic=topic;
			this.wordslist=w;
		}
	}
	private Node head;
	private Node tail;
	private int index_counter;
	public Vocab() {
		head=null;
		tail=null;
		index_counter=0;
	}
	public boolean isEmpty() {
			return head==null ;
	}
	/*public void InsertFirstPosition(String topic,Wordslist w) {
		Node newnode=new Node(topic,w);
		if(isEmpty()) {
			tail=newnode;
		}
		else {
			head.previous=newnode;
		}
		newnode.next=head;
		head=newnode;
	}*/
	public void InsertLastPosition(String topic,Wordslist w) {
		Node newnode=new Node(topic,w);
		index_counter++;
		newnode.index=index_counter;
		if(isEmpty()) {
			head=newnode;
		}
		else {
			tail.next=newnode;
			newnode.previous=tail;
		}
		tail=newnode;
	}
	public void InsertAfterKey(String topic,Wordslist w,String key) {
		Node newnode=new Node(topic,w);
		index_counter++;
		Node currentnode=head;
		while(!(currentnode.topic.equals(key))) {
			currentnode=currentnode.next;
			if(currentnode==null)
				return;
		}
		if(currentnode==tail) {
			newnode.next=null;
			tail=newnode;
		}
		else {
			newnode.next=currentnode.next;
			currentnode.next.previous=newnode;
		}
		newnode.previous=currentnode;
		currentnode.next=newnode;
		
		Node index_adjuster=tail;
		int i=index_counter;
		while(index_adjuster!=null) {
			index_adjuster.index=i;
			i--;
			index_adjuster=index_adjuster.previous;
		}
	}
	public int size() {
		int count =0;
		Node position=head;
		while(position!=null) {
			count++;
			position=position.next;
		}
		return count;
	}
	public Node findByName(String target) {
		Node position=head;
		String topicAtPosition;
		while(position!=null) {
			topicAtPosition= position.topic;
			if(topicAtPosition.equals(target))
				return position;
			position=position.next;
		}
		return null;
	}
	public Node findByPosition(int target) {
		Node position=head;
		int indexAtPosition;
		while(position!=null) {
			indexAtPosition= position.index;
			if(indexAtPosition==target)
				return position;
			position=position.next;
		}
		return null;
	}
	public void display() {
		Node node = head;
		while(node != null) {
			System.out.println(node.topic);
			//if(node.wordslist!=null)
			node.wordslist.display();
			System.out.println();
			node=node.next;
		}
	}
	public void displayTopicName() {
		Node node = head;
		int i=1;
		while(node != null) {
			System.out.println(" "+node.index+" "+node.topic);
			//System.out.println();
			node=node.next;
			i++;
		}
	}
	public void displayWords(Node node) {
		node.wordslist.display();
	}
	public String GetTopic(Node node) {
		return node.topic;
	}
	public void removeNode(Node nodeToRemove) {
		index_counter--;
        if (nodeToRemove == null) {
            return;
        }
        
        if (nodeToRemove == head) {
            head = head.next;
        }
        
        if (nodeToRemove == tail) {
            tail = tail.previous;
        }
        
        if (nodeToRemove.previous != null) {
            nodeToRemove.previous.next = nodeToRemove.next;
        }
        
        if (nodeToRemove.next != null) {
            nodeToRemove.next.previous = nodeToRemove.previous;
        }
        Node index_adjuster=tail;
        int i=index_counter;
		while(index_adjuster!=null) {
			index_adjuster.index=i;
			i--;
			index_adjuster=index_adjuster.previous;
		} 
    }
	public void addword(Node node,String word) {
		node.wordslist.Insert(word);
	}
	public void removeWord(Node node, String word) {
		node.wordslist.removeWord(word);
	}
	public void changeWord(Node node, String oldword,  String newword) {
		node.wordslist.changeNodeWord(oldword, newword);
	}
	public ArrayList<String> findWord(String letter) {
		ArrayList<String> biglist = new ArrayList<String>();
		Node node=head;
		while(node != null) {
			ArrayList<String> minilist = new ArrayList<String>();
			minilist=node.wordslist.matchWord(letter);
			biglist.addAll(minilist);
			node=node.next;
		}
		return biglist;
	}
	public void writeToFile() {
		PrintWriter writing;
		try {
			writing=new PrintWriter(new FileWriter("Assignment3_outputfile.txt"));
			writing.close();
			Node node=head;
			while(node!=null) {
				writing=new PrintWriter(new FileWriter("Assignment3_outputfile.txt",true));
				writing.println("#"+node.topic);
				writing.close();
				node.wordslist.writeToFile();
				node=node.next;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String containsWord(String word) {
		Node node=head;
		while(node!=null) {
			if(node.wordslist.containsWord(word))
				return "the "+node.topic+" category";
			node=node.next;
		}
		return "no category";
	}
}

