package Tasks;
import java.lang.String;
import Entities.Person;
import org.apache.log4j.Logger;

import org.testng.annotations.BeforeSuite;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static org.testng.Assert.*;

public class TestNGTests {
    static Logger LOGGER = Logger.getLogger("TestNG");

    private List<String> getKidNames(List<Person> collection) {
        return collection
                .stream()
                .filter(kid -> kid.getAge()<18)
                .map(kid -> kid.getName())
                .collect(Collectors.toList());
    }
    private String namesToString(List<Person> collection) {
        return collection.stream()
                .map(person -> person.getName())
                .collect(Collectors.joining(", ","Names: ", "."));
    }

    private int getOldestPerson(List<Person> collection) {
        return collection
                .stream()
                .max(Comparator.comparing(Person::getAge))
                .orElseThrow(NoSuchFieldError::new)
                .getAge();
    }


    @BeforeSuite
    public static void before(){
        LOGGER.info(String.format("I'm %s! Execute me!", LOGGER.getName()));
    }

    @Test(priority = 1)
    public void getKidNameShouldReturnNamesOfYoungerThan18() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        Person anna = new Person("Anna", 5);
        List<Person> collection = asList(sara, eva, viktor, anna);
        LOGGER.info("The method is executed!");
        List<String> result = getKidNames(collection);
        assertTrue(result
                .containsAll(asList("Sara", "Anna")));
        assertFalse(result
                .containsAll(asList("Viktor", "Eva")));
    }

    @Test(priority = 3)
    public void toStringShouldReturnPeopleNamesSeparatedByComma() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, viktor, eva);
        LOGGER.info("The method is executed!");
        assertEquals(namesToString(collection)
                ,"Names: Sara, Viktor, Eva.");
    }


    @Test(priority =2)
    public void getOldestPersonShouldReturnOldestPerson() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        LOGGER.info("The method is executed!");
        List<Person> collection = asList(sara, eva, viktor);
        assertEquals(getOldestPerson(collection),eva.getAge());


    }


    @AfterSuite
    public static void after(){
        LOGGER.info("See! These are my tests, cool, aren't they?");
    }



}
