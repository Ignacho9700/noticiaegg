/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.EggNewss.controladores;

import com.ignacio.EggNewss.excepciones.MiException;
import com.ignacio.EggNewss.servicios.NoticiaServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
        } catch (MiException ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "noticia_form.html";
        }
        return "noticia_form.html";
    }
}
