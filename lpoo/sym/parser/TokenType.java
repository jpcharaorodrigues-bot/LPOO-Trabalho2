/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.sym.parser;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Pedro Henrique da Silva Mendes
 * @author Guilherme Peres Pinto
 */
enum TokenType
{
  EOI,
  ADD_OP,
  SUB_OP,
  MUL_OP,
  DIV_OP,
  POW_OP,
  ASSIGN_OP,
  LPAREN,
  RPAREN,
  FLOAT,
  COMMA,
  NAME,
  EVAL,
  DIFF,
  DEFINE,
  CLEAR,
  LIST,
  EXIT,
  ERROR;

  @Override
  public String toString()
  {
    return strings[ordinal()];
  }

  private static String[] strings =
  {
    "<eoi>",
    "+",
    "-",
    "*",
    "/",
    "^",
    "=",
    "(",
    ")",
    "float",
    ",",
    "name",
    "eval",
    "diff",
    "define",
    "clear",
    "list",
    "exit",
    "<error>"
  };

} // TokenType
