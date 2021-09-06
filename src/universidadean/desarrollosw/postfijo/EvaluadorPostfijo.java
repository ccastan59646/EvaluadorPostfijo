/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Esta clase representa una clase que evalúa expresiones en notación polaca o
 * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    /**
     * Realiza la evaluación de la expresión postfijo utilizando una pila
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static int evaluarPostFija(List<String> expresion) {
        Stack<Integer> pila = new Stack<>();

        // TODO: Realiza la evaluación de la expresión en formato postfijo
        // Declaramos tipo de variable para realizar las expresiones correspondientes.
        int num1;
        int num2;
        // Bucle para recorrer la cadanea en lista de valores ingresados.
        for (String e : expresion) {
            // Evalúa sí la expresión ingresada al final de la cadena es "+" (Signo mas).
            if (e.equals("+")) {
                /*
                 Método que elimina el último elemento insertado (En esta caso la expresión).
                 Saca elemento de la pila.
                */
                num1 = pila.pop();
                /*
                 Método que elimina el último elemento insertado (En esta caso el num1).
                 Saca elemento de la pila.
                */
                num2 = pila.pop();
                // Introduce elemento en la pila con realizando la operación de suma.
                pila.push(num1 + num2);
            }
            // Evalúa sí la expresión ingresada al final de la cadena es "-" (Signo Menos).
            else if (e.equals("-")) {
                num1 = pila.pop();
                num2 = pila.pop();
                // Introduce elemento en la pila con realizando la operación de resta.
                pila.push(num2 - num1);
            }
            // Evalúa sí la expresión ingresada al final de la cadena es "-" (Signo multiplicación).
            else if (e.equals("*")){
                num1 = pila.pop();
                num2 = pila.pop();
                // Introduce elemento en la pila con realizando la operación de multiplicación.
                pila.push(num2 * num1);
            }
            // Evalúa sí la expresión ingresada al final de la cadena es "-" (Signo división).
            else if (e.equals("/")) {
                num1 = pila.pop();
                num2 = pila.pop();
                // Sí el divisor es cero, se condiciona para que no salga la excepeción.
                if (num1 != 0) {
                    // Introduce elemento en la pila con realizando la operación de división.
                    pila.push(num2 / num1);
                } else {
                    // Introduce elemento en la pila con cero.
                    pila.push(0);
                }
            }
            // Si no cumple con ninguna expresión (+ - * /) devuelve el número ingresado.
            else {
                pila.push(Integer.parseInt(e));
            }
        }
        return pila.pop();
    }

    /**
     * Programa principal
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("> ");
        String linea = teclado.nextLine();

        try {
            List<String> expresion = Token.dividir(linea);
            System.out.println(evaluarPostFija(expresion));
        }
        catch (Exception e) {
            System.err.printf("Error grave en la expresión: %s", e.getMessage());
        }

    }
}
