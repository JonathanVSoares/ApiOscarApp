package oscarapp.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oscarapp.api.dao.UsuarioRepository;
import oscarapp.api.model.Usuario;

@RequestMapping("/user")
@RestController
public class UsuarioController {
	@Autowired
	private UsuarioRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> cadastrarUsuario(@RequestParam(value = "login") String login,
			@RequestParam(value = "senha") String senha) {
		Usuario usuarioEncontrado = repo.findByLogin(login);
		
		if (usuarioEncontrado != null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		Usuario user = new Usuario();
		user.setLogin(login);
		user.setSenha(senha);
		repo.save(user);
		
		return new ResponseEntity(HttpStatus.OK);
	}
}
