package br.com.guest.restaurante_admin.pessoa.impl;

import br.com.guest.restaurante_admin.pessoa.Pessoa;
import br.com.guest.restaurante_admin.pessoa.PessoaRepository;
import br.com.guest.restaurante_admin.pessoa.PessoaService;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public void salvarNovaPessoa(Pessoa pessoa) {
        pessoaRepository.salvarNovaPessoa(pessoa);
    }
}
