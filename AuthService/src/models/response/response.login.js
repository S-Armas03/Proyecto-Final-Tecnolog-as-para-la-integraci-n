class ResponseLogin {
    static success(user,token) {
        return {
            error: false,
            message: null,
            user: user,
            token: token,
        };
    }
    static fail(message) {
        return {
            error: true,
            message: message,
            user: null,
            token: null,
        };
    }
}
module.exports = ResponseLogin;