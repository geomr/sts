package br.com.pp.repository.postgresql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;
import com.google.common.reflect.TypeToken;


/**
 * Entidade que representa o Usuario no Banco Postgresql.
 * 
 * @author George
 *
 */
@Entity(name="usuario")
@Table
@AccessType(Type.PROPERTY)
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


	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	public Long getId() {
		return this.id;
	}
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	public Long getId() {
//		return super.getId();
//	}
	

	
	@Column(nullable=false)
	@Override
	public Long getPrioridade() {
		return super.getPrioridade();
	}
	
	@Column(nullable=false,length=36)
	@Override
	public String getCodigo() {
		return super.getCodigo();
	}
	
	@Column(nullable=false,length=500)
	@Override
	public String getLogin() {
		return super.getLogin();
	}
	
	@Column(nullable=false,length=500)
	@Override
	public String getNome() {
		return super.getNome();
	}
	
	
}
