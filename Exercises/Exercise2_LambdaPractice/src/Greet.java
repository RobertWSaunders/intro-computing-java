/**
 * Created by robertsaunders on 2017-04-21.
 */
public class Greet {

    //lambdas are just functions that reside in isolation
    //i.e. a function can be a variable


    //dont need the return type because the compiler can just figure that out
    //dont need the name because the variable is now the name
    //dont need tje public because it doesnt make sense for isolated function


    public void welcomeGreet(Greeting greet) {
        greet.perform();
    }

    public static void main(String[] args) {
        Greet greeting = new Greet();
        WelcomeGreet welcomeGreet = new WelcomeGreet();
        greeting.welcomeGreet(welcomeGreet);


        Greeting hello = () -> System.out.print("String");
        hello.perform();

        Thread myThread = new Thread(() -> System.out.print("Hello"));
        myThread.run();

    }

}

//functional interface should only have one method
