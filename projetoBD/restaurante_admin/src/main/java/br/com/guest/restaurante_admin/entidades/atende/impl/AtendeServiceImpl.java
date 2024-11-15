package br.com.guest.restaurante_admin.entidades.atende.impl;

import br.com.guest.restaurante_admin.entidades.atende.Atende;
import br.com.guest.restaurante_admin.entidades.atende.AtendeRepository;
import br.com.guest.restaurante_admin.entidades.atende.AtendeService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class AtendeServiceImpl implements AtendeService {

    private AtendeRepository atendeRepository;

    public AtendeServiceImpl(AtendeRepository atendeRepository) {
        this.atendeRepository = atendeRepository;
    }

    @Override
    public void salvarAtende(Atende atende) {
        atendeRepository.salvarAtende(atende);
    }

    @Override
    public List<Atende> buscarAtendePorGarcom(String cpfGarcom) {
        return atendeRepository.buscarAtendePorGarcom(cpfGarcom);
    }

    @Override
    public List<Atende> buscarAtendePorMesa(Integer idMesa) {
        return atendeRepository.buscarAtendePorMesa(idMesa);
    }

    @Override
    public void excluirAtendePorGarcom(String cpf) {
        atendeRepository.excluirAtendePorGarcom(cpf);
    }

    @Override
    public String buscarPorMesaEHora(Integer id_mesa) {
        atendeRepository.buscarPorMesaEHora(id_mesa);
        return atendeRepository.buscarPorMesaEHora(id_mesa);
    }
}
