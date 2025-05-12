package one.digitalinnovation.gof.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Clientes")
                        .description("API REST para gerenciamento de clientes utilizando padrões de projeto")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Digital Innovation One")
                                .url("https://digitalinnovation.one")
                                .email("contato@digitalinnovation.one"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}