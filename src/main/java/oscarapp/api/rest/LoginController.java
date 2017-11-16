package oscarapp.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oscarapp.api.dao.UsuarioRepository;
import oscarapp.api.model.Usuario;
import oscarapp.domain.Votos;

@RequestMapping("/inview")
@RestController
public class LoginController {
	@Autowired
	private UsuarioRepository repo;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Votos> login(@RequestBody Usuario usuario) {
		Usuario usuarioEncontrado = repo.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
		
		if (usuarioEncontrado == null) {
			return new ResponseEntity<Votos>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<Votos>(usuario.getVotos(), HttpStatus.OK);
	}
}
