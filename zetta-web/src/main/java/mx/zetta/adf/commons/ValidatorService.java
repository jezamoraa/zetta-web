package mx.zetta.adf.commons;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.zetta.adf.infra.Constants;

@Service
public class ValidatorService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    MessagesService messageService;

    private Validator validator;

    @PostConstruct
    public void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <T extends Object> void validate(T object, Class<?> groups) throws ApplicationException {

        if (object == null || groups == null) {
            throw new ApplicationException(messageService.getString(Constants.M000005));
        }

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);

        Map<String, String> messages = new TreeMap<String, String>();

        Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> violation = iterator.next();
            messages.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        if (!messages.isEmpty()) {
            throw new ApplicationException(messages);
        }

    }

    public <T extends Object> void validate(T object) throws ApplicationException {

        if (object == null) {
            throw new ApplicationException(messageService.getString(Constants.M000005));
        }

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);

        Map<String, String> messages = new TreeMap<String, String>();

        Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> violation = iterator.next();
            messages.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        if (!messages.isEmpty()) {
            throw new ApplicationException(messages);
        }

    }

}
