package br.com.guest.restaurante_admin.execoes;

public class GarcomNaoEncontradoException extends RuntimeException {
    public GarcomNaoEncontradoException(String message) {
        super(message);
    }
}
