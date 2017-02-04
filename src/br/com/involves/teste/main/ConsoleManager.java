package br.com.involves.teste.main;

import java.util.List;
import java.util.Scanner;

import br.com.involves.teste.util.CSVExplorer;

public class ConsoleManager {

	private CSVExplorer csvExplorer;
	private Scanner leitor;

	public ConsoleManager(Scanner leitor) {
		csvExplorer = new CSVExplorer();
		this.leitor = leitor;
	}

	public void lerComandos() {
		String comando = leitor.next();
		switch (comando) {
		case "read":
			comandoRead(); break;
		case "count":
			comandoCount(); break;
		case "filter":
			comandoFilter(); break;
		default:
			comandoDesconhecido(comando); break;
		}
	}

	protected void comandoFilter() {
		String propriedade = leitor.next();
		if (propriedade.startsWith("[") && propriedade.endsWith("]")) {
			propriedade = propriedade.substring(1, propriedade.length() - 1);
		} else {
			comandoDesconhecido(propriedade);
		}
		String valor = leitor.next();
		if (valor.startsWith("[") && valor.endsWith("]")) {
			valor = valor.substring(1, valor.length() - 1);
			List<String> linhas = csvExplorer.filter(propriedade, valor);
			for (String linha : linhas) {
				System.out.println(linha);
			}
		}else{
			comandoDesconhecido(valor);
		}
	}

	protected void comandoCount() {
		String contador = leitor.next();
		if (contador.equals("*")) {
			System.out.println(csvExplorer.count());
		} else {
			if (contador.equals("distinct")) {
				String propriedade = leitor.next();
				if (propriedade.startsWith("[") && propriedade.endsWith("]")) {
					propriedade = propriedade.substring(1, propriedade.length() - 1);
					System.out.println(csvExplorer.countDistincts(propriedade));
				}
			} else {
				comandoDesconhecido(contador);
			}
		}
	}

	protected void comandoRead() {
		String arquivoCSV = leitor.next();
		csvExplorer.lerArquivoCSV(arquivoCSV);
	}

	private void comandoDesconhecido(String comando) {
		System.out.println("Comando desconhecido. (" + comando+").");
	}

}
