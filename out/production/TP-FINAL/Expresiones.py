import re

f = open("../transitions.txt", "r")
mensaje = f.read()
print(mensaje)
f.close()

transiciones = re.findall(r'T\d+', mensaje)

contador = {}
for transicion in transiciones:
    if transicion in contador:
        contador[transicion] += 1
    else:
        contador[transicion] = 1


patron = "((T1 )(.*?)(T3 )(.*?)|(T2 )(.*?)(T4 )(.*?))((T5 )(.*?)(T7 )(.*?)|(T6 )(.*?)(T8 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?))(T13 )(.*?)(T14 )"
resultado = mensaje

reemplazo_count = 0


while True:
    resultado_anterior = resultado

    coincidencia = re.search(patron, resultado)
    if coincidencia:
        parte_eliminada = coincidencia.group()

    resultado = re.sub(patron, r'\g<3>\g<5>\g<7>\g<9>\g<12>\g<14>\g<16>\g<18>\g<21>\g<23>\g<25>\g<27>\g<29>', resultado)

    if(resultado == resultado_anterior): break

    reemplazo_count += 1

    print(f"\nIteración {reemplazo_count} - Parte eliminada:\n{parte_eliminada}\n")

    print(resultado)


print("\nContador de transiciones:")
for transicion, count in contador.items():
    print(f"{transicion}: {count}")

if resultado.count != 0:
    print("\nSobrante:\n", resultado)

print("Contador de reemplazos:", reemplazo_count)


#\g<3>\g<6>\g<8>\g<11>\g<14>\g<16>\g<19>\g<22>\g<24>\g<26>
#((T1)(.*?)(T3)|(T2)(.*?)(T4))(.*?)((T5)(.*?)(T7)|(T6)(.*?)(T8))(.*?)((T9)(.*?)(T11)|(T10)(.*?)(T12))(.*?)(T13)(.*?)(T14)

#r'\g<2>\g<5>\g<9>\g<11>\g<14>\g<17>\g<19>\g<22>\g<25>\g<27>\g<29>\g<31>'
#patron = "(T0)(.*?)((T1)(.*?)((T3))|(T2)(.*?)(T4))(.*?)((T5)(.*?)(T7)|(T6)(.*?)(T8))(.*?)((T9)(.*?)(T11)|(T10)(.*?)(T12))(.*?)(T13)(.*?)(T14)(.*?)"

#\g<2>\g<4>\g<6>\g<8>\g<10>\g<12>\g<14>\g<16>
#(T1 |T2 )(.*?)(T3 |T4 )(.*?)(T5 |T6 )(.*?)(T7 |T8 )(.*?)(T9 |T10 )(.*?)(T11 |T12 )(.*?)(T13 )(.*?)(T14 )(.*?)

#\g<3>\g<5>\g<7>\g<9>\g<12>\g<14>\g<16>\g<18>\g<21>\g<23>\g<25>\g<27>\g<29>
#((T1 )(.*?)(T3 )(.*?)|(T2 )(.*?)(T4 )(.*?))((T5 )(.*?)(T7 )(.*?)|(T6 )(.*?)(T8 )(.*?))((T9 )(.*?)(T11 )(.*?)|(T10 )(.*?)(T12 )(.*?))(T13 )(.*?)(T14 )