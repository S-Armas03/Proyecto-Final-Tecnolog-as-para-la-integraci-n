const pool = require('../config/db');

class UserRepository {
    async findByUsername(username, email) {
        const query = 'SELECT * FROM usuario WHERE username = $1 OR email = $2';
        const result = await pool.query(query, [username, email]);
        return result.rows[0];
    }

    async save(userData) {
        const query = `
            INSERT INTO usuario (
                "nombre", "apellidoPaterno", "apellidoMaterno", "claveUsuario", 
                "email", "telefono", "username", "password", "estatus", 
                "idRol", "idTipoUsuario", "idProgramaEducativo", "tiempoCreacion"
            ) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, NOW())
            RETURNING *;
        `;
        
        const values = [
            userData.nombre,
            userData.apellidoPaterno,
            userData.apellidoMaterno,
            userData.claveUsuario,
            userData.email,
            userData.telefono,
            userData.username,
            userData.password,
            '1', 
            userData.idRol,
            userData.idTipoUsuario,
            userData.idProgramaEducativo
        ];

        const result = await pool.query(query, values);
        return result.rows[0];
    }

    async updateStatus(idUsuario, bitStatus) {
        const query = 'UPDATE usuario SET estatus = $1 WHERE "idUsuario" = $2 RETURNING *';
        const result = await pool.query(query, [bitStatus, idUsuario]);
        return result.rows[0];
    }
}

module.exports = new UserRepository();