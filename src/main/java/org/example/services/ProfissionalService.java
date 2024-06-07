package org.example.services;

import org.example.entities.Profissional;
import org.example.entities.dto.SearchProfissionalDto;
import org.example.repositories.ProfissionalRepository;

import java.util.List;

public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    public ProfissionalService(){
        profissionalRepository = new ProfissionalRepository();
    }

    public Profissional addProfissional(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    public SearchProfissionalDto getAll(String nome, String sobrenome, String genero, String nacionalidade,
                                        String telefone, String email, String especializacao, String atuacao,
                                        String cep, String logradouro, String numero, String complemento, String bairro,
                                        String localidade, String estado, String orderBy, String direction, int limit, int offset) {

        List<Profissional> profissionais = profissionalRepository.getAll(nome, sobrenome, genero, nacionalidade, telefone,
                email, especializacao, atuacao, cep, logradouro, numero, complemento, bairro, localidade, estado, limit, offset);

        int totalItems = profissionalRepository.count(nome, sobrenome, genero, nacionalidade, telefone, email, especializacao, atuacao);

        return new SearchProfissionalDto(nome, sobrenome, genero, nacionalidade, telefone, email, especializacao, atuacao,
                cep, logradouro, numero, complemento, bairro, localidade, estado, orderBy, direction, limit, offset, totalItems, profissionais);
    }

    public void create(Profissional profissional){
        var validation = profissional.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            profissionalRepository.create(profissional);
    }

    public void update(int id, Profissional profissional){
        var validation = profissional.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            profissionalRepository.update(id, profissional);
    }

    public void delete(int id) {
        profissionalRepository.delete(id);
    }

}
