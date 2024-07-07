import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> stream1 = persons.stream();
        long count = stream1
                .filter(age -> age.getAge() > 18)
                .count();
        System.out.println(count);

        Stream<Person> stream2 = persons.stream();
        List list = stream2.filter(age -> age.getAge() >= 18)
                .filter(age -> age.getAge() <= 27)
                .filter(sex -> sex.getSex() == Sex.WOMAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(list);

        Stream<Person> stream3 = persons.stream();
        List result = new ArrayList<>();
        List listWomen = stream3.filter(sex -> sex.getSex() == Sex.WOMAN)
                .filter(age -> age.getAge() >= 18)
                .filter(age -> age.getAge() < 60)
                .filter(education -> education.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        result.add(listWomen);

        Stream<Person> stream4 = persons.stream();
        List listMen = stream4.filter(sex -> sex.getSex() == Sex.MAN)
                .filter(age -> age.getAge() >= 18)
                .filter(age -> age.getAge() < 65)
                .filter(education -> education.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        result.add(listMen);
        System.out.println(result);
    }
}