package br.com.guest.restaurante_admin.entidades.gerente;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;

public class Gerente {

    private String cpf;

    private Funcionario funcionario;

    public Gerente(String cpf, Funcionario funcionario) {
        this.cpf = cpf;
        this.funcionario = funcionario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
