package br.com.involves.teste.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.involves.teste.util.CSVExplorer;

public class CSVExplorerTest extends CSVExplorer {

	static final String csvCidades = "csv/cidades.csv";
	static final String csvCountries = "csv/countries.csv";
	static final Integer qntRegistrosCidades = 5565;
	static final Integer qntRegistrosCountries = 244;
	static final Integer qntRegistrosCountriesPaisBrasil = 1;
	static final Integer qntRegistrosCidadesUFSC = 293;

	@Test
	public void testLerArquivoCSV() {
		lerArquivoCSV(csvCidades);
		lerArquivoCSV(csvCountries);
	}

	@Test
	public void testCount() {
		lerArquivoCSV(csvCidades);
		assertEquals(qntRegistrosCidades, count());
		lerArquivoCSV(csvCountries);
		assertEquals(qntRegistrosCountries, count());
	}

	@Test
	public void tesCountDistincts() {
		lerArquivoCSV(csvCountries);
		countDistincts("Country");
	}

	@Test
	public void testFilter() {
		lerArquivoCSV(csvCountries);
		assertEquals(qntRegistrosCountriesPaisBrasil + 1, filter("Country", "Brazil").size());
		lerArquivoCSV(csvCidades);
		assertEquals(qntRegistrosCidadesUFSC + 1, filter("uf", "SC").size());
	}

}
