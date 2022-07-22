/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.EggNewss.servicios;

import com.ignacio.EggNewss.entidades.Noticia;
import com.ignacio.EggNewss.excepciones.MiException;
import com.ignacio.EggNewss.repositorios.NoticiaRepositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ignac
 */
@Service
public class NoticiaServicio {
    @Autowired
    private NoticiaRepositorio noticiarepositorio;
    @Transactional
    public void crearNoticia(String titulo, String cuerpo, String foto)throws MiException{ 
        validar(titulo, cuerpo, foto);
        Noticia noticia=new Noticia();
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setFoto(foto);
        noticia.setAlta(new Date());
        
        noticiarepositorio.save(noticia);
        
    } 
    public List<Noticia> listarNoticia(){
    List <Noticia>noticias=new ArrayList();
    noticias=noticiarepositorio.findAll();  
    return noticias;
}
    @Transactional
    public void modificarNoticia(String titulo, String cuerpo, String foto)throws MiException{
        validar(titulo, cuerpo, foto);
        Optional<Noticia> respuesta=noticiarepositorio.findById(titulo);
        if(respuesta.isPresent()){
            Noticia noticia=respuesta.get();
           // noticia.setTitulo(titulo);
            noticiarepositorio.save(noticia);
        }
    }
    private void validar(String titulo, String cuerpo, String foto)throws MiException{
         if(titulo.isEmpty() || titulo==null){
            throw new MiException("el titulo no puede esta vacio"); 
        }
        if(cuerpo.isEmpty() || cuerpo==null){
            throw new MiException("el cuerpo no puede esta vacio"); 
        }
        if(foto.isEmpty() || foto==null){
            throw new MiException("el url de la foto no puede estar vacia"); 
        }
    }
}