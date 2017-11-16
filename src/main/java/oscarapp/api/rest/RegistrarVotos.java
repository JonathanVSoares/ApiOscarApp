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

@RequestMapping("/votar")
@RestController
public class RegistrarVotos {
	@Autowired
	private UsuarioRepository repo;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Votos> login(@RequestBody Usuario usuario) {
		Usuario usuarioEncontrado = repo.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());

		if (usuarioEncontrado == null) {
			return new ResponseEntity<Votos>(HttpStatus.NOT_FOUND);
		} else {
			Votos votos = usuarioEncontrado.getVotos();
			if (votos != null && votos.getDiretor() != null && votos.getFilme() != null) {
				return new ResponseEntity<Votos>(HttpStatus.BAD_REQUEST);
			}
		}

		Votos votos = usuarioEncontrado.getVotos();

		if (votos == null) {
			votos = new Votos();
		}

		if (votos.getFilme() == null) {
			votos.setFilme(usuario.getVotos().getFilme());
		}
		
		if (votos.getDiretor() == null) {
			votos.setDiretor(usuario.getVotos().getDiretor());
		}
		
		repo.save(usuarioEncontrado);

		return new ResponseEntity<Votos>(usuario.getVotos(), HttpStatus.OK);
	}
}
