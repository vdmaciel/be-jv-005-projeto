package org.letscode.tecnicasprogramacao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Filme filme = new Filme(
            !campos.get(0).isEmpty() ? Long.parseLong(campos.get(0)) : null,
            campos.get(1),
            campos.get(2),
            campos.get(3),
            campos.get(4),
            campos.get(5),
            !campos.get(6).isEmpty() ? Long.parseLong(campos.get(6)) : null,
            !campos.get(7).isEmpty() ? Long.parseLong(campos.get(7)) : null,
            !campos.get(8).isEmpty() ? Double.parseDouble(campos.get(8)) : null,
            !campos.get(9).isEmpty() ? Long.parseLong(campos.get(9)) : null,
            !campos.get(10).isEmpty() ? Double.parseDouble(campos.get(10)) : null,
            !campos.get(11).isEmpty() ? Long.parseLong(campos.get(11)) : null
        );
        return filme;
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
        for (FileReader thread : threads) {
            thread.start();
        }

        List<String> linhas = new ArrayList<>();

        //Espera todas as threads terminarem
        for(FileReader thread : threads) {
            try {
                thread.join();
                linhas.addAll(thread.getLines());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Para cada linha dos arquivos, cria um filme e adiciona a lista de filmes
        for (String linha : linhas) {
            filmes.add(parseFilme(linha));
        }

        return filmes;
    }

}
