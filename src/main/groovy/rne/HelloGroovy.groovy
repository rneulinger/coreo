package rne

//import coreo.Defs

class HelloGroovy implements rne.HelloJava {

    static void main(String[] args) {
        println(new HelloGroovy().helloWorld())
//        println( new practice.HelloScala().helloWorld() )
    }

    @Override
    String helloWorld() {
        return "Hello from Groovy"
    }
}