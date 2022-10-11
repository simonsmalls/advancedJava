package be.abis.exercise.test;





import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

public class MytestRegex {
    public static void main(String[] args) {
        String regexp1="S\\w{1}"; // uppercase “S” + 1 more word character
        String s2="Sp";
         boolean a = s2.matches(regexp1);
        System.out.println(a);

        String regex= "\\S+@[a-z]+\\.[a-z]{2,5}";

        String email="simon.hazevoets@hotmail.com";
        Logger l = LogManager.getLogger("exceptionLogger");
        l.error("test");
        l.error("test2");
        Logger l2 = LogManager.getLogger("Console");
        l2.atInfo();

        l2.info("doe ik ");

        FilePersonRepository f = FilePersonRepository.getInstance();
        try {
            f.findPerson(18);
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Person p=new Person("sim","h");
        f.addPerson(p);
        System.out.println("working");



    }
}
