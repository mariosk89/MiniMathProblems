package passbyvalue;

public class PassByValue {
    public static void main(String[] args) {
        Dog t = new Dog("initial value");
        new PassByValue().changeValue(t);
        System.out.println(t.name);

        System.out.println("-------------------------------------");

        Dog aDog = new Dog("Max");
        Dog oldDog = aDog;

        // we pass the object to foo
        foo(aDog);
        // aDog variable is still pointing to the "Max" dog when foo(...) returns

        System.out.println(aDog.getName());
        System.out.println(aDog == oldDog);


        System.out.println("-------------------------------------");

        foo2(aDog);
        // when foo(...) returns, the name of the dog has been changed to "Fifi"
        System.out.println(aDog.getName()); //Fifi
        // but it is still the same dog:
        System.out.println(aDog == oldDog);
    }


    public static void foo(Dog d) {
        d.getName().equals("Max"); // true
        // change d (d is a copy of the original reference 'aDog' reference) inside of foo() to point to a new passbyvalue.Dog instance "Fifi"
        d = new Dog("Fifi");
        d.getName().equals("Fifi"); // true
    }

    public static void foo2(Dog d) {
        d.getName().equals("Max"); // true
        // this changes the name of d to be "Fifi"
        d.setName("Fifi");
    }

    public void changeValue(Dog f) {
        f.name = "update value";
    }
}

class Dog {
    String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
