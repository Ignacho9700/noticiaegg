/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.EggNewss.controladores;

import com.ignacio.EggNewss.entidades.Noticia;
import com.ignacio.EggNewss.excepciones.MiException;
import com.ignacio.EggNewss.servicios.NoticiaServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ignac
 */
@Controller 
@RequestMapping("/noticia")
public class NoticiaControlador {
    @Autowired
    private NoticiaServicio noticiaServicio;
    @GetMapping("/registrar") 
    public String registrar(){
        return "noticia_form.html";
    }
    @PostMapping("/registro")
    public String registro(@RequestParam String  titulo, @RequestParam String cuerpo, @RequestParam String foto, ModelMap nl){
        System.out.println("titulo"+titulo);
        try {
            noticiaServicio.crearNoticia(titulo, cuerpo, foto);
            nl.put("exito", "la noticia fue cargada correctamente!" );
        } catch (MiException ex) {
           nl.put("error", ex.getMessage());
            return "noticia_form.html";
        }
        return "index.html";
    }
    @GetMapping("/lista")
    public String listar(ModelMap nl){
    List<Noticia>noticias=noticiaServicio.listarNoticia();
    nl.addAttribute("noticias", noticias);
    System.out.println(noticiaServicio.getOne(noticias.get(0).getTitulo()).toString());
    return "noticias_list.html";
    }
    @GetMapping("/modificar/{titulo}")
    public String  modificar(@PathVariable String titulo, ModelMap nl){
        
        nl.put("noticia", noticiaServicio.getOne(titulo));
        
        return "noticia_modificar.html";
    }
    @PostMapping("/modificar/{titulo}")
    public String modificar(@ModelAttribute Noticia noticia, ModelMap nl){
        System.out.println(noticia.getTitulo());
        try {
            noticiaServicio.modificarNoticia(noticia.getTitulo(), noticia.getCuerpo(), noticia.getFoto());
            return "redirect:../lista";
        } catch (MiException ex) {
            nl.put("error", ex.getMessage());
            return "noticia_modificar.html";
           
        }
    }
    
}

