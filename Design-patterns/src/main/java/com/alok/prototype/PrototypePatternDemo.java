package com.alok.prototype;

// Step 1: Prototype Interface
interface Prototype extends Cloneable {
    Prototype clone();
}

// Step 2: Concrete Prototype Class
class Person implements Prototype {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Overriding the clone method
    @Override
    public Person clone() {
        try {
            return (Person) super.clone(); // Shallow copy
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported", e);
        }
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }

    // Getters and setters (optional)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

// Step 3: Client
public class PrototypePatternDemo {
    public static void main(String[] args) {
        Person original = new Person("John", 30);
        System.out.println("Original: " + original);

        // Clone the original object
        Person clone = original.clone();
        System.out.println("Clone: " + clone);

        // Modify the clone
        clone.setName("Jane");
        clone.setAge(25);

        System.out.println("After modification:");
        System.out.println("Original: " + original);
        System.out.println("Clone: " + clone);
    }
}
