package br.com.guest.restaurante_admin.execoes;

public class PedidosNaoEntreguesException extends RuntimeException {
    public PedidosNaoEntreguesException(String message) {
        super(message);
    }
}
