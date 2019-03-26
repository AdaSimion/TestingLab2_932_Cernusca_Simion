package TestingLab2.Validator;
import TestingLab2.Exceptions.ValidatorException;
public interface IValidator<E> {
    void validate(E entity) throws ValidatorException;
}