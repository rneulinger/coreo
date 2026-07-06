package steps;

import coreo.*;

import io.cucumber.java8.En;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaSteps implements En {
    void log( Object... args){
        for( Object arg:args){
            System.out.print(">" + arg.toString() + "< ");
        }
        System.out.println();
    }
    public JavaSteps() {

        // NO PARAMS
        // no parameter
        And("^# ([^\"]*[^:])$", (String t1) -> {
            log(t1);
        });
        // no parameter : object
        And("^# ([^\"]+)(?::)$", (String t1, Object o) -> {
            log(t1, o.toString());
        });

        // one parameter no trailing text
        And("^# ([^\"]+) \"([^\"]*)\"$", (String t1, String p1) -> {
            log(t1, p1);
        });
        // one parameter no trailing text : object
        And("^# ([^\"]+) \"([^\"]*)\"(?::)$", (String t1, String p1, Object o) -> {
            log(t1, p1, o);
        });

        // one parameter with trailing text
        And("^# ([^\"]+) \"([^\"]*)\" ([^\"]*[^:])$", (String t1, String p1, String t2) -> {
            log(t1, p1, t2);
        });
        // one parameter with trailing text : object
        And("^# ([^\"]+) \"([^\"]*)\" ([^\"]*)(?::)$", (String t1, String p1, String t2, Object o) -> {
            log(t1, p1, t2, o);
        });

        // TWO PARAMS
        // two parameters no trailing text 
        And("^# ([^\"]+) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"$", (String t1, String p1, String t2
                , String p2 ) -> {
            log(t1, p1, t2, p2);
        });

        // two parameters no trailing text : object
        And("^# ([^\"]+) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"(?::)$", (String t1, String p1, String t2
                , String p2, Object o) -> {
            log(t1, p1, t2, p2, o);
        });

        // two parameters with trailing text
        And("^# ([^\"]+) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"([^\"]*[^:])$", (String t1, String p1, String t2
                , String p2, String t3) -> {
            log(t1, p1, t2, p2, t3);
        });

        // two parameters with trailing text : object
        And("^# ([^\"]+) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"([^\"]+)(?::)$", (String t1, String p1, String t2
                , String p2, String t3, Object o) -> {
            log(t1, p1, t2, p2, t3, o);
        });

        // THREE PARAMS
        // three parameters no trailing text 
        And("^# ([^\"]+) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"$", (String t1, String p1, String t2
                , String p2, String t3, String p3 ) -> {
            log(t1, p1, t2, p2, t3, p3);
        });

        // three parameters no trailing text : object
        And("^# ([^\"]+) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"(?::)$", (String t1, String p1, String t2
                , String p2, String t3, String p3, Object o) -> {
            log(t1, p1, t2, p2, t3, p3, o);
        });

        // three parameters with trailing text
        And("^# ([^\"]+) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"([^\"]*[^:])$", (String t1, String p1, String t2
                , String p2, String t3, String p3, String t4) -> {
            log(t1, p1, t2, p2, t3, p3, t4);
        });

        // three parameters with trailing text : object
        And("^# ([^\"]+) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"([^\"]*) \"([^\"]*)\"([^\"]+)(?::)$", (String t1, String p1, String t2
                , String p2, String t3, String p3, String t4, Object o) -> {
            log(t1, p1, t2, p2, t3, p3, t4, o);
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

        And("click text {string}", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });

        And("set {string} = {string}", (String string, String string2) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new io.cucumber.java8.PendingException();
        });
        And("expect:", (io.cucumber.datatable.DataTable dataTable) -> {
            // Write code here that turns the phrase above into concrete actions
            // For automatic transformation, change DataTable to one of
            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
            // Double, Byte, Short, Long, BigInteger or BigDecimal.
            //
            // For other transformations you can register a DataTableType.
            throw new io.cucumber.java8.PendingException();
        });
        And("expect: {string}", (String string, io.cucumber.datatable.DataTable dataTable) -> {
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

