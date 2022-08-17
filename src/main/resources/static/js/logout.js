
//CALL DATA MENU
$(document).ready(function () {

    agregarEventos();

});

async function cerrarSesion() {
    localStorage.clear();
    window.location.href="index.html";
}

async function nuevoUsuario(){
    let datos = {};
    datos.username = document.getElementById('inputUsuario').value;
    datos.password = document.getElementById('inputPassword').value;
    const request = await fetch('auth/singup', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
        body: JSON.stringify(datos)
    });
    const response = await request.text();
    cerrarLoginModal();
}
async function eliminarCuenta(id){
    if (!confirm('¿Eliminar el usuario?')) {
        return;
    }
    const request = await fetch('auth/'+ id,{
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
        body: JSON.stringify(datos)
    });
    const response = await request.text();
    alert ('El Usuario fue eliminado')
    cerrarSesion();
}
function agregarEventos() {
    let btnCerrarSesion = document.getElementById('btn-salir');
    btnCerrarSesion.addEventListener('click',function(){cerrarSesion()});
/*
    let btnAdmin = document.getElementById('btn-nuevo-usuario');
    btnAdmin.addEventListener('click', function () { abrirLoginModal() });

    let btnCerrar = document.getElementById('btn-cerrar');
    btnCerrar.addEventListener('click', function () { cerrarLoginModal() });

    let btnNuevoUsuario = document.getElementById('btn-nuevo');
    btnNuevoUsuario.addEventListener('click',function(){nuevoUsuario()});

    let btnEliminarCuenta = document.getElementById('btn-borrar-cuenta');
    btnEliminarCuenta.addEventListener('click',function(){eliminarCuenta()});
*/    
}
function cerrarLoginModal() {
    let modal = document.getElementById('modal-login');
    modal.classList.remove('show-modal');
}
function abrirLoginModal() {
    let modal = document.getElementById('modal-login');
    modal.classList.add('show-modal');
}