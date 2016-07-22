package mx.zetta.adf.infra.usuario;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.SpringTest;
import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.dto.ParamInputTO;
import mx.zetta.adf.commons.infra.entities.Usuario;

public class UsuarioService_GetByID_OK extends SpringTest {

	@Autowired
	UsuarioService service;

	String usuarioID;

	@Before
	public void before() throws ApplicationException {
		usuarioID = "nuevousuario";

		Usuario usuario = new Usuario();
		usuario.setUsuarioID(usuarioID);
		usuario.setAdministrador(false);
		usuario.setContrasena("secret");
		usuario.setCorreo("newusuario@localhost.com");
		usuario.setDescripcion("Prueba de Creaci�n OK");
		usuario.setHabilitado(true);
		usuario.setNombre("nuevo");
		usuario.setRepetirContrasena("secret");

		service.doDelete(new ParamInputTO<Usuario>(null, usuario));

		service.doCreate(new ParamInputTO<Usuario>(null, usuario));
	}

	@After
	public void after() throws ApplicationException {

		Usuario usuario = new Usuario();
		usuario.setUsuarioID(usuarioID);
		service.doDelete(new ParamInputTO<Usuario>(null, usuario));
	}

	@Test
	public void testGetByIDOK() throws ApplicationException {
		Usuario usuario = new Usuario();
		usuario.setUsuarioID(usuarioID);

		try {
			usuario = service.getByID(new ParamInputTO<Usuario>(null, usuario)).getOutput();

		} catch (ApplicationException e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull(usuario);

	}

}
