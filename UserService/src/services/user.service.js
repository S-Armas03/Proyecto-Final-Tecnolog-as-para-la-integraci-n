const userRepository = require('../repositories/user.repository');
const generateToken = require('../config/jwt');
const { hashPassword } = require('../utils/bcrypt');

class UserService {
    async registrarUsuario(userData) {
        const userExiste = await userReository.findByUsername(userData.username, userData.email);
        if (userExiste) {
            throw new Error('El nombre de usuario o correo electrónico ya están registrados');
        }
        userData.password = await hashPassword(userData.password);
        userData.claveUsuario = await generateClave(userData.nombre, userData.apellido_paterno);
        return await userRepository.save(userData);
    }

    async cambioDatos(idUsuario, status) {
        const status = (status === 'activo') ? 'inactivo' : 'activo';
        return await userRepository.subirStatus(idUsuario, status);
    }
};

module.exports = new UserService();