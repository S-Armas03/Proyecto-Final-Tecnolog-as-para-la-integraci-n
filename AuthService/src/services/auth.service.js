const repo = require('../repositories/auth.repositoy');
const ResponseLogin = require('../models/response/response.login');
const generateToken = require('../config/jwt');
const comparePassword = require('../utils/bcrypt');

exports.testApi = async () => {
    return "The test was successful (Authentication Microservices)";
};

exports.login = async (user) => {
    if (!user.username || !user.password) {
        return ResponseLogin.fail("Los datos del usuario son requeridos");
    }
    const data = await repo.login(user);
    if (!data) {
        return ResponseLogin.fail("El usuario no existe, verifica las credenciales");
    }
    const validPassword = await comparePassword(user.password, data.password);
    if (!validPassword) {
        return ResponseLogin.fail("contraseña incorrecta, verifica las credenciales");
    }
    const token = generateToken(data);
    return ResponseLogin.success(data, token);
};