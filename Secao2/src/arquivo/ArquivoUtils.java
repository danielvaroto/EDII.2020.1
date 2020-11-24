package arquivo;

import estrutura.SondagemDuplaTabelaHash;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import model.Autor;
import model.Livro;

public class ArquivoUtils {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private static final int DEFAULT_LIVRO_TITULO = 9;
    private static final int DEFAULT_LIVRO_AUTORES = 0;
    private static final int DEFAULT_AUTOR_ID = 0;
    private static final int DEFAULT_AUTOR_NOME = 1;

    private static final Random random = new Random();

    public static List<Livro> lerDatasetLivros(String caminhoDataset) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoDataset));
        List<Livro> livros = new ArrayList();

        while (scanner.hasNext()) {
            var linha = buscaLinha(scanner.nextLine(), DEFAULT_SEPARATOR, DEFAULT_QUOTE);
            livros.add(
                    new Livro(
                            linha.get(DEFAULT_LIVRO_TITULO),
                            linha.get(DEFAULT_LIVRO_AUTORES)
                    )
            );
        }

        scanner.close();

        return livros;
    }

    public static SondagemDuplaTabelaHash<Autor> lerDatasetAutores(String caminhoDataset) throws FileNotFoundException, Exception {
        Scanner scanner = new Scanner(new File(caminhoDataset));
        var autores = new SondagemDuplaTabelaHash<Autor>();

        while (scanner.hasNext()) {
            var linha = buscaLinha(scanner.nextLine(), DEFAULT_SEPARATOR, DEFAULT_QUOTE);
            var id = Integer.parseInt(linha.get(DEFAULT_AUTOR_ID), 10);
            autores.inserir(
                    id,
                    new Autor(id, linha.get(DEFAULT_AUTOR_NOME))
            );
        }

        scanner.close();

        return autores;
    }

    public static <T> T buscarAleatorio(List<T> titles) {
        return titles.remove(random.nextInt(titles.size()));
    }

    public static void salvarSaidaAutores(String caminhoSaida, Autor[] autores, int quantidade) throws IOException {
        var linhas = new ArrayList<String>();

        for (var i = 0; i < quantidade; i++) {
            linhas.add(autores[i].buscarNome() + ": " + autores[i].buscarQuantidadeLivros());
        }

        Files.write(Paths.get(caminhoSaida),
                linhas,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    private static List<String> buscaLinha(String cvsLine, char separators, char customQuote) {
        List<String> result = new ArrayList<>();

        //Se está vazio retorna
        if (cvsLine == null || cvsLine.isEmpty()) {
            return result;
        }
        //identifica o quote padrão do texto para evitar quebras na leitura
        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }
        //separador de coluna no texto
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;
        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {
            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {
                    //Permiti uso de " no texto
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {
                if (ch == customQuote) {
                    inQuotes = true;

                    //permiti aspas no texto
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == separators) {
                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\r') {
                    continue;
                } else if (ch == '\n') {
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }

        result.add(curVal.toString());

        return result;
    }
}
