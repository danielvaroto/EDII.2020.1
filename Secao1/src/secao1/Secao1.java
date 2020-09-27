package secao1;

import model.*;
import sort.*;

public class Secao1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //String fileName = args[0] != null ? args[0] : "entrada.txt";;
        //int lineCount = args[1] != null ? Integer.parseInt(args[1]) : 5000;
        
        // Read dataset 'entrada.txt' random limited by line count
        
        String[] mock = new String[] {
            "1984-George-Orwell",
            "100-Deadly-Skills-Survival-Edition-Clint-Emerson",
            "100-Most-Pointless-Things-World-Alexander-Armstrong",
            "50-Knitted-Dolls-Sarah-Keen",
            "20-Knit-Pocket-Pets-Sachiyo-Ishii"
        };
        
        //SortResult qickSortResult = QuickSort.Sort(mock);
        SortResult mergeSortResult = MergeSort.Sort(mock);
        
        // Save statistic data in 'saida.txt'
    }
    
}
