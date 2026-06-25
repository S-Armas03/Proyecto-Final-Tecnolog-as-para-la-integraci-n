const db = require("../config/db");

exports.login = async (user) => {
  const query = `
  SELECT 
  "idUsuario", "nombre", "apellidoPaterno", "apellidoMaterno",
  "username", "password", "idRol", "rol", "estatus",
  "idTipoUsuario", "tipoUsuario"
  FROM "usuarioFullInfo" 
  WHERE username = $1
  `;
  const result = await db.query(query, [user.username]);
  return result.rows[0];
};

