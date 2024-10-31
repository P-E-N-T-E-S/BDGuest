package br.com.guest.restaurante_admin.execoes;

public class MesaNaoEncontradaException extends RuntimeException {
    public MesaNaoEncontradaException(String message) {
        super(message);
    }
}
