package br.com.pp.controller;

import java.util.Optional;

import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.rest.template.AbstractController;

@Controller
public class CargaArquivoController extends AbstractController {


	
    @RequestMapping(value = "/carga-arquivo",method= {RequestMethod.GET})
    public String getCargaArquivo(Model model) {
    	model.addAttribute("arquivo", new Arquivo());
    	return "cargaArquivo";
    }

    
    @RequestMapping(value = "/carga-arquivo",method= {RequestMethod.POST})
    public String postCargaArquivo(@ModelAttribute Arquivo arquivo,Model model) {
    	cargaArquivo(arquivo);	
    	return "cargaArquivo";
    }
    
    private void cargaArquivo(Arquivo arquivo) {
    	String uriUsuarioService = "http://localhost:8000/pp/usuario/carga";
    	
    	RestTemplate restTemplate = getRestTemplate("pp","pp");
    	HttpEntity<Arquivo> request = new HttpEntity<>(arquivo);
    	ResponseEntity<Arquivo> responseEntity = restTemplate.exchange(uriUsuarioService, HttpMethod.POST, request, Arquivo.class);
    	
    	System.out.println("RESPOSTA CARGA ARQUIVO" + responseEntity.getStatusCode());
    }

//    private void cargaArquivoPrioridade(Arquivo arquivo) {
//    	String uriUsuarioService = "http://localhost:8000/pp/usuario/carga";
//    	
//    	RestTemplate restTemplate = getRestTemplate("pp","pp");
//    	HttpEntity<Arquivo> request = new HttpEntity<>(arquivo);
//    	ResponseEntity<Arquivo> responseEntity = restTemplate.exchange(uriUsuarioService, HttpMethod.POST, request, Arquivo.class);
//    	
//    	System.out.println("RESPOSTA CARGA ARQUIVO" + responseEntity.getStatusCode());
//    }
    
}
