package tklibs;

public class Test1 {
    //
    public int count;

    // constructor
    public Test1() {
        this.count = 0;
    }

    public Test1(int count) {
        this.count = count;
    }

    // Method
    public void plus (int x) {
        count = count + x;
    }
    void print() {
        System.out.println(count);
    }
}
