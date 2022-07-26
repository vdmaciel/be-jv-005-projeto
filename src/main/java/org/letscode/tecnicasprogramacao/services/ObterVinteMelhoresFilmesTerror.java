package org.letscode.tecnicasprogramacao.services;

import org.letscode.tecnicasprogramacao.model.Filme;
import org.letscode.tecnicasprogramacao.model.Processamento;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ObterVinteMelhoresFilmesTerror implements Processamento<Filme> {

    public List<Filme> executar(List<Filme> filmes) {
        return filtrarMelhoresFilmesTerror(filmes);
    }

    private List<Filme> filtrarMelhoresFilmesTerror(List<Filme> filmes){
        return filmes.stream().filter(this::isTerror).sorted(new Comparator<Filme>() {
            @Override
            public int compare(Filme o1, Filme o2) {
                return o1.getRank() > o2.getRank() ? 1 : (o1.getRank() < o2.getRank() ? -1 : 0);
            }
        }).limit(20).collect(Collectors.toList());
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
