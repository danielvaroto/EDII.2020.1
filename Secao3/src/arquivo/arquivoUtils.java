package arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ArquivoUtils {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private static final int DEFAULT_TITLE = 11;

    public static List<String> LerDataset(String caminhoDataset) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminhoDataset));
        List<String> titles = new ArrayList();

        while (scanner.hasNext()) {
            List<String> line = BuscaLinha(scanner.nextLine(), DEFAULT_SEPARATOR, DEFAULT_QUOTE);
            if (line.size() == 28) {
                titles.add(line.get(DEFAULT_TITLE));
            }
        }

        scanner.close();

        return titles;
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

    public static long[] BuscarAleatorio(List<String> titles, int quantity) throws FileNotFoundException {
        long[] titleRandom = new long[quantity];

        for (int i = 0; i < quantity; i++) {
            Random random = new Random();
            titleRandom[i] = Long.parseLong(titles.get(random.nextInt(titles.size() - 1)));
        }

        return titleRandom;
    }

    public static void SalvaEstatisticasOrdenacao(String caminhoSaida, List<String> operacaoResultado) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss").format(ZonedDateTime.now()));
        lines.add("Num de entradas,Num de comparacoes,Num de copias,Tempo de processamento em milissegundos");
        
        lines.addAll(operacaoResultado);

        Path file = Paths.get(caminhoSaida);
        Files.write(file,
                lines,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
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
