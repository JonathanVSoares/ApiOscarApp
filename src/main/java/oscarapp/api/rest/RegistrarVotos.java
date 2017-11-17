package oscarapp.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oscarapp.api.dao.UsuarioRepository;
import oscarapp.api.model.Usuario;
import oscarapp.domain.Votos;

@RequestMapping("/votar")
@RestController
public class RegistrarVotos {
	@Autowired
	private UsuarioRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Votos> login(@RequestParam(value = "login") String login,
			@RequestParam(value = "votoDiretor") String votoDiretor,
			@RequestParam(value = "votoFilme") String votoFilme) {
		Usuario usuarioEncontrado = repo.findByLogin(login);

		if (usuarioEncontrado == null) {
			return new ResponseEntity<Votos>(HttpStatus.NOT_FOUND);
		} else {
			Votos votos = usuarioEncontrado.getVotos();
			if (votos != null && !StringUtils.isEmpty(votos.getDiretor()) && !StringUtils.isEmpty(votos.getFilme())) {
				return new ResponseEntity<Votos>(HttpStatus.BAD_REQUEST);
			}
		}

		Votos votos = usuarioEncontrado.getVotos();

		if (votos == null) {
			votos = new Votos();
			usuarioEncontrado.setVotos(votos);
		}

		if (StringUtils.isEmpty(votos.getFilme())) {
			votos.setFilme(votoFilme);
		}

		if (StringUtils.isEmpty(votos.getDiretor())) {
			votos.setDiretor(votoDiretor);
		}

		repo.save(usuarioEncontrado);

		return new ResponseEntity<Votos>(usuarioEncontrado.getVotos(), HttpStatus.OK);
	}
}
