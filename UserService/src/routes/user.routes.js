const express = require('express');
const router = express.Router();
const userController = require('../controllers/user.controller');
const validarToken = require('../middlewares/auth.middleware');
const { isAdmin } = require('../middlewares/role.middleware');

// Ruta para obtener todos los usuarios (solo accesible para administradores)
router.get('/usuarios', validarToken.validarToken, isAdmin, userController.obtenerUsuarios);

// Ruta para registrar un nuevo usuario
router.post('/registro', validarToken.validarToken, userController.registroUsuario);

module.exports = router;