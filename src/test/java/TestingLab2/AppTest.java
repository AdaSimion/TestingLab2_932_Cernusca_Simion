package TestingLab2;

import TestingLab2.Domain.Student;
import TestingLab2.Exceptions.ValidatorException;
import TestingLab2.Repository.MemoryRepository.StudentRepo;
import TestingLab2.Validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
import static junit.framework.TestCase.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private StudentRepo repo;
    private StudentValidator validator;

    @Before
    public void setup() {
        validator = new StudentValidator();
        repo = new StudentRepo(validator);
    }

    @Test
    public void testAddStudentValid() {
        try {
            assertEquals(null, repo.save(new Student("1","Ada Simion",932,"ada@gmail.com","Prof. Iuliana Bocicor")));
        } catch(ValidatorException ex) {
            fail();
        }
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudentInvalidGroup() throws ValidatorException {
        repo.save(new Student("1","Ada Simion",-5,"ada@gmail.com","Prof. Iuliana Bocicor"));
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudentInvalidName() throws ValidatorException {
        repo.save(new Student("1","",932,"ada@gmail.com","Prof. Iuliana Bocicor"));
    }
}
