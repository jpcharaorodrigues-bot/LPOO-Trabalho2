/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.sym;

public abstract class UnaryExpression
  extends Expression
{
  @Override
  public String toString()
  {
    boolean parentheses =
      e.isBinary() || e instanceof UnaryExpression;

    return parentheses ?
      String.format("%c(%s)", op, e) :
      op + e.toString();
  }

  protected Expression e;
  protected char op;

  protected UnaryExpression(Expression e, char op)
  {
    this.e = e;
    this.op = op;
  }

} // UnaryExpression
