package TestingLab2.Service.TxtFileService;
import TestingLab2.Domain.*;
import TestingLab2.Repository.TxtFileRepository.NotaFileRepo;

public class NotaService extends AbstractService<Integer,Nota> {
    public NotaService(NotaFileRepo notaRepo){
        super(notaRepo);
    }
}
