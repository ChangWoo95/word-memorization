package p1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class AlphabetBST {
	private AlphabetNode root;
	private int WordCnt;

	public AlphabetBST() {
		root = null;
		WordCnt = 0;

		char[] alpha = { 'P', 'H', 'X', 'D', 'L', 'T', 'Z', 'B', 'F', 'J', 'N', 'R', 'V', 'Y', 'A', 'C', 'E', 'G', 'I',
				'K', 'M', 'O', 'Q', 'S', 'U', 'W' };// insert alphabet

		for (int i = 0; i < 26; i++) // 26 ( number of alphabet )
		{
			AlphabetNode pnew = new AlphabetNode();
			WordBST newBST = new WordBST(); // inserting AlphabetNode when program started
			pnew.SetBST(newBST);
			pnew.SetAlphabet(alpha[i]);
			Insert(pnew);
		}
	}

	public void Insert(AlphabetNode node) // inserting AlphabetNode(Created AlphabetBST) when program started
	{
		AlphabetNode p = new AlphabetNode(), pp = null;
		p = root;
		if (root == null) {
			root = node; // if it's first node
			return;
		}

		while (p != null) {
			pp = p;
			if (node.GetAlphabet() < p.GetAlphabet()) // if Inserting node's alphabet < Existing node's alphabet
				p = p.GetLeft();
			else
				p = p.GetRight();
		}

		p = node;
		if (root != null) {
			if (node.GetAlphabet() < pp.GetAlphabet())// if Inserting node's alphabet < Existing node's alphabet
				pp.SetLeft(p);
			else
				pp.SetRight(p);
		}
	}

	public boolean Print() throws IOException {

		if (getCnt() == 0) // if there's no nodes on BST
			return false;

		BufferedWriter print = new BufferedWriter(new FileWriter("log.txt", true));
		print.write("======== print ========" + "\n");
		print.close();
		Preorder(root); // call recursive Preorder function from root
		BufferedWriter print2 = new BufferedWriter(new FileWriter("log.txt", true));
		print2.write("=======================" + "\n\n");
		print2.close();
		return true;
	}

	public AlphabetNode Search(char alpha) {
		int change = alpha - 32; // Lowercase to uppercase alphabet used ASCII
		char alphabet = (char) change;
		AlphabetNode node = root; // start searching from root
		while (node != null) {
			if (alphabet < node.GetAlphabet())
				node = node.GetLeft();
			else if (alphabet > node.GetAlphabet())
				node = node.GetRight();
			else
				return node;
		}
		return null; // if failed Node Searching, but it is not used.
	}

	public boolean Save() throws IOException {
		if (WordCnt == 0) // if no nodes on BST
			return false;

		BufferedWriter save = new BufferedWriter(new FileWriter("memorizing_word.txt"));
		Stack<AlphabetNode> stack = new Stack(); // Allocate memory to new Stack
		AlphabetNode node = root; // Stack Pushing from root

		stack.push(node); // Pushing root

		while (stack.empty() != true && root != null) {
			node = stack.peek(); // copy Top node
			stack.pop(); // delete Top node

			node.GetBST().Save(); // call BST's Save function

			if (node.GetRight() != null)
				stack.push(node.GetRight()); // Put the right node first, in reverse order
			if (node.GetLeft() != null) // because you have to call it from the left node
				stack.push(node.GetLeft());
		}
		save.close();
		return true;
	}

	public void Preorder(AlphabetNode node) throws IOException {
		if (node != null) { // recursive Preorder function
			node.GetBST().Print();
			Preorder(node.GetLeft());
			Preorder(node.GetRight());
		}
	}

	public int getCnt() {
		return WordCnt; // check number of BST's nodes
	}

	public void setCnt(int cnt) {
		WordCnt += cnt; // add integer to number of BST nodes.
	}
}