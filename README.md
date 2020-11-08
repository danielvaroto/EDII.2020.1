# EDII.2020.1

## Instruções para execução:

- Seção 1:

```
Comando para executar a Seção 1:

java -jar ".\Secao1.jar" ".\exemploDataset.csv" ".\exemploEntrada.txt" ".\exemploSaida.txt"

- Caminho do jar de execução da Seção 1.

- Caminho para o dataset (ex: ".\exemploDataset.csv") que conterá os dados de livros para a ordenação por título.

- Caminho para o arquivo com os tamanhos de entradas a serem testados (ex: ".\exemploEntrada.txt") com o seguinte formato:

5 -> número de valores de N que se seguem, um por linha
1000
5000
10000
50000
100000

- Caminho para o arquivo de saída (ex: ".\exemploSaida.txt") que será inserido os dados de saída após a execução das ordenações. Obs: caso o caminho não exista o arquivo será criado no caminho especificado.
```

- Seção 2:

```
Comando para executar a Seção 2:

java -jar ".\Secao2.jar" "M" "N" ".\exemploDatasetLivros.csv" ".\exemploDatasetAutores.csv" ".\exemploSaida.txt"

- Caminho do jar de execução da Seção 2.

- M: Quantidade de autores mais frequentes a serem exibidos.

- N: Quantidade de livros a serem pesquisados.

- Caminho para o dataset de livros (ex: ".\exemploDatasetLivros.csv") que conterá os dados de livros para encontrar os autores mais frequentes.

- Caminho para o dataset de autores (ex: ".\exemploDatasetAutores.csv") que conterá os dados de autores para encontrar os nomes dos autores.

- Caminho para o arquivo de saída (ex: ".\exemploSaida.txt") que será inserido os dados de saída após a execução das ordenações. Obs: caso o caminho não exista o arquivo será criado no caminho especificado.
```
