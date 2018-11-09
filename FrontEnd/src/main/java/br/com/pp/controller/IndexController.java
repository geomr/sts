package br.com.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador da pagina inicial.
 * 
 * Nao possui implementacao, somente redirecionamento.
 * @author George
 *
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String get() {
    	 return "listaUsuarios";
    }
    
}
