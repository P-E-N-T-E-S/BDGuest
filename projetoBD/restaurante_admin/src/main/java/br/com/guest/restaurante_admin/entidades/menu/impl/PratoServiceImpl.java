package br.com.guest.restaurante_admin.entidades.menu.impl;

import br.com.guest.restaurante_admin.entidades.menu.Prato;
import br.com.guest.restaurante_admin.entidades.menu.PratoRepository;
import br.com.guest.restaurante_admin.entidades.menu.PratoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PratoServiceImpl implements PratoService {
    
    private PratoRepository pratoRepository;

    public PratoServiceImpl(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }

    @Override
    public void salvarPrato(Prato prato) {
        pratoRepository.salvarPrato(prato);
    }

    @Override
    public Prato buscarPratoPorId(Long id) {
        return pratoRepository.buscarPratoPorId(id);
    }

    @Override
    public List<Prato> listarPratos() {
        return pratoRepository.listarPratos();
    }

    @Override
    public List<Prato> buscarPratoPorFiltro(String filtro, String valor) {
        return pratoRepository.buscarPratoPorFiltro(filtro, valor);
    }

    @Override
    public void removerPratoPorId(Long id) {
        pratoRepository.removerPratoPorId(id);
    }

    @Override
    public void atualizarPrato(Prato prato, Long id) {
        pratoRepository.atualizarPrato(prato, id);
    }
}
