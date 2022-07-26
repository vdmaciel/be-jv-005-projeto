package org.letscode.tecnicasprogramacao.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class FileReader implements Callable<List<String>> {
    private final Path path;

    public FileReader(Path path) {
        this.path = path;
    }

    @Override
    public List<String> call() throws RuntimeException {
        try {
            List<String> lines = Files.lines(path)
                    // Ignora a primeira linha do arquivo, adiciona apenas as linhas que possuem índice
                    .skip(1)
                    .parallel()
                    .collect(Collectors.toList());
            System.out.println("Arquivo " + path.getFileName() + " lido com sucesso!");

            return lines;
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo " + path.getFileName());
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
