package br.com.guest.restaurante_admin.execoes;

public class ClienteNaoCadastradoException extends RuntimeException {
    public ClienteNaoCadastradoException(String message) {
        super(message);
    }
}
