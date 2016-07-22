package mx.zetta.adf.infra;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import mx.zetta.adf.commons.ApplicationException;
import mx.zetta.adf.commons.MessagesService;
import mx.zetta.adf.commons.dto.ParamInputTO;

/**
 */
public class AutorizacionService {

    @Autowired
    MessagesService messageService;

    /**
     * Method autorizacion.
     * 
     * @param ic
     *            InvocationContext
     * @return Object
     * @throws Exception
     */
    @Before("execution(* mx.zetta.adf.business..*Service.*(..))")
    public void autorizacion(JoinPoint ic) throws ApplicationException {

        if (ic.getArgs().length <= 0 || (ic.getArgs()[0] instanceof ParamInputTO<?>) == false) {
            return;
        }
        ParamInputTO<?> paramInput = (ParamInputTO<?>) ic.getArgs()[0];

        if (paramInput.getTrn().equals(StringUtils.EMPTY)) {
            return;
        }

        if (BooleanUtils.isFalse(paramInput.getUsuarioFirmadoTO().getServicios().contains(paramInput.getTrn()))) {
            throw new ApplicationException(messageService.getString(Constants.M000007, new Object[] { paramInput.getTrn() }, null));
        }
    }
}
