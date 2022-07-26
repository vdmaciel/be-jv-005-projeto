package org.letscode.tecnicasprogramacao.services;

import org.letscode.tecnicasprogramacao.core.FileWrite;
import org.letscode.tecnicasprogramacao.model.Filme;
import org.letscode.tecnicasprogramacao.model.Processamento;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ObterCinquentaMelhoresFilmesPorAno implements Processamento<Filme> {

    @Override
    public void executar(List<Filme> filmes) {

        for (Long ano : filmes.stream().parallel().map(x -> x.getYear()).distinct().collect(Collectors.toList()) ) {
            FileWrite writer = new FileWrite("src/main/resources/melhores_filmes_"+ ano +".txt");
            writer.setLines(filtrarMelhoresFilmes(filmes, ano)
                .parallelStream()
                .map(f -> f.toString())
                .collect(Collectors.toList()));
            writer.run(null);
        }

    }

    private List<Filme> filtrarMelhoresFilmes(List<Filme> filmes, Long ano){
        List<Filme> MelhoresFilmes = filmes.stream().filter(x -> x.getYear().equals(ano)).sorted(new Comparator<Filme>() {
            @Override
            public int compare(Filme o1, Filme o2) {
                if (o1.getRank() < o2.getRank()){
                    return -1;
                } else if (o1.getRank() > o2.getRank()) {
                    return 1;
                }
                return 0;
            }
        }).limit(50).collect(Collectors.toList());
        return MelhoresFilmes;
    }

}
