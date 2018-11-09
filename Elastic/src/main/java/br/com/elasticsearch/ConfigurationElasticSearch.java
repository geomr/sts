package br.com.elasticsearch;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "br.com.elasticsearch.repository")
@ComponentScan(basePackages = { "br.com.elasticsearch" })
public class ConfigurationElasticSearch {
	
	@Value("${elasticsearch.home:C:\\aplicativos\\elasticsearch\\elasticsearch-5.2.0}")
    private String elasticsearchHome;
	
    @Value("${elasticsearch.cluster.name:elasticsearch}")
    private String clusterName;
    
    @Bean
    public Client client() {
    	try {
	        Settings elasticsearchSettings = Settings.builder()
	          .put("client.transport.sniff", true)
	          .put("path.home", elasticsearchHome).build();
//	          .put("cluster.name", clusterName).build();
	        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
	        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
	        return client;
    	}catch (Exception e) {
    		e.printStackTrace();
    		return null;
		}
    }
 
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
    
}
