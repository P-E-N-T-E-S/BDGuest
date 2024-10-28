package br.com.guest.restaurante_admin.entidades.atende;

import java.util.List;

public interface AtendeService {
    void salvarAtende(Atende atende);
    List<Atende> buscarAtendePorGarcom(String cpfGarcom);
    List<Atende> buscarAtendePorMesa(Integer idMesa);
    void excluirAtende(Atende atende);
}
