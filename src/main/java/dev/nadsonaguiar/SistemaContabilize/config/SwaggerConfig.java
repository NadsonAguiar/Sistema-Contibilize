package dev.nadsonaguiar.SistemaContabilize.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema Contabilize API")
                        .version("1.0.0")
                        .description("API para gerenciamento de processos contábeis, tarefas e clientes")
                        .contact(new Contact()
                                .name("Nadson Aguiar")
                                .email("devnadson@gmail.com")
                                .url("https://github.com/NadsonAguiar"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
