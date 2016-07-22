package mx.zetta.adf.infra;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.MessagesService;
import mx.zetta.adf.commons.Function;
import mx.zetta.adf.commons.dto.ParamInputTO;
import mx.zetta.adf.infra.session.SessionService;

/**
 */
public class AutenticacionService {

    /**
     * Field sessionService.
     */
    @Autowired
    SessionService sessionService;

    @Autowired
    MessagesService messageService;

    /**
     * Method autenticacion.
     * 
     * @param ic
     *            InvocationContext
     * @return Object
     * @throws Exception
     */
    @Before("execution(* mx.zetta.adf.business..*Service.*(..))")
    public void autenticacion(JoinPoint ic) throws ApplicationException {

        if (ic.getArgs().length <= 0 || (ic.getArgs()[0] instanceof ParamInputTO<?>) == false) {
            return;
        }
        Function tx = null;
        try {
            tx = ic.getSignature().getClass().getAnnotation(Function.class);
        } catch (Exception e) {

        }
        if (Objects.isNull(tx)) {
            return;
        }
        final ParamInputTO<?> paramInput = (ParamInputTO<?>) ic.getArgs()[0];

        if (paramInput == null) {
            throw new ApplicationException(messageService.getString(Constants.M000006));
        }
        if (paramInput.getSessionID() == null) {
            throw new ApplicationException(messageService.getString(Constants.M000006));
        }
        paramInput.setUsuarioFirmadoTO(sessionService.getUsuarioCache(paramInput.getSessionID()));

        if (paramInput.getUsuarioFirmadoTO() == null) {
            throw new ApplicationException(messageService.getString(Constants.M000006));
        }
        if (tx == null) {
            paramInput.setTrn(StringUtils.EMPTY);
        } else {
            paramInput.setTrn(tx.value());
        }
    }
}
