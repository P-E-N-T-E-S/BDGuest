package br.com.guest.restaurante_admin.entidades.menu.impl;

import br.com.guest.restaurante_admin.entidades.menu.Prato;
import br.com.guest.restaurante_admin.entidades.menu.PratoRepository;
import br.com.guest.restaurante_admin.entidades.menu.PratoService;
import br.com.guest.restaurante_admin.entidades.usa.Usa;
import br.com.guest.restaurante_admin.entidades.usa.UsaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PratoServiceImpl implements PratoService {
    
    private PratoRepository pratoRepository;
    private UsaService usaService;

    public PratoServiceImpl(PratoRepository pratoRepository, UsaService usaService) {
        this.pratoRepository = pratoRepository;
        this.usaService = usaService;
    }

    @Override
    public void salvarPrato(Prato prato) {
        pratoRepository.salvarPrato(prato);
        for (List<Integer> ingrediente : prato.getIngredientes() ) {
            usaService.salvarUso(new Usa(prato.getId(), ingrediente.get(0), ingrediente.get(1))); //TODO: resolver a questão da quantidade
        }
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

    @Override
    public void removerPratoPorIngrediente(Long ingredienteId) {
        pratoRepository.removerPratoPorIngrediente(ingredienteId);
    }
}
