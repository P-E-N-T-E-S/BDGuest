package br.com.guest.restaurante_admin.entidades.menu;

import java.util.List;

public interface PratoRepository {
    void salvarPrato(Prato prato);
    Prato buscarPratoPorId(Long id);
    List<Prato> listarPratos();
    List<Prato> buscarPratoPorFiltro(String filtro, String valor);
    void removerPratoPorId(Long id);
    void atualizarPrato(Prato prato, Long id);
}
