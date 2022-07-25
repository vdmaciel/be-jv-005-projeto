package org.letscode.tecnicasprogramacao.model;

import java.util.List;

public interface Processamento<T> {
    List<T> executar(List<T> filmes);
}
