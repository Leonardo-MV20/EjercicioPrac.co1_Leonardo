package Leonardo_Moscoa.Ejercicio_Prac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Inicio_Contacto_Controller {
    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }
    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}
