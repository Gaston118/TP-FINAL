import re

f = open("../transitions.txt", "r")
mensaje = f.read()
print(mensaje)
f.close()

transiciones = mensaje.split()
contador = {"T1": 0, "T2": 0, "T3": 0, "T4": 0,
            "T5": 0, "T6": 0, "T7": 0, "T8": 0, "T9": 0, "T10": 0,
            "T11": 0, "T12": 0, "T13": 0, "T14": 0}

for transicion in transiciones:
    contador[transicion] += 1

ALL_IT = [
    [2, 4, 6, 8, 10, 12, 13, 14],
    [2, 4, 6, 8, 9, 11, 13, 14],
    [2, 4, 5, 7, 10, 12, 13, 14],
    [2, 4, 5, 7, 9, 11, 13, 14],
    [1, 3, 6, 8, 10, 12, 13, 14],
    [1, 3, 6, 8, 9, 11, 13, 14],
    [1, 3, 5, 7, 10, 12, 13, 14],
    [1, 3, 5, 7, 9, 11, 13, 14]
]
position_counters = [0] * len(ALL_IT)

patron = r"((((T1 )(.*?)(T3 )(.*?)((((T5 )(.*?)(T7 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?)))|(((T6 )(.*?)(T8 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?)))))|((T2 )(.*?)(T4 )(.*?)((((T5 )(.*?)(T7 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?)))|(((T6 )(.*?)(T8 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?))))))(T13 )(.*?)(T14 )(.*?))"
resultado = mensaje

while True:
    resultado_anterior = resultado

    resultado = re.sub(patron, r'\g<5>\g<7>\g<12>\g<14>\g<17>\g<19>\g<21>\g<23>\g<27>\g<29>\g<32>\g<34>\g<36>\g<38>\g<41>\g<43>\g<48>\g<50>\g<53>\g<55>\g<57>\g<59>\g<63>\g<65>\g<68>\g<70>\g<72>\g<74>\g<76>\g<78>', resultado)

    if(resultado == resultado_anterior): break

    print(resultado)


print("\n", contador,"\n")

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