package lt.viko.eif.asilaikis.bookstore_REST.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Arnas Šilaikis",
                        email = "arnas.silaikis@ad.viko.lt",
                        url= "gmail.com"),
                description = "REST API for BookStore.",
                title = "BookStore REST",
                version = "1.0.0",
                license = @License(
                        name = "Free To Use",
                        url = "ad.viko.lt")

        ),
        servers = {
                @Server(
                        url = "http://localhost:8082",
                        description = "Local"
                )
        }
)
public class config {

}
