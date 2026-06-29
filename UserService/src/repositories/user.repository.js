const pool = require('../config/db');

class UserRepository {
    async findByUsername(username, email) {
        const query = 'SELECT * FROM users WHERE username = $1 OR email = $2';
        const result = await pool.query(query, [username, email]);
        return result.rows[0];
    }

    async save(userDate){
        const query = 'INSERT INTO users (nombre, apellido_paterno, apellido_materno, username, email, password, role) VALUES ($1, $2, $3, $4, $5, $6, $7) RETURNING *';
        const values = [
            userDate.nombre,
            userDate.apellido_paterno,
            userDate.apellido_materno,
            userDate.username,
            userDate.email,
            userDate.password,
            userDate.role
        ];
        const result = await pool.query(query, values);
        return result.rows[0];
    }

    async subirStatus(idUsuario, status) {
        const query = 'UPDATE users SET status = $1 WHERE id_usuario = $2 RETURNING *';
        const values = [status, idUsuario];
        const result = await pool.query(query, values);
        return result.rows[0];
    }
};

module.exports = new UserRepository();