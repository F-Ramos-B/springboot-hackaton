# Instruções

# 1 - Instalar lombok

O lombok é um plugin que adiciona anotações para fazer Getters, Setters, Equals, HashCode, Construtores e outras coisas. Usei ele nesse projeto então precisa instalar ele pro eclipse detectar os Getters and Setters gerados pela anotação @Data.

Para instalar:

Feche o eclipse e execute o arquivo lombok.jar em src/main/resources do projeto. Ele vai pesquisar aonde o eclipse estiver salvo no seu computador. Se ele não achar aponte qual pasta instalou. Quando estiver ok, clique em instalar e ele vai instalar o lombok.

Ao voltar pro eclipse ele deve detectar os Getters and Setters gerados pela anotação @Data (para testar, aperte ctrl espaço na classe Pessoa por exemplo).

# 2 - Configurar conexão com o banco de dados

O banco de dados deverá estar criado no seu PgAdmin, se não estiver eu deixei o script .sql para gerar ele. Se ele já estiver criado e funcionando normalmente conforme as aulas passadas do hackaton, não precisa mexer nele.

Acesse o pacote config > JpaConfig.java e altere o nome do database no fim da url, o username e o password pra aqueles que você usa no PgAdmin.

# 3 - Para rodar o projeto, clique com o botão direito sobre o HackatonSpringApplication e escolha Run As > Java Application. Ele deverá aparecer no console que está subindo o servidor.

O url utilizado no aplicativo é:

http://localhost:8080/hackaton-springboot/pessoas/

# 4 (Opcional) - Arquivo com as requisições pra Insomnia.

Deixei na pasta src/main/resources um arquivo com as requisições já criadas pra poder usar no aplicativo Insomnia (similar ao Postman). Basta importar que ele vai aparecer com os endpoints que fiz já com exemplos.

# 5 (Opcional) - Acessar o Swagger

Você pode acessar o Swagger do projeto quando subir o servidor e acessar o seguinte link:

http://localhost:8080/hackaton-springboot/swagger-ui.html

O Swagger vai ser gerado toda vez que o servidor subir, documentando os endpoints que estão na classe PessoaRest. Se fizer mudanças e reiniciar o servidor imagino que elas apareçam lá também; não precisa configurar, ele deve detectar as mudanças automaticamente.

# Mais sobre o spring:

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

