package br.com.guest.restaurante_admin.execoes;

public class IngredientesInsuficientesException extends RuntimeException {
    public IngredientesInsuficientesException(String message) {
        super(message);
    }
}
