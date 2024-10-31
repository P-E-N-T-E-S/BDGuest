package br.com.guest.restaurante_admin.execoes;

public class EstoqueNaoInformadoException extends RuntimeException {
    public EstoqueNaoInformadoException(String message) {
        super(message);
    }
}
