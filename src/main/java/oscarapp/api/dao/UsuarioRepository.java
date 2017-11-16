package oscarapp.api.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import oscarapp.api.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{
	Usuario findByLoginAndSenha(String login, String senha);
}
