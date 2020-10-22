package file;

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
import model.SortResult;

public class FileUtils {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private static final int DEFAULT_TITLE = 9;

    public static String[] getRandom(List<String> titles, int quantity) throws FileNotFoundException {
        String[] titleRandom = new String[quantity];

        for (int i = 0; i < quantity; i++) {
            Random random = new Random();
            titleRandom[i] = titles.get(random.nextInt(titles.size() - 1));
        }

        return titleRandom;
    }

    public static List<String> readFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        List<String> titles = new ArrayList();

        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine(), DEFAULT_SEPARATOR, DEFAULT_QUOTE);
            if (line.size() == 10) {
                titles.add(line.get(DEFAULT_TITLE));
            }
        }

        scanner.close();

        return titles;
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {
        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null || cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

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
                    //Fixed : allow "" in custom quote enclosed
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

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == separators) {
                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }

        result.add(curVal.toString());

        return result;
    }

    public static void saveSortStatistics(String sortName, List<SortResult> sortResults) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss").format(ZonedDateTime.now()));
        lines.add(sortName);
        lines.add("Num de entradas,Num de comparacoes,Num de copias,Tempo de processamento em milissegundos");

        for (SortResult sortResult : sortResults) {
            String resultLine = new StringBuilder()
                    .append(sortResult.getEntriesCount())
                    .append(",")
                    .append(sortResult.getComparisonCount())
                    .append(",")
                    .append(sortResult.getCopyCount())
                    .append(",")
                    .append(sortResult.getProcessingTimeInMiliseconds())
                    .toString();

            lines.add(resultLine);
        }

        Path file = Paths.get("..\\saida.txt");
        Files.write(file,
                lines,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
}
