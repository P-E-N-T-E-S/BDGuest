package br.com.guest.restaurante_admin.entidades.garcom.impl;

import br.com.guest.restaurante_admin.entidades.atende.Atende;
import br.com.guest.restaurante_admin.entidades.atende.AtendeService;
import br.com.guest.restaurante_admin.entidades.funcionarios.FuncionarioService;
import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.garcom.GarcomRepository;
import br.com.guest.restaurante_admin.entidades.garcom.GarcomService;
import br.com.guest.restaurante_admin.execoes.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GarcomServiceImpl implements GarcomService {


    private GarcomRepository garcomRepository;
    private FuncionarioService funcionarioService;
    private AtendeService atendeService;

    private  final List<String> colunasFuncionario =  Arrays.asList("data_contratacao", "salario", "horario_entrada", "horario_saida");
    private final List<String> colunasPessoa = Arrays.asList("nome", "rua", "bairro", "estado", "cidade", "cep", "email", "data_nascimento", "telefone");
    private final List<String> colunasGarcom = Arrays.asList("cpf", "cpf_gerente");

    public GarcomServiceImpl(GarcomRepository garcomRepository, FuncionarioService funcionarioService, AtendeService atendeService) {
        this.garcomRepository = garcomRepository;
        this.funcionarioService = funcionarioService;
        this.atendeService = atendeService;
    }

    @Override
    public void salvarGarcom(Garcom garcom) throws FuncionarioNaoEncontradoException, GerenteNaoEncontradoException {
        if(funcionarioService.buscarFuncionarioPorcpf(garcom.getCpf()) == null) {
            throw new FuncionarioNaoEncontradoException(garcom.getCpf());
        }
        if (funcionarioService.buscarFuncionarioPorcpf(garcom.getGerenteCpf()) != null || garcom.getGerenteCpf() == null) {
            throw new GerenteNaoEncontradoException(garcom.getGerenteCpf());
        }
        garcomRepository.salvarGarcom(garcom);
        if(garcom.getGerenteCpf() != null) {
            for(Integer mesa : garcom.getMesasAtendidas()) {
                atendeService.salvarAtende(new Atende(garcom.getCpf(), mesa));
            }
        }
    }

    @Override
    public List<Garcom> listarGarcoms() {
        return garcomRepository.listarGarcoms();
    }

    @Override
    public List<Garcom> buscarGarcomPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        //converter cpf para G.cpf no front
        if(colunasGarcom.contains(filtro)) {
            return garcomRepository.buscarGarcomPorFiltro(filtro, valor);
        }
        throw new FiltroNaoDisponivelException(filtro);
    }

    @Override
    public Garcom buscarGarcomPorCpf(String cpf) {
        return garcomRepository.buscarGarcomPorCpf(cpf);
    }

    @Override
    public void atualizarGarcomPorCpf(Garcom garcom, String cpf) {
        garcomRepository.atualizarGarcomPorCpf(garcom, cpf);
    }

    @Override
    public void atualizarGarcomPorFiltro(String filtro, String valor, String campoAlterado, String valorAlterado) throws FiltroNaoDisponivelException, CampoDeAlteracaoNaoEncontradoException {
        if(colunasGarcom.contains(campoAlterado)){
            if (colunasGarcom.contains(filtro)) {
                garcomRepository.atualizarGarcomPorFiltro(filtro, valor, campoAlterado, valorAlterado);
                return;
            }
            if (colunasFuncionario.contains(filtro)) {
                garcomRepository.atualizarGarcomPorFiltroDeFuncionario(filtro, valor, campoAlterado, valorAlterado);
                return;
            }
            if (colunasPessoa.contains(filtro)) {
                garcomRepository.atualizarGarcomPorFiltroDePessoa(filtro, valor, campoAlterado, valorAlterado);
                return;
            }
            throw new FiltroNaoDisponivelException(filtro);
        }
        throw new CampoDeAlteracaoNaoEncontradoException(campoAlterado);
    }

    @Override
    public void atualizarMesas(List<Integer> mesas, String cpfGarcom) {
        if(buscarGarcomPorCpf(cpfGarcom) != null) {
            atendeService.excluirAtendePorGarcom(cpfGarcom);
            for(Integer mesa : mesas) {
                atendeService.salvarAtende(new Atende(cpfGarcom, mesa));
            }
        }
        throw new GarcomNaoEncontradoException(cpfGarcom);
    }

    @Override
    public void removerGarcomPorCpf(String cpf) {
        garcomRepository.removerGarcomPorCpf(cpf);
    }

    @Override
    public void removerGarcomPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        if (colunasGarcom.contains(filtro)) {
            garcomRepository.removerGarcomPorFiltro(filtro, valor);
            return;
        }
        if (colunasFuncionario.contains(filtro)) {
            garcomRepository.removerGarcomPorFiltroDeFuncionario(filtro, valor);
            return;
        }
        if (colunasPessoa.contains(filtro)) {
            garcomRepository.removerGarcomPorFiltroDePessoa(filtro, valor);
            return;
        }
        throw new FiltroNaoDisponivelException(filtro);
    }
}
