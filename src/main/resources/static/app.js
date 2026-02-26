let idUsuarioGlobal = null;

function login() {

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch("http://localhost:8087/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, password })
    })
        .then(res => res.json())
        .then(data => {

            idUsuarioGlobal = data.idUsuario;
            localStorage.setItem("idUsuario", data.idUsuario);

            if (data.estado === "CAMBIO_OBLIGATORIO") {
                window.location.href = "cambiar-password.html";
            } else if (data.estado === "LOGIN_OK") {
                window.location.href = "panel-cliente.html";
            } else {
                document.getElementById("mensaje").innerText = "Error de login";
            }
        })
        .catch(err => {
            document.getElementById("mensaje").innerText = "Error de conexión";
        });
}

function cambiarCredenciales() {

    const nuevoUsername = document.getElementById("nuevoUsername").value.trim();
    const nuevaPassword = document.getElementById("nuevaPassword").value.trim();
    const mensaje = document.getElementById("mensaje");
    const idUsuario = localStorage.getItem("idUsuario");

    mensaje.innerText = "";

    if (!nuevoUsername || !nuevaPassword) {
        mensaje.innerText = "Debe completar todos los campos";
        return;
    }

    if (nuevaPassword.length < 6) {
        mensaje.innerText = "La contraseña debe tener al menos 6 caracteres";
        return;
    }

    fetch("http://localhost:8087/api/auth/cambiar-password", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            idUsuario: idUsuario,
            nuevoUsername: nuevoUsername,
            nuevaPassword: nuevaPassword
        })
    })
        .then(res => {
            if (!res.ok) {
                return res.text().then(text => { throw new Error(text); });
            }
            return res.text();
        })
        .then(data => {
            alert("Credenciales actualizadas correctamente");
            window.location.href = "panel-cliente.html";
        })
        .catch(error => {
            mensaje.innerText = error.message;
        });
}
function irMisSolicitudes() {
    alert("Aquí irán las solicitudes del cliente");
}

function irCrearSolicitud() {
    alert("Aquí irá el formulario para crear solicitud");
}

function irConfiguraciones() {
    alert("Configuraciones del usuario");
}