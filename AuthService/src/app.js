//Importa el framework de express para crear el servidor 
const express = require("express");

//Importar las rutas
const authRoutes = require("./routes/auth.routes");

const app = express();

app.use(express.json());

app.use("/sicae/auth/api", authRoutes);

module.exports = app;