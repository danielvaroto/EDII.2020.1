package model;

public class ArgumentosEntrada {

    public int m, n;
    public String caminhoDatasetLivros, caminhoDatasetAutores, caminhoSaida;

    public ArgumentosEntrada(String[] args) {
        this.m = this.buscarArgumentoInteger(args, 0, "M", 10);
        this.n = this.buscarArgumentoInteger(args, 1, "N", 300);
        this.caminhoDatasetLivros = this.buscarArgumentoString(args, 2, "CaminhoDatasetLivros", "./exemploDatasetLivros.csv");
        this.caminhoDatasetAutores = this.buscarArgumentoString(args, 3, "CaminhoDatasetAutores", "./exemploDatasetAutores.csv");
        this.caminhoSaida = this.buscarArgumentoString(args, 4, "CaminhoSaida", "./exemploSaida.txt");
    }

    private int buscarArgumentoInteger(String[] args, int index, String key, int defaultValue) {
        if (args.length <= index) {
            System.out.println(key + " não especificado, utilizando valor " + defaultValue + " como padrão.");
            return defaultValue;
        }

        return Integer.parseInt(args[index], 10);
    }

    private String buscarArgumentoString(String[] args, int index, String key, String defaultValue) {
        if (args.length <= index) {
            System.out.println(key + " não especificado, utilizando valor " + defaultValue + " como padrão.");
            return defaultValue;
        }

        return args[index];
    }
}
