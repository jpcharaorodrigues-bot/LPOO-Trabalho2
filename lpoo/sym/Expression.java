/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.sym;

public abstract class Expression
{
  public abstract float eval(float x, float y, float z);
  public abstract Expression diff(Expression variable);

  public Expression simplify()
  {
    return this;
  }

  public final boolean isConstant()
  {
    return this instanceof Constant;
  }

  public final boolean isBinary()
  {
    return this instanceof BinaryExpression;
  }

} // Expression
