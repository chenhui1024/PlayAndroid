package cn.landi.playandroid.function;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/8/5
 * @edit TODO
 */
public class FuncUser {

    private String name;
    private int age;

    public FuncUser(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "FuncUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
