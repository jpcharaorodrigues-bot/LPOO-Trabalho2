/*
 * Autores do trabalho:
 * João Pedro Rodrigues Charão
 * Pedro Henrique da Silva Mendes
 * Guilherme Peres Pinto
 */

package lpoo.sym.parser;

import java.util.Arrays;

/**
 *
 * @author Paulo Pagliosa
 */
public final class InputBuffer extends Input
{
  public InputBuffer(char[] buffer)
  {
    assert(buffer != null);
    this.buffer = Arrays.copyOf(buffer, buffer.length + 1);
  }

} // InputBuffer
