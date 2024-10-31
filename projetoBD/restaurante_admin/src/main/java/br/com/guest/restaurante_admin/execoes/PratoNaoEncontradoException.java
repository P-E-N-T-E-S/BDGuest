package br.com.guest.restaurante_admin.execoes;

public class PratoNaoEncontradoException extends RuntimeException {
    public PratoNaoEncontradoException(String message) {
        super(message);
    }
}
