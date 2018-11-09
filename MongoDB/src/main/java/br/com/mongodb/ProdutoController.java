package br.com.mongodb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/produto" )
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@PostMapping(value="")
	public ProdutoType post(@RequestBody ProdutoType produtoType ) {
		produtoRepository.save(produtoType);
		return produtoType;
	}

	@DeleteMapping(value="")
	public void deleteAll() {
		produtoRepository.deleteAll();
	}

	@DeleteMapping(value="/{id}") 
	public  ProdutoType delete(@PathVariable(name="id") String id) {
		produtoRepository.deleteById(id);
		return null;
	}

	@GetMapping(value="",params="termo")
	public List<ProdutoType> getParam(@RequestParam("termo") String termo) { 
		List<ProdutoType> list = new ArrayList<ProdutoType>();
//		list = produtoRepository.findByNome(termo);
		return list;
	}

	@GetMapping(value="/{id}")
	public Optional<ProdutoType> get(@PathVariable(name="id") String id) {
		return produtoRepository.findById(id);
	}
	 
	
	 	@PostMapping(value = "/carga",params={"caminhoArquivo"})
	    public String carga(@RequestParam("caminhoArquivo") String caminhoArquivo,@RequestParam(name="qtd") Long qtd) {
	        BufferedReader br = null;
	        String linha = "";
	        String csvDivisor = ",";
	        try {
	            br = new BufferedReader(new FileReader(caminhoArquivo));
	            int count = 0;
	            List<ProdutoType> list = new ArrayList<ProdutoType>();
	            
	            while ((linha = br.readLine()) != null) {
	            	boolean limiteRegistros = (count == qtd);
	            	count++;
	                String[] array = linha.split(csvDivisor);
	                	String codigo = array[0];
	                	
		   	       		 ProdutoType produtoType = new ProdutoType();
		   	       		 produtoType.setCodigo(codigo);
		   	       		 produtoType.setNome(array[1]);
		   	       		 list.add(produtoType);
		   	       		 if(list.size() == 10000 || limiteRegistros ) {
		   	       			 produtoRepository.saveAll(list);
		   	       			 list = new ArrayList<ProdutoType>();
		   	       			 System.out.println("Salvo: " + count);
		   	       		 }
	                if(limiteRegistros) {
	                	return"";
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
