package org.letscode.tecnicasprogramacao.model;

import java.util.List;

public interface Processamento<T> {
    void executar(List<T> filmes);
}
