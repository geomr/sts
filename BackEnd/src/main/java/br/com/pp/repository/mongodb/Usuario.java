package br.com.pp.repository.mongodb;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.reflect.TypeToken;



/**
 * Entidade que representa o Usuario no MongoDB.
 * 
 * @author George
 *
 */
@AccessType(Type.PROPERTY)
@Document(collection = "usuario")
public class Usuario extends br.com.pp.repository.Usuario{

	private static final long serialVersionUID = 1L;
	
	
	
	public Usuario() {
		super();
	}

	public static Usuario toUsuario(br.com.pp.repository.Usuario usuario){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario,Usuario.class);
	}

	public static List<Usuario> toUsuario(List<br.com.pp.repository.Usuario> usuarios){
		ModelMapper modelMapper = new ModelMapper();
		java.lang.reflect.Type targetListType = new TypeToken<List<Usuario>>(){}.getType();
		return modelMapper.map(usuarios,targetListType );
	}

	public Usuario(Long prioridade,String codigo,String login,String nome) {
		super();
		setPrioridade(prioridade);
		setCodigo(codigo);
		setLogin(login);
		setNome(nome);
	}

	private String id;
	public void setId(String id) {
		this.id = id;
	}
	
	@Id
	public String getId() {
		return this.id;
	}
	
	
	@Override
	public Long getPrioridade() {
		return super.getPrioridade();
	}
	
	@Override
	public String getCodigo() {
		return super.getCodigo();
	}
	
	@Override
	public String getLogin() {
		return super.getLogin();
	}
	
	@Override
	public String getNome() {
		return super.getNome();
	}
	
	
}
