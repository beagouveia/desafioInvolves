package br.com.involves.teste.main;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}

	public void run() {
		Scanner leitor = new Scanner(System.in);
		ConsoleManager consoleManager = new ConsoleManager(leitor);
		while (leitor.hasNext()) {
			consoleManager.lerComandos();
		}
		leitor.close();
	}
}
