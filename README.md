# API casa do código
  Neste desafio preciso desenvolver uma API para suportar parte do funcionamento da casa do código.
  A ideia é trabalhar o design do código, criando código que seja suficiente para a funcionalidade.

## Começando
Para executar o projeto, será necessário instalar os seguintes programas:

- [Java 11+](https://openjdk.java.net/projects/jdk/11/)
- [Maven 3+](https://maven.apache.org/download.cgi)
- [Postman](https://www.postman.com/downloads/) ou [Imsominia](https://insomnia.rest/download)
- MySQL(8.0.23)

## Observação
* Projeto usa ecossistema Spring
* MySQL como banco de dados

## Desenvolvimento

* Para iniciar o desenvolvimento Tenha uma IDE(eclipse com STS) e clone o projeto do GitHub num diretório:

```shell
	cd "<seu diretório(workspace)>"
	git clone https://github.com/fmchagas/orange-talents-04-template-casa-do-codigo.git
```

* Rode a aplicação

```shell
	cd "<diretório raiz da aplicação>"
	./mvnw spring-boot:run
	ou ./mvnw spring-boot:start
```

* Pare a aplicação se usar start

```shell
    ctrl + c
    ./mvnw spring-boot:stop
```

faça uma requisição POST para:
http://localhost:8080/api/v1/autores

```shell
{
    "nome":"Fernando",
    "email":"fer@provedor.com",
    "descricao":"Virado no Jiraya"
}
```

* Um desafio extra para eu é adicionar documentação da api com springfox -> localhost:8080/swagger-ui.html
- [Documentação](http://localhost:8080/swagger-ui.html) 