package br.com.guest.restaurante_admin.entidades.comanda.impl;

import br.com.guest.restaurante_admin.entidades.atende.AtendeService;
import br.com.guest.restaurante_admin.entidades.clientes.ClienteService;
import br.com.guest.restaurante_admin.entidades.comanda.Comanda;
import br.com.guest.restaurante_admin.entidades.comanda.ComandaRepository;
import br.com.guest.restaurante_admin.entidades.comanda.ComandaService;
import br.com.guest.restaurante_admin.entidades.usa.UsaService;
import br.com.guest.restaurante_admin.execoes.ClienteNaoCadastradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComandaServiceImpl implements ComandaService {

    private ComandaRepository comandaRepository;

    private ClienteService clienteService;

    private AtendeService atendeService;

    public ComandaServiceImpl(ComandaRepository comandaRepository, ClienteService clienteService, AtendeService atendeService) {
        this.comandaRepository = comandaRepository;
        this.clienteService = clienteService;
        this.atendeService = atendeService;
    }

    @Override
    public void salvarComanda(Comanda comanda) throws ClienteNaoCadastradoException {
        String cpf_garcom = atendeService.buscarPorMesaEHora(comanda.getMesaId());
        comanda.setCpfGarcom(cpf_garcom);
        comandaRepository.salvarComanda(comanda);
        if(clienteService.buscarClientePorCpf(comanda.getCpfPessoa()) == null) {
            throw new ClienteNaoCadastradoException("Cliente nao cadastrado");
        }
    }
    @Override
    public Comanda buscarComandaPorId(Integer id) {
        return comandaRepository.buscarComandaPorId(id);
    }

    @Override
    public List<Comanda> listarComandas() {
        return comandaRepository.listarComandas();
    }

    @Override
    public void excluirComanda(Integer id) {
        comandaRepository.excluirComanda(id);
    }

    @Override
    public void alterarComanda(Comanda comanda, Integer id) {
        comandaRepository.alterarComanda(comanda, id);
    }

    @Override
    public void chamarGarcom(Integer id) {
        comandaRepository.chamarGarcom(id);
    }

    @Override
    public void cancelarChamado(Integer id) {
        comandaRepository.cancelarChamado(id);
    }

    @Override
    public List<Comanda> buscarComandaPorCpf(String cpf) {
        return comandaRepository.buscarComandaPorCpf(cpf);
    }
}
