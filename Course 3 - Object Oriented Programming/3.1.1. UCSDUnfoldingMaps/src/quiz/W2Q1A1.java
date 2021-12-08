public class W2Q1A1
{
  private int a;
  public double b;
  
  public W2Q1A1(int first, double second)
  {
    this.a = first;
    this.b = second;
  }
  public static void main(String[] args)
  {
    W2Q1A1 c1 = new W2Q1A1(10, 20.5);
    W2Q1A1 c2 = new W2Q1A1(10, 31.5);
    System.out.println(c1.a + ", " + c1.b); //10, 20.5
  }
}