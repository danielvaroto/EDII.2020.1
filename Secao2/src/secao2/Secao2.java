package secao2;

import file.FileUtils;
import java.io.FileNotFoundException;
import model.ArgumentosEntrada;

public class Secao2 {

    public static void main(String[] args) throws FileNotFoundException {
        var argumentos = new ArgumentosEntrada(args);

        var listaLivros = FileUtils.LerDatasetLivros(argumentos.caminhoDatasetLivros);
    }

}
