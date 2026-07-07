package steps;

import io.cucumber.java8.En;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaSteps implements En {
    void log(Object... args) {
        for (Object arg : args) {
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
                , String p2) -> {
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
                , String p2, String t3, String p3) -> {
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
            throw new io.cucumber.java8.PendingException();
        });
        // like next / back
        And("onTo {string}", (String string) -> {
            throw new io.cucumber.java8.PendingException();
        });

        // like gosub / return
        And("inTo {string}", (String string) -> {
            throw new io.cucumber.java8.PendingException();
        });

        And("back:", () -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("wait {double}", (Double seconds) -> {
            throw new io.cucumber.java8.PendingException();
        });

        And("click text {string}", (String string) -> {
            throw new io.cucumber.java8.PendingException();
        });

        And("click text ^{int}", (Integer idx) -> {
            throw new io.cucumber.java8.PendingException();
        });

        And("click {string}", (String string) -> {
            throw new io.cucumber.java8.PendingException();
        });

        And("click ^{int}", (Integer idx) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("click fail {string}", (String string) -> {
            throw new io.cucumber.java8.PendingException();
        });

        And("click fail ^{int}", (Integer idx) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("set {string} = {string}", (String string, String string2) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("set {string} = ^{int}", (String string, Integer idx) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("set:", (io.cucumber.datatable.DataTable dataTable) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("set^", () -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("set: {string}", (io.cucumber.datatable.DataTable dataTable) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("set^ {string}", () -> {
            throw new io.cucumber.java8.PendingException();
        });

        And("expect:", (io.cucumber.datatable.DataTable dataTable) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("expect^", () -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("expect: {string}", (io.cucumber.datatable.DataTable dataTable) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("expect^ {string}", () -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("expect {string} isDisabled", (String string) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("expect {string} != {string}", (String string, String string2) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("expect {string} =~ {string}", (String string, String string2) -> {
            throw new io.cucumber.java8.PendingException();
        });

        And("jira:", (io.cucumber.datatable.DataTable dataTable) -> {
            throw new io.cucumber.java8.PendingException();
        });
        And("click faulty {string}", (String string) -> {
            throw new io.cucumber.java8.PendingException();
        });
    }
}

