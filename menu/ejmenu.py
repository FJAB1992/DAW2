""" Author: Francisco Javier Aranda Barba
Date: 13/10/2023 """

import funciones;
def Menu():
    print("\tMENÚ DE OPCIONES\n");
    print("a)Mostrar rombo.");
    print("b)Adivinar un número.");
    print("c)Resolver ecuación de segundo grado.");
    print("d)Tabla de números.");
    print("e)Cálculo del número factorial de un número.");
    print("f)Cálculo de un número de la sucesión de Fibonacci.");
    print("g)Tabla de multiplicar.");
    print("h)Salir\n");

def do_option(option):
    match option:
        case 'a':
            funciones.rhombus();
        case 'b':
            funciones.dealer();
        case 'c':
            funciones.to_solv_equation();
        case 'd':
            funciones.table_num();
        case 'e':
            num = read_number();
            result = funciones.factorial(num);
            print("El factorial de", num, "es:", result,"\n");
        case 'f':
            pos = read_number();
            result = funciones.fibonacci(pos);
            print("El número en la posición", pos, "en la secuencia de Fibonacci es:", result,"\n");
        case 'g':
            funciones.multiply();
        case _:
            print("Opción no válida. Por favor, elige una opción válida (a-h).\n");

def read_option():
    print("Introduzca una opción: \n");
    text = input();
    if text.isalpha() and len(text) == 1:
        return text.lower();
    else:
        print("El valor introducido no es válido. Por favor, introduzca una sola letra.\n");
        return read_option();

def read_number():
    flag = True;
    while flag:
        try:
            print("Introduzca un número: \n");
            number = int(input());
            flag = False;
        except ValueError:
            print("El valor introducido no es un número. Por favor, introduzca un número.\n");
    return number;
    
go_exit = False;
while not go_exit:
    Menu();
    option = read_option();
    if option == 'h':
        go_exit = True;
    else:
        do_option(option);
else:
    print("Saliendo del programa. Que tengas un buen día.\n");
