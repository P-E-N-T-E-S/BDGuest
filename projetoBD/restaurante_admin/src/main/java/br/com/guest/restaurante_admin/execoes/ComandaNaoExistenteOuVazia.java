package br.com.guest.restaurante_admin.execoes;

public class ComandaNaoExistenteOuVazia extends RuntimeException {
    public ComandaNaoExistenteOuVazia(String message) {
        super(message);
    }
}
