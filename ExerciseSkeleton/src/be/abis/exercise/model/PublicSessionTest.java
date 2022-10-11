package be.abis.exercise.model;

import be.abis.exercise.repository.FilePersonRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PublicSessionTest {
    FilePersonRepository f=FilePersonRepository.getInstance();
    Person p=new Person("sandy","schil");





    @Test
    public void dothis(){

        PublicSession publicSession=new PublicSession(Course.INTERNET_ENABLING, LocalDate.now(),f.getPersonList().get(0).getCompany(),p);
        for (Person person:f.getPersonList()) {
            publicSession.addEnrolment(person);
        }
        publicSession.printListOfParticipants() ;

    }


}