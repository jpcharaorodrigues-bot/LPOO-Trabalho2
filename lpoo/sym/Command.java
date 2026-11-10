package lpoo.sym;

import lpoo.util.*;

/**
 *
 * @author insert your name here
 */
public abstract class Command
{
  public void setArguments(Object... args)
  {
    arguments = args;
  }

  public final boolean execute()
  {
    boolean returnValue = run();

    arguments = null;
    return returnValue;
  }

  protected abstract boolean run();

  protected Object[] arguments;

  public static final Command eval = new Command()
  {
    @Override
    protected boolean run()
    {
      Expression e = (Expression)arguments[0];
      float x = (Float)arguments[1];
      float y = (Float)arguments[2];
      float z = (Float)arguments[3];

      Console.println(e.eval(x, y, z));
      return true;
    }
  };

  public static final Command diff = new Command()
  {
    @Override
    protected boolean run()
    {
      Expression e = (Expression)arguments[0];
      Expression variable = (Expression)arguments[1];

      Console.println(e.diff(variable).simplify());
      return true;
    }
  };

  public static final Command define = new Command()
  {
    @Override
    protected boolean run()
    {
      SymbolTable symbols = (SymbolTable)arguments[0];
      String name = (String)arguments[1];
      Expression e = (Expression)arguments[2];

      if (!symbols.define(name, e))
      {
        Console.println("Error: Name '" + name + "' already defined");
        return true;
      }

      if (e == null)
        Console.println(name + " defined");
      else
        Console.println(name + " = " + e);

      return true;
    }
  };

  public static final Command assign = new Command()
  {
    @Override
    protected boolean run()
    {
      SymbolTable symbols = (SymbolTable)arguments[0];
      String name = (String)arguments[1];
      Expression e = (Expression)arguments[2];

      if (!symbols.assign(name, e))
      {
        Console.println("Error: Undefined name '" + name + "'");
        return true;
      }

      Console.println(name + " = " + e);
      return true;
    }
  };

  public static final Command clear = new Command()
  {
    @Override
    protected boolean run()
    {
      SymbolTable symbols = (SymbolTable)arguments[0];
      String name = (String)arguments[1];

      if (name == null)
      {
        symbols.clear();
        Console.println("Symbols cleared");
        return true;
      }

      if (!symbols.clear(name))
      {
        Console.println("Error: Undefined name '" + name + "'");
        return true;
      }

      Console.println(name + " cleared");
      return true;
    }
  };

  public static final Command list = new Command()
  {
    @Override
    protected boolean run()
    {
      SymbolTable symbols = (SymbolTable)arguments[0];

      if (symbols.isEmpty())
      {
        Console.println("No symbols defined");
        return true;
      }

      for (String name : symbols.names())
      {
        Expression e = symbols.get(name);

        if (e == null)
          Console.println(name);
        else
          Console.println(name + " = " + e);
      }

      return true;
    }
  };

  public static final Command exit = new Command()
  {
    @Override
    protected boolean run()
    {
      return false;
    }
  };

} // Command
