package oscarapp.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import oscarapp.domain.Votos;

@Document(collection = "usuariosOscar")
@Data
public class Usuario {
	private String login;
	private String senha;
	private Votos votos;
}
