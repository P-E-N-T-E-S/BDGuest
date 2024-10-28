package br.com.guest.restaurante_admin.entidades.atende.impl;

import br.com.guest.restaurante_admin.entidades.atende.Atende;
import br.com.guest.restaurante_admin.entidades.atende.AtendeRepository;
import br.com.guest.restaurante_admin.entidades.atende.mapper.MapeadorAtende;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AtendeRepositoryImpl implements AtendeRepository {

    private JdbcTemplate jdbcTemplate;

    public AtendeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvarAtende(Atende atende) {
        String sql = "INSERT INTO ATENDE (fk_Garcom_cpf, fk_Mesas_numero_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, atende.getCpfGarcom(), atende.getIdMesa());
    }

    @Override
    public List<Atende> buscarAtendePorGarcom(String cpfGarcom) {
        String sql = "SELECT * FROM Garcom G join Funcionario F on G.cpf = F.cpf join Pessoa P on F.cpf = P.cpf join Atende A on A.fk_Garcom_cpf = G.cpf join Mesa M on M.numero_id = A.fk_Mesas_numero_id WHERE G.cpf = ?";
        return jdbcTemplate.query(sql, new MapeadorAtende(), cpfGarcom);
    }

    @Override
    public List<Atende> buscarAtendePorMesa(Integer id_mesa) {
        String sql = "SELECT * FROM Garcom G join Funcionario F on G.cpf = F.cpf join Pessoa P on F.cpf = P.cpf join Atende A on A.fk_Garcom_cpf = G.cpf join Mesa M on M.numero_id = A.fk_Mesas_numero_id WHERE M.numero_id = ?";
        return jdbcTemplate.query(sql, new MapeadorAtende(), id_mesa);
    }

    @Override
    public void excluirAtendePorGarcom(String cpf) {
        String sql = "DELETE FROM Atende A WHERE fk_Garcom_cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }
}
