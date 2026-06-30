// Importa el framework de express para crear el servidor 
const express = require("express");

// Importar las rutas CORRECTAS del UserService
const userRoutes = require("./routes/user.routes");

const app = express();

app.use(express.json());

// El prefijo correcto para el microservicio de usuarios
app.use("/api/users", userRoutes);

module.exports = app;