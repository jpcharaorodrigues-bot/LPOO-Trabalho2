package lpoo.sym;

public final class Y
  extends Expression
{
  public static final Y INSTANCE = new Y();

  @Override
  public float eval(float x, float y, float z)
  {
    return y;
  }

  @Override
  public Expression diff(Expression variable)
  {
    return variable == INSTANCE ? Constant.ONE : Constant.ZERO;
  }

  @Override
  public String toString()
  {
    return "y";
  }

  private Y()
  {
  }

} // Y
