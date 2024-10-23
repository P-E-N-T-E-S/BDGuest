package br.com.guest.restaurante_admin.execoes;

public class EstoqueNaoEncontrado extends RuntimeException {
    public EstoqueNaoEncontrado(String message) {
        super(message);
    }
}
