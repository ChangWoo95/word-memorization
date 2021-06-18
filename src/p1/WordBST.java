package p1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class WordBST {

	public WordNode root; // Word BST Root

	public WordBST() {
		root = null;
	}

	public void Insert(WordNode node) // insert function
	{
		if (root == null) {
			root = node;
			return;
		}

		WordNode p = root;
		WordNode pp = null;
		/* repetition: search Where to Insert a Node */
		while (p != null) {
			pp = p;
			if (node.GetWord().compareTo(p.GetWord()) < 0)
				p = p.GetLeft();
			else if (node.GetWord().compareTo(p.GetWord()) > 0)
				p = p.GetRight();
			else
				break;
		}
		/* Insert Node */
		p = node;
		if (root != null) {
			if (node.GetWord().compareTo(pp.GetWord()) < 0)
				pp.SetLeft(node);
			else
				pp.SetRight(node);
		}
	}

	/* delete function */
	public WordNode Delete(String word) {
		WordNode p = root, q = null;
		WordNode temp = new WordNode(); // allocation
		/* repetition : search delete node */
		while (p != null && word.compareTo(p.GetWord()) != 0) {
			q = p;
			if (word.compareTo(p.GetWord()) < 0)
				p = p.GetLeft();
			else
				p = p.GetRight();
		}

		if (p == null)
			return null;

		temp.SetWord(p.GetWord());
		temp.SetMean(p.GetMean());

		/* both child are not exist */
		if (p.GetLeft() == null && p.GetRight() == null) {
			if (q == null)
				root = null;
			else if (q.GetLeft() == p)
				q.SetLeft(null); // q's leftchild ==NULL
			else
				q.SetRight(null); // q's rightchild == NULL

			p = null;
		}

		/* right child is exist */
		else if (p.GetLeft() == null) {
			if (q == null)
				root = p.GetRight();
			else if (q.GetLeft() == p)
				q.SetLeft(p.GetRight());
			else
				q.SetRight(p.GetRight());

			p = null;
		}

		/* left child is exist */
		else {
			WordNode prevprev = p;
			WordNode prev = p.GetLeft();
			WordNode curr = p.GetLeft().GetRight();
			/* repetition: curr is not nullptr */
			while (curr != null) {
				prevprev = prev;
				prev = curr;
				curr = curr.GetRight();
			}

			p.SetWord(prev.GetWord());
			p.SetMean(prev.GetMean());

			if (prevprev == p)
				prevprev.SetLeft(prev.GetRight());
			else
				prevprev.SetRight(prev.GetLeft());

			prev = null;
		}
		return temp;
	}

	/* search function */
	public WordNode Search(String word) {
		if (root == null)
			return null;

		WordNode node = root;
		/* repetition: search the node */
		while (node != null) {
			if (word.compareTo(node.GetWord()) < 0)
				node = node.GetLeft();
			else if (word.compareTo(node.GetWord()) > 0)
				node = node.GetRight();
			else
				return node;
		}
		return null;
	}

	/* Print function */
	public boolean Print() throws IOException {
		if (root == null)
			return false;

		Preorder(root); // Reculsive Preorder
		return true;
	}

	/* Save function */
	public boolean Save() throws IOException {
		if (root == null)
			return false;

		BufferedWriter save = new BufferedWriter(new FileWriter("memorizing_word.txt", true)); // file open for write
		Stack<WordNode> stack = new Stack(); // allocation with stack
		WordNode node = root;

		stack.push(node);
		/* repetition: print node */
		while (stack.empty() != true && root != null) {
			node = stack.peek(); // top()
			stack.pop(); // delete

			save.write(node.GetWord() + " " + node.GetMean() + "\n"); // print
			if (node.GetRight() != null)
				stack.push(node.GetRight());
			if (node.GetLeft() != null)
				stack.push(node.GetLeft());
		}
		save.close(); // file close
		return true;
	}

	/* Preorder function */
	public void Preorder(WordNode node) throws IOException {
		BufferedWriter print = new BufferedWriter(new FileWriter("log.txt", true)); // file open for write
		/* Reculsive Preorder */
		if (node != null) {
			print.write(node.GetWord() + " " + node.GetMean() + "\n");
			Preorder(node.GetLeft());
			Preorder(node.GetRight());
		}
		print.close();
	}

}