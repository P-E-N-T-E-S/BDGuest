package br.com.guest.restaurante_admin.entidades.atende;

import java.util.List;

public interface AtendeRepository {
    void salvarAtende(Atende atende);
    List<Atende> buscarAtendePorGarcom(String cpfGarcom);
    List<Atende> buscarAtendePorMesa(Integer id_mesa);
    void excluirAtende(Atende atende);
}
