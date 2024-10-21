package br.com.guest.restaurante_admin.execoes;

public class IdentificadorNaoEncontradoException extends RuntimeException {
    public IdentificadorNaoEncontradoException(String message) {
        super(message);
    }
}
