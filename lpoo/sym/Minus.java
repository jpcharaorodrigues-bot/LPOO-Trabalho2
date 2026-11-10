/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.sym;

public final class Minus
  extends UnaryExpression
{
  public Minus(Expression e)
  {
    super(e, '-');
  }

  @Override
  public float eval(float x, float y, float z)
  {
    return -e.eval(x, y, z);
  }

  @Override
  public Expression diff(Expression variable)
  {
    return (new Minus(e.diff(variable))).simplify();
  }

  @Override
  public Expression simplify()
  {
    Expression s = e.simplify();

    if (s.isConstant())
    {
      float v = ((Constant)s).value;
      return 0 == v ? Constant.ZERO : new Constant(-v);
    }
    return s instanceof Minus ? ((Minus)s).e : new Minus(s);
  }

} // Minus
