/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.sym;

public final class Pow
  extends BinaryExpression
{
  public Pow(Expression base, Expression exp)
  {
    super(base, exp, '^');
  }

  public Pow(Expression base, float exp)
  {
    this(base, new Constant(exp));
  }

  @Override
  public float eval(float x, float y, float z)
  {
    return (float)Math.pow(
      e1.eval(x, y, z),
      e2.eval(x, y, z));
  }

  @Override
  public Expression diff(Expression variable)
  {
    if (e2.isConstant())
    {
      float exp = ((Constant)e2).value;

      if (0 == exp)
        return Constant.ZERO;

      Expression bd = e1.diff(variable);

      if (1 == exp)
        return bd;

      return (new Mul(
        new Mul(e2, new Pow(e1, exp - 1)),
        bd)).simplify();
    }

    Expression t1 =
      new Mul(e2.diff(variable), Function.loge(e1));

    Expression t2 =
      new Mul(e2, new Div(e1.diff(variable), e1));

    return (new Mul(
      new Pow(e1, e2),
      new Add(t1, t2))).simplify();
  }

  @Override
  public Expression simplify()
  {
    Expression bs = e1.simplify();
    Expression es = e2.simplify();

    if (es.isConstant())
    {
      float exp = ((Constant)es).value;

      if (0 == exp)
        return Constant.ONE;

      if (1 == exp)
        return bs;

      if (bs.isConstant())
        return new Constant(
          (float)Math.pow(((Constant)bs).value, exp));
    }
    else if (bs.isConstant() &&
      1 == ((Constant)bs).value)
      return Constant.ONE;

    return new Pow(bs, es);
  }

} // Pow
