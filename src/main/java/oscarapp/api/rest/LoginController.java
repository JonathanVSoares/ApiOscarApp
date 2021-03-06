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
import oscarapp.domain.Votos;

@RequestMapping("/login")
@RestController
public class LoginController {
	@Autowired
	private UsuarioRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Votos> login(@RequestParam(value = "login") String login, @RequestParam(value = "senha") String senha) {
		Usuario usuarioEncontrado = repo.findByLoginAndSenha(login, senha);
		
		if (usuarioEncontrado == null) {
			return new ResponseEntity<Votos>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<Votos>(usuarioEncontrado.getVotos(), HttpStatus.OK);
	}
}
