package br.com.guest.restaurante_admin.execoes;

public class EstoqueNaoEncontradoException extends RuntimeException {
    public EstoqueNaoEncontradoException(String message) {
        super(message);
    }
}
