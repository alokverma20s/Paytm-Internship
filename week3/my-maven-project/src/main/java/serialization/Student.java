package serialization;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private int rollNo;



    // @Override
    // public String toString() {
    //     return "Student [name=" + name + ", age=" + age + ", rollNo=" + rollNo + "]";
    // }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

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
