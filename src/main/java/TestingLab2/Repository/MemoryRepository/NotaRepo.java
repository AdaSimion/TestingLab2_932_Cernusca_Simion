package TestingLab2.Repository.MemoryRepository;
import TestingLab2.Validator.IValidator;
import TestingLab2.Domain.Nota;

public class NotaRepo extends AbstractCrudRepo<Integer,Nota > {
    public NotaRepo(IValidator<Nota> v){ super(v);
    }
}