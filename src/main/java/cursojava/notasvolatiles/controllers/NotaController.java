package cursojava.notasvolatiles.controllers;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cursojava.notasvolatiles.models.Nota;
import cursojava.notasvolatiles.repositories.NotaRepository;

@Controller
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

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
        notaRepository.save(nota);

        model.addAttribute("mensaje", "Nota creada");
        model.addAttribute("url", "http://localhost:8080/nota/" + randomCode);
        return "aviso";
    }

    @GetMapping("/nota/{clave}")
    public String verPreambuloNota(@PathVariable String clave, Model model) {
        // Lógica para ver el preámbulo de una nota
        Nota nota = notaRepository.findById(clave).orElse(null);
        if (nota != null) {
            model.addAttribute("nota", nota);
            return "preambulo";
        } else {
            return "notfound";
        }
    }

    @PostMapping("/nota/{clave}")
    public String verNotaYEliminar(@PathVariable String clave, Model model) {
        // Lógica para ver y eliminar una nota
        Nota nota = notaRepository.findById(clave).orElse(null);
        if (nota != null) {
            // Cargamos la nota en la plantilla de la vista
            model.addAttribute("nota", nota);
            // Eliminamos la nota de la base de datos
            notaRepository.deleteById(clave);
            return "nota";
        } else {
            // Si no se encontró la nota, dirigimos a una página de error
            return "notfound";
        }
    }
}