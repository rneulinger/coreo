package steps;

import coreo.*;

import io.cucumber.java8.En;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaSteps implements En {
    void log( String... args){
        for( String arg:args){
            System.out.print(arg + ":");
        }
        System.out.println();
    }
    public JavaSteps() {

        // simple; no parameter
        And("^# ([^\"]+[^:])$", (String t1) -> {
            log(t1);
        });
        And("^# ([^\"]+)(?::)$", (String t1, Object o) -> {
            log(t1, o.toString());
        });
        And("^# ([^\"]+) \"([^\"]*)\"([^\"]*)$", (String t1, String p1, String t2) -> {
            log(t1, p1, t2);
        });
        And("^# ([^\"]+) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"([^\"]*)$", (String t1, String p1, String t2, String p2, String t3) -> {
            log(t1, p1, t2, p2, t3);
        });

        And("goTo {string}", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
            //throw new io.cucumber.java8.PendingException();
        });
        // like next / back
        When("onTo {string}", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
        });

        // like gosub / return
        When("inTo {string}", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
        });

        And("click {string}", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
        When("set:", (io.cucumber.datatable.DataTable dataTable) -> {
            throw new io.cucumber.java8.PendingException();
        });
        When("back:", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
        When("wait {double}", (Integer int1) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });

        When("set: {string}", (String string, io.cucumber.datatable.DataTable dataTable) -> {
            // Write code here that turns the phrase above into concrete actions
            // For automatic transformation, change DataTable to one of
            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
            // Double, Byte, Short, Long, BigInteger or BigDecimal.
            //
            // For other transformations you can register a DataTableType.
            throw new io.cucumber.java8.PendingException();
        });

        When("click text {string}", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });

        When("set {string} = {string}", (String string, String string2) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
        Given("expect:", (io.cucumber.datatable.DataTable dataTable) -> {
            // Write code here that turns the phrase above into concrete actions
            // For automatic transformation, change DataTable to one of
            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
            // Double, Byte, Short, Long, BigInteger or BigDecimal.
            //
            // For other transformations you can register a DataTableType.
            throw new io.cucumber.java8.PendingException();
        });
        Given("expect: {string}", (String string, io.cucumber.datatable.DataTable dataTable) -> {
            // Write code here that turns the phrase above into concrete actions
            // For automatic transformation, change DataTable to one of
            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
            // Double, Byte, Short, Long, BigInteger or BigDecimal.
            //
            // For other transformations you can register a DataTableType.
            throw new io.cucumber.java8.PendingException();
        });
        Given("expect {string} isDisabled", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
        Given("expect {string} != {string}", (String string, String string2) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
        Given("expect {string} =~ {string}", (String string, String string2) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
        Given("!= All users are deleted", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });

        Given("jira:", (io.cucumber.datatable.DataTable dataTable) -> {
            // Write code here that turns the phrase above into concrete actions
            // For automatic transformation, change DataTable to one of
            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
            // Double, Byte, Short, Long, BigInteger or BigDecimal.
            //
            // For other transformations you can register a DataTableType.
            throw new io.cucumber.java8.PendingException();
        });
        Then("click faulty {string}", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
    }
}

