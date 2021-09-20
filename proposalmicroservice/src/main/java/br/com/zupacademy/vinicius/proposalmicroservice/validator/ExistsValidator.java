package br.com.zupacademy.vinicius.proposalmicroservice.validator;

import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.List;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {

    @PersistenceContext
    EntityManager entityManager;

    private boolean optional;

    @Override
    public void initialize(Exists constraintAnnotation) {
        optional = constraintAnnotation.optional();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (ObjectUtils.isEmpty(value)) {
            return optional;
        }

        try {
            Class<?> classe = value.getClass();
            Field field = classe.getDeclaredField("id");
            field.setAccessible(true);
            Object id = field.get(value);
            if (optional && id == null) {
                return true;
            }
            Query query = entityManager.createQuery("select a from " + classe.getSimpleName() + " a where a." + field.getName() + " = :id");
            query.setParameter("id", id);
            List<?> resultList = query.getResultList();
            return !resultList.isEmpty();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }
}
