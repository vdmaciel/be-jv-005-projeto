package org.letscode.tecnicasprogramacao;

import java.util.List;

import org.letscode.tecnicasprogramacao.model.Filme;
import org.letscode.tecnicasprogramacao.services.CarregarFilmes;

public class Aplicacao {
    public static void main(String[] args) {
        CarregarFilmes carregarFilmes = new CarregarFilmes();
        List<Filme> filmes = carregarFilmes.executar();
    }
}
