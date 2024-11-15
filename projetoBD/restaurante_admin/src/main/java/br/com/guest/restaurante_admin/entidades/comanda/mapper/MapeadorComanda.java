package br.com.guest.restaurante_admin.entidades.comanda.mapper;

import br.com.guest.restaurante_admin.entidades.comanda.Comanda;
import br.com.guest.restaurante_admin.entidades.funcionarios.Funcionario;
import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import br.com.guest.restaurante_admin.entidades.mesa.Mesa;
import br.com.guest.restaurante_admin.entidades.pessoa.Pessoa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class MapeadorComanda implements RowMapper<Comanda> {
    @Override
    public Comanda mapRow(ResultSet rs, int rowNum) throws SQLException { //TODO: pegar o garçom
        String nome = rs.getString("nome");

        Pessoa pessoa = new Pessoa(null, nome, null, null, null, null, null, null, null, null, null);

        LocalTime horaEntrada = rs.getTime("horario_entrada").toLocalTime();
        LocalTime horaSaida = rs.getTime("horario_saida").toLocalTime();
        Funcionario funcionario =  new Funcionario(pessoa, null, null, null, horaEntrada, horaSaida);


        Garcom garcom = new Garcom(null, null, null, funcionario);

        Integer idMesa = rs.getInt("M.numero_id");
        Integer quantidadeCadeiras = rs.getInt("quantidade_cadeiras");

        Mesa mesa = new Mesa(idMesa, quantidadeCadeiras);

        Integer id = rs.getInt("C.numero_id");
        String cpfPessoa = rs.getString("cpf_pessoa");
        LocalDateTime dataCadastro = rs.getTimestamp("acesso").toLocalDateTime();
        String nomeCliente = rs.getString("nome_cliente");
        Integer mesaId = rs.getInt("mesa");
        Boolean chamada = rs.getBoolean("chamando_garcom");
        return new Comanda(garcom, mesa, id, cpfPessoa, nomeCliente, dataCadastro, mesaId, null, chamada);
    }
}
