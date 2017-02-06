package br.com.involves.teste.main;

import java.io.IOException;
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

	public void lerComandos() throws IOException {
		String comando = leitor.nextLine();
		String[] parametrosComando = comando.split(" ");
		try {
			switch (parametrosComando[0]) {
			case "read":
				comandoRead(parametrosComando[1]);
				break;
			case "count":
				if (parametrosComando[1].equals("*")) {
					comandoCount();
				} else {
					if (parametrosComando[1].equals("distinct")) {
						comandoCountDistinct(parametrosComando[2]);
					} else {
						comandoDesconhecido(parametrosComando[1]);
					}
				}
				break;
			case "filter":
				comandoFilter(parametrosComando[1], parametrosComando[2]);
				break;
			case "help":
				help();
				break;
			default:
				comandoDesconhecido(comando);
				break;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Comando incompleto. Para ajuda digite 'help'.");
		}
	}

	private void comandoCountDistinct(String propriedade) {
		if (propriedade.startsWith("[") && propriedade.endsWith("]")) {
			propriedade = propriedade.substring(1, propriedade.length() - 1);
			System.out.println(csvExplorer.countDistincts(propriedade));
		} else {
			comandoDesconhecido(propriedade);
		}
	}

	protected void comandoCount() {
		System.out.println(csvExplorer.count());
	}

	protected void comandoFilter(String propriedade, String valor) {
		if (propriedade.startsWith("[") && propriedade.endsWith("]")) {
			propriedade = propriedade.substring(1, propriedade.length() - 1);

			if (valor.startsWith("[") && valor.endsWith("]")) {
				valor = valor.substring(1, valor.length() - 1);
				List<String> linhas = csvExplorer.filter(propriedade, valor);
				for (String linha : linhas) {
					System.out.println(linha);
				}
			} else {
				comandoDesconhecido(valor);
			}

		} else {
			comandoDesconhecido(propriedade);
		}
	}

	protected void comandoRead(String nomeArquivo) {
		csvExplorer.lerArquivoCSV(nomeArquivo);
	}

	private void comandoDesconhecido(String comando) {
		System.out.println("Comando desconhecido. ('" + comando + "'). Para ajuda digite 'help'.");
	}

	private void help() {
		StringBuilder help = new StringBuilder();
		help.append("Comandos reconhecíveis: \n\n");
		help.append("count * : escreve no console a contagem total de registros importados \n");
		help.append(
				"count distinct [propriedade] : escreve no console o total de valores distintos da propriedade enviada \n");
		help.append(
				"filter [propriedade] [valor] : escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado");
		System.out.println(help);
	}

}
