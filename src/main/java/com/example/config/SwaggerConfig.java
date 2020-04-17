package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig {

	  @Bean
	  public Docket productApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	    		.groupName("API Hackaton-Springboot-Chiquito")
	    		
	    		/**
				 * Correção pro caminho não vir repetido, antes vinha
				 * http://localhost:8080/hackaton-springboot/hackaton-springboot/
				 * (exemplo http://localhost:8080/hackaton-springboot/hackaton-springboot/pessoas)
				 * pra todas as requisições; colocando esse PathProvider tem como interceptar o endereço
				 * que o swagger usa e então cortei pra ter apenas /pessoas ou seja qual for
				 * o nome do endpoint que ele vai ler. Assim ele corretamente usa o
				 * http://localhost:8080/hackaton-springboot/
				 * como endereço base da aplicação.
				 **/
	    		
	    		.host("localhost:8080")
	    		.pathProvider(new PathProvider() {
	    			
					@Override
					public String getOperationPath(String operationPath) {
						return operationPath.substring(20);
					}
					@Override
					public String getResourceListingPath(String groupName, String apiDeclaration) {
						return groupName;
					}
	            })
	    		
	    		/**
				 * Fim da correção usando PathProvider
				 **/
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("com.example.api"))
	            .paths(PathSelectors.any())
	            .build()
	            .apiInfo(apiInfo());
	  }
	  private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("Hackaton-SpringBoot-Chiquito Swagger")
	            .description("Criando aplicação do Hackaton da Stefanini no SpringBoot")
	            .version("1.0.0")
	            .license("Apache License Version 2.0")
	            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	            .contact(new Contact("Francisco Ramos", "https://www.linkedin.com/in/francisco-ramos-bb515a181/", "frbisneto@stefanini.com"))
	            .build();
	  }

}
