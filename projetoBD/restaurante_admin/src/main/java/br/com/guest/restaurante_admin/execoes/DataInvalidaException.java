package br.com.guest.restaurante_admin.execoes;

public class DataInvalidaException extends RuntimeException {
    public DataInvalidaException(String message) {
        super(message);
    }
}
