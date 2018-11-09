package br.com.pp.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Grupo de seguranca.
 * 
 * @author George
 *
 */
public class Role implements GrantedAuthority {

		private static final long serialVersionUID = 1L;

	    private String roleName;

	    public Role() {
		}

	    public Role(String nome){
	    	this.roleName = nome;
	    }
	    
	    public String getRoleName() {
	        return roleName;
	    }

	    public void setRoleName(String nome) {
	        this.roleName = nome;
	    }

	    @Override
	    public String getAuthority() {
	        return this.roleName;
	    }
	    
	   
}
