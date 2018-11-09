package br.com.pp.security;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Serviço para simulação dos usuários de sistema.
 * 
 * @author George
 *
 */
@Component
public class UserRoleService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if( !getUsuariosDoBanco().containsKey(username)) {
			 throw new UsernameNotFoundException("Usuário " + username + " não foi encontrado");
		}
		return getUsuariosDoBanco().get(username);
	}
	
	/**
	 * Simula lista de usuários.
	 * @return
	 */
	public static Map<String, UserRole> getUsuariosDoBanco(){
		Map<String, UserRole> map = new TreeMap<String, UserRole>();
		map.put("pp" ,new UserRole("pp","$2a$10$vqBp/qpmD4SyUUyk7wtbUOXXFoSxgiuw..psgmr/ljIB7VXvzzfly",new Role("ROLE_ADMIN")));
		return map; 
	}

}
