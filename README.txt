TRABALHO 2
Linguagem de Programação Orientada a Objetos - 2026
Universidade Federal de Mato Grosso do Sul
Faculdade de Computação
Professor: Paulo A. Pagliosa

Autores:
João Pedro Rodrigues Charão
Pedro Henrique da Silva Mendes
Guilherme Peres Pinto


SITUAÇÃO DAS ATIVIDADES

A1 - CONCLUÍDA

As classes de expressões do pacote lpoo.sym foram refatoradas para
representar e avaliar expressões nas variáveis x, y e z e para calcular
derivadas parciais em relação a qualquer uma dessas variáveis.

As expressões x, y e z são representadas por objetos singleton.

A implementação permite potências cujo expoente pode ser qualquer
expressão. Também foram incluídas a constante e e a função loge,
mantendo as funções sin e cos e a constante pi.

As expressões suportam soma, subtração, multiplicação, divisão, menos
unário, potência e funções matemáticas, com simplificação das expressões
resultantes quando aplicável.


A2 - CONCLUÍDA

O interpretador de expressões foi finalizado de acordo com a gramática
utilizada no trabalho.

Foram implementados os comandos:

eval
diff
define
atribuição
clear
list
exit

O comando define permite declarar um símbolo com ou sem expressão
inicial. A atribuição associa uma expressão a um símbolo previamente
declarado.

O comando clear pode remover um símbolo específico ou todos os símbolos
definidos. O comando list apresenta os símbolos atualmente definidos.

O analisador reconhece expressões contendo operadores aritméticos,
potências, sinais unários, parênteses, números, variáveis, constantes,
funções e símbolos definidos pelo usuário.

Também são produzidas respostas para entradas inválidas, incluindo erros
sintáticos, nomes inexistentes, redefinição de símbolos, nomes reservados,
variável de diferenciação inválida e formato numérico incorreto.


A3 - CONCLUÍDA

O programa trata os argumentos recebidos pela linha de comando.

Cada argumento é interpretado como o nome de um arquivo texto contendo
comandos da calculadora. Quando mais de um arquivo é informado, os
arquivos são processados sequencialmente, na ordem recebida.

Cada linha é interpretada como um comando da calculadora e os resultados
são apresentados na saída padrão.

Quando nenhum arquivo é especificado na linha de comando, o programa
funciona em modo interativo.

Foram fornecidos dois arquivos de entrada para testes:

testes.txt
Contém comandos válidos para avaliação de expressões, operações
aritméticas, potências, constantes, funções, derivadas parciais,
definição e atribuição de símbolos, listagem, remoção de símbolos e
encerramento do programa.

testes-erros.txt
Contém entradas destinadas a verificar o tratamento de erros léxicos,
sintáticos e semânticos do interpretador.


A4 - PENDENTE

O código-fonte, a compilação, a execução e os arquivos de entrada estão
preparados para a demonstração.

Permanece pendente a gravação e publicação do vídeo de até 10 minutos
contendo a explicação do código-fonte, a compilação, a execução do
programa e a apresentação dos resultados.


VÍDEO

Link: PENDENTE
