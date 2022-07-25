package org.letscode.tecnicasprogramacao.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader extends Thread {
    private final String filePath;
    private List<String> lines = new ArrayList<>();

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            Files.lines(Path.of(filePath)).forEach(line -> {
                // Ignora a primeira linha do arquivo, adiciona apenas as linhas que possuem índice
                if(Character.isDigit(line.charAt(0))){
                    lines.add(line);
                }
            });
            System.out.println("Arquivo " + filePath + " lido com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo " + filePath);
            e.printStackTrace();
        }
    }

    public List<String> getLines() {
        return lines;
    }
}
