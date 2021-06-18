package p1;

public class AlphabetNode {
	private char alphabet;
	private WordBST bst;
	private AlphabetNode pLeft; // BST Left Pointer
	private AlphabetNode pRight; // BST Right Pointer

	public AlphabetNode() {
		alphabet = '\0';
		bst = null;
		pLeft = null;
		pRight = null;
	}

	public char GetAlphabet() {
		return alphabet;
	}

	public WordBST GetBST() {
		return bst;
	}

	public AlphabetNode GetLeft() {
		return pLeft;
	}

	public AlphabetNode GetRight() {
		return pRight;
	}

	public void SetAlphabet(char alphabet) {
		this.alphabet = alphabet;
	}

	public void SetLeft(AlphabetNode node) {
		pLeft = node;
	}

	public void SetRight(AlphabetNode node) {
		pRight = node;
	}

	public void SetBST(WordBST node) {
		bst = node;
	}

}