package TestingLab2.Validator;
import TestingLab2.Exceptions.ValidatorException;
import TestingLab2.Domain.Student;

public class StudentValidator implements IValidator<Student> {

    public void validate(Student s) throws ValidatorException {
        String errors="";
        if(s.getId().equals("") || !s.getId().matches("[0-9]*")){
            //throw new ValidatorException("Id invalid\n");
            errors+="Id invalid\n";
        }
        if(s.getNume().equals("") || s.getNume()==null){
            //throw new ValidatorException("Nume invalid\n");
            errors+="Nume invalid\n";
        }
        if(s.getGrupa()<=0){
            //throw new ValidatorException("Grupa invalida\n");
            errors+="Grupa invalid\n";
        }
        if(s.getEmail().equals("") || s.getEmail()==null || !s.getEmail().contains("@")){
            //throw new ValidatorException("Email invalid\n");
            errors+="Email invalid\n";
        }
        if(s.getIndrumator().equals("")) {
            errors += "Profesor indrumator invalid\n";
        }
        if (errors.length()!=0){
            throw  new ValidatorException(errors);
        }
    }
}