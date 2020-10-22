package secao1;

import file.FileUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.*;
import sort.*;

public class Secao1 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // Verifica se o nome do arquivo foi enviado
        if (args.length == 0) {
            System.out.println("Caminho do dataset não especificado, executando com arquivo 'exemploDataset.csv'.");
        }
        
        String fileName = (args.length == 0) ? "./exemploDataset.csv" : args[0];

        List<String> titles = FileUtils.readFile(fileName);
        List<SortResult> quickSortResults = new ArrayList<>();
        List<SortResult> mergeSortResults = new ArrayList<>();

        int[] sizes = new int[]{1000, 5000, 10000, 50000, 100000};
        for (int size : sizes) {
            for (int i = 0; i < 5; i++) {
                String[] randomEntries = FileUtils.getRandom(titles, size);

                SortResult qickSortResult = QuickSort.Sort(randomEntries);
                SortResult mergeSortResult = MergeSort.Sort(randomEntries);

                quickSortResults.add(qickSortResult);
                mergeSortResults.add(mergeSortResult);
            }
        }

        FileUtils.saveSortStatistics("QuickSort", quickSortResults);
        FileUtils.saveSortStatistics("MergeSort", mergeSortResults);
        
        System.out.println("Dados salvos no arquivo saida.txt");
    }
}
