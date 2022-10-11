package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.exception.ZipCodeNotCorrectException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilePersonRepositoryTest {
    FilePersonRepository f = FilePersonRepository.getInstance();
    private final static String COMMA_DELIMITER = ";";

    @BeforeEach
    public void setUp(){




    }
    @Test

    public void TestSetup(){
        try (BufferedReader br = new BufferedReader(new FileReader("c:\\temp\\javacourses\\persons.csv"))) {
            String line;
            List<String[]> list=new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                list.add(values );

            }
            String[] s=list.get(list.size()-1);
            System.out.println(s[1]);
            System.out.println(f.getPersonList().get(0).getFirstName());


            assertEquals(13,s.length);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
    @Test
    public void throwing(){
        Person p1=f.getPersonList().get(0);
        System.out.println(f.getPersonList().get(0).getPersonNumber());
        assertThrows(ZipCodeNotCorrectException.class,()->p1.getCompany().getAddress().checkZipCode());



    }
    @Test
    public void getName(){
        String p1=f.getPersonList().get(0).getCompany().getAddress().getStreet();
        assertEquals("loui",p1);



    }

    @Test
    public void hashSet(){
        Person p1=new Person("sim","haas");
        Person p2=new Person("sim","haas");
        Person p3=new Person("jan","jef");

        HashSet<Person> personHashSet=new HashSet<>();

        personHashSet.add(p1);
        personHashSet.add(p1);
        personHashSet.add(p2);
        personHashSet.add(p3);
        System.out.println(personHashSet.contains(p2));
        assertEquals(p1,p2);
    }
    @Test
    public void findid(){

        try {
            Person p=f.findPerson(1);
            assertEquals("simon",p.getFirstName());
        } catch (PersonNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void findidnot(){

        assertThrows(PersonNotFoundException.class,() ->f.findPerson(15));

    }
    @Test
    public void findemail(){
        try {
            Person person1= f.findPerson("email@","test");
            assertEquals("simon",person1.getFirstName());
        } catch (PersonNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void findemailnot(){

        assertThrows(PersonNotFoundException.class,() ->f.findPerson("azea","165454"));

    }


}