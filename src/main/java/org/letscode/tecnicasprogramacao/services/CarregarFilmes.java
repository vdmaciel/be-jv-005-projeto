package org.letscode.tecnicasprogramacao.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Path file1 = Path.of("src/main/resources/movies1.csv");
        Path file2 = Path.of("src/main/resources/movies2.csv");
        Path file3 = Path.of("src/main/resources/movies3.csv");

        try {
            Files.lines(file1).skip(1).forEach(line -> {
                filmes.add(this.parseFilme(line));
            });
            System.out.println("Filmes do arquivo 1 carregados com sucesso!");
            Files.lines(file2).forEach(line -> {
                filmes.add(this.parseFilme(line));
            });
            System.out.println("Filmes do arquivo 2 carregados com sucesso!");
            Files.lines(file3).forEach(line -> {
                filmes.add(this.parseFilme(line));
            });
            System.out.println("Filmes do arquivo 3 carregados com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar filmes");
            e.printStackTrace();
        }

        return filmes;
    }

}
