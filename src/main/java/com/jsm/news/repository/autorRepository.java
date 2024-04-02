/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsm.news.repository;

import com.jsm.news.entity.Autor;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface autorRepository extends JpaRepository<Autor, String> {
     @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    public Autor buscarporNombre (@Param("nombre") String nombre);
    
    @Query("SELECT a FROM Autor a WHERE a.fechapublicacion = :fechadepublicacion")
        public List<Autor> buscarPorAutor(@Param("fechapublicacion") Date fechapublicacion);
}
