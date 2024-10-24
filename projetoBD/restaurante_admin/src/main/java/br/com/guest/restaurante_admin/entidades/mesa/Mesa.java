package br.com.guest.restaurante_admin.entidades.mesa;

import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class Mesa {

    @JsonProperty("numero_id")
    private Integer numeroId;
    @JsonProperty("quantidade_cadeiras")
    private Integer quantidadeCadeiras;
    @JsonProperty("cpf_garcom")
    private String cpfGarcom;

    public Mesa(Integer numeroId, Integer quantidadeCadeiras, String cpfGarcom) {
        this.numeroId = numeroId;
        this.quantidadeCadeiras = quantidadeCadeiras;
        this.cpfGarcom = cpfGarcom;
    }

    public Integer getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(Integer numeroId) {
        this.numeroId = numeroId;
    }

    public Integer getQuantidadeCadeiras() {
        return quantidadeCadeiras;
    }

    public void setQuantidadeCadeiras(Integer quantidadeCadeiras) {
        this.quantidadeCadeiras = quantidadeCadeiras;
    }

    public String getCpfGarcom() {
        return cpfGarcom;
    }

    public void setCpfGarcom(String cpfGarcom) {
        this.cpfGarcom = cpfGarcom;
    }
}
