package br.com.involves.teste.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.involves.teste.util.CSVExplorer;

/**
 * Classe utilizada para testar o a manipulação de arquivos CSV
 */
public class CSVExplorerTest extends CSVExplorer {

	static final String csvCidades = "resources/cidades.csv";
	static final String csvCountries = "resources/countries.csv";
	static final Integer qntRegistrosCidades = 5565;
	static final Integer qntRegistrosCountries = 244;
	static final Integer qntDistinctCountries = 244;
	static final Integer qntRegistrosCountriesPaisBrasil = 1;
	static final Integer qntRegistrosCidadesUFSC = 293;

	@Test
	public void testLerArquivoCSV() {
		assertEquals("Arquivo carregado", lerArquivoCSV(csvCidades));
		assertEquals("Arquivo carregado", lerArquivoCSV(csvCountries));
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
		try {
			assertEquals(qntDistinctCountries, countDistincts("Country"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFilter() {
		lerArquivoCSV(csvCountries);
		assertEquals(qntRegistrosCountriesPaisBrasil + 1, filter("Country", "Brazil").size());
		lerArquivoCSV(csvCidades);
		assertEquals(qntRegistrosCidadesUFSC + 1, filter("uf", "SC").size());
	}

}
