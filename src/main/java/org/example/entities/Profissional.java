package org.example.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({
        "nome",
        "sobrenome",
        "genero",
        "nacionalidade",
        "telefone",
        "email",
        "especializacao",
        "atuacao",
        "cep",
        "logradouro",
        "numero",
        "complemento",
        "bairro",
        "localidade",
        "estado",
        "senha"
})

public class Profissional {
    private int id;
    private String nome;
    private String sobrenome;
    private String genero;
    private String nacionalidade;
    private String telefone;
    private String email;
    private String especializacao;
    private String atuacao;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String estado;
    private String senha;
    private transient Pesquisa pesquisa;

    public Profissional() {}

    public Profissional(int id, String nome, String sobrenome, String genero, String nacionalidade, String telefone,
                        String email, String especializacao, String atuacao, String cep, String logradouro, String numero,
                        String complemento, String bairro, String localidade, String estado, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.genero = genero;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.email = email;
        this.especializacao = especializacao;
        this.atuacao = atuacao;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.estado = estado;
        this.senha = senha;
    }

    public Profissional(int id, String nome, String sobrenome, String genero, String nacionalidade, String telefone,
                        String email, String especializacao, String atuacao, String cep, String logradouro, String numero,
                        String complemento, String bairro, String localidade, String estado, String senha, Pesquisa pesquisa) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.genero = genero;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.email = email;
        this.especializacao = especializacao;
        this.atuacao = atuacao;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.estado = estado;
        this.senha = senha;
        this.pesquisa = pesquisa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public String getAtuacao() {
        return atuacao;
    }

    public void setAtuacao(String atuacao) {
        this.atuacao = atuacao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pesquisa getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }

    // Método para verificar a senha em texto plano contra o hash armazenado
    public boolean verificarSenha(String senha) {
        return BCrypt.checkpw(senha, this.senha);
    }

    @Override
    public String toString() {
        return "Profissional{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", genero='" + genero + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", especializacao='" + especializacao + '\'' +
                ", atuacao='" + atuacao + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", estado='" + estado + '\'' +
                ", senha='" + senha + '\'' +
                ", pesquisa=" + pesquisa +
                '}';
    }

    public Map<Boolean, List<String>> validate() {
        List<String> errors = new ArrayList<>();

        if (nome == null || nome.isBlank()) {
            errors.add("Nome não pode ser vazio");
        }
        if (sobrenome == null || sobrenome.isBlank()) {
            errors.add("Sobrenome não pode ser nulo");
        }
        if (genero == null || genero.isBlank()) {
            errors.add("Gênero não pode ser nulo");
        }
        if (nacionalidade == null || nacionalidade.isBlank()) {
            errors.add("Nacionalidade não pode ser nula");
        }
        if (telefone == null || telefone.isBlank()) {
            errors.add("Telefone não pode ser nulo");
        }
        if (email == null || email.isBlank()) {
            errors.add("E-mail não pode ser nulo");
        }
        if (especializacao == null || especializacao.isBlank()) {
            errors.add("Especialização não pode ser nula");
        }
        if (atuacao == null || atuacao.isBlank()) {
            errors.add("Atuação não pode ser nula");
        }
        if (cep == null || cep.isBlank()) {
            errors.add("CEP não pode ser vazio");
        }
        if (logradouro == null || logradouro.isBlank()) {
            errors.add("Logradouro não pode ser vazio");
        }
        if (numero == null || numero.isBlank()) {
            errors.add("Número não pode ser vazio");
        }
        if (complemento == null || complemento.isBlank()) {
            errors.add("Complemento não pode ser vazio");
        }
        if (bairro == null || bairro.isBlank()) {
            errors.add("Bairro não pode ser vazio");
        }
        if (localidade == null || localidade.isBlank()) {
            errors.add("Localidade não pode ser vazia");
        }
        if (estado == null || estado.isBlank()) {
            errors.add("Estado não pode ser vazio");
        }
        if (senha == null || senha.isBlank()) {
            errors.add("Senha não pode ser vazia");
        }

        return !errors.isEmpty() ?
                Map.of(false, errors) :
                Map.of(true, List.of());
    }
}
