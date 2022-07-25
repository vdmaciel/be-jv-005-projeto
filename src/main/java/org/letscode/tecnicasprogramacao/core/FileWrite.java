package org.letscode.tecnicasprogramacao.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileWrite {
    private final String filePath;
    private List<String> lines = new ArrayList<>();

    public FileWrite(String filePath) {
        this.filePath = filePath;
    }

    public String formata(String line , HashMap<String, String> chave){
        for (Map.Entry row : chave.entrySet()){
            line = line.replace(row.getKey().toString(), row.getValue().toString());
        }
        return  line;
    }

    public void run(HashMap<String, String> chaves) {

        List<String> newLines = new ArrayList<>();

        if (chaves != null && !chaves.isEmpty()){
            lines.forEach(p->newLines.add(formata(p, chaves)));
        }
        else{
            lines.forEach(p->newLines.add(p));
        }

        try {
            Files.write(Path.of(filePath), newLines);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever o arquivo" + filePath);
        }

        System.out.println("Arquivo " + filePath + " escrito com sucesso!");
    }


    public List<String> getLines() {
        return lines;
    }

    public  void setLines(List<String> lines) {
        this.lines=lines;
    }
}
