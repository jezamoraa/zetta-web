package mx.zetta.adf.infra.session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.dto.UsuarioFirmadoTO;
import mx.zetta.adf.commons.infra.entities.Session;

@Service
public class SessionService implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Field logger.
     */
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    /**
     * Field entityManager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Method addCache.
     * 
     * @param usuarioImpl
     *            UsuarioFirmadoTO
     * @return UsuarioFirmadoTO
     * @throws ApplicationException
     */
    public UsuarioFirmadoTO addCache(UsuarioFirmadoTO usuarioImpl) throws ApplicationException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        String id = "" + System.currentTimeMillis();
        usuarioImpl.setSessionID(id);
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream salida = new ObjectOutputStream(byteArrayOutputStream);
            salida.writeObject(usuarioImpl);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        Session sesion = new Session();
        sesion.setId(id);
        sesion.setDat(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
        sesion.setDateTime(Calendar.getInstance());
        entityManager.persist(sesion);

        entityManager.merge(sesion);
        return usuarioImpl;
    }

    /**
     * Method getUsuarioCache.
     * 
     * @param sessionID
     *            String
     * @return UsuarioFirmadoTO
     */
    public UsuarioFirmadoTO getUsuarioCache(String sessionID) {

        Session session = entityManager.find(Session.class, sessionID);
        if (session == null) {
            return null;
        }
        UsuarioFirmadoTO usuarioFirmadoTO = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(session.getDat().array());
            ObjectInputStream entrada = new ObjectInputStream(byteArrayInputStream);
            usuarioFirmadoTO = (UsuarioFirmadoTO) entrada.readObject();
            session.setDateTime(Calendar.getInstance());
            entityManager.merge(session);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return usuarioFirmadoTO;
    }

    /**
     * Method doRemoveUsuario.
     * 
     * @param usuarioID
     *            String
     */
    public void doRemoveUsuario(String usuarioID) {
        entityManager.remove(entityManager.find(Session.class, usuarioID));
    }

}
