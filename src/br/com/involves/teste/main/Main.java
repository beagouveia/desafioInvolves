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
		CommandManager commandManager = new CommandManager();
		try {
			while (leitor.hasNext()) {
				System.out.println(commandManager.executarComando(leitor.nextLine()));
			}
			leitor.close();
		} catch (IOException e) {
			System.out.println("Não foi possível executar o comando");
		}
	}
}
