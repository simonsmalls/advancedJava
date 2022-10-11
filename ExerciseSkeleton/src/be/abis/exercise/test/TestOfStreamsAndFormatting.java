package be.abis.exercise.test;

import be.abis.exercise.model.*;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.SessionRepository;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TestOfStreamsAndFormatting {
    public static void main(String[] args) {
        SessionRepository s=SessionRepository.getInstance();
        List<Session> sessionList= s.getSessionList();
        System.out.println(s.getSessionList().get(1).getCourse().getTitle());
        s.findMonth(10,2021).stream().map(k->k.getLocation().getName()).forEach(System.out::println);

        Address address= new Address("loui","9","0100","leuv","be","BE");
        Company company=new Company("abis",address);
        Session ses= sessionList.get(0);
        Company comp = ses.getLocation();

        sessionList.stream().filter(p->p.getClass()==PublicSession.class)
                .map(p-> ((PublicSession)p).getEnrolments()).forEach(System.out::println);

        System.out.println();


        //s.findLocation(comp).stream().map(k->k.getLocation().getName()).forEach(System.out::println);

        sessionList.stream().filter(f->!f.getLocation().equals(comp));
        //sessionList.stream().sorted(Comparator.comparing(Session::getDate)).map(f->f.getDate()).forEach(System.out::println);
        //sessionList.stream().map(f->f.getDate()).sorted().forEach(System.out::println);
 //       Map<Company, List<Person>> listMap=personList.stream().filter(p->p.getCompany()!=null).collect(Collectors.groupingBy(p -> p.getCompany()) );

        Map<Company, List<Session>> listMap= sessionList.stream().collect(Collectors.groupingBy(p->p.getLocation()));
        Map<Instructor, List<Session>> listMap1= sessionList.stream().collect(Collectors.groupingBy(p->p.getInstructor()));
        System.out.println(listMap);
        System.out.println(listMap1);

        FilePersonRepository personRepository= FilePersonRepository.getInstance();
        List<Person> personList=personRepository.getPersonList();

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            //do this one tuesday


            Map<String, List<Company>> StringList= personList.stream().filter(p->p.getCompany()!=null).map(p->p.getCompany()).collect(Collectors.groupingBy(p->p.getAddress().getCountry()));
            fw = new FileWriter("c:\\temp\\javacourses\\companiespretty.txt", false);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            Set<String> stringSet = StringList.keySet();
            for (String str:stringSet){
                pw.println(str+":");
                List<Company> companyList=StringList.get(str);
                for (Company com:companyList){

                    pw.printf("%1$-5s%2$-10s%3$-20s%4$-10s%5$-10s", "", com.getName(), com.getAddress().getStreet()+" " +com.getAddress().getNr(), com.getAddress().getZipCode(),com.getAddress().getTown());
                    pw.println();

                }

            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            }
            catch (IOException io) {}

        }
        List<String> countryCodeList=personList.stream().filter(p->p.getCompany()!=null && p.getCompany().getAddress()!=null)
                .map(p->p.getCompany().getAddress().getCountryCode())
                .collect(Collectors.toList());

        for (Person person:personList){
            if( person.getCompany()!=null && person.getCompany().getAddress()!=null){
                String language=person.getCompany().getAddress().getCountryCode().toLowerCase();
                Locale locale=new Locale(language);
                DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MMMM-yy",locale);
                System.out.println(person.getBirthDate().format((formatter)));
            }

        }

    }
}
