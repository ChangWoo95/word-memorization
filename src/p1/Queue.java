package p1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import p1.WordNode;

public class Queue {
	private int Qcnt;
	public WordNode pHead;
	public WordNode pTail;

	Queue() // constructor
	{
		Qcnt = 0;
		pHead = null; // When Queuelist created Initialize Qcnt, pHead, pTail.
		pTail = null;
	}

	// LOAD, ADD
	public void Push(WordNode push) {

		if (pHead == null) {
			pHead = push; // if it's first Push Node
			pTail = push;
		} else {
			pTail.SetNext(push); // else
			pTail = push;
		}

	}

	// MOVE
	public WordNode Pop() {
		if (pHead == null) // If there is no node
			return null;

		WordNode popNode = pHead; // else
		WordNode retNode = new WordNode(); // Assign memory to new node
		pHead = pHead.GetNext();

		retNode.SetWord(popNode.GetWord());
		retNode.SetMean(popNode.GetMean()); // copy to data new node
		popNode = null; // = delete popNode
		return retNode;
	}

	// SEARCH, UPDATE
	public WordNode Search(String word) {
		WordNode s = pHead; // searching from pHead to pTail
		while (s != null) {
			if (s.GetWord().compareTo(word) == 0) // if find same word Node
				return s;
			s = s.GetNext(); // else
		}
		return null; // if failed to find same word Node
	}

	// PRINT
	public boolean Print() throws IOException {
		if (pHead == null) // if there's no Node
			return false;

		BufferedWriter print = new BufferedWriter(new FileWriter("log.txt", true)); // open log.txt
		// file open
		WordNode p = pHead; // Print from pHead to pTail

		print.write("======== print ========" + "\n");
		while (p != null) {
			print.write(p.GetWord() + " " + p.GetMean() + "\n");
			p = p.GetNext();
		}
		print.write("=======================" + "\n\n");
		print.close();
		return true;
	}

	// SAVE
	public boolean Save() throws IOException {
		if (pHead == null) // if there's no Node
			return false;

		BufferedWriter save = new BufferedWriter(new FileWriter("to_memorize_word.txt")); // ready to save
																							// to_memorize_word.txt file
		WordNode p = pHead; // save from pHead to pTail

		while (p != null) {
			save.write(p.GetWord() + " " + p.GetMean() + "\n");
			p = p.GetNext();
		}
		save.close();
		return true;
	}

	public int getCnt() {
		return Qcnt; // check number of the Queue list's nodes
	}

	public void setCnt(int cnt) {
		Qcnt += cnt; // add the number of Queue list nodes
	}

}