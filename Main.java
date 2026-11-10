/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import lpoo.sym.*;
import lpoo.util.*;

/**
 *
 * @author João Pedro Rodrigues Charão
 * @author Pedro Henrique da Silva Mendes
 * @author Guilherme Peres Pinto
 */
public class Main
{
  public static void main(String[] args)
  {
    if (args.length == 0)
      interactive();
    else
      for (String fileName : args)
        if (!executeFile(fileName))
          return;
  }

  private static void interactive()
  {
    for (;;)
    {
      String line = Console.readString("> ");

      if (line == null)
        return;
      if (!line.equals("") && !Calc.execute(line))
        return;
    }
  }

  private static boolean executeFile(String fileName)
  {
    try (FileReader fr = new FileReader(fileName);
      BufferedReader bf = new BufferedReader(fr))
    {
      for (;;)
      {
        String line = bf.readLine();

        if (line == null)
          break;
        if (!line.equals("") && !Calc.execute(line))
          return false;
      }
    }
    catch (IOException e)
    {
      Console.println("Error: " + e.getMessage());
    }

    return true;
  }

} // Main
