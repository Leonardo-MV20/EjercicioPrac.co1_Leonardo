package Leonardo_Moscoa.Ejercicio_Prac.controller;

import Leonardo_Moscoa.Ejercicio_Prac.domain.Servicio;
import Leonardo_Moscoa.Ejercicio_Prac.service.CategoriaService;
import Leonardo_Moscoa.Ejercicio_Prac.service.ServicioService;
import java.util.Locale;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    private final ServicioService servicioService;
    private final CategoriaService categoriaService;
    private final MessageSource messageSource;

    public ServicioController(ServicioService servicioService, CategoriaService categoriaService,MessageSource messageSource) {
        this.servicioService = servicioService;
        this.categoriaService = categoriaService;
        this.messageSource = messageSource;
    }

    @GetMapping("")
    public String servicioRoot() {
        return "redirect:/servicio/listado";
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var servicios = servicioService.getServicios();
        var categorias = categoriaService.getCategorias();

        model.addAttribute("servicios", servicios);
        model.addAttribute("totalServicios", servicios.size());
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("categorias", categorias);

        return "/servicio/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Servicio servicio, RedirectAttributes redirectAttributes) {
        servicioService.save(servicio);

        redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/servicio/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";

        try {
            servicioService.delete(id);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "servicio.error01";
        } catch (IllegalStateException e) {
            titulo = "error";
            detalle = "servicio.error02";
        } catch (Exception e) {
            titulo = "error";
            detalle = "servicio.error03";
        }

        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault())
        );

        return "redirect:/servicio/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Integer id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Optional<Servicio> servicioOpt = servicioService.getServicio(id);

        if (servicioOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("servicio.error01", null, Locale.getDefault()));
            
            return "redirect:/servicio/listado";
        }
        model.addAttribute("servicio", servicioOpt.get());
        model.addAttribute("categorias", categoriaService.getCategorias());

        return "/servicio/modifica";
    }
}
