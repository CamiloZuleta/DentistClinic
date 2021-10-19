package com.DH.DentistClinic.service;

import java.util.ArrayList;

public interface Crud<t> {
    public boolean agregar(t data);

    public boolean eliminarById(Integer id);

    public ArrayList<t> buscarTodos();

    public t actualizar(t data) throws Exception;

}
