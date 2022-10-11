package be.abis.exercise.test;


import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.PublicSession;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class TestOfDateAndBumberPrints {
    public static void main(String[] args) {
        LocalDate now=LocalDate.now();
        Locale bel=new Locale("nl","BE");
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy", bel );
        String NNBSP =  "\u00A0";

        LocalDate date1= now.plusDays(100).plusMonths(2).plusYears(3);
        LocalDate date2=date1.plusMonths(2);
        LocalDate date3=date2.plusYears(3);
        String formattedDate = date3.format(myFormatObj);

        System.out.println(formattedDate);

        LocalDate birth=LocalDate.of(1997,9,3);
        formattedDate = birth.format(myFormatObj);
        System.out.println(formattedDate);

        LocalDate birthDay=LocalDate.of(2023,9,3);
        Period diff= Period.between(now,birthDay);
        int daysToBirthday= diff.getDays();
        System.out.println(daysToBirthday);

        Locale india=new Locale("en","IN");


        Date birthday=new Date(2023,9,3);
        Date now1=new Date(2022,10,7);
        Date birth1=new Date(1997,9,3);

        Long diffe= now1.getTime()-birthday.getTime();
        Long differ= now1.getTime()-birth1.getTime();

        System.out.println ("Days: " + (diffe / 1000 / 60 / 60 / 24)*-1);
        System.out.println ("Days: " + (differ / 1000 / 60 / 60 / 24));

        Set<String> allZones = ZoneId.getAvailableZoneIds();
        LocalDateTime dt = LocalDateTime.now();
        String mytime=null;
        String kolk=null;

        for (String s : allZones) {
            ZoneId zone = ZoneId.of(s);
            ZonedDateTime zdt = dt.atZone(zone);
            ZoneOffset offset = zdt.getOffset();
            int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
            String out = String.format("%35s %10s%n", zone, offset);

            // Write only time zones that do not have a whole hour offset
            // to standard out.

            String zone1=zone.toString();

            if (zone1.equals("Europe/Monaco")){
                String in=offset.toString();
                System.out.println("got here");
                mytime=in;
            }
            if (zone1.equals("Asia/Calcutta")){
                String in=offset.toString();
                kolk=in;
            }

        }
        System.out.println(mytime);
        System.out.println(kolk);

        double p= 155565.65344;
        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(bel);
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator(' ');

        DecimalFormat df = new DecimalFormat("###0.00",otherSymbols);
        NumberFormat df2 = DecimalFormat.getCurrencyInstance(bel);
        df2.setGroupingUsed(false);
        String output=df2.format(p);
        Double d=5.1;


        System.out.println(output.replace(output.substring(1,2),""));

        System.out.println( output.replace(NNBSP,"") );
        Company company=new Company("abis");
        Person pers=new Person("sim","haas");

        PublicSession publ=new PublicSession(Course.INTERNET_ENABLING,now,company,pers);
        publ.toStringInt(bel);
        publ.toStringInt(new Locale("fr"));
        publ.toStringInt(new Locale("en"));
        publ.revenue();






    }


}
