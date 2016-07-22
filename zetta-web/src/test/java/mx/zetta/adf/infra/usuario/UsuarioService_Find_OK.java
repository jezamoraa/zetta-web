package mx.zetta.adf.infra.usuario;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.dto.ParamInputTO;
import mx.zetta.adf.commons.infra.entities.Usuario;

public class UsuarioService_Find_OK extends SpringTest {

	@Autowired
	UsuarioService service;

	@Test
	public void testFind_01() throws ApplicationException {

		List<Usuario> output = service.find(new ParamInputTO<Usuario>(null, new Usuario())).getOutput();

		for (Usuario job : output) {
			LOGGER.info(String.format("UsuarioID = %s\n", job.getUsuarioID()));
		}
	}
	@Test
	public void testFind_02() throws ApplicationException {
		Usuario usuario = new Usuario();
		usuario.setUsuarioID("i");

		List<Usuario> output = service.find(new ParamInputTO<Usuario>(null, usuario)).getOutput();

		for (Usuario job : output) {
			LOGGER.info(String.format("UsuarioID = %s\n", job.getUsuarioID()));
		}
	}
	@Test
	public void testFind_03() throws ApplicationException {
		Usuario usuario = new Usuario();
		usuario.setUsuarioID("i");
		usuario.setNombre("i");
		List<Usuario> output = service.find(new ParamInputTO<Usuario>(null, usuario)).getOutput();

		for (Usuario job : output) {
			LOGGER.info(String.format("UsuarioID = %s\n", job.getUsuarioID()));
		}
	}
	@Test
	public void testFind_04() throws ApplicationException {
		Usuario usuario = new Usuario();
		usuario.setUsuarioID("i");
		usuario.setNombre("i");
		usuario.setCorreo("i");
		List<Usuario> output = service.find(new ParamInputTO<Usuario>(null, usuario)).getOutput();

		for (Usuario job : output) {
			LOGGER.info(String.format("UsuarioID = %s\n", job.getUsuarioID()));
		}
	}

}
