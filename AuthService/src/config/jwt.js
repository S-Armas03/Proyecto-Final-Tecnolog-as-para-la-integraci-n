const jwt = require("jsonwebtoken");

const generateToken = (user) => {
    const payload = {
        idUsuario: user.idUsuario,
        username: user.username,
        idRol: user.idRol
    };
    return jwt.sign(
        payload, //Datos incluidos en el token
        process.env.JWT_SECRET, //clave secreta
        {expiresIn: "1h"}, //tiempo de expiracion
        //{algorithm: "HS512"} //#HS512 algoritmo para el token
    );
};

module.exports = generateToken;
