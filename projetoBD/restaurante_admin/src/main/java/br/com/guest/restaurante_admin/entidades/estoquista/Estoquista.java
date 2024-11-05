package br.com.guest.restaurante_admin.entidades.estoquista;

import br.com.guest.restaurante_admin.entidades.estoque.Estoque;
import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Estoquista {
    private Funcionario funcionario;
    private Estoque estoque;

    private String cpf;
    @JsonProperty("cpg_gerente")
    private String cpfGerente;
    @JsonProperty("estoque_id")
    private Integer estoqueId;

    public Estoquista(Funcionario funcionario, Estoque estoque, String cpf, String cpfGerente, Integer estoqueId) {
        this.funcionario = funcionario;
        this.estoque = estoque;
        this.cpf = cpf;
        this.cpfGerente = cpfGerente;
        this.estoqueId = estoqueId;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpfGerente() {
        return cpfGerente;
    }

    public void setCpfGerente(String cpfGerente) {
        this.cpfGerente = cpfGerente;
    }

    public Integer getEstoqueId() {
        return estoqueId;
    }

    public void setEstoqueId(Integer estoqueId) {
        this.estoqueId = estoqueId;
    }
}
