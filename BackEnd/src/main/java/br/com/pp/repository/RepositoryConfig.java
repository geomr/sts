package br.com.pp.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import br.com.pp.repository.postgresql.Usuario;

/**
 * Configurações gerais do repositório.
 * 
 * @author George
 *
 */
@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(br.com.pp.repository.postgresql.Usuario.class);
        config.exposeIdsFor(br.com.pp.repository.mongodb.Usuario.class);
    }
}