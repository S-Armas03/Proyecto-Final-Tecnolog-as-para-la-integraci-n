# Proyecto de tecnología - parte usuario
Este sera el servicio que ayudara para registrar nuevos usuarios en el sistema cumpliendo los siguientes puntos
# Reglas del negocio
- El adminsitrador es el unico que puede registrar usuarios
-El usuario deberá autenticarse previamente mediante el servicio de login y proporcionar un token de autenticación válido para realizar la operación.
− No deben existir usuario con el mismo correo ni username,
− Cifrar contraseñas con bcrypt.
− Usuario activo por defecto.
− Datos obligatorios: rol, tipo de usuario, nombre, apellido paterno programa educativo, usuario, contraseña, correo, teléfono y tiempo de registro, clave de usuario.
− La clave de usuario se debe generar internamente y debe de ser única y seguir un patrón (ejemplo: MAOC-253).
### Validacion
* Validar los datos obligatorios.
* Validar formato de correo.
* Validar mediante el correo y username que no existan usuario repetidos.
* Validar el tamaño de los campos.
#  Respuesta
* Mensaje que indique si la operación se realizó correctamente o, en caso contrario, un mensaje que especifique la causa por la cual no pudo completarse