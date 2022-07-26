package org.letscode.tecnicasprogramacao;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.letscode.tecnicasprogramacao.model.Filme;
import org.letscode.tecnicasprogramacao.services.CarregarFilmes;
import org.letscode.tecnicasprogramacao.services.ObterVinteMelhoresFilmesTerror;

public class Aplicacao {
    public static void main(String[] args) throws InterruptedException {

        Path[] entradaArquivos = {
                Path.of("src/main/resources/movies1.csv"),
                Path.of("src/main/resources/movies2.csv"),
                Path.of("src/main/resources/movies3.csv")
        };

        CarregarFilmes carregarFilmes = new CarregarFilmes();
        List<Filme> filmes = carregarFilmes.executar(Arrays.asList(entradaArquivos));

        filmes.stream().sequential().forEach(System.out::println);

        /* TODO usar Strategy para executar todos os serviços (/services) usando interface Processamento;
            - Criar uma lista de todos os processamentos a executar
            - forEch na lista passando os filmes carregados
        * */
        ObterVinteMelhoresFilmesTerror obterVinteMelhoresFilmesTerror = new ObterVinteMelhoresFilmesTerror();
        List<Filme> melhoresFilmesTerror = obterVinteMelhoresFilmesTerror.executar(filmes);

    }
}
