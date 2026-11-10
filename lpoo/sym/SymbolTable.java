/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.sym;

import java.util.*;

public final class SymbolTable
{
  public boolean define(String name, Expression expression)
  {
    if (symbols.containsKey(name))
      return false;

    symbols.put(name, expression);
    return true;
  }

  public boolean assign(String name, Expression expression)
  {
    if (!symbols.containsKey(name))
      return false;

    symbols.put(name, expression);
    return true;
  }

  public boolean contains(String name)
  {
    return symbols.containsKey(name);
  }

  public Expression get(String name)
  {
    return symbols.get(name);
  }

  public boolean clear(String name)
  {
    if (!symbols.containsKey(name))
      return false;

    symbols.remove(name);
    return true;
  }

  public void clear()
  {
    symbols.clear();
  }

  public boolean isEmpty()
  {
    return symbols.isEmpty();
  }

  public Iterable<String> names()
  {
    return symbols.keySet();
  }

  private final TreeMap<String, Expression> symbols =
    new TreeMap<>();

} // SymbolTable
