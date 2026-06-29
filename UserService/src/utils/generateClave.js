const generateClave = async (nombre, apellidoPaterno) => {
    const letras = (nombre.charAt(0) + apellidoPaterno.charAt(0)).toUpperCase();
    const numeros = Math.floor(Math.random() * 9000) + 1000; // Genera un número aleatorio de 4 dígitos
    return `${letras}${numeros}`;
};

module.exports = generateClave;
