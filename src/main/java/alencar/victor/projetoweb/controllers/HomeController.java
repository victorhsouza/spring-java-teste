package alencar.victor.projetoweb.controllers;

import alencar.victor.projetoweb.models.Administrador;
import alencar.victor.projetoweb.servico.CookieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


@Controller
public class HomeController {
    @GetMapping("/")
    // Deixa adicionar dados que queremos enviar ao template assinando atributos

    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        return "home/index";
    }
}
