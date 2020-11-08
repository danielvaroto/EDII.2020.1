package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import model.Livro;

public class FileUtils {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private static final int DEFAULT_TITULO = 9;
    private static final int DEFAULT_AUTORES = 0;

    public static List<String> LerDataset(String caminhoDataset) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoDataset));
        List<String> titles = new ArrayList();

        while (scanner.hasNext()) {
            List<String> line = BuscaLinha(scanner.nextLine(), DEFAULT_SEPARATOR, DEFAULT_QUOTE);
            if (line.size() == 10) {
                titles.add(line.get(DEFAULT_TITULO));
            }
        }

        scanner.close();

        return titles;
    }

    public static List<Livro> LerDatasetLivros(String caminhoDataset) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoDataset));
        List<Livro> livros = new ArrayList();

        while (scanner.hasNext()) {
            var linha = BuscaLinha(scanner.nextLine(), DEFAULT_SEPARATOR, DEFAULT_QUOTE);
            livros.add(
                    new Livro(
                            linha.get(DEFAULT_TITULO),
                            linha.get(DEFAULT_AUTORES)
                    )
            );
        }

        scanner.close();

        return livros;
    }

    public static int[] LerTamanhoEntradas(String caminhoEntrada) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoEntrada));
        List<String> titles = new ArrayList();

        int quantidadeTamanhoEntradas = scanner.nextInt();
        int[] tamanhoEntradas = new int[quantidadeTamanhoEntradas];

        for (int i = 0; i < quantidadeTamanhoEntradas; i++) {
            tamanhoEntradas[i] = scanner.nextInt();
        }

        return tamanhoEntradas;
    }

    public static String[] BuscarAleatorio(List<String> titles, int quantity) throws FileNotFoundException {
        String[] titleRandom = new String[quantity];

        for (int i = 0; i < quantity; i++) {
            Random random = new Random();
            titleRandom[i] = titles.get(random.nextInt(titles.size() - 1));
        }

        return titleRandom;
    }

    private static List<String> BuscaLinha(String cvsLine, char separators, char customQuote) {
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
