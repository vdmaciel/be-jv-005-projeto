package org.letscode.tecnicasprogramacao.services;

import org.letscode.tecnicasprogramacao.model.Filme;
import org.letscode.tecnicasprogramacao.model.Processamento;

import java.util.List;

public class ObterCinquentaMelhoresFilmesPorAno implements Processamento<Filme> {

    @Override
    public HashMap<List<Filme>, Long> executar(List<Filme> filmes, Long ano) {
        
        HashMap<List<Filme>, Long> melhoresFilmes = new HashMap<>();

        for (Long ano : filmes.stream().parallelStream().map(x -> x.getYear()).distinct().collect(Collections.toList()) ) {
            melhoresFilmes.put(filtrarMelhoresFilmes(), ano)
        }

        return melhoresFilmes;
    }

    private List<Filme> filtrarMelhoresFilmes(List<Filme> filmes, Long ano){
        List<Filme> MelhoresFilmes = filmes.stream().filter(x -> x.getYear == ano).sorted(new Comparator<Filme>() {
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
