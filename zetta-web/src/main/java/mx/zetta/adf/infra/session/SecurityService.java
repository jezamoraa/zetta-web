package mx.zetta.adf.infra.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.MessagesService;
import mx.zetta.adf.commons.ValidatorService;
import mx.zetta.adf.commons.dto.UsuarioFirmadoTO;
import mx.zetta.adf.commons.infra.entities.Usuario;
import mx.zetta.adf.infra.Constants;

@Service
public class SecurityService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    SessionService sessionService;

    @Autowired
    ValidatorService validatorService;

    @Autowired
    MessagesService messageService;

    @PersistenceContext
    EntityManager entityManager;

    public UsuarioFirmadoTO doLogin(String login, String password) throws ApplicationException {

        UsuarioFirmadoTO usuarioImpl = new UsuarioFirmadoTO();

        Usuario usuario = entityManager.find(Usuario.class, login);

        if (Objects.isNull(usuario)) {
            throw new ApplicationException(messageService.getString(Constants.M000001));
        }

        if (BooleanUtils.isFalse(usuario.getContrasena().trim().equals(password.trim()))) {
            throw new ApplicationException(messageService.getString(Constants.M000001));
        }

        usuarioImpl.setServicios(new ArrayList<String>());

        usuarioImpl.getServicios().add(Constants.SERVICE_MATCH_QUERY);
        usuarioImpl.setUsuario(new Usuario("testing10"));

        usuarioImpl = sessionService.addCache(usuarioImpl);

        return usuarioImpl;
    }

    public void doLogout(String usuarioID) {
        sessionService.doRemoveUsuario(usuarioID);
    }

}
