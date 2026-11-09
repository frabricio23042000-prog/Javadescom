# Descompilar libZyZGfx64.so con Ghidra (GitHub Actions)

1. Crea un repositorio **privado** en GitHub y sube todo el contenido de esta carpeta
   (incluida la carpeta oculta `.github`).
2. Ve a **Actions -> Descompilar con Ghidra -> Run workflow**
   (tambien corre solo al subir un archivo nuevo a `input/`).
3. Al terminar (10-40 min), abre la ejecucion y descarga el artefacto **descompilado**.
   Dentro de `resultado.zip`:
   - `decompiled.c`: pseudo-C de todas las funciones
   - `strings_y_shaders.txt`: textos y shaders GLSL
   - `ghidra_log.txt`: registro del analisis

Para otro .so: subelo a `input/` y ejecuta el flujo indicando su ruta.
Nota: es pseudo-C (nombres tipo FUN_xxxx), no compila tal cual.
