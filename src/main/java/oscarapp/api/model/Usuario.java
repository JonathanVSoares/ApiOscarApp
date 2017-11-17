package oscarapp.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import oscarapp.domain.Votos;

@Document(collection = "usuariosOscar")
@Data
public class Usuario {
	@Id
	private String id;
	private String login;
	private String senha;
	private Votos votos;
}
