package Tasks;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JunitTests {
    static Logger LOGGER = Logger.getLogger("Junit");

    private int calculate (List<Integer> list){
        return list.stream()
                .reduce(0, Integer::sum);

    }

    private List<String> transform2(List<String> collection ){
        return collection
                .stream()
                .filter(str -> str.length()<4)
                .collect(Collectors.toList());
    }

    private List<String> transform1(List<String> collection ) {
        return  collection
                .stream()
                .map(str -> str.toUpperCase())
                .collect(Collectors.toList());
    }

    @BeforeAll
    public static void before(){
        LOGGER.info(String.format("I'm %s! Execute me!", LOGGER.getName()));
    }
    @Test
    public void calculateShouldReturnSumOfAllIntegersInCollection() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        assertEquals(calculate(numbers),(1 + 2 + 3 + 4 + 5));
        LOGGER.trace("The method is executed;\n");
    }
    @Test
    public void transformKeepStringsShorterThant4Characters() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("My", "is", "Doe");
        assertEquals(transform2(collection),expected);
        LOGGER.trace("The method is executed;\n");

    }

    @Test
    public void transformShouldConvertCollectionElementsToUpperCase() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("MY", "NAME", "IS", "JOHN", "DOE");
        assertEquals(transform1(collection), expected);
        LOGGER.trace("The method is executed;\n");

    }
    
    @AfterAll
    public static void after(){
        LOGGER.info("See! These are my tests, cool, aren't they?");
    }

}
