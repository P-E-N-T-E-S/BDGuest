    package br.com.guest.restaurante_admin.entidades.mesa.impl;

    import br.com.guest.restaurante_admin.entidades.mesa.Mesa;
    import br.com.guest.restaurante_admin.entidades.mesa.MesaRepository;
    import br.com.guest.restaurante_admin.entidades.mesa.MesaService;
    import br.com.guest.restaurante_admin.execoes.GarcomNaoEncontradoException;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class MesaServiceImpl implements MesaService {

    private MesaRepository mesaRepository;

    public MesaServiceImpl(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }


    @Override
    public void salvarMesa(Mesa mesa) {
    mesaRepository.salvarMesa(mesa);
    }

    @Override
    public Mesa acharMesaPorId(Integer id) {
        return mesaRepository.acharMesaPorId(id);
    }

    @Override
    public List<Mesa> listarMesaPorGarcom(String cpfGarcom) {
        return mesaRepository.listarMesaPorGarcom(cpfGarcom);
    }

    @Override
    public List<Mesa> listarMesas() {
        return mesaRepository.listarMesas();
    }

    @Override
    public void excluirMesaPorId(Integer id) {
        mesaRepository.excluirMesaPorId(id);
    }

    @Override
    public void alterarMesaPorID(Mesa mesa, Integer id) {
        mesaRepository.alterarMesaPorID(mesa, id);
    }
    }
