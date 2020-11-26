package model;

public class ArgumentosEntrada {

    public int m, n;
    public String caminhoEntrada,
            caminhoDatasetLivros,
            caminhoSaidaInsercao,
            caminhoSaidaBusca;

    public ArgumentosEntrada(String[] args) {
        this.caminhoEntrada = this.buscarArgumentoString(args, 0, "CaminhoEntrada", "./exemploEntrada.txt");
        this.caminhoDatasetLivros = this.buscarArgumentoString(args, 1, "CaminhoDatasetLivros", "./exemploDatasetLivros.csv");
        this.caminhoSaidaInsercao = this.buscarArgumentoString(args, 2, "CaminhoSaidaInsercao", "./exemploSaidaInsercao.txt");
        this.caminhoSaidaBusca = this.buscarArgumentoString(args, 3, "CaminhoSaidaBusca", "./exemploSaidaBusca.txt");
    }

    private String buscarArgumentoString(String[] args, int index, String key, String defaultValue) {
        if (args.length <= index) {
            System.out.println(key + " não especificado, utilizando valor " + defaultValue + " como padrão.");
            return defaultValue;
        }

        return args[index];
    }
}
