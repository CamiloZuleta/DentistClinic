package com.DH.DentistClinic.service;


import com.DH.DentistClinic.persistence.entities.Odontologo;
import com.DH.DentistClinic.persistence.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class OdontologoServiceImpl implements OdontologoService{

     @Autowired
     OdontologoRepository odontologoRepository;

     @Autowired
     ObjectMapper mapper;

    @Override
    public boolean agregar(Odontologo data) {
        if (data != null){
            odontologoRepository.save(data);
        }
        return odontologoRepository.existsById(data.getId());
    }

    @Override
    public boolean eliminarById(Integer id) {
        odontologoRepository.deleteById(id);
        return odontologoRepository.findById(id)==null;
    }

    @Override
    public ArrayList<Odontologo> buscarTodos() {
        ArrayList<Odontologo> odontologoList = new ArrayList<Odontologo>(odontologoRepository.findAll());
        return odontologoList;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) throws Exception {
        if(odontologoRepository.existsById(odontologo.getId())){
            odontologoRepository.save(odontologo);
        }else{
            throw new Exception("Error al actualizar el odontologo no se encontró el Id");
        }

        return odontologo;
    }

    @Override
    public boolean eliminarByMatricula(Integer matricula) {
        ArrayList<Odontologo> odontologoList = this.buscarTodos();
        for(Odontologo odontologo: odontologoList){
            if(odontologo.getMatricula() == matricula){
                odontologoRepository.deleteById(odontologo.getId());
                return odontologoRepository.findById(odontologo.getId()) == null;
            }
        }
        return false;
    }
}
