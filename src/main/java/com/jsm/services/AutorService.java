/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsm.services;

import com.jsm.news.entity.Autor;
import com.jsm.news.exceptions.MyExceptions;
import com.jsm.news.repository.autorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorService {
    
    @Autowired
    autorRepository autorrepositorio;
    
    @Transactional
    public void crearAutor(String nombre) throws MyExceptions{
        
        if(nombre.isEmpty()|| nombre == null){
            throw new MyExceptions("El Titulo no puede estar vacio o ser nulo");
        }
        Autor autor= new Autor();
        
        autor.setNombre(nombre);
        autorrepositorio.save(autor);
    }
    
     public List<Autor> listarAutores(){
        List<Autor> autores = new ArrayList();
        autores = autorrepositorio.findAll();
        
        return autores;
    }
     public void modificarAutor(String id, String nombre){
          
         Optional <Autor> respuesta = autorrepositorio.findById(id);
         if(respuesta.isPresent()){
             Autor autor = respuesta.get();
             
             autor.setNombre(nombre);
             autorrepositorio.save(autor);
         }
     }
}
