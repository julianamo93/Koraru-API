package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.example.entities.Profissional;
import org.example.entities.dto.SearchProfissionalDto;
import org.example.repositories.ProfissionalRepository;
import org.example.services.ProfissionalService;

import java.util.Optional;

@Path("profissional")
public class ProfissionalResource {

    private final ProfissionalService profissionalService;
    private final ProfissionalRepository profissionalRepository;

    public ProfissionalResource() {
        this.profissionalService = new ProfissionalService();
        this.profissionalRepository = new ProfissionalRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        // Parâmetros padrão para paginação e ordenação
        int limit = 1000; // valor ajustador conforme necessário - configuramos para trazer o máximo de cadastros
        int offset = 0;
        String orderBy = "id"; // Campo para ordenação padrão
        String direction = "asc"; // Direção padrão para ordenação

        SearchProfissionalDto result = profissionalService.getAll(
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, orderBy, direction, limit, offset);

        return Response.ok(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfissional(Profissional profissional) {
        Profissional novoProfissional = profissionalService.addProfissional(profissional);
        return Response.status(Response.Status.CREATED).entity(novoProfissional).build();
    }

    @GET
    @Path("pesquisa/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByPesquisa(@PathParam("id") int idPesquisa) {
        return Response.ok(profissionalRepository.getAllByPesquisa(idPesquisa)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Optional<Profissional> profissional = profissionalRepository.get(id);
        return profissional.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Profissional profissional) {
        try {
            profissionalService.create(profissional);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Profissional profissional) {
        try {
            profissionalService.update(id, profissional);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            profissionalService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
