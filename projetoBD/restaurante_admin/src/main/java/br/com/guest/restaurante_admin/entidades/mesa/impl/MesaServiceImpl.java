package br.com.guest.restaurante_admin.entidades.mesa.impl;

import br.com.guest.restaurante_admin.entidades.mesa.Mesa;
import br.com.guest.restaurante_admin.entidades.mesa.MesaRepository;
import br.com.guest.restaurante_admin.entidades.mesa.MesaService;
import br.com.guest.restaurante_admin.execoes.GarcomNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * Implementação de MesaService para gerenciar as operações relacionadas a Mesa.
 */
public class MesaServiceImpl implements MesaService {

    private MesaRepository mesaRepository;

/**
 * Construtor para MesaServiceImpl.
 *
 * @param mesaRepository o repositório de Mesa a ser utilizado.
 */
public MesaServiceImpl(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

/**
 * Salva uma mesa no repositório, verificando se o garçom existe.
 *
 * @param mesa a mesa a ser salva.
 * @throws GarcomNaoEncontradoException se o garçom associado à mesa não for encontrado.
 */
@Override
public void salvarMesa(Mesa mesa) {
    mesaRepository.salvarMesa(mesa);
}

/**
 * Busca uma mesa pelo seu ID.
 *
 * @param id o ID da mesa a ser buscada.
 * @return a mesa encontrada, ou null se não houver mesa com o ID fornecido.
 */
@Override
public Mesa acharMesaPorId(Integer id) {
        return mesaRepository.acharMesaPorId(id);
    }

/**
 * Lista todas as mesas do repositório.
 *
 * @return a lista de mesas.
 */
@Override
public List<Mesa> listarMesas() {
        return mesaRepository.listarMesas();
    }

/**
 * Lista mesas com base em um filtro e valor fornecidos.
 *
 * @param filtro o filtro a ser aplicado.
 * @param valor o valor do filtro.
 * @return a lista de mesas filtradas.
 */
@Override
public List<Mesa> listarMesasPorFiltro(String filtro, String valor) {
        return mesaRepository.listarMesasPorFiltro(filtro, valor);
    }

/**
 * Exclui uma mesa com base no ID fornecido.
 *
 * @param id o ID da mesa a ser excluída.
 */
@Override
public void excluirMesaPorId(Integer id) {
        mesaRepository.excluirMesaPorId(id);
    }

/**
 * Altera uma mesa com base no ID fornecido.
 *
 * @param mesa a mesa com as novas informações.
 * @param id o ID da mesa a ser alterada.
 */
@Override
public void alterarMesaPorID(Mesa mesa, Integer id) {
        mesaRepository.alterarMesaPorID(mesa, id);
    }
}
