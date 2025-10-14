import os

class Fusioner:
    @staticmethod
    def main(args):
        total_freq = dict(int)
        for archivo in args:
            try:
                with open(archivo) as a:
                    for linea in a:
                        partes = partes.split[","]
                        if len(partes)==2:
                            palabra = partes[0]
                            frecuencia = int(partes[1])
                            total_freq[palabra] += frecuencia
                        pass
            except IOError:
                print("Error de lectura")

        print("5 palabras más frecuentes")
        top5 = sorted(total_freq.items(), key=lambda x: x[1], reverse=True)[:5]

        for palabra, frecuencia in top5:
            print(f"{palabra}: {frecuencia}")

        for archivo in args:
            try:
                os.remove(archivo)
            except OSError:
                print("El archivo no existe.")
    

            
