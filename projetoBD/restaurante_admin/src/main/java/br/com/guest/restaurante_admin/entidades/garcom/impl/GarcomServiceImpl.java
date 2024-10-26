package br.com.guest.restaurante_admin.entidades.garcom.impl;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.entidades.funcionarios.FuncionarioService;
import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.garcom.GarcomRepository;
import br.com.guest.restaurante_admin.entidades.garcom.GarcomService;
import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.execoes.FuncionarioNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@Service
public class GarcomServiceImpl implements GarcomService {
    //TODO [HIGH]: fazer as validações de cpf para deleção e alteração


    private GarcomRepository garcomRepository;
    private FuncionarioService funcionarioService;

    private  final List<String> colunasFuncionario =  Arrays.asList("data_contratacao", "salario", "horario_entrada", "horario_saida");
    private final List<String> colunasPessoa = Arrays.asList("nome", "rua", "bairro", "estado", "cidade", "cep", "email", "data_nascimento", "telefone");
    private final List<String> colunasGarcom = Arrays.asList("cpf", "cpf_gerente");

    public GarcomServiceImpl(GarcomRepository garcomRepository, FuncionarioService funcionarioService) {
        this.garcomRepository = garcomRepository;
        this.funcionarioService = funcionarioService;
    }

    @Override
    public void salvarGarcom(Garcom garcom) throws FuncionarioNaoEncontradoException {
        //TODO [HIGH]: fazer salvar as mesas que o garçom vai atender e colocar isso na parte de alterar tambem
        //TODO [LOW]: fazer verificacao do cpf do garcom gerente
        if(funcionarioService.buscarFuncionarioPorcpf(garcom.getCpf()) == null) {
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado");
        }
        garcomRepository.salvarGarcom(garcom);
    }

    @Override
    public List<Garcom> listarGarcoms() {
        return garcomRepository.listarGarcoms();
    }

    @Override
    public List<Garcom> buscarGarcomPorFiltro(String filtro, String valor) throws FiltroNaoDisponivelException {
        //todo: converter cpf para G.cpf
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
