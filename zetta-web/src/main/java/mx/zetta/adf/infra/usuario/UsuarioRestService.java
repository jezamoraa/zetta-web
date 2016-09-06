package mx.zetta.adf.infra.usuario;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioRestService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/usuario.json", produces = "application/json", method = RequestMethod.GET)
    public Saludo greetingJSON(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Saludo(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value = "/usuario.xml", produces = "application/xml", method = RequestMethod.GET)
    public Saludo greetingXML(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Saludo(counter.incrementAndGet(), String.format(template, name));
    }
}
