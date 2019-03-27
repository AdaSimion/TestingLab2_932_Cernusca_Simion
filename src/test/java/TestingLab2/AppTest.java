package TestingLab2;

import TestingLab2.Exceptions.ValidatorException;
import TestingLab2.Repository.XMLFileRepository.StudentXMLRepo;
import TestingLab2.Service.XMLFileService.StudentXMLService;
import TestingLab2.Validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private StudentXMLRepo XMLrepo;
    private StudentXMLService XMLservice;
    private StudentValidator validator;

    @Before
    public void setup() {
        validator = new StudentValidator();
        XMLrepo = new StudentXMLRepo(validator, "TestStudentsXML.xml");
        XMLservice = new StudentXMLService(XMLrepo);
    }

    @Test
    public void testAddStudentValid() {
        try {
            String[] params = {"1","Ada Simion","932","ada@gmail.com","Prof. Iuliana Bocicor"};
            XMLservice.add(params);
        } catch(ValidatorException ex) {
            fail();
        }
    }

//    @Test(expected = ValidatorException.class)
//    public void testAddStudentNegativeGroup() throws ValidatorException {
//        String[] params = {"1","Ada Simion","-5","ada@gmail.com","Prof. Iuliana Bocicor"};
//        XMLservice.add(params);
//    }

    @Test
    public void testAddStudentNegativeGroup() {
        try {
            String[] params = {"1","Ada Simion","-5","ada@gmail.com","Prof. Iuliana Bocicor"};
            XMLservice.add(params);
            fail();
        } catch(ValidatorException ex) {
            assertTrue(true);
        }
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudentInvalidName() throws ValidatorException {
        String[] params = {"1","","932","ada@gmail.com","Prof. Iuliana Bocicor"};
        XMLservice.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudentInvalidId() throws ValidatorException {
        String[] params = {"","Simion Ada","932","ada@gmail.com","Prof. Iuliana Bocicor"};
        XMLservice.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudentIdWithLettersOrSymbols() throws ValidatorException {
        String[] params = {"234r","Simion Ada","932","ada@gmail.com","Prof. Iuliana Bocicor"};
        XMLservice.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudentInvalidGroup() throws ValidatorException {
        String[] params = {"1234","Simion Ada","","ada@gmail.com","Prof. Iuliana Bocicor"};
        XMLservice.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudentInvalidEmail() throws ValidatorException {
        String[] params = {"1234","Simion Ada","932","","Prof. Iuliana Bocicor"};
        XMLservice.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudentIncorrectEmailFormat() throws ValidatorException {
        String[] params = {"1234","Simion Ada","932","adasimion.com","Prof. Iuliana Bocicor"};
        XMLservice.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudentInvalidTeacherName() throws ValidatorException {
        String[] params = {"1234","Simion Ada","932","ada@gmail.com",""};
        XMLservice.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudentIncorrectGroupFormat() throws ValidatorException {
        String[] params = {"1234","Simion Ada","93x","ada@gmail.com","Iuliana Bocicor"};
        XMLservice.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void testAddStudent_GroupBoundaryTest1() throws ValidatorException {
        String[] params = {"1234","Simion Ada","0","ada@gmail.com","Iuliana Bocicor"};
        XMLservice.add(params);
    }

    @Test
    public void testAddStudent_GroupBoundaryTest2() throws ValidatorException {
        try {
            String[] params = {"1234","Ada Simion","2","ada@gmail.com","Prof. Iuliana Bocicor"};
            XMLservice.add(params);
        } catch(Exception ex) {
            fail();
        }
    }

    @Test
    public void testAddStudent_GroupBoundaryTest3() {
        try {
            String[] params = {"1234","Ada Simion","1","ada@gmail.com","Prof. Iuliana Bocicor"};
            XMLservice.add(params);
        } catch(Exception ex) {
            fail();
        }
    }


}
