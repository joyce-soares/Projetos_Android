package com.joyce.listadetarefas.helper;

import com.joyce.listadetarefas.model.Tarefa;

import java.util.List;

public interface InterfaceTarefaDao {

    boolean salvar(Tarefa tarefa);

    boolean atualizar(Tarefa tarefa);

    boolean deletar(Tarefa tarefa);

    List<Tarefa> listar();
}
