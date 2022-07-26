package org.letscode.tecnicasprogramacao.services;

import org.letscode.tecnicasprogramacao.core.FileWrite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MetricaTempo {
    final private FileWrite file;
    private final HashMap<String, String> chave=new HashMap<>();
    private List<String> lines;
    private final DateTimeFormatter formatoDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    public MetricaTempo(String filePath) {
        this.file = new FileWrite(filePath);
        file.setLines(List.of(
                "Incio processamento: <%Incio processamento%>",
                "Fim processamento: <%Fim processamento%>",
                "Tempo em milisegundos: <%milisegundos%> milisegundos",
                "Tempo em segundos: <%segundos%> segundos"));
    }

    public FileWrite getFile(){return file;}

    public void iniciar(){
        this.chave.put("<%Incio processamento%>", LocalDateTime.now().format(this.formatoDate));
    }

    public void finalizar(){
        LocalDateTime fim = LocalDateTime.now();
        LocalDateTime inicio = LocalDateTime.parse(this.chave.get("<%Incio processamento%>"), this.formatoDate);
        this.chave.put("<%Fim processamento%>", fim.format(this.formatoDate));
        this.chave.put("<%milisegundos%>", String.valueOf(ChronoUnit.MILLIS.between(inicio, fim)));
        this.chave.put("<%segundos%>", String.valueOf(ChronoUnit.SECONDS.between(inicio, fim)));

        this.file.run(this.chave);
    }
}
