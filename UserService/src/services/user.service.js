const userRepository = require('../repositories/user.repository');
const { hashPassword } = require('../utils/bcrypt');
const generateClave = require('../utils/generateClave');

class UserService {
    async registrarUsuario(userData) {
        const userExiste = await userRepository.findByUsername(userData.username, userData.email);
        if (userExiste) {
            throw new Error('El nombre de usuario o correo electrónico ya están registrados');
        }
        userData.password = await hashPassword(userData.password);
        userData.claveUsuario = generateClave(userData.nombre, userData.apellidoPaterno);

        return await userRepository.save(userData);
    }

    async cambioDatos(idUsuario, statusString) {
        const bitStatus = (statusString === 'activo' || statusString === '1') ? '1' : '0';
        return await userRepository.updateStatus(idUsuario, bitStatus);
    }
}

module.exports = new UserService();