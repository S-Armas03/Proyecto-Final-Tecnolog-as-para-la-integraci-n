const express = require('express');
const router = express.Router();
const userController = require('../controllers/user.controller');
const { validarToken } = require('../middlewares/auth.middleware');
const { isAdmin } = require('../middlewares/role.middleware');

// Registro de usuarios
router.post('/', validarToken, isAdmin, userController.registroUsuario);

// Cambio de status
router.put('/:idUsuario/status', validarToken, isAdmin, userController.cambioDatos);

module.exports = router;