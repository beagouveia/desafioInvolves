package br.com.involves.teste.main;

import java.io.IOException;
import java.util.List;

import br.com.involves.teste.util.CSVExplorer;
/**
 * Classe de gerenciamento dos comandos de entrada
 */
public class CommandManager {

	private CSVExplorer csvExplorer;

	public CommandManager() {
		csvExplorer = new CSVExplorer();
	}

	public String executarComando(String comando) throws IOException {
		String[] parametrosComando = comando.split(" ");
		try {
			switch (parametrosComando[0]) {
			case "read":
				return comandoRead(parametrosComando[1]);
			case "count":
				if (parametrosComando[1].equals("*")) {
					return comandoCount();
				} else {
					if (parametrosComando[1].equals("distinct")) {
						return comandoCountDistinct(parametrosComando[2]);
					} else {
						return comandoDesconhecido(parametrosComando[1]);
					}
				}
			case "filter":
				return comandoFilter(parametrosComando[1], parametrosComando[2]);
			default:
				return comandoDesconhecido(comando);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Comando incompleto";
		}
	}

	protected String comandoCountDistinct(String propriedade) {
		if (propriedade.startsWith("[") && propriedade.endsWith("]")) {
			propriedade = propriedade.substring(1, propriedade.length() - 1);
			try {
				return csvExplorer.countDistincts(propriedade).toString();
			} catch (Exception e) {
				return e.getMessage();
			}
		} else {
			return comandoDesconhecido(propriedade);
		}
	}

	protected String comandoCount() {
		return csvExplorer.count().toString();
	}

	protected String comandoFilter(String propriedade, String valor) {
		if (propriedade.startsWith("[") && propriedade.endsWith("]")) {
			propriedade = propriedade.substring(1, propriedade.length() - 1);
			if (valor.startsWith("[") && valor.endsWith("]")) {
				valor = valor.substring(1, valor.length() - 1);
				return listToString(csvExplorer.filter(propriedade, valor));
			} else {
				return comandoDesconhecido(valor);
			}

		} else {
			return comandoDesconhecido(propriedade);
		}
	}

	protected String comandoRead(String nomeArquivo) {
		return csvExplorer.lerArquivoCSV(nomeArquivo);
	}

	private String comandoDesconhecido(String comando) {
		return "Comando desconhecido ('" + comando + "')";
	}

	private String listToString(List<String> linhas) {
		StringBuilder retorno = new StringBuilder();
		for (String linha : linhas) {
			retorno.append(linha + "\n");
		}
		return retorno.toString();
	}

}
