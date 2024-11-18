package br.com.guest.restaurante_admin.entidades.garcom;

import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Garcom {

    private String cpf;
    @JsonProperty("cpf_gerente")
    private String gerenteCpf;
    @JsonProperty("mesas_atendidas")
    private List<Integer> mesasAtendidas;

    private Funcionario funcionario;

    public Garcom(String cpf, String gerenteCpf, List<Integer> mesasAtendidas, Funcionario funcionario) {
        this.cpf = cpf;
        this.gerenteCpf = gerenteCpf;
        this.mesasAtendidas = mesasAtendidas;
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

    public List<Integer> getMesasAtendidas() {
        return mesasAtendidas;
    }

    public void setMesasAtendidas(List<Integer> mesasAtendidas) {
        this.mesasAtendidas = mesasAtendidas;
    }
}
