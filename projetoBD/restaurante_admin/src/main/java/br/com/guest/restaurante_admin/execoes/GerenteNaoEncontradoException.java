package br.com.guest.restaurante_admin.execoes;

public class GerenteNaoEncontradoException extends RuntimeException {
    public GerenteNaoEncontradoException(String message) {
        super(message);
    }
}
