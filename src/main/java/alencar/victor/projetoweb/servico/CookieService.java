package alencar.victor.projetoweb.servico;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

// Serviço para gravar o cookie
//HttpServletResponse grava o cookie
//HttpServletRequest recupera o cookie o cookie
public class CookieService {
    public static void setCookie(HttpServletResponse response, String key, String valor, int segundos) throws IOException {
        // create a cookie
        Cookie cookie = new Cookie(key, URLEncoder.encode(valor,"UTF-8"));
        cookie.setMaxAge(segundos);
        cookie.setHttpOnly(true);
        //add cookie to response
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String key) throws UnsupportedEncodingException {
        String valor =   Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> key.equals(cookie.getName()))
                        .findAny()
                )
                .map(e->e.getValue())
                .orElse(null);

        valor = URLDecoder.decode(valor,"UTF-8");
        return valor;
    }
}
