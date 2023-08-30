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

patron = r"((T1 )(.*?)(T3 )(.*?)|(T2 )(.*?)(T4 )(.*?))((T5 )(.*?)(T7 )(.*?)|(T6 )(.*?)(T8 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?))(T13 )(.*?)(T14 )"
resultado = mensaje

while True:
    resultado_anterior = resultado

    resultado = re.sub(patron, r'\g<3>\g<5>\g<7>\g<9>\g<12>\g<14>\g<16>\g<18>\g<21>\g<23>\g<25>\g<27>\g<29>', resultado)

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