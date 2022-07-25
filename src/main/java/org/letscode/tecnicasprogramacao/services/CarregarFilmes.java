package org.letscode.tecnicasprogramacao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.letscode.tecnicasprogramacao.core.FileReader;
import org.letscode.tecnicasprogramacao.model.Filme;

public class CarregarFilmes {

    public Filme parseFilme(String fileline) {
        Pattern pattern = Pattern.compile("(?:,|\\n|^)(\"(?:(?:\"\")*[^\"]*)*\"|[^\",\n]*|(?:\n|$))");
        Matcher matcher = pattern.matcher(fileline);
        List<String> campos = new ArrayList<>();

        while (matcher.find()) {
            campos.add(matcher.group(1));
        }

        return Filme.builder()
                .Rank(!campos.get(0).isEmpty() ? Long.parseLong(campos.get(0)) : null)
                .Title(campos.get(1))
                .Genre(campos.get(2))
                .Description(campos.get(3))
                .Director(campos.get(4))
                .Actors(campos.get(5))
                .Year(!campos.get(6).isEmpty() ? Long.parseLong(campos.get(6)) : null)
                .Runtime(!campos.get(7).isEmpty() ? Long.parseLong(campos.get(7)) : null)
                .Rating(!campos.get(8).isEmpty() ? Double.parseDouble(campos.get(8)) : null)
                .Votes(!campos.get(9).isEmpty() ? Long.parseLong(campos.get(9)) : null)
                .Revenue(!campos.get(10).isEmpty() ? Double.parseDouble(campos.get(10)) : null)
                .Metascore(!campos.get(11).isEmpty() ? Long.parseLong(campos.get(11)) : null)
                .build();
    }

    public List<Filme> executar() {
        System.out.println("Carregando filmes...");

        List<Filme> filmes = new ArrayList<>();

        //Array de threads de leitura
        List<FileReader> threads = new ArrayList<>();

        //Array de arquivos carregados por cada thread
        threads.add(new FileReader("src/main/resources/movies1.csv"));
        threads.add(new FileReader("src/main/resources/movies2.csv"));
        threads.add(new FileReader("src/main/resources/movies3.csv"));

        //Inicia as threads
        return threads.parallelStream()
                .map(thread -> {
                    thread.run();
                    try {
                        //Espera todas as threads terminarem
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Stream.of(thread.getLines());
                })
                .flatMap(stream -> stream.distinct())
                .flatMap(lista -> lista.stream())
                .distinct()
                //Para cada linha dos arquivos, cria um filme e adiciona a lista de filmes
                .map(linha -> parseFilme(linha))
                // Ordena os filmes
                .sorted((f1, f2) -> Long.compare(f1.getRank(),f2.getRank()))
                .collect(Collectors.toList());
    }

}
