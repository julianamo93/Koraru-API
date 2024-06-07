package org.example.entities.dto;

import org.example.entities.Profissional;

import java.util.List;

public record SearchProfissionalDto(String nome, String sobrenome, String genero, String nacionalidade, String telefone,
                                    String email, String especializacao, String atuacao, String cep, String logradouro,
                                    String numero, String complemento, String bairro, String localidade, String estado,
                                    String orderBy, String direction, int limit, int offset, int totalItems,
                                    List<Profissional> profissionais) {}
