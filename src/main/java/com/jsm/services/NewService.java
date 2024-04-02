/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsm.services;

import com.jsm.news.entity.Autor;
import com.jsm.news.entity.News;
import com.jsm.news.exceptions.MyExceptions;
import com.jsm.news.repository.autorRepository;
import com.jsm.news.repository.newsRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewService {
    
    @Autowired //inyeccion de dependencias  
    private newsRepository newsrepositorio;
    @Autowired
    private autorRepository autorrepositorio;
    
    @Transactional
    public void crearNoticia(String id, String titulo, String cuerpo, String foto, String idAutor) throws MyExceptions{
       
    
        validar(id, titulo, cuerpo, idAutor);
        Autor autor = autorrepositorio.findById(idAutor).get();
        News news = new News();
        
        news.setId(id);
        news.setTitulo(titulo);
        news.setCuerpo(cuerpo);
        news.setFechapublicacion(new Date());
        
        news.setAutor(autor);
        newsrepositorio.save(news);
    }
    public List<News> listarNoticias(){
        List<News> news = new ArrayList();
        news = newsrepositorio.findAll();
        
        return news;
    }
    @Transactional
    public void modificarNoticia(String id, String titulo, String cuerpo, String foto, String idAutor) throws MyExceptions{
        validar(id, titulo, cuerpo, idAutor);
        Optional<News> respuesta = newsrepositorio.findById(id);
        Optional<Autor> aut = autorrepositorio.findById(idAutor);
        if(respuesta.isPresent()){
            News news = respuesta.get();
            news.setTitulo(titulo);
            news.setCuerpo(cuerpo);
            news.setFoto(foto);
            if(aut.isPresent()){
               Autor autor = aut.get();
               news.setAutor(autor); 
            }
            
           newsrepositorio.save(news); 
        }
        
    }
    private void validar(String id, String titulo, String cuerpo, String idAutor) throws MyExceptions{
         if(id == null){
            throw new MyExceptions("El Id no puede ser nulo");
        }
        if(titulo.isEmpty()|| titulo == null){
            throw new MyExceptions("El Titulo no puede estar vacio o ser nulo");
        }
        if(cuerpo.isEmpty() || cuerpo == null){
            throw new MyExceptions("El cuerpo no puede ser nulo ni estar vacio");
        }
        if(idAutor == null){
            throw new MyExceptions("El Id Autor no puede ser nulo");
        }
    }
}
