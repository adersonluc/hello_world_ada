package com.ada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlunoRest {

    private final Map<Integer, AlunoDto> alunos = new HashMap<>();

    @POST
    @Path("/alunos")
    public Response criaAluno(AlunoDto aluno) {
        if(Objects.isNull(aluno)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            alunos.put(aluno.getId(), aluno);
            return Response.status(Response.Status.CREATED).build();
        }
    }

    @GET
    @Path("/alunos/{id}")
    public Response buscaAlunoPorId(@PathParam("id") Integer id){
        AlunoDto aluno = this.alunos.get(id);
        if(Objects.isNull(aluno)){
            return Response.status(Response.Status.NOT_FOUND).build();    
        } else {
            return Response.ok(aluno).build();
        }        
    }

    @GET
    @Path("/listarAlunos")
    public Response buscarAlunos(){
        return Response.ok(alunos.values()).build();
    }

    @PUT
    @Path("/alunos/{id}")
    public Response alterarAluno(@PathParam("id") Integer id, String nome){
        AlunoDto aluno = alunos.get(id);
        if(Objects.isNull(aluno)){
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            aluno.setNome(nome);
            return Response.ok(aluno).build();
        }
        
    }

    @DELETE
    @Path("/alunos/{id}")
    public Response deletarAluno(@PathParam("id") Integer id){
        AlunoDto aluno = alunos.get(id);
        if(Objects.isNull(aluno)){
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            alunos.remove(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Path("/alunos")
    public Response filtrarAlunos(@QueryParam("prefixo") String prefixo){
        List<AlunoDto> alunosFiltrados = new ArrayList<>();
        alunosFiltrados = alunos.values().stream()
                                .filter(valor -> valor.getNome().startsWith(prefixo))
                                .collect(Collectors.toList());
        alunosFiltrados.forEach(x -> System.out.println(x.getNome()));
        return Response.ok(alunosFiltrados).build();
    }

}