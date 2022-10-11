package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Calculator;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Newtest {
    public static void main(String[] args) {
        //Calculator cal=(a,d)->a*(100-d)/100;
        BiFunction<Double,Double,Double> cal=(a, d)->a*(100-d)/100;
        //System.out.println(cal.apply(8.0,5.0));

        FilePersonRepository f= FilePersonRepository.getInstance();
        List<Person> personList=f.getPersonList();

        try {
            personList.forEach(System.out::println);
            Person person= f.findPerson(1);

           // System.out.println(person.getFirstName());
            //System.out.println(person.getEmail() + " " +person.getPassword());
            Person person1= f.findPerson("email@test.com","test");
            //System.out.println(person1.getLastName());
        } catch (PersonNotFoundException e) {
            throw new RuntimeException(e);
        }
        int j=f.removePersonsWithoutCompany().size();
        //System.out.println(j);

        List<Person> result = personList.stream().filter(p->p.getLastName().startsWith("s")).sorted(Comparator.comparing(Person::getFirstName)).collect(Collectors.toList());
        //result.forEach(p->System.out.println(p.getFirstName()));

        List<String> res=personList.stream().filter(p->p.getCompany()!=null)
                //.map(Person::getCompany).map(Company::getName)
                .map(p->p.getCompany())
                .distinct()
                .map(c->c.getName())
                .collect(Collectors.toList());
        res.forEach(System.out::println);

        long number = personList.stream().filter(p->p.getCompany()!=null).filter(p->p.getCompany().getAddress()!=null).filter(p->p.getCompany().getAddress().getTown().equals("leuv")).count();
        //System.out.println(number  );

        Person per=personList.stream().filter(p->p.getBirthDate()!=null).sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList()).get(0);
        Person pers=personList.stream().filter(p->p.getBirthDate()!=null).filter(p->p.getAge()>50).sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList()).get(0);
        //System.out.println(per.getFirstName());
        //System.out.println(pers.getFirstName() + " "+pers.getLastName());


        Map<Company, List<Person>> listMap=personList.stream().filter(p->p.getCompany()!=null).collect(Collectors.groupingBy( p -> p.getCompany()) );
        //List<Person> percompany=listMap.get("google");
        System.out.println(listMap);

        double averageAge=personList.stream().filter(p->p.getBirthDate()!=null).mapToInt(p->p.getAge()).average().getAsDouble();
        //System.out.println(averageAge);

        personList.removeIf(p->p.getCompany()==null);
       // personList.forEach(System.out::println);

        //int a= (false)? 5:6;
        //System.out.println(a);
        Path path = Paths.get("c:\\temp\\javacourses\\persons.csv");

        try (Stream<String> allLines = Files.lines(Paths.get("c:\\temp\\javacourses\\persons.csv"))) {
            long nrOfLines = allLines.count();
            System.out.println("number of lines in file: "+nrOfLines);
            Files.lines(path).map(s->f.readInString(s))
                    .filter(p->p.getCompany()!=null &&  p.getCompany().getAddress()!=null  && p.getCompany().getAddress().getCountryCode().equals("BE"))
                    .filter(p->p.getAge()>40)
                    .forEach(p->f.writePersonToFile(path,p));
                    //.collect(Collectors.toList());
            //List<String> stringList= Files.lines(path).collect(Collectors.toList());
           // personList1.forEach(System.out::println);

            //Files.lines(path).filter(x->x.startsWith("a")).forEach(System.out::println);
        } catch (IOException e) {
// Handle file I/O exception...
        }


    }
}
