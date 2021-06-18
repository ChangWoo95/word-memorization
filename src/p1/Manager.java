package p1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Manager {

	public CircularLinkedList cll = new CircularLinkedList(); // MEMORIZED Circular Linked List
	public AlphabetBST bst = new AlphabetBST(); // MEMORIZING BST
	public Queue queue = new Queue();; // TO_MEMORIZE Queue

	int moveCnt = 0;
	String order = null;
	String searchWord = null;
	String updateWord = null;
	String updateMean = null;
	String testWord = null;
	String testMean = null; // Declare global variables to replace factors

	public Manager() {
	}

	public void run(String command) throws IOException // manager run
	{
		BufferedWriter fout = new BufferedWriter(new FileWriter("log.txt", true)); // open log.txt
		BufferedReader fin = new BufferedReader(new FileReader("command.txt")); // open command.txt
		String[] tmp = null; // single-line variable
		String cmd = null; // command language variable

		while ((cmd = fin.readLine()) != null) { // repeat, if the file doesn't end
			tmp = cmd.split(" ");

			if (tmp[0].equals("LOAD")) { // LOAD
				if (LOAD())
					printSuccessCode("LOAD");
				else
					printErrorCode(100);
			} else if (tmp[0].equals("ADD")) { // ADD
				if (ADD())
					printSuccessCode("ADD");
				else
					printErrorCode(200);
			} else if (tmp[0].equals("MOVE")) { // MOVE
				if (tmp.length != 2) // exception handling
					printErrorCode(200);
				else {
					moveCnt = Integer.valueOf(tmp[1]); // move factor
					if (MOVE())
						printSuccessCode("MOVE");
					else
						printErrorCode(300);
				}
			} else if (tmp[0].equals("SAVE")) { // SAVE
				if (SAVE())
					printSuccessCode("SAVE");
				else
					printErrorCode(400);
			} else if (tmp[0].equals("TEST")) { // TEST
				if (tmp.length != 3) // exception handling
					printErrorCode(500);
				else {
					testWord = (tmp[1].toLowerCase()); // test factor, exception handling
					testMean = tmp[2]; // test factor
					if (TEST())
						testpassCode("TEST");
					else
						printErrorCode(500);
				}
			} else if (tmp[0].equals("SEARCH")) { // SEARCH
				if (tmp.length != 2) // exception handling
					printErrorCode(600);
				else {
					searchWord = (tmp[1].toLowerCase()); // test factor, exception handling
					if (SEARCH())
						continue;
					else
						printErrorCode(600);
				}
			} else if (tmp[0].equals("PRINT")) { // PRINT
				if (tmp.length != 2) // exception handling
					printErrorCode(700);
				else {
					if (tmp[1].equals("TO_MEMORIZE")) { // Queue
						order = tmp[1]; // print factor
						if (PRINT())
							continue;
						else
							printErrorCode(700);
					} else if (tmp[1].equals("MEMORIZING")) { // BST
						order = tmp[1]; // print factor
						if (PRINT())
							continue;
						else
							printErrorCode(700);
					} else if (tmp[1].equals("MEMORIZED")) { // Circle Linked List
						order = tmp[1]; // print factor
						if (PRINT())
							continue;
						else
							printErrorCode(700);
					} else
						printErrorCode(700);
				}
			} else if (tmp[0].equals("UPDATE")) { // UPDATE
				if (tmp.length != 3) // exception handling
					printErrorCode(800);
				else {
					updateWord = tmp[1].toLowerCase(); // update factor
					updateMean = tmp[2];
					if (UPDATE())
						continue;
					else
						printErrorCode(800);
				}
			} else if (tmp[0].equals("EXIT")) { // EXIT
				printSuccessCode("EXIT");
				return;
			} else
				printErrorCode(0); // exception handling
		}

		fin.close(); // file close
		fout.close(); // file close

		return;
	}

	public boolean LOAD() throws IOException // LOAD
	{
		boolean a = false, b = false, c = false; 	// exception handling 1
		char k;
		int count = 0;

		for (int i = 97; i < 123; i++) {
			k = (char) i;
			if (bst.Search(k).GetBST().root == null)
				count++;
		}
		if (queue.pHead != null)
			a = true; // empty?
		if (count != 26)
			b = true;
		if (cll.pHead != null)
			c = true;

		if (a == true || b == true || c == true) 	// when all three lists are empty
			return false;

		FileReader file1 = null; 					// exception handling 2
		FileReader file2 = null;
		FileReader file3 = null;
		boolean f1 = false, f2 = false, f3 = false;
		try {
			file1 = new FileReader("to_memorize_word.txt");
		} catch (FileNotFoundException e) {
			f1 = true;
		}
		try {
			file2 = new FileReader("memorizing_word.txt");
		} catch (FileNotFoundException e) {
			f2 = true;
		}
		try {
			file3 = new FileReader("memorized_word.txt");
		} catch (FileNotFoundException e) {
			f3 = true;
		}

		if (f1 == true && f2 == true && f3 == true)
			return false; 							// when all three files fail to open

		BufferedReader fin1;
		BufferedReader fin2;
		BufferedReader fin3;

		String line;
		int cnt = 0; // exception handling

		if (f1 == false) { // TO_MEMORIZE FILE
			fin1 = new BufferedReader(file1); // file open
			while ((line = fin1.readLine()) != null) {
				WordNode node1 = new WordNode();

				String[] wordmean = line.split(" ");

				if (node1 == null) // exception handling
					continue;

				node1.SetWord(wordmean[0]);
				node1.SetMean(wordmean[1]);

				queue.Push(node1);
				queue.setCnt(1);
				cnt++;
			}
			fin1.close();
		}
		if (f2 == false) { 						// MEMORIZING FILE
			fin2 = new BufferedReader(file2);	 // file open
			while ((line = fin2.readLine()) != null) {
				AlphabetNode node2_1 = new AlphabetNode();
				WordNode node2_2 = new WordNode();

				String[] wordmean = line.split(" ");
				if (wordmean == null) 			// exception handling
					continue;

				node2_2.SetWord(wordmean[0]);
				node2_2.SetMean(wordmean[1]);

				node2_1 = bst.Search(node2_2.GetWord().charAt(0));
				node2_1.GetBST().Insert(node2_2);
				bst.setCnt(1);
				cnt++;
			}
			fin2.close();
		}
		if (f3 == false) { 						// MEMORIZED FILE
			fin3 = new BufferedReader(file3); 		// file open
			while ((line = fin3.readLine()) != null) {
				WordNode node3 = new WordNode();
				String[] wordmean = line.split(" ");

				if (wordmean == null) 				// exception handling
					continue;

				node3.SetWord(wordmean[0]);
				node3.SetMean(wordmean[1]);

				cll.Insert(node3);
				cnt++;
			}

			fin3.close();
		}

		if (cnt == 0) 						// exception handling 3
			return false;
		return true;
	}

	public boolean ADD() throws IOException // ADD
	{
		FileReader kk;
		try { // exception handling
			kk = new FileReader("word.txt");
		} catch (FileNotFoundException e) {
			return false;
		} // when the file does not open
		BufferedReader fin2 = new BufferedReader(kk);

		String line = null;
		WordNode add = new WordNode();
		int cnt = 0;

		while ((line = fin2.readLine()) != null) // repeat, if the file doesn't end
		{
			cnt++;
			add = new WordNode();
			String[] wordmean = line.split("\t");

			if (OVERLAP(wordmean[0]) == true) // a duplicate check
				continue;

			add.SetWord(wordmean[0]); // copy word
			add.SetMean(wordmean[1]); // copy mean

			if (add == null) // exception handling
				continue;

			queue.Push(add); // push queue
			queue.setCnt(1); // increase the number of elements in the queue

		}
		fin2.close(); // file close

		if (line == null && cnt == 0) // for empty files
			return false;
		return true;
	}

	public boolean MOVE() // MOVE
	{ // exception handling
		if (queue.getCnt() == 0 || bst.getCnt() + moveCnt > 100 || queue.getCnt() < moveCnt)
			return false;

		while (moveCnt != 0) { // repeat as much as a moveCnt
			WordNode moveNode = queue.Pop(); // copy and delete

			char Alpha = moveNode.GetWord().charAt(0); // search bst node
			AlphabetNode pushNode = bst.Search(Alpha);
			pushNode.GetBST().Insert(moveNode);

			queue.setCnt(-1); // decrease the number of queue elements
			bst.setCnt(1); // increase the number of bst elements
			moveCnt--;
		}

		return true;
	}

	public boolean SAVE() throws IOException // SAVE
	{
		boolean qsave = queue.Save();
		boolean bsave = bst.Save();
		boolean csave = cll.Save();

		if (qsave == false && bsave == false && csave == false)
			return false; // exception handling

		return true;
	}

	public boolean TEST() // TEST
	{
		char Alpha = testWord.charAt(0);
		AlphabetNode AS = bst.Search(Alpha); // alphabet bst
		WordNode WS = AS.GetBST().Search(testWord); // word bst

		if (WS == null || testMean.compareTo(WS.GetMean()) != 0)
			return false; // exception handling

		WordNode pass = AS.GetBST().Delete(WS.GetWord()); // delete a word in bst
		cll.Insert(pass); // insert a word in Circle Linked List
		bst.setCnt(-1); // decrease bst element number

		return true;
	}

	public boolean SEARCH() throws IOException // SEARCH
	{
		char Alpha = searchWord.charAt(0); // the first letter

		AlphabetNode AS = bst.Search(Alpha);
		WordNode WS = AS.GetBST().Search(searchWord);
		WordNode QS = queue.Search(searchWord);
		WordNode CS = cll.Search(searchWord);

		if (QS == null && WS == null && CS == null)
			return false; // exception handling

		BufferedWriter print = new BufferedWriter(new FileWriter("log.txt", true)); // open log.txt
		print.write("======== SEARCH ========" + "\n");
		if (QS != null) // there is a word in QS
			print.write(QS.GetWord() + " " + QS.GetMean() + "\n");
		else if (WS != null) // there is a word in WS
			print.write(WS.GetWord() + " " + WS.GetMean() + "\n");
		else if (CS != null) // there is a word in CS
			print.write(CS.GetWord() + " " + CS.GetMean() + "\n");
		print.write("=======================" + "\n\n");
		print.close(); // file close

		return true;
	}

	public boolean PRINT() throws IOException // PRINT
	{
		String QQ = "TO_MEMORIZE";
		String BB = "MEMORIZING";
		String CC = "MEMORIZED";

		boolean qflag = false, bflag = false, cflag = false;

		if (order.compareTo(QQ) == 0) {
			qflag = queue.Print();
		} else if (order.compareTo(BB) == 0) {
			bflag = bst.Print();
		} else if (order.compareTo(CC) == 0) {
			cflag = cll.Print();
		}

		if (qflag == false && bflag == false && cflag == false)
			return false; // exception handling

		return true;
	}

	public boolean UPDATE() throws IOException // UPDATE
	{
		AlphabetNode AS = bst.Search(updateWord.charAt(0));
		WordNode WS = AS.GetBST().Search(updateWord);
		WordNode QS = queue.Search(updateWord);
		WordNode CS = cll.Search(updateWord); // search updateWord in Lists

		if (QS == null && WS == null && CS == null)
			return false; // exception handling

		BufferedWriter fout = new BufferedWriter(new FileWriter("log.txt", true)); // log.txt open
		fout.write("======== UPDATE ========" + "\n");
		if (QS != null) {
			fout.write(QS.GetWord() + " " + QS.GetMean() + " -> " + updateMean + "\n");
			QS.SetMean(updateMean); // update mean
		} else if (WS != null) {
			fout.write(WS.GetWord() + " " + WS.GetMean() + " -> " + updateMean + "\n");
			WS.SetMean(updateMean); // update mean
		} else if (CS != null) {
			fout.write(CS.GetWord() + " " + CS.GetMean() + " -> " + updateMean + "\n");
			CS.SetMean(updateMean); // update mean
		}
		fout.write("=======================" + "\n\n");
		fout.close(); // file close

		return true;
	}

	public boolean OVERLAP(String overlap) throws IOException // OVERLAP
	{ // A DUPLICATE CHECK
		char Alpha = overlap.charAt(0);
		AlphabetNode AS = bst.Search(Alpha);
		WordNode WS = AS.GetBST().Search(overlap);
		WordNode QS = queue.Search(overlap);
		WordNode CS = cll.Search(overlap); // Search a word in Lists

		if (QS != null) // if word is here
			return true;
		else if (WS != null)
			return true;
		else if (CS != null)
			return true;

		return false; // Not Found
	}

	public void printErrorCode(int n) throws IOException // ERROR PRINT
	{
		BufferedWriter fout = new BufferedWriter(new FileWriter("log.txt", true));// ERROR CODE PRINT
		fout.write("======== ERROR ========" + "\n");
		fout.write(n + "\n");
		fout.write("=======================" + "\n\n");
		fout.close();
	}

	public void printSuccessCode(String cmdname) throws IOException // SUCCESS PRINT
	{
		BufferedWriter fout = new BufferedWriter(new FileWriter("log.txt", true)); // SUCCESS CODE PRINT
		fout.write("======== " + cmdname + " ========" + "\n");
		fout.write("Success" + "\n");
		fout.write("=======================" + "\n\n");
		fout.close();
	}

	public void testpassCode(String cmdname) throws IOException // PASS PRINT
	{
		BufferedWriter fout = new BufferedWriter(new FileWriter("log.txt", true)); // PASS CODE PRINT
		fout.write("======== " + cmdname + " ========" + "\n");
		fout.write("Pass" + "\n");
		fout.write("=======================" + "\n\n");
		fout.close();
	}
}