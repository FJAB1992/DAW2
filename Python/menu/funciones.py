""" Author: Francisco Javier Aranda Barba
Date: 18/10/2023 """

import math;
import random;

def rhombus():
    flag = True;
    
    while (flag==True):
        try:
            num = int(input("Introduce un número impar: \n"));
            if num % 2 == 0:
                print("El número no es impar. Debe ser impar para mostrar un rombo.\n");
                rhombus();
            else:
                for i in range(1, num + 1, 2):
                    print(" " * ((num - i) // 2) + "*" * i);
                for i in range(num - 2, 0, -2):
                    print(" " * ((num - i) // 2) + "*" * i);
            flag = False;
        except ValueError:
            print("El valor introducido no es un número.\n");

def dealer():
    num = 0;
    chances = 0;
    magic_number = random.randint(1, 100);

    while num != magic_number:
        flag = True;
        while flag:
            try:
                num = int(input("Adivina un número entre 1 y 100:\n"));
                chances += 1;
                if num > 100:
                    print("El número debe estar entre 1 y 100.\n");
                elif num < 1:
                    print("El número debe estar entre 1 y 100.\n");
                else:
                    if num > magic_number:
                        print("El número es menor.\n");
                    else:
                        print("El número es mayor.\n");
                flag = False;
            except ValueError:
                print("El valor introducido no es un número.\n");   
    print(f"Has adivinado el número en {chances} intentos.\n");

def to_solv_equation():
    a = 0;
    b = 0;
    c = 0;
    x1 = 0;
    x2 = 0;
    disc = 0;
    flag = True;
    
    while flag == True:
        try:
            a = int(input("Introduzca valor de a: "));
            b = int(input("Introduzca valor de b: "));
            c = int(input("Introduzca valor de c: "));
            flag = False;
        except ValueError:
            print("El valor introducido no es un número. Por favor, introduzca un número.\n");
            
    disc = b**2 - 4*a*c;
    
    if disc < 0 or a == 0:
        print("La ecuación no tiene solución.\n");
    else:
        x1 = -b + math.isqrt(disc) / 2*a;
        x2 = -b +  math.isqrt(disc) / 2*a;
        print("\nLas soluciones son:",x1,"y",x2,"\n");

def table_num():
    flag = True;
    
    while flag:
        try:
            rows = int(input("Introduce el número de filas: "));
            cols = int(input("Introduce el número de columnas: "));
            
            for i in range(rows):
                for j in range(cols):
                    print(random.randint(1, 100), end=" ");
                print();
            flag = False;
        except ValueError:
            print("El valor introducido no es un número.\n");

def factorial(num):
    if num == 0:
        return 1;
    return num * factorial(num - 1);

def fibonacci(pos):
    sequence=[1,1];
    for i in range(2,pos):
        sequence.append(sequence[i-1] + sequence[i-2]);
    return sequence[pos-1];
        
def multiply():
    flag = True;
    while flag:
        try:
            num = int(input("Introduce un número para mostrar su tabla de multiplicar: \n"));
            for i in range(1, 11):
                print(num, "x", i ,"=", num * i);
                print();
            flag = False;
        except ValueError:
            print("El valor introducido no es un número.\n");