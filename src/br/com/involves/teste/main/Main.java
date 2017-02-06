package br.com.involves.teste.main;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}

	public void run() {
		Scanner leitor = new Scanner(System.in);
		ConsoleManager consoleManager = new ConsoleManager(leitor);
		try {
			while (true) {
				consoleManager.lerComandos();
			}
		} catch (IOException e) {
			System.out.println("Não foi possível executar o comando. Para ajuda digite 'help'.");
		} finally {
			if (leitor != null) {
				leitor.close();
			}
		}
	}
}
