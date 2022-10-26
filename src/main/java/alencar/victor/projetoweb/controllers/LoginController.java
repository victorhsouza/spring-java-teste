package alencar.victor.projetoweb.controllers;

import alencar.victor.projetoweb.models.Administrador;
import alencar.victor.projetoweb.repositorios.AdministradoresRepo;
import alencar.victor.projetoweb.servico.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class LoginController {
    @Autowired
    private AdministradoresRepo repo;

    @GetMapping("/login")
    // Deixa adicionar dados que queremos enviar ao template assinando atributos
    public String index() {
        return "home/login/index";
    }

    @PostMapping("/logar")
    public String logar(Model model, Administrador adminParam, String lembrar, HttpServletResponse response) throws IOException {
        Administrador adm = repo.login(adminParam.getEmail(), adminParam.getSenha());
        int tempoLogado = 0;
        if (adm != null) {
            tempoLogado = 60 * 60;
            if (lembrar != null) {
                tempoLogado = (60 * 60 * 24 * 365);
            }
                CookieService.setCookie(response, "usuarioId", String.valueOf(adm.getId()), tempoLogado);
                CookieService.setCookie(response, "nomeUsuario", String.valueOf(adm.getNome()), tempoLogado);
                return "redirect:/";
        }
        model.addAttribute("erro", "Usuário ou senha invalidos!");
        return "home/login/index";
    }

    @GetMapping("/sair")
    public String logoff(HttpServletResponse response) throws IOException {
            CookieService.setCookie(response, "usuarioId", "", 0);
            return "redirect:/login";
        }

}
