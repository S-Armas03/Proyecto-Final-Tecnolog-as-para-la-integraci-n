# Servicios de estacionamiento (ParkingService)
## Registrar movimiento (entrada): 
* Permite registrar la entrada de los vehículos al
estacionamiento.
* Reglas de negocio: Solo podrán entrar a vehículos que cumplan con lo siguiente:
o Estén asociados a un usuario registrado y activo.
o Solo podrán acceder máximo de 2 vehículos por usuario y los
vehículos deben de estar activos.
− Datos obligatorios: claveUsuario, placa, tiempo de entrada, tiempo
creación, tarifa, espacio (idEspacio).
− Si se cumplen las validaciones, una vez creado el registro de la entrada se
deberá actualizar el campo “ocupado” del espacio asignado al auto.
• Validaciones:
− Validar que el usuario este activo, Validar los datos obligatorios.
− Validar la asociación entre usuario y vehículo, que el vehículo que
pretende entrar pertenezca al usuario que pretende ingresar el auto.
• Respuesta:
− Si la operación de entrada se realizó correctamente deberá devolver los
datos del movimiento (idMovimiento, tiempo de Entrada, espacio
asignado, tarifa por Hora), en caso contrario, un mensaje que especifique
la causa por la cual no pudo ingresar.
o Actualizar movimiento (salida): Permite registrar la salida de los vehículos del
estacionamiento.
• Reglas de negocio:
− Solo podrán salir los vehículos que cumplan con lo siguiente:
o Estén asociados a un usuario registrado y activo.
− Datos obligatorios: claveUsuario, placa, tiempo de salida, tiempo
actualización, costo total, horas cobradas, minutos Estacionado.
− Si se cumplen las validaciones, una vez actualizado el registro (salida) se
deberá actualizar el campo “ocupado” del espacio que está liberando el
auto.
• Validaciones:
− Validar que el usuario este activo, Validar los datos obligatorios.
− Validar la asociación entre usuario y vehículo, que el vehículo que
pretende salir pertenezca al usuario que pretende sacar el auto.
• Respuesta:
− Si la operación de salida se realizó correctamente deberá devolver los
datos del movimiento (idMovimiento, tiempo Entrada, tiempo de salida
espacio asignado, tarifa Hora, costo total, horas cobradas), en caso
contrario, un mensaje que especifique la causa por la cual no puedo salir.
o Consultar espacios: Permite consultar los espacios del estacionamiento.
• Reglas de negocio:
− El usuario deberá autenticarse previamente mediante el servicio de login
y proporcionar un token de autenticación válido para realizar la operación.
• Respuesta:
− Datos de los espacios disponibles.