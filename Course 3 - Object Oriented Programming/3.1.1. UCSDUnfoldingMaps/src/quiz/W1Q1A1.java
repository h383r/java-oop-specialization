public class MyClass {

  public int a;
  public double b;
  public MyClass(int first, double second)
  {

    this.a = first;
    this.b = second;

  }

  public boolean same(MyClass other)
  {

    return other.a == this.a && other.b == this.b;

  }
  public static void main(String[] args)
  {
  
    MyClass c1 = new MyClass(30, 123.9);
    MyClass c2 = new MyClass(30, 29.7);
    MyClass c3 = new MyClass(c1.a, c2.b);
  
    System.out.println(c2.same(c3));
  
  }
}