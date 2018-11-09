package br.com.pp.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pp.repository.Usuario;
import br.com.pp.service.UsuarioService;

/**
 * Classe que disponibiliza serviços REST para acesso aos recursos do Usuario. 
 * 
 * @author George
 *
 */
@RestController
@RequestMapping(value="/pp/usuario",produces = MediaTypes.HAL_JSON_VALUE )
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * Realiza a busca de Usuarios.
	 * @param filtroNome
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="",params={"filtroNome","page","size"})
	public PagedResources<Usuario> get(@RequestParam("filtroNome") String filtroNome, @RequestParam("page") Integer page,@RequestParam("size") Integer size,@RequestParam(name="repository") String repository) {
		if(size != null && size > 15) size = 15;
		Page<Usuario> usuarios = usuarioService.findByNomeContainsIgnoreCaseOrLoginContainsIgnoreCase(filtroNome,page,size,repository);

		PageMetadata pageMetadata = new PageMetadata(usuarios.getSize(),usuarios.getNumber(),usuarios.getTotalElements(),usuarios.getTotalPages());
		Link withSelfRel = linkTo(methodOn(UsuarioController.class).get(filtroNome,page,size,repository)).withSelfRel();

		List<Usuario> list = usuarios.getContent();
		PagedResources<Usuario> resources = new PagedResources<Usuario>( list,pageMetadata ,withSelfRel );
		return resources;
	}
	
	
 	@PostMapping(value = "/carga")
    public String carga(@RequestBody Arquivo arquivo) {
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ",";
        try {
            br = new BufferedReader(new FileReader(arquivo.getCaminhoArquivo()));
            int count = 0;
            List<Usuario> list = new ArrayList<Usuario>();
            
            boolean ultimoRegistro = false;
            int inicio=0;
            while (!ultimoRegistro) {
            	ultimoRegistro = !((linha = br.readLine()) != null);
            	
            	 if(arquivo.getPrioridade() == null) {
            		 if(inicio < 1000000) {
            			 inicio++;
            			 continue;
            		 }
            	 }
            	
            	boolean limiteRegistros = (count == arquivo.getQuantidadeRegistros());
            	boolean bulk = (list.size() == 10000);
            	boolean gravarRegistros = (bulk || limiteRegistros || ultimoRegistro);
            	if(arquivo.getPrioridade() == null) {
					if( gravarRegistros ) {
						 usuarioService.saveAll(list,arquivo.getRepositorioDestino());
						 list = new ArrayList<Usuario>();
					}
            	}else {
					if( gravarRegistros ) {
						 usuarioService.updateAll(list,arquivo.getRepositorioDestino());
						 list = new ArrayList<Usuario>();
					}
            	}
            	//encerra o processamento caso já esteja no último registro ou o limite seja atingido.
            	if(ultimoRegistro || limiteRegistros) {
            		return "";
            	}
            	count++;
                String[] array = linha.split(csvDivisor);

                if(arquivo.getPrioridade() == null) {
                	Usuario usuario = new Usuario();
	            	usuario.setCodigo(array[0]);
	            	usuario.setNome(array[1]);
	            	usuario.setLogin(array[2]);
	            	usuario.setPrioridade( 3l );
					list.add(usuario);
                }else {
                	Usuario usuario = new Usuario();
	            	usuario.setCodigo(array[0]);
	            	usuario.setPrioridade( (long) arquivo.getPrioridade() );
					list.add(usuario);
                }
                
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    	 return "";
    }
	
}
