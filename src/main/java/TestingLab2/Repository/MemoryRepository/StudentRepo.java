package TestingLab2.Repository.MemoryRepository;
import TestingLab2.Validator.IValidator;
import TestingLab2.Domain.Student;

public class StudentRepo extends AbstractCrudRepo<String, Student> {
    public StudentRepo(IValidator<Student> v){ super(v);
    }
}