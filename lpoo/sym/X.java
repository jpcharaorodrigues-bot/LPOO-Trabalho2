/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.sym;

public final class X
  extends Expression
{
  public static final X INSTANCE = new X();

  @Override
  public float eval(float x, float y, float z)
  {
    return x;
  }

  @Override
  public Expression diff(Expression variable)
  {
    return variable == INSTANCE ? Constant.ONE : Constant.ZERO;
  }

  @Override
  public String toString()
  {
    return "x";
  }

  private X()
  {
  }

} // X
