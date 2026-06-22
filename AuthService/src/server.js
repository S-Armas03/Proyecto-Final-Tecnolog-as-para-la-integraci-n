//Carga las variables de entorno desde el archivo .env
require("dotenv").config();

//Importa la aplicacion de express definida en app.js
const app = require("./app");

/**
 * Inicia el servidor y lo pone a escuchar en el puerto definido
 * en las variables de entorno.
 */
app.listen(process.env.PORT_SERVER, () => {
    //Mensaje en la consola cuando el servidor esta en ejecucion
  console.log("El servidor se esta ejecutando en el puerto 5050");
});