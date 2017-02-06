# CSV Explorer 
===================

## O Programa
  CSV Explorer é um programa implementado para aplicação no processo seletivo de Desenvolvedor Java da empresa **Involves**.
  
## O que ele faz?
  CSV Explorer é um programa de manipulação em arquivos de extensão **.csv**.
   
## Como executar?
  Para executar o programa, é necessário ter o Apache Maven instalado em seu computador. Caso não o tenha ainda, faça o download e configure-o [clicando aqui](https://maven.apache.org/download.cgi).
  
1. Efetue o download do projeto;
2. Descompactue a pasta baixada;
3. No terminal de comandos, entre na pasta descompactuada (**cd ../testeInvolves-master**) e digite o comando: **mvn package** para compilar o código fonte do projeto e executar os testes unitários implementados com a ferramenta jUnit;
4. Entre na pasta '/target' gerada no seu projeto pelo passo 3 (**cd /target**), e digite o comando: **java -jar testeInvolves-0.0.1.jar** para rodar o programa;

Pronto. Agora você pode executar os comandos listados na próxima seção.

## Comandos válidos
  
  - **read /caminho/do/Arquivo :**  Importa os registros do arquivo 
  - **count * :** Escreve no console a contagem total de registros importados
  - **count distinct [propriedade] :** Escreve no console o total de valores distintos da propriedade (coluna) enviada
  - **filter [propriedade] [valor] :** Escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado
