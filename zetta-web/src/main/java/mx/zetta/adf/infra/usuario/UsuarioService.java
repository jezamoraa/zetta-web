package mx.zetta.adf.infra.usuario;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.BVal.VCreate;
import mx.zetta.adf.commons.BVal.VUpdate;
import mx.zetta.adf.commons.BVal.VUpdatePassword;
import mx.zetta.adf.commons.JPQL;
import mx.zetta.adf.commons.ValidatorService;
import mx.zetta.adf.commons.dto.ParamInputTO;
import mx.zetta.adf.commons.dto.ParamOutputTO;
import mx.zetta.adf.commons.infra.entities.Usuario;
import mx.zetta.adf.infra.Constants;

@Service
public class UsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ValidatorService validatorServiceEJB;

    @Autowired
    MessageSource messageSource;

    public ParamOutputTO<Usuario> doCreate(ParamInputTO<Usuario> paramInputTO) throws ApplicationException {
        validatorServiceEJB.validate(paramInputTO.getInput(), VCreate.class);

        if (BooleanUtils.isFalse(paramInputTO.getInput().getContrasena().equals(paramInputTO.getInput().getRepetirContrasena()))) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M01, null, null));
        }
        if (Objects.nonNull(entityManager.find(Usuario.class, paramInputTO.getInput().getUsuarioID()))) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M02, null, null));
        }
        entityManager.persist(paramInputTO.getInput());
        return new ParamOutputTO<Usuario>(paramInputTO.getInput());

    }

    public ParamOutputTO<Usuario> getByID(ParamInputTO<Usuario> paramInputTO) throws ApplicationException {

        if (Objects.isNull(paramInputTO.getInput())) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M04, null, null));
        }
        if (Objects.isNull(paramInputTO.getInput().getUsuarioID())) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M04, null, null));
        }

        Usuario usuario = entityManager.find(Usuario.class, paramInputTO.getInput().getUsuarioID());
        if (Objects.isNull(usuario)) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M03, null, null));
        }

        Usuario usuarioReturn = new Usuario();
        usuarioReturn.setAdministrador(usuario.isAdministrador());
        usuarioReturn.setCorreo(usuario.getCorreo());
        usuarioReturn.setDescripcion(usuario.getDescripcion());
        usuarioReturn.setHabilitado(usuario.isHabilitado());
        usuarioReturn.setNombre(usuario.getNombre());
        usuarioReturn.setUsuarioID(usuario.getUsuarioID());
        return new ParamOutputTO<Usuario>(usuarioReturn);
    }

    public ParamOutputTO<Usuario> doUpdate(ParamInputTO<Usuario> paramInputTO) throws ApplicationException {

        validatorServiceEJB.validate(paramInputTO.getInput(), VUpdate.class);
        Usuario usuario = entityManager.find(Usuario.class, paramInputTO.getInput().getUsuarioID());

        if (Objects.isNull(usuario)) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M03, null, null));
        }

        usuario.setAdministrador(paramInputTO.getInput().isAdministrador());
        usuario.setCorreo(paramInputTO.getInput().getCorreo());
        usuario.setDescripcion(paramInputTO.getInput().getDescripcion());
        usuario.setHabilitado(paramInputTO.getInput().isHabilitado());
        usuario.setNombre(paramInputTO.getInput().getNombre());
        return new ParamOutputTO<Usuario>(entityManager.merge(usuario));

    }

    public ParamOutputTO<Usuario> doUpdatePassword(ParamInputTO<Usuario> paramInputTO) throws ApplicationException {

        if (Objects.isNull(paramInputTO.getInput())) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M04, null, null));
        }
        if (Objects.isNull(paramInputTO.getInput().getUsuarioID())) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M04, null, null));
        }
        validatorServiceEJB.validate(paramInputTO.getInput(), VUpdatePassword.class);
        if (BooleanUtils.isFalse(paramInputTO.getInput().getContrasena().equals(paramInputTO.getInput().getRepetirContrasena()))) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M01, null, null));
        }
        Usuario usuario = entityManager.find(Usuario.class, paramInputTO.getInput().getUsuarioID());
        if (Objects.isNull(usuario)) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M03, null, null));
        }
        usuario.setContrasena(paramInputTO.getInput().getContrasena());
        entityManager.merge(paramInputTO.getInput());
        return new ParamOutputTO<Usuario>(paramInputTO.getInput());

    }

    public ParamOutputTO<Usuario> doDelete(ParamInputTO<Usuario> paramInputTO) throws ApplicationException {
        if (Objects.isNull(paramInputTO.getInput())) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M04, null, null));
        }
        if (Objects.isNull(paramInputTO.getInput().getUsuarioID())) {
            throw new ApplicationException(messageSource.getMessage(UsuarioConstants.USUARIO_M04, null, null));
        }
        Usuario usuario = entityManager.find(Usuario.class, paramInputTO.getInput().getUsuarioID());

        if (Objects.nonNull(usuario)) {
            entityManager.remove(usuario);
        }
        return new ParamOutputTO<Usuario>(new Usuario());

    }

    public ParamOutputTO<List<Usuario>> find(ParamInputTO<Usuario> paramInputTO) throws ApplicationException {
        Usuario entity = paramInputTO.getInput();
        final JPQL jpql = new JPQL("select entity from Usuario entity ");

        jpql.append(StringUtils.isNotEmpty(entity.getUsuarioID()), " entity.usuarioID LIKE :paramUsuarioID ", "paramUsuarioID",
                Constants.STRING_LIKE + entity.getUsuarioID().trim() + Constants.STRING_LIKE);

        jpql.append(StringUtils.isNotEmpty(entity.getNombre()), " entity.nombre LIKE :paramNombre ", "paramNombre",
                Constants.STRING_LIKE + entity.getNombre().trim() + Constants.STRING_LIKE);

        jpql.append(StringUtils.isNotEmpty(entity.getCorreo()), " entity.correo LIKE :paramCorreo ", "paramCorreo",
                Constants.STRING_LIKE + entity.getCorreo().trim() + Constants.STRING_LIKE);

        final TypedQuery<Usuario> query = entityManager.createQuery(jpql.toString(), Usuario.class);
        jpql.setParameters(query);

        return new ParamOutputTO<List<Usuario>>(query.getResultList());

    }

}
