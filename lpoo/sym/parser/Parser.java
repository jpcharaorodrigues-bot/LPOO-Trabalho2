/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.sym.parser;

import lpoo.sym.*;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Pedro Henrique da Silva Mendes
 * @author Guilherme Peres Pinto
 */
public class Parser
{
  public Parser()
  {
    scanner = new Scanner();
  }

  public void setInput(Input input)
    throws ParseException
  {
    scanner.setInput(input);
    advance();
  }

  public Command parse()
    throws ParseException
  {
    Command c = null;

    if (token.type == TokenType.EVAL)
    {
      advance();
      match(TokenType.LPAREN);

      Expression e = expression();

      match(TokenType.COMMA);

      float[] a = args();

      match(TokenType.RPAREN);

      c = Command.eval;
      c.setArguments(e, a[0], a[1], a[2]);
    }
    else if (token.type == TokenType.DEFINE)
    {
      advance();

      if (token.type != TokenType.NAME)
        error(IDENTIFIER_EXPECTED);

      String name = ((Token.Name)token).value();

      if (isReserved(name))
        error("Reserved name '%1$s'", name);

      advance();

      Expression e = null;

      if (token.type == TokenType.ASSIGN_OP)
      {
        advance();
        e = expression();
      }

      c = Command.define;
      c.setArguments(symbols, name, e);
    }
    else if (token.type == TokenType.NAME)
    {
      String name = ((Token.Name)token).value();

      advance();
      match(TokenType.ASSIGN_OP);

      Expression e = expression();

      c = Command.assign;
      c.setArguments(symbols, name, e);
    }
    else if (token.type == TokenType.DIFF)
    {
      advance();
      match(TokenType.LPAREN);

      Expression e = expression();

      match(TokenType.COMMA);

      if (token.type != TokenType.NAME)
        error(IDENTIFIER_EXPECTED);

      String name = ((Token.Name)token).value();

      advance();

      Expression variable;

      if (name.equals("x"))
        variable = X.INSTANCE;
      else if (name.equals("y"))
        variable = Y.INSTANCE;
      else if (name.equals("z"))
        variable = Z.INSTANCE;
      else
      {
        error("Invalid differentiation variable '%1$s'", name);
        return null;
      }

      match(TokenType.RPAREN);

      c = Command.diff;
      c.setArguments(e, variable);
    }
    else if (token.type == TokenType.CLEAR)
    {
      advance();

      String name = null;

      if (token.type == TokenType.NAME)
      {
        name = ((Token.Name)token).value();
        advance();
      }

      c = Command.clear;
      c.setArguments(symbols, name);
    }
    else if (token.type == TokenType.LIST)
    {
      advance();

      c = Command.list;
      c.setArguments(symbols);
    }
    else if (token.type == TokenType.EXIT)
    {
      advance();
      c = Command.exit;
    }

    if (c == null)
      error(SYNTAX);

    if (token.type != TokenType.EOI)
      error(SYNTAX);

    return c;
  }

  private float[] args()
    throws ParseException
  {
    float[] a = new float[3];

    for (int i = 0; i < 3; ++i)
    {
      if (token.type != TokenType.FLOAT)
        error(NUMBER_EXPECTED);

      a[i] = ((Token.Float)token).value();
      advance();

      if (i < 2)
        match(TokenType.COMMA);
    }

    return a;
  }

  private Expression expression()
    throws ParseException
  {
    Expression e = mul();

    while (token.type == TokenType.ADD_OP ||
      token.type == TokenType.SUB_OP)
    {
      TokenType op = token.type;

      advance();

      Expression e2 = mul();

      e = op == TokenType.ADD_OP ?
        new Add(e, e2) :
        new Sub(e, e2);
    }

    return e;
  }

  private Expression mul()
    throws ParseException
  {
    Expression e = pow();

    while (token.type == TokenType.MUL_OP ||
      token.type == TokenType.DIV_OP)
    {
      TokenType op = token.type;

      advance();

      Expression e2 = pow();

      e = op == TokenType.MUL_OP ?
        new Mul(e, e2) :
        new Div(e, e2);
    }

    return e;
  }

  private Expression pow()
    throws ParseException
  {
    boolean minus = false;

    if (token.type == TokenType.ADD_OP ||
      token.type == TokenType.SUB_OP)
    {
      minus = token.type == TokenType.SUB_OP;
      advance();
    }

    Expression e = primary();

    if (token.type == TokenType.POW_OP)
    {
      advance();
      e = new Pow(e, pow());
    }

    return minus ? new Minus(e) : e;
  }

  private Expression primary()
    throws ParseException
  {
    if (token.type == TokenType.FLOAT)
    {
      float value = ((Token.Float)token).value();

      advance();
      return new Constant(value);
    }

    if (token.type == TokenType.LPAREN)
    {
      advance();

      Expression e = expression();

      match(TokenType.RPAREN);
      return e;
    }

    if (token.type != TokenType.NAME)
      error(SYNTAX);

    String name = ((Token.Name)token).value();

    advance();

    if (token.type == TokenType.LPAREN)
    {
      advance();

      Expression arg = expression();

      match(TokenType.RPAREN);

      if (name.equals("sin"))
        return Function.sin(arg);
      if (name.equals("cos"))
        return Function.cos(arg);
      if (name.equals("loge"))
        return Function.loge(arg);

      error("Unknown function '%1$s'", name);
    }

    if (name.equals("x"))
      return X.INSTANCE;
    if (name.equals("y"))
      return Y.INSTANCE;
    if (name.equals("z"))
      return Z.INSTANCE;
    if (name.equals("pi"))
      return Constant.PI;
    if (name.equals("e"))
      return Constant.E;

    if (symbols.contains(name))
    {
      Expression e = symbols.get(name);

      if (e == null)
        error("Name '%1$s' has no value", name);

      return e;
    }

    error("Undefined name '%1$s'", name);
    return null;
  }

  private boolean isReserved(String name)
  {
    return name.equals("x") ||
      name.equals("y") ||
      name.equals("z") ||
      name.equals("pi") ||
      name.equals("e") ||
      name.equals("sin") ||
      name.equals("cos") ||
      name.equals("loge");
  }

  private void error(int code, Object... args)
    throws ParseException
  {
    throw new ParseException(parseErrorMessages[code], args);
  }

  private void error(String s)
    throws ParseException
  {
    throw new ParseException(s);
  }

  private void error(String s, Object... args)
    throws ParseException
  {
    throw new ParseException(s, args);
  }

  private Token advance()
    throws ParseException
  {
    try
    {
      token = scanner.nextToken();
    }
    catch (InvalidInputException e)
    {
      error(e.getMessage());
    }

    return token;
  }

  private void match(TokenType t)
    throws ParseException
  {
    if (token.type != t)
    {
      if (token.type == TokenType.EOI)
        error(EXPECTED, t.toString());

      error(UNEXPECTED, token.lexeme);
    }

    advance();
  }

  private Scanner scanner;
  private Token token;
  private final SymbolTable symbols = new SymbolTable();

  static final int SYNTAX = 0;
  static final int IDENTIFIER_EXPECTED = 1;
  static final int NUMBER_EXPECTED = 2;
  static final int EXPECTED = 3;
  static final int UNEXPECTED = 4;

  private static final String[] parseErrorMessages =
  {
    "Syntax error",
    "Identifier expected",
    "Number expected",
    "'%1$s' expected",
    "Unexpected '%1$s'"
  };

} // Parser
