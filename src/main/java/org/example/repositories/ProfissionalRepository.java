package org.example.repositories;

import org.example.entities.Profissional;
import org.example.infrastructure.OracleDbConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProfissionalRepository {

    public static final String TB_NAME = "TB_PROFISSIONAL";

    private Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
        String username = "rm554113";
        String password = "081093";
        return DriverManager.getConnection(url, username, password);
    }

    public Profissional save(Profissional profissional) {
        String sql = "INSERT INTO tb_profissional (nome, sobrenome, genero, nacionalidade, telefone, email, " +
                "especializacao, atuacao, cep, logradouro, numero, complemento, bairro, localidade, estado, senha) " +
               "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getSobrenome());
            statement.setString(3, profissional.getGenero());
            statement.setString(4, profissional.getNacionalidade());
            statement.setString(5, profissional.getTelefone());
            statement.setString(6, profissional.getEmail());
            statement.setString(7, profissional.getEspecializacao());
            statement.setString(8, profissional.getAtuacao());
            statement.setString(9, profissional.getCep());
            statement.setString(10, profissional.getLogradouro());
            statement.setString(11, profissional.getNumero());
            statement.setString(12, profissional.getComplemento());
            statement.setString(13, profissional.getBairro());
            statement.setString(14, profissional.getLocalidade());
            statement.setString(15, profissional.getEstado());
            statement.setString(16, profissional.getSenha());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    profissional.setId(generatedId);
                } else {
                    throw new SQLException("Falha ao inserir profissional. ID não obtido.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profissional;
    }

    public List<Profissional> getAll(String nome, String sobrenome, String genero, String nacionalidade, String telefone,
                                     String email, String especializacao, String atuacao, String cep, String logradouro,
                                     String numero, String complemento, String bairro, String localidade, String estado,
                                     int limit, int offset) {

        List<Profissional> profissionais = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM TB_PROFISSIONAL WHERE 1=1");

        List<Object> parameters = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            query.append(" AND nome LIKE ?");
            parameters.add("%" + nome + "%");
        }
        if (sobrenome != null && !sobrenome.isEmpty()) {
            query.append(" AND sobrenome LIKE ?");
            parameters.add("%" + sobrenome + "%");
        }
        if (genero != null && !genero.isEmpty()) {
            query.append(" AND genero = ?");
            parameters.add(genero);
        }
        if (nacionalidade != null && !nacionalidade.isEmpty()) {
            query.append(" AND nacionalidade LIKE ?");
            parameters.add("%" + nacionalidade + "%");
        }
        if (telefone != null && !telefone.isEmpty()) {
            query.append(" AND telefone LIKE ?");
            parameters.add("%" + telefone + "%");
        }
        if (email != null && !email.isEmpty()) {
            query.append(" AND email LIKE ?");
            parameters.add("%" + email + "%");
        }
        if (especializacao != null && !especializacao.isEmpty()) {
            query.append(" AND especializacao LIKE ?");
            parameters.add("%" + especializacao + "%");
        }
        if (atuacao != null && !atuacao.isEmpty()) {
            query.append(" AND atuacao LIKE ?");
            parameters.add("%" + atuacao + "%");
        }
        if (cep != null && !cep.isEmpty()) {
            query.append(" AND cep LIKE ?");
            parameters.add("%" + cep + "%");
        }
        if (logradouro != null && !logradouro.isEmpty()) {
            query.append(" AND logradouro LIKE ?");
            parameters.add("%" + logradouro + "%");
        }
        if (numero != null && !numero.isEmpty()) {
            query.append(" AND numero LIKE ?");
            parameters.add("%" + numero + "%");
        }
        if (complemento != null && !complemento.isEmpty()) {
            query.append(" AND complemento LIKE ?");
            parameters.add("%" + complemento + "%");
        }
        if (bairro != null && !bairro.isEmpty()) {
            query.append(" AND bairro LIKE ?");
            parameters.add("%" + bairro + "%");
        }
        if (localidade != null && !localidade.isEmpty()) {
            query.append(" AND localidade LIKE ?");
            parameters.add("%" + localidade + "%");
        }
        if (estado != null && !estado.isEmpty()) {
            query.append(" AND estado LIKE ?");
            parameters.add("%" + estado + "%");
        }

        query.append(" ORDER BY id ASC");
        query.append(" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        parameters.add(offset);
        parameters.add(limit);

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            ResultSet resultSet = statement.executeQuery();while (resultSet.next()) {
                Profissional profissional = new Profissional(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("sobrenome"),
                        resultSet.getString("genero"),
                        resultSet.getString("nacionalidade"),
                        resultSet.getString("telefone"),
                        resultSet.getString("email"),
                        resultSet.getString("especializacao"),
                        resultSet.getString("atuacao"),
                        resultSet.getString("cep"),
                        resultSet.getString("logradouro"),
                        resultSet.getString("numero"),
                        resultSet.getString("complemento"),
                        resultSet.getString("bairro"),
                        resultSet.getString("localidade"),
                        resultSet.getString("estado"),
                        resultSet.getString("senha")
                );
                profissionais.add(profissional);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profissionais;
    }

    public List<Object> getAllByPesquisa(int idPesquisa) {
        List<Object> profissionais = new ArrayList<>();
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE IDPESQUISA = ?")) {
            stmt.setInt(1, idPesquisa);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                profissionais.add(new Profissional(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("SOBRENOME"),
                        rs.getString("GENERO"),
                        rs.getString("NACIONALIDADE"),
                        rs.getString("TELEFONE"),
                        rs.getString("EMAIL"),
                        rs.getString("ESPECIALIZACAO"),
                        rs.getString("ATUACAO"),
                        rs.getString("CEP"),
                        rs.getString("LOGRADOURO"),
                        rs.getString("NUMERO"),
                        rs.getString("COMPLEMENTO"),
                        rs.getString("BAIRRO"),
                        rs.getString("LOCALIDADE"),
                        rs.getString("ESTADO"),
                        rs.getString("SENHA")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profissionais;
    }

    public Optional<Profissional> get(int id) {
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?")) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Profissional(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("SOBRENOME"),
                        rs.getString("GENERO"),
                        rs.getString("NACIONALIDADE"),
                        rs.getString("TELEFONE"),
                        rs.getString("EMAIL"),
                        rs.getString("ESPECIALIZACAO"),
                        rs.getString("ATUACAO"),
                        rs.getString("CEP"),
                        rs.getString("LOGRADOURO"),
                        rs.getString("NUMERO"),
                        rs.getString("COMPLEMENTO"),
                        rs.getString("BAIRRO"),
                        rs.getString("LOCALIDADE"),
                        rs.getString("ESTADO"),
                        rs.getString("SENHA")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void create(Profissional profissional) {
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("INSERT INTO " + TB_NAME + " (NOME, SOBRENOME, GENERO, NACIONALIDADE, TELEFONE, " +
                     "EMAIL, ESPECIALIZACAO, ATUACAO, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, LOCALIDADE, ESTADO, SENHA) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getSobrenome());
            stmt.setString(3, profissional.getGenero());
            stmt.setString(4, profissional.getNacionalidade());
            stmt.setString(5, profissional.getTelefone());
            stmt.setString(6, profissional.getEmail());
            stmt.setString(7, profissional.getEspecializacao());
            stmt.setString(8, profissional.getAtuacao());
            stmt.setString(9, profissional.getCep());
            stmt.setString(10, profissional.getLogradouro());
            stmt.setString(11, profissional.getNumero());
            stmt.setString(12, profissional.getComplemento());
            stmt.setString(13, profissional.getBairro());
            stmt.setString(14, profissional.getLocalidade());
            stmt.setString(15, profissional.getEstado());
            stmt.setString(16, profissional.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update (int id, Profissional profissional) {
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("UPDATE " + TB_NAME + " SET NOME = ?, SOBRENOME = ?, GENERO = ?, NACIONALIDADE = ?, " +
                     "TELEFONE = ?, EMAIL = ?, ESPECIALIZACAO = ?, ATUACAO = ?, CEP = ?, LOGRADOURO = ?, NUMERO = ?, COMPLEMENTO = ?, " +
                     "BAIRRO = ?, LOCALIDADE = ?, ESTADO = ?, SENHA = ? WHERE ID = ?");) {
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getSobrenome());
            stmt.setString(3, profissional.getGenero());
            stmt.setString(4, profissional.getNacionalidade());
            stmt.setString(5, profissional.getTelefone());
            stmt.setString(6, profissional.getEmail());
            stmt.setString(7, profissional.getEspecializacao());
            stmt.setString(8, profissional.getAtuacao());
            stmt.setString(9, profissional.getCep());
            stmt.setString(10, profissional.getLogradouro());
            stmt.setString(11, profissional.getNumero());
            stmt.setString(12, profissional.getComplemento());
            stmt.setString(13, profissional.getBairro());
            stmt.setString(14, profissional.getLocalidade());
            stmt.setString(15, profissional.getEstado());
            stmt.setString(16, profissional.getSenha());
            stmt.setInt(17, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int count(String nome, String sobrenome, String genero, String nacionalidade, String telefone, String email,
                     String especializacao, String atuacao) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM TB_PROFISSIONAL WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            query.append(" AND nome LIKE ?");
            parameters.add("%" + nome + "%");
        }
        if (sobrenome != null && !sobrenome.isEmpty()) {
            query.append(" AND sobrenome LIKE ?");
            parameters.add("%" + sobrenome + "%");
        }
        if (genero != null && !genero.isEmpty()) {
            query.append(" AND genero = ?");
            parameters.add(genero);
        }
        if (nacionalidade != null && !nacionalidade.isEmpty()) {
            query.append(" AND nacionalidade LIKE ?");
            parameters.add("%" + nacionalidade + "%");
        }
        if (telefone != null && !telefone.isEmpty()) {
            query.append(" AND telefone LIKE ?");
            parameters.add("%" + telefone + "%");
        }
        if (email != null && !email.isEmpty()) {
            query.append(" AND email LIKE ?");
            parameters.add("%" + email + "%");
        }
        if (especializacao != null && !especializacao.isEmpty()) {
            query.append(" AND especializacao LIKE ?");
            parameters.add("%" + especializacao + "%");
        }
        if (atuacao != null && !atuacao.isEmpty()) {
            query.append(" AND atuacao LIKE ?");
            parameters.add("%" + atuacao + "%");
        }

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void delete(int id) {
        try (var conn = new OracleDbConfiguration().getConnection();
             var stmt = conn.prepareStatement("DELETE FROM " + TB_NAME + " WHERE ID = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

