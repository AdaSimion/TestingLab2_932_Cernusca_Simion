package TestingLab2;

import TestingLab2.Domain.TemaLab;
import TestingLab2.Exceptions.ValidatorException;
import TestingLab2.Repository.XMLFileRepository.NotaXMLRepo;
import TestingLab2.Repository.XMLFileRepository.StudentXMLRepo;
import TestingLab2.Repository.XMLFileRepository.TemaLabXMLRepo;
import TestingLab2.Service.XMLFileService.NotaXMLService;
import TestingLab2.Service.XMLFileService.StudentXMLService;
import TestingLab2.Service.XMLFileService.TemaLabXMLService;
import TestingLab2.Validator.NotaValidator;
import TestingLab2.Validator.StudentValidator;
import TestingLab2.Validator.TemaLabValidator;
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

    private TemaLabValidator assignmentValidator;
    private TemaLabXMLService assignmentXMLservice;
    private TemaLabXMLRepo assignmentXMLrepo;

    private NotaValidator gradeValidator;
    private NotaXMLService gradeXMLservice;
    private NotaXMLRepo gradeXMLrepo;

    @Before
    public void setup() {
        validator = new StudentValidator();
        XMLrepo = new StudentXMLRepo(validator, "TestStudentsXML.xml");
        XMLservice = new StudentXMLService(XMLrepo);

        assignmentValidator = new TemaLabValidator();
        assignmentXMLrepo = new TemaLabXMLRepo(assignmentValidator, "TestAssignmentsXML.xml");
        assignmentXMLservice = new TemaLabXMLService(assignmentXMLrepo);

        gradeValidator = new NotaValidator();
        gradeXMLrepo = new NotaXMLRepo(gradeValidator, "TestGradesXML.xml");
        gradeXMLservice = new NotaXMLService(gradeXMLrepo);
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

    @Test
    public void testValidateLabAssignment() {
        try {
            assignmentValidator.validate(new TemaLab(1, "Lab 1 Testing", 5, 3));
        } catch(ValidatorException ex) {
            fail();
        }
    }

    @Test(expected = ValidatorException.class)
    public void testValidateLabAssignment_InvalidDescription() throws ValidatorException {
        assignmentValidator.validate(new TemaLab(1, "", 5, 3));
    }

    @Test(expected = ValidatorException.class)
    public void testValidateLabAssignment_InvalidDeadlineWeek() throws  ValidatorException {
        assignmentValidator.validate(new TemaLab(1,"desc",-3,3));
    }

    @Test(expected = ValidatorException.class)
    public void testValidateLabAssignment_InvalidTurnInWeek() throws  ValidatorException {
        assignmentValidator.validate(new TemaLab(1,"desc",3,-3));
    }

    @Test(expected = ValidatorException.class)
    public void testValidateLabAssignment_InvalidId() throws  ValidatorException {
        assignmentValidator.validate(new TemaLab(Integer.parseInt(""+0),"desc",3,3));
    }

    /* Lab 4 In Class: */
    @Test
    public void testAddAssignmentValid_BlackBox() {
        try {
            String[] params = {"1","Lab 4 IC","3","5"};
            assignmentXMLservice.add(params);
        } catch(ValidatorException ex) {
            fail();
        }
    }

    // for add student, see above - testAddStudentValid

    @Test
    public void testAddGradeValid_BlackBox() {
        try {
            String[] params = {"1", "1", "1", "7", "2016-11-09T11:44:44.797"};
            gradeXMLservice.add(params);
        } catch(ValidatorException ex) {
            fail();
        }
    }

    @Test
    public void addEntitiesBigBangIntegration() {
        try {
            //student
            String[] params = {"1","Ada Simion","932","ada@gmail.com","Prof. Iuliana Bocicor"};
            XMLservice.add(params);

            //assignment
            String[] params2 = {"1","Lab 4 IC","3","5"};
            assignmentXMLservice.add(params2);

            //grade
            String[] params3 = {"1", "1", "1", "7", "2016-11-09T11:44:44.797"};
            gradeXMLservice.add(params3);
        } catch(ValidatorException ex) {
            fail();
        }
    }
}
