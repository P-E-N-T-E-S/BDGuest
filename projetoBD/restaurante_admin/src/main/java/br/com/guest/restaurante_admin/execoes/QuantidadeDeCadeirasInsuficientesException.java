package br.com.guest.restaurante_admin.execoes;

public class QuantidadeDeCadeirasInsuficientesException extends RuntimeException {
    public QuantidadeDeCadeirasInsuficientesException(String message) {
        super(message);
    }
}
