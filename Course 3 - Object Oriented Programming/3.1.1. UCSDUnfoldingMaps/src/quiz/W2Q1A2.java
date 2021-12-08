public class W2Q1A2
{
  private int a;
  public double b;
  
  public W2Q1A2(int first, double second)
  {
    this.a = first;
    this.b = second;
  }
  public static void main(String[] args)
  {
    W2Q1A2 c1 = new W2Q1A2(10, 20.5);
    W2Q1A2 c2 = new W2Q1A2(10, 31.5);
    // lines below are changed from the question above
    c2 = c1;   
    c1.a = 2;
    System.out.println(c2.a); //2
  }
}