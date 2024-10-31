package br.com.guest.restaurante_admin.execoes;

public class QuantidadeDeIngredienteNaoDisponivel extends RuntimeException {
    public QuantidadeDeIngredienteNaoDisponivel(String message) {
        super(message);
    }
}
