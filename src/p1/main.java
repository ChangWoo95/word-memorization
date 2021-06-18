package p1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import p1.Pair;
import p1.WordNode;

public class main {

	public static void main(String[] args) throws IOException {

		Manager manager = new Manager();
		manager.run("command.txt");

		return;
	}

}
