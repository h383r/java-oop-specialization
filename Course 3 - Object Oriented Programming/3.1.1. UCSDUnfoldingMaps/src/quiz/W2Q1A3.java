public class W2Q1A3
{
  private int a;
  public double b;
  
  public W2Q1A3(int first, double second)
  {
    this.a = first;
    this.b = second;
  }

  // new method
  public static void incrementBoth(W2Q1A3 c1) {
    c1.a = c1.a + 1;
    c1.b = c1.b + 1.0;
  }

  public static void main(String[] args)
  {
    W2Q1A3 c1 = new W2Q1A3(10, 20.5);
    W2Q1A3 c2 = new W2Q1A3(10, 31.5);
    // different code below
    incrementBoth(c2);
    System.out.println(c1.a + ", "+ c2.a); //10, 11
  }
}