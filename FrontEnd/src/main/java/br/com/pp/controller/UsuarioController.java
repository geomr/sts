package br.com.pp.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.rest.template.AbstractController;

/**
 * Controlador da pagina de usuarios.
 * 
 * @author George
 *
 */
@Controller
public class UsuarioController extends AbstractController {

//    private static final String URI_USUARIO = "http://192.168.99.100:8000/pp/usuario";
	private static final String URI_USUARIO = "http://localhost:8000/pp/usuario";
	private static Integer currentPage = 1;
    private static Integer pageSize = 15;
    private static String repo = "mongodb";
    private List<Repositorios> repositorios = Repositorios.getRepositorios();
    
    
    
    /**
     * Retorna a página de lista de usuários.
     * 
     * @param filtroUsuario
     * @param model
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/listaUsuarios",method= {RequestMethod.GET,RequestMethod.POST})
    public String get(@RequestParam("filtroUsuario") Optional<String> filtroUsuario,Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,@RequestParam("repository") Optional<String> repository) {
    	
    
    	String nome = filtroUsuario.isPresent()?filtroUsuario.get():"";
    	page.ifPresent(p -> currentPage = p);
        size.ifPresent(s -> pageSize = s);
        repository.ifPresent(k -> repo = k);
        
        PagedResources<Usuario> pageList = buscarListaUsuarios(nome, currentPage - 1, pageSize,repo);
        
        Integer totalPages = (int)pageList.getMetadata().getTotalPages();
        if (totalPages > 0) {
	        List<Integer> pageNumbers = criarPaginacao(totalPages);
	        model.addAttribute("pageNumbers", pageNumbers);
	        model.addAttribute("pageList", pageList);
	        model.addAttribute("listaUsuario",pageList.getContent());
	    	model.addAttribute("filtro", nome);
	    	
        }
        
        
        return "listaUsuarios";
    }
    
    private List<Integer> criarPaginacao(Integer totalPages) {
    	Integer primeira = 0;
    	Integer ultima = 0;
    	Integer maximoPaginas = 5;
    	
    	if(totalPages >= maximoPaginas) {
    		primeira = currentPage - 2 >0?currentPage - 2:1;
    		ultima = currentPage + 2 <= totalPages?currentPage + 2:totalPages;
    		if(ultima < maximoPaginas) {
    			ultima = maximoPaginas;
    		}
    	}else {
    		primeira = 1;
    		ultima = totalPages.intValue();
    	}
    	
    	return IntStream.rangeClosed(primeira, ultima).boxed().collect(Collectors.toList());

    }
    
    private PagedResources<Usuario> buscarListaUsuarios(String filtroNome,Integer page,Integer size,String repository) {
    	String uriUsuarioService = URI_USUARIO;
    	
    	RestTemplate restTemplate = getRestTemplate("pp","pp");
    	
		ResponseEntity<PagedResources<Usuario>> responseEntity =
				restTemplate.exchange(uriUsuarioService+"?filtroNome="+filtroNome+"&page="+page+"&size="+size+"&repository="+repository
				, HttpMethod.GET, null
				, new ParameterizedTypeReference<PagedResources<Usuario>>(){}, Collections.emptyMap());

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			 return responseEntity.getBody();
		}
		 return null;
    }

	public List<Repositorios> getRepositorios() {
		return repositorios;
	}

	public void setRepositorios(List<Repositorios> repositorios) {
		this.repositorios = repositorios;
	}

	
}
