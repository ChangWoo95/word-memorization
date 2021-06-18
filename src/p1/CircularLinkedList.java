package p1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CircularLinkedList {
	public WordNode pHead;
	public WordNode tail; // Circular Linked List Head Pointer

	public CircularLinkedList() {
		pHead = null; // Initialize values ​​in the constructor
	}

	// TEST, LOAD
	public void Insert(WordNode node) {
		if (pHead == null) { // if first node Inserting from Manager.java
			pHead = node; // TEST function or LOAD function
			pHead.SetNext(pHead);
			return;
		}

		WordNode Pnode = pHead; // else
		while (Pnode.GetNext() != pHead)
			Pnode = Pnode.GetNext();

		Pnode.SetNext(node);
		node.SetNext(pHead);
	}

	public WordNode Search(String word) {
		if (pHead == null) // if there's no nodes on CircularLinkedList
			return null;

		WordNode s = pHead; // else

		do {
			if (word.compareTo(s.GetWord()) == 0) // find Front of pHead's Node
				return s;
			s = s.GetNext();
		} while (s != pHead);
		return null; // if it's failed
	}

	public boolean Print() throws IOException {
		if (pHead == null) // if there's no nodes on CircularLinkedList
			return false;
		BufferedWriter print = new BufferedWriter(new FileWriter("log.txt", true)); // open log.txt file
		WordNode p = pHead; // Printing from pHead to front of pHead node.
		print.write("======== print ========" + "\n");
		do {
			print.write(p.GetWord() + " " + p.GetMean() + "\n"); // print & move to next node
			p = p.GetNext();
		} while (p != pHead);
		print.write("=======================" + "\n\n");
		print.close();
		return true;
	}

	public boolean Save() throws IOException {
		if (pHead == null) // if there's no nodes on CircularLinkedList
			return false;
		BufferedWriter save = new BufferedWriter(new FileWriter("memorized_word.txt"));
		WordNode s = pHead; // Save from pHead to front of pHead node

		do {
			save.write(s.GetWord() + " " + s.GetMean() + "\n"); // save node's data & move to next node
			s = s.GetNext();
		} while (s != pHead);
		save.close();
		return true;
	}
}