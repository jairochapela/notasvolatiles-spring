package cursojava.notasvolatiles.controllers;

import java.time.LocalDateTime;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.SessionFactoryBuilder;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cursojava.notasvolatiles.models.Nota;

@Controller
public class NotaController {
    

    @GetMapping("/")
    public String mostrarFormularioCrearNota() {
        // Lógica para mostrar el formulario de creación de nota
        return "escribirnota";
    }

    @PostMapping("/")
    public String crearNuevaNota(Model model, @RequestParam("titulo") String titulo, @RequestParam("mensaje") String mensaje) {
        // Lógica para crear una nueva nota
        Nota nota = new Nota();
        nota.setTitulo(titulo);
        nota.setMensaje(mensaje);
        nota.setFechaCreacion(LocalDateTime.now());
        String randomCode = "" + new Random().nextLong(1000000000L, 9999999999L);
        nota.setClave(randomCode);

        // Persistencia del objeto
        


        // EntityTransaction transaction = entityManager.getTransaction();
        // transaction.begin();
        // entityManager.persist(nota);
        // transaction.commit();



        model.addAttribute("mensaje", "Nota creada");
        model.addAttribute("url", "http://localhost:8080/nota/123456788990");
        return "aviso";
    }

    @GetMapping("/nota/{clave}")
    public String verPreambuloNota(@PathVariable String clave) {
        // Lógica para ver el preámbulo de una nota
        return "preambulo";
    }

    @PostMapping("/nota/{clave}")
    public String verNotaYEliminar(@PathVariable String clave) {
        // Lógica para ver y eliminar una nota
        return "nota";
    }
}