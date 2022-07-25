package org.letscode.tecnicasprogramacao.services;

import org.letscode.tecnicasprogramacao.model.Filme;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ObterVinteMelhoresFilmesTerror {

    public List<Filme> executar(List<Filme> filmes) {
        List<Filme> filmesTerror = filtrarMelhoresFilmesTerror(filmes);
        return filmesTerror;
    }

    private List<Filme> filtrarMelhoresFilmesTerror(List<Filme> filmes){
        List<Filme> filmesTerror = filmes.stream().filter(filme -> isTerror(filme)).sorted(new Comparator<Filme>() {
            @Override
            public int compare(Filme o1, Filme o2) {
                if (o1.getRank() < o2.getRank()){
                    return -1;
                } else if (o1.getRank() > o2.getRank()) {
                    return 1;
                }
                return 0;
            }
        }).limit(20).collect(Collectors.toList());
        return filmesTerror;
    }

    private boolean isTerror(Filme filme){
        Pattern pattern = Pattern.compile(".*(?i:(Horror)).*");
        Matcher matcher = pattern.matcher(filme.getGenre());

        while(matcher.find()){
            return true;
        }
        return false;
    }

    
}
