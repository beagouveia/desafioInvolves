package br.com.involves.teste.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVExplorer {
	
	static final String caminhoCSVDefault = "csv/cidades.csv"; 

	private Integer countRegistros;
	private ArrayList<String[]> registros;
	private String[] propriedades;
	

	public CSVExplorer() {
		lerArquivoCSV(caminhoCSVDefault);
	}

	public Integer count() {
		return countRegistros;
	}

	public Integer countDistincts(String propriedade) {
		Integer coluna;
		List<String> countDistincts = new ArrayList<>();
		try {
			coluna = getColuna(propriedade);
			countDistincts = new ArrayList<>();
			for (String[] linha : registros) {
				if (!countDistincts.contains(linha[coluna])) {
					countDistincts.add(linha[coluna]);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return countDistincts.size();
	}

	public List<String> filter(String propriedade, String valor) {
		Integer coluna;
		List<String> linhas = new ArrayList<>();
		try {
			coluna = getColuna(propriedade);
			linhas.add(Arrays.toString(propriedades));
			for (String[] linha : registros) {
				if (linha[coluna].equals(valor)) {
					linhas.add(Arrays.toString(linha));
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return linhas;
	}

	public Integer getColuna(String propriedade) throws Exception {
		for (int i = 0; i < propriedades.length; i++) {
			if (propriedades[i].equals(propriedade))
				return i;
		}
		throw new Exception("Propriedade inexistente;");
	}

	public void lerArquivoCSV(String arquivo) {
		countRegistros = 0;
		BufferedReader reader = null;
		String linha = "";
		String separador = ",";
		try {
			reader = new BufferedReader(new FileReader(arquivo));
			propriedades = reader.readLine().split(separador);
			registros = new ArrayList<String[]>();
			while ((linha = reader.readLine()) != null) {
				registros.add(linha.split(separador));
			}
			countRegistros = registros.size();
		} catch (Exception e) {
			System.out.println("Não foi possível carregar o arquivo.");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
