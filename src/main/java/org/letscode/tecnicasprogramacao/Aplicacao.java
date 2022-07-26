package org.letscode.tecnicasprogramacao;

import java.util.ArrayList;
import java.util.List;

import org.letscode.tecnicasprogramacao.model.Filme;
import org.letscode.tecnicasprogramacao.model.Processamento;
import org.letscode.tecnicasprogramacao.services.CarregarFilmes;
import org.letscode.tecnicasprogramacao.services.ObterCinquentaMelhoresFilmesPorAno;
import org.letscode.tecnicasprogramacao.services.ObterVinteMelhoresFilmesTerror;

public class Aplicacao {
    public static void main(String[] args) {

        CarregarFilmes carregarFilmes = new CarregarFilmes();
        List<Filme> filmes = carregarFilmes.executar();

        List<Processamento<Filme>> services = new ArrayList<>();
        services.add(new ObterVinteMelhoresFilmesTerror());
        services.add(new ObterCinquentaMelhoresFilmesPorAno());
        
        for (Processamento<Filme> service: services) {
            service.executar(filmes);
        }
    }
}
