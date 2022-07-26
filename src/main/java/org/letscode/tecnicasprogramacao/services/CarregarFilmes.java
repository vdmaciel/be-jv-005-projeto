package org.letscode.tecnicasprogramacao.services;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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

    public List<Filme> executar(List<Path> arquivos) throws InterruptedException {
        System.out.println("Carregando filmes...");

        //Array de threads de leitura
        List<FileReader> threads =
                arquivos
                        .parallelStream()
                        .map(FileReader::new)
                        .collect(Collectors.toList());

        ExecutorService executor = Executors.newCachedThreadPool();

        List<Future<List<String>>> futures = executor.invokeAll(threads);

        executor.shutdown();

        //Inicia as threads
        return futures.parallelStream()
                .map(future -> {
                    try {
                        return Stream.of(future.get());
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(Stream::distinct)
                .flatMap(Collection::stream)
                .distinct()
                //Para cada linha dos arquivos, cria um filme e adiciona a lista de filmes
                .map(this::parseFilme)
                // Ordena os filmes
                .sorted(Comparator.comparingLong(Filme::getRank))
                .collect(Collectors.toList());
    }

}
