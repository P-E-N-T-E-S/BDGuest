package br.com.guest.restaurante_admin.entidades.garcom;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;

public class Garcom {
    private String cpf;
    private String gerenteCpf;

    private Funcionario funcionario;

    public Garcom(String cpf, String gerenteCpf, Funcionario funcionario) {
        this.cpf = cpf;
        this.gerenteCpf = gerenteCpf;
        this.funcionario = funcionario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGerenteCpf() {
        return gerenteCpf;
    }

    public void setGerenteCpf(String gerenteCpf) {
        this.gerenteCpf = gerenteCpf;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
