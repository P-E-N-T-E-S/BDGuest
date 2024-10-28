package br.com.guest.restaurante_admin.entidades.atende;

import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.mesa.Mesa;

public class Atende {

    private Garcom garcom;
    private Mesa mesa;

    private String cpfGarcom;
    private Integer idMesa;

    public Atende(Garcom garcom, Mesa mesa, String cpfGarcom, Integer idMesa) {
        this.garcom = garcom;
        this.mesa = mesa;
        this.cpfGarcom = cpfGarcom;
        this.idMesa = idMesa;
    }

    public Atende(String cpfGarcom, Integer idMesa) {
        this.cpfGarcom = cpfGarcom;
        this.idMesa = idMesa;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public void setGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public String getCpfGarcom() {
        return cpfGarcom;
    }

    public void setCpfGarcom(String cpfGarcom) {
        this.cpfGarcom = cpfGarcom;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }
}
