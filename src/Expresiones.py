import re

f = open("../transitions.txt", "r")
mensaje = f.read()
print("\n", mensaje)
f.close()

EXPRESION = mensaje

transiciones = EXPRESION.split()
contador = {"T1": 0, "T2": 0, "T3": 0, "T4": 0,
            "T5": 0, "T6": 0, "T7": 0, "T8": 0, "T9": 0, "T10": 0,
            "T11": 0, "T12": 0, "T13": 0, "T14": 0}

for transicion in transiciones:
    contador[transicion] += 1

patron = r"((((T1 )(.*?)(T3 )(.*?)((((T5 )(.*?)(T7 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?)))|(((T6 )(.*?)(T8 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?)))))|((T2 )(.*?)(T4 )(.*?)((((T5 )(.*?)(T7 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?)))|(((T6 )(.*?)(T8 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?))))))(T13 )(.*?)(T14 )(.*?))"
resultado = EXPRESION
condicion = True

while True:
    nuevoResultado = re.sub(patron, r'\g<5>\g<7>\g<12>\g<14>\g<17>\g<19>\g<21>\g<23>\g<27>\g<29>\g<32>\g<34>\g<36>\g<38>\g<41>\g<43>\g<48>\g<50>\g<53>\g<55>\g<57>\g<59>\g<63>\g<65>\g<68>\g<70>\g<72>\g<74>\g<76>\g<78>', resultado)
    if(nuevoResultado == resultado ): break
    resultado = nuevoResultado
    print(resultado)


print("\n", contador)
if resultado.count != 0:
    print("\nSobrante:\n", resultado)

#T1 T3 T6 T8 T10 T12 T13 T14
#T1 T3 T6 T8 T9 T11 T13 T14
#T1 T3 T5 T7 T10 T12 T13 T14
#T1 T3 T5 T7 T9 T11 T13 T14
#T2 T4 T6 T8 T10 T12 T13 T14
#T2 T4 T6 T8 T9 T11 T13 T14
#T2 T4 T5 T7 T10 T12 T13 T14
#T2 T4 T5 T7 T9 T11 T13 T14