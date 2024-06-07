package org.example.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pesquisa {
    private int id;
    private String titulo;
    private Date dataInicial;
    private Date dataFinal;
    private String resumo;
    private String resultados;
    private List<Profissional> profissionais;

    public Pesquisa() {
        profissionais = new ArrayList<>();
    }

    public Pesquisa(int id, String titulo, Date dataInicial, Date dataFinal, String resumo, String resultados) {
        this.id = id;
        this.titulo = titulo;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.resumo = resumo;
        this.resultados = resultados;
        profissionais = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public List<Profissional> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }

    @Override
    public String toString() {
        return "Pesquisa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", resumo='" + resumo + '\'' +
                ", resultados='" + resultados + '\'' +
                ", profissionais=" + profissionais +
                '}';
    }
}
