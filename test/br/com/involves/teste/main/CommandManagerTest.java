package br.com.involves.teste.main;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

/**
 * Classe utilizada para testar o tratamento dos comandos de entrada
 */
public class CommandManagerTest extends CommandManager {

	@Test
	public void executarComandoTest() {
		try {
			/* Comando read */
			assertTrue(executarComando("read resources/countries").startsWith("Não foi possível"));
			assertTrue(executarComando("read resources/countries.csv").startsWith("Arquivo"));
			/* Comando count * */
			assertEquals("244", executarComando("count *"));
			assertEquals("Comando incompleto", executarComando("count"));
			/* Comando count distinct */
			assertEquals("244", executarComando("count distinct [Country]"));
			assertEquals("Propriedade inexistente", executarComando("count distinct [X]"));
			assertEquals("Comando incompleto", executarComando("count distinct"));
			/* Comando filter */
			assertTrue(executarComando("filter [Country] [Brazil]")
					.startsWith("[Rank, Country, Population, Date,  world population]"));
			assertTrue(executarComando("filter [Y] [Brazil]").startsWith("Propriedade inexistente"));
			assertEquals("Comando incompleto", executarComando("filter [Country]"));
			assertEquals("Comando desconhecido ('Country')", executarComando("filter Country [Brazil]"));
			/* Comandos inexistentes */
			assertEquals("Comando desconhecido ('x')", executarComando("x"));
			assertEquals("Comando desconhecido ('xyz x')", executarComando("xyz x"));
			assertEquals("Comando desconhecido ('COUNT *')", executarComando("COUNT *"));
			assertEquals("Comando desconhecido ('COUNT X')", executarComando("COUNT X"));
			assertEquals("Comando desconhecido ('p')", executarComando("count distinct p"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
