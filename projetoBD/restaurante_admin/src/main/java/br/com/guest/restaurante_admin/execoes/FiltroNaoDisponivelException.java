package br.com.guest.restaurante_admin.execoes;

public class FiltroNaoDisponivelException extends RuntimeException {
    public FiltroNaoDisponivelException(String message) {
        super(message);
    }
}
