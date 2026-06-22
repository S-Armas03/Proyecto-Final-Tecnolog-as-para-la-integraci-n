const service = require("../services/auth.service");

exports.test = async (req, res) => {
    try {
        const result = await service.testApi();
        res.status(200).json({result});
    } catch (err) {
        res.status(500).json({ error: "Error el ejecutar el servicio" });
        console.log(err.message); //solo para depurar
    }
};
exports.login = async (req, res) => {
    try {
        const result = await service.login(req.body);
        res.status(200).json( result );
    } catch (err) {
        res.status(500).json({ error: "Error al ejecutar el servicio" });
        console.log(err.message); //solo para depurar
    }
};