Servicios de Autenticación (AuthService)
o Login: Permite autenticar usuarios mediante credenciales válidas (usuario y
contraseña) para obtener acceso al sistema.
• Reglas de negocio:
− Solo usuarios registrados y activos pueden iniciar sesión.
− Datos obligatorios: usuario y contraseña.
− Generar token de autenticación.
− El token deberá de usarse como mecanismo de autenticación para el
consumo de los otros servicios.
• Validaciones:
− Validar los datos obligatorios.
− Verificar existencia del usuario en la base de datos y verificar que la
contraseña sea correcta.
− Validar el tamaño de los campos
• Respuesta:
− Datos (idUsuario, idRol, rol, usuario, nombre completo, idTipoUsuario y
tipo de usuario) del usuario y el token.
