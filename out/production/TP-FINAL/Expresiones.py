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


patron = "((T1 )(.*?)(T3 )|(T2 )(.*?)(T4 ))(.*?)((T5 )(.*?)(T7 )|(T6 )(.*?)(T8 ))(.*?)((T9 )(.*?)(T11 )|(T10 )(.*?)(T12 ))(.*?)(T13 )(.*?)((T14 )|(T14))"
resultado = mensaje

numero=0

while True:
    print(resultado)

    nuevo_resultado, sustituciones = re.subn(patron, r'\g<3>\g<6>\g<8>\g<11>\g<14>\g<16>\g<19>\g<22>\g<24>\g<26>', resultado)

    if(nuevo_resultado == resultado): break

    resultado=nuevo_resultado

    numero+=sustituciones

print("\nContador de transiciones:")
for transicion, count in contador.items():
    print(f"{transicion}: {count}")

if resultado.count != 0:
    print("\nSobrante:", resultado)

print("\nCantidad de sustituciones:", numero)