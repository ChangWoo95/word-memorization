package p1;

import p1.Pair;

public class WordNode {

	private Pair<String, String> wordmean; // wordmean Pair
	private WordNode pLeft; // BST Left Pointer
	private WordNode pRight; // BST Right Pointer
	private WordNode pNext; // Queue Next Pointer

	WordNode() {
		wordmean = wordmean.of(null, null); // wordmean has Pair class
		pLeft = null;
		pRight = null;
		pNext = null;
	}

	public String GetWord() {
		return wordmean.first;
	}

	public String GetMean() {
		return wordmean.second;
	}

	public WordNode GetLeft() {
		return pLeft;
	}

	public WordNode GetRight() {
		return pRight;
	}

	public WordNode GetNext() {
		return pNext;
	}

	public void SetWord(String word) {
		wordmean.first = word;
	}

	public void SetMean(String mean) {
		wordmean.second = mean;
	}

	public void SetLeft(WordNode node) {
		pLeft = node;
	}

	public void SetRight(WordNode node) {
		pRight = node;
	}

	public void SetNext(WordNode node) {
		pNext = node;
	}
}