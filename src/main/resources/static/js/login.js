
//CALL DATA MENU
$(document).ready(function () {

    agregarEventos();

});

async function iniciarSesion() {

    let datos = {};
    datos.username = document.getElementById('inputUsuario').value;
    datos.password = document.getElementById('inputPassword').value;


    const request = await fetch('auth/singin', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const response = await request.text();
    try {
        let token = JSON.parse(response)
        token = "Bearer " + token.jwt
        localStorage.token = token;
        localStorage.user = datos.username;
        window.location.href="admin.html"
    } catch (error) {
        alert( "Los datos ingresado no son válidos. Verificá los datos ingresados.")    
    }
}




function agregarEventos() {
    let btnAdmin = document.getElementById('btn-admin');
    btnAdmin.addEventListener('click', function () { abrirLoginModal() });

    let btnCerrar = document.getElementById('btn-cerrar');
    btnCerrar.addEventListener('click', function () { cerrarLoginModal() });

    let btnIniciarSesion = document.getElementById('btn-acceso');
    btnIniciarSesion.addEventListener('click',function(){iniciarSesion()});
}
function cerrarLoginModal() {
    let modal = document.getElementById('modal-login');
    modal.classList.remove('show-modal');
}

function abrirLoginModal() {
    let modal = document.getElementById('modal-login');
    modal.classList.add('show-modal');
}