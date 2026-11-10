package lpoo.sym;

public final class Z
  extends Expression
{
  public static final Z INSTANCE = new Z();

  @Override
  public float eval(float x, float y, float z)
  {
    return z;
  }

  @Override
  public Expression diff(Expression variable)
  {
    return variable == INSTANCE ? Constant.ONE : Constant.ZERO;
  }

  @Override
  public String toString()
  {
    return "z";
  }

  private Z()
  {
  }

} // Z
