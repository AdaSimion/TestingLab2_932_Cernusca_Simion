package TestingLab2;

import TestingLab2.Exceptions.*;
import TestingLab2.Repository.XMLFileRepository.NotaXMLRepo;
import TestingLab2.Repository.XMLFileRepository.StudentXMLRepo;
import TestingLab2.Repository.XMLFileRepository.TemaLabXMLRepo;
import TestingLab2.Service.XMLFileService.NotaXMLService;
import TestingLab2.Service.XMLFileService.StudentXMLService;
import TestingLab2.Service.XMLFileService.TemaLabXMLService;
import TestingLab2.Validator.*;
import TestingLab2.UI.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ValidatorException {
        //System.out.println("Hello World!");
        StudentValidator vs=new StudentValidator();
        TemaLabValidator vt=new TemaLabValidator();
        NotaValidator vn=new NotaValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
        NotaXMLRepo ntrepo=new NotaXMLRepo(vn,"NotaXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        NotaXMLService ntsrv=new NotaXMLService(ntrepo);
        ui ui=new ui(stsrv,tmsrv,ntsrv);
        ui.run();
    }
}