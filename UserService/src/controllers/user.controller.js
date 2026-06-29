const userService = require('../services/user.service');

class UserController {
    async registroUsuario(req, res) {
        try {
            await userService.registrarUsuario(req.body);
            res.status(201).json({ message: 'Usuario registrado correctamente' });
        } catch (error) {
            res.status(400).json({ message: error.message });
        }
    }

    async cambioDatos(req, res) {
        try {
            const {idUsuario} = req.params;
            const {status} = req.body;
            await userService.cambioDatos(idUsuario, status);
            res.status(200).json({ message: 'Estado del usuario actualizado correctamente' });
        } catch (error) {
            res.status(400).json({ message: error.message });
        }
    }
};

module.exports = new UserController();