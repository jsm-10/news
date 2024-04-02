package com.jsm.news.repository;

import com.jsm.news.entity.News;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface newsRepository extends JpaRepository<News, String> {
    
    @Query("SELECT n FROM News n WHERE n.titulo = :titulo")
    public News buscarporTitulo (@Param("titulo") String titulo);
    
    @Query("SELECT n FROM News n WHERE n.autor.nombre = :nombre")
        public List<News> buscarPorAutor(@Param("nombre") String nombre);
    
}
