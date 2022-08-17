
//CALL DATA MENU
$(document).ready(function () {
    bienvenidoUser();
    cargarCategoriasHtml();
    cargarProductosHtml();

});
function accionesOnLoad() {
    unauth();
    scrollIntoChanges();
}
async function cargarCategoriasHtml() {
    const request = await fetch('/categorias', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
    });
    const categorias = await request.json();

    let listaDeCategorias = '';




    for (let categoria of categorias) {

        /*ELementos del modal*/
        let inputNombre = '<div  class="input-group mb-3"><span class="input-group-text">Nombre de la categoría </span><input type="text" id="txtCatNombre' + categoria.id + '" value= "' + categoria.nombre + '" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"></div>'
        let inputOrden = '<div  class="input-group mb-3"><span class="input-group-text">Orden nº</span><input type="text" id="txtCatOrden' + categoria.id + '" value= "' + categoria.orden + '" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"></div>'
        let checkVisibilidad = '<div  class="input-group mb-3 visibility-class"><span class="input-group-text">Visibilidad </span><input type="checkbox" id="visibilidadCat' + categoria.id + '" value="true" checked="true" class="visibility-input" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"></div>'

        let btnCerrar = '<div class="btns-modal"><div class="boton-cerrar" id="btn-cerrar" onclick="cerrarCatModal()"><i class="bx bx-x"></i></div>'
        let btnGuardar = '<div onclick ="editarCategoria(' + categoria.id + ')" class="boton-guardar-producto" id="btn-guardar"><i class="bx bx-save"></i></div></div>'
        let modalHtml = '<div class="cat-modal-container" id="cat-modal-container(' + categoria.id + ')" ><div class="cat-modal-editar">' +
            inputNombre + '' +
            inputOrden + '' +
            checkVisibilidad + '' +
            btnCerrar + '' +
            btnGuardar + '</div></div>'

        /*Elementos del modal BTN-NUEVO  */

        let inputNombreNuevo = '<div  class="input-group mb-3"><span class="input-group-text">Nombre del producto </span><input type="text" id="txtNombreNuevo' + categoria.id + '" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"></div>'
        let inputDescripcionNuevo = '<div class="input-group mb-3"><span class="input-group-text">Descripción </span><textarea id="txtDescripcionNuevo' + categoria.id + '" class="form-control" aria-label="With textarea"></textarea></div>'
        let inputPrecioNuevo = '<div class="input-group mb-3"><span class="input-group-text">Precio </span><input type="number" id = "txtPrecioNuevo' + categoria.id + '" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"></div>'
        let btnCerrarNuevo = '<div class="btns-modal"><div class="boton-cerrar" id="btn-cerrar" onclick="cerrarModal()"><i class="bx bx-x"></i></div>'
        let btnGuardarNuevo = '<div onclick="nuevoProducto(' + categoria.id + ')" class="boton-guardar-producto" id="btn-guardar"><i class="bx bx-save"></i></div></div>'

        let modalBtnNuevo = '<div class="modal-container" id="producto-nuevo-modal-container' + categoria.id + '"><div class="modal-editar">' + inputNombreNuevo + '' + inputDescripcionNuevo + '' + inputPrecioNuevo + '' + btnCerrarNuevo + '' + btnGuardarNuevo + '</div></div>'
        let botonNuevo = '<div class="nuevo-producto" ><a onclick = "abrirProductoNuevoModal(' + categoria.id + ')" class="btn-nuevo-producto"><i class="gg-add" value="Nuevo producto"></i></a></div>' + modalBtnNuevo + ''


        /*CATEGORIA HTM*/
        let categoriaHtml = '<div class="col-xs-12 col-sm-6"><div class="menu-section" id="(' + categoria.id + ')" value="(' + categoria.id + ')"><h2 class="menu-section-title">(' + categoria.orden + ') ' + categoria.nombre + '<div class="menu-actions-cat"><a class= "catDelete" onclick ="categoriaDelete(' + categoria.id + ')";"><i class="gg-trash"></i></a><a onclick = "abrirCatModal(' + categoria.id + ')" class="editarCat" ;"><i class="gg-pen"></i></a></div></h2><hr><div id="productoCat' + categoria.id + '" class="menu-item"></div>' + botonNuevo + '</div></div>'

        listaDeCategorias += categoriaHtml + modalHtml;
    }

    /*ELementos del modal NUEVA CATEGORIA*/
    let inputNombre = '<div  class="input-group mb-3"><span class="input-group-text">Nombre de la categoría </span><input type="text" id="txtCatNombreNuevo" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"></div>'
    let btnCerrar = '<div class="btns-modal"><div class="boton-cerrar" id="btn-cerrar" onclick="cerrarCatNuevaModal()"><i class="bx bx-x"></i></div>'
    let btnGuardar = '<div onclick ="crearNuevaCategoria()" class="boton-guardar-producto" id="btn-guardar"><i class="bx bx-save"></i></div></div>'
    let modalNuevaCatHtml = '<div class="cat-modal-container" id="nueva-cat-modal" ><div class="cat-modal-editar">' +
        inputNombre + '' +
        btnCerrar + '' +
        btnGuardar + '</div></div>'



    let btnNuevaCategoria = '<span style="display: flex;justify-content: center;">Nueva Categoria</span><a onclick ="abrirModalCategoriaNueva()" style="display: flex;justify-content: center;"><i class="gg-play-list-add"></i></a>' + modalNuevaCatHtml + ''
    document.querySelector('.col-xs-12 ,col-sm-6').outerHTML = listaDeCategorias + btnNuevaCategoria;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
async function cargarProductosHtml() {
    const request = await fetch('/productos', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
    });
    const productos = await request.json();
    for (let producto of productos) {
        // por cada producto idCategoria
        let productoCat = 'productoCat' + producto.idCategoria;

        // Creo atributos del producto
        let productoHtmlItemName = document.createElement("div");
        productoHtmlItemName.classList.add("menu-item-name");
        let productoNombre = document.createTextNode(producto.nombre);
        productoHtmlItemName.appendChild(productoNombre);

        let productoHtmlItemPrice = document.createElement("div");
        productoHtmlItemPrice.classList.add("menu-item-price");
        let productoPrecio = document.createTextNode('$' + producto.precio);
        productoHtmlItemPrice.appendChild(productoPrecio);

        let productoHtmlItemDescription = document.createElement("div");
        productoHtmlItemDescription.classList.add("menu-item-description");
        let productoDescripcion = document.createTextNode(producto.descripcion);
        productoHtmlItemDescription.appendChild(productoDescripcion);
        //Creo div Acciones
        let productoHtmlMenuActions = document.createElement("div");
        productoHtmlMenuActions.classList.add("menu-actions");
        //Creo boton Eliminar
        let btnDelete = document.createElement("a");
        let btnDeleteId = `btn-delete-id-${producto.id}`;
        btnDelete.id = btnDeleteId;
        btnDelete.addEventListener('click', function () { productoDelete(producto.id, producto.idCategoria) });
        //btnDelete.onclick = function(){productoDelete(producto.id)};
        btnDelete.classList.add("botones-eliminar");
        let iconTrash = document.createElement("i");
        iconTrash.classList.add("gg-trash")
        btnDelete.appendChild(iconTrash);
        productoHtmlMenuActions.appendChild(btnDelete);
        //Creo boton Editar
        let btnEdit = document.createElement("a");
        let btnEditId = "btn-edit-id-" + producto.id;
        btnEdit.id = btnEditId;
        btnEdit.addEventListener('click', function () { abrirModal(producto.id) });
        //btnEdit.onclick=function(){alert(producto.id)};//MODIFICAR FUNCION
        btnEdit.classList.add("botones-abrir");
        btnEdit.style = 'margin: 10px';
        let iconPen = document.createElement("i");
        iconPen.classList.add("gg-pen")
        btnEdit.appendChild(iconPen);
        productoHtmlMenuActions.appendChild(btnEdit);
        //Creo Modal Editar Producto
        let modalContainer = document.createElement("div");
        modalContainer.classList.add("modal-container");
        let modalId = "modal-container-" + producto.id;
        modalContainer.id = modalId;
        //Contenedor modal-editar
        let modalContainerEditar = document.createElement("div");
        modalContainerEditar.classList.add("modal-editar");
        modalContainer.appendChild(modalContainerEditar);
        //Elementos del modal
        //input nombre
        let inputNombre = document.createElement("div");
        inputNombre.classList.add("input-group", "mb-3r");

        let inputNombreSpan = document.createElement("span");
        inputNombreSpan.classList.add("input-group-text");
        let inputNombreSpanTxt = document.createTextNode('Nombre del producto');
        inputNombreSpan.appendChild(inputNombreSpanTxt);
        inputNombre.appendChild(inputNombreSpan);

        let inputNombreInputType = document.createElement("input");
        inputNombreInputType.type = "text";
        let inputNomTypeId = "txtNombre-" + producto.id;
        inputNombreInputType.id = inputNomTypeId;
        inputNombreInputType.classList.add('form-control');
        inputNombreInputType.setAttribute('aria-label', "Sizing example input");
        inputNombreInputType.setAttribute('aria-describedby', "inputGroup-sizing-default");
        inputNombreInputType.setAttribute('value', producto.nombre);
        inputNombre.appendChild(inputNombreInputType);

        modalContainerEditar.appendChild(inputNombre);
        //input descripcion
        let inputDescripcion = document.createElement("div");
        inputDescripcion.classList.add("input-group", "mb-3r");

        let inputDescripcionSpan = document.createElement("span");
        inputDescripcionSpan.classList.add("input-group-text");
        let inputDescripcionSpanTxt = document.createTextNode('Descripción');
        inputDescripcionSpan.appendChild(inputDescripcionSpanTxt);
        inputDescripcion.appendChild(inputDescripcionSpan);

        let inputDescripcionInputType = document.createElement("textarea");
        inputDescripcionInputType.setAttribute('type', "text");
        let inputDesTypeId = "txtDescripcion-" + producto.id;
        inputDescripcionInputType.id = inputDesTypeId;
        inputDescripcionInputType.classList.add('form-control');
        inputDescripcionInputType.setAttribute('aria-label', "With textarea");
        let txtDefault = document.createTextNode(producto.descripcion);
        inputDescripcionInputType.appendChild(txtDefault);
        //inputDescripcionInputType.setAttribute('value', producto.descripcion);   NO FUNCIONA
        inputDescripcion.appendChild(inputDescripcionInputType);

        modalContainerEditar.appendChild(inputDescripcion);
        //input precio
        let inputPrecio = document.createElement("div");
        inputPrecio.classList.add("input-group", "mb-3r");

        let inputPrecioSpan = document.createElement("span");
        inputPrecioSpan.classList.add("input-group-text");
        let inputPrecioSpanTxt = document.createTextNode('Precio');
        inputPrecioSpan.appendChild(inputPrecioSpanTxt);
        inputPrecio.appendChild(inputPrecioSpan);

        let inputPrecioInputType = document.createElement("input");
        inputPrecioInputType.type = "number";
        let inputPreTypeId = "txtPrecio-" + producto.id;
        inputPrecioInputType.id = inputPreTypeId;
        inputPrecioInputType.classList.add('form-control');
        inputPrecioInputType.setAttribute('aria-label', "Sizing example input");
        inputPrecioInputType.setAttribute('aria-describedby', "inputGroup-sizing-default");
        inputPrecioInputType.setAttribute('value', producto.precio);
        inputPrecio.appendChild(inputPrecioInputType);

        modalContainerEditar.appendChild(inputPrecio);

        //Botones Cerrar/Guardar
        let btnContainer = document.createElement("div");
        btnContainer.classList.add("btns-modal");

        //Cerrar
        let btnContainerClose = document.createElement("div");
        btnContainerClose.classList.add("boton-cerrar");
        btnContainerClose.id = "btn-cerrar";
        btnContainerClose.addEventListener('click', function () { cerrarModal() });
        btnContainer.appendChild(btnContainerClose);

        let btnIconClose = document.createElement("i");
        btnIconClose.classList.add("bx", "bx-x");
        btnContainerClose.appendChild(btnIconClose);

        //Guardar
        let btnContainerSave = document.createElement("div");
        btnContainerSave.classList.add("boton-guardar-producto");
        btnContainerSave.id = "btn-guardar";
        btnContainerSave.addEventListener('click', function () { editarProducto(producto.id, producto.idCategoria) });
        btnContainer.appendChild(btnContainerSave);

        let btnIconSave = document.createElement("i");
        btnIconSave.classList.add("bx", "bx-save");
        btnContainerSave.appendChild(btnIconSave);

        modalContainerEditar.appendChild(btnContainer);

        let nodoPadre = document.getElementById(productoCat);

        nodoPadre.appendChild(productoHtmlItemName);
        nodoPadre.appendChild(productoHtmlItemPrice);
        nodoPadre.appendChild(productoHtmlItemDescription);
        nodoPadre.appendChild(productoHtmlMenuActions);
        nodoPadre.appendChild(modalContainer);
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Acciones del MODAL EDITAR CATEGORIA
function abrirCatModal(id) {
    let modales = document.getElementsByClassName('cat-modal-container');
    let modal = modales.namedItem("cat-modal-container(" + id + ")"); //Busco en la coleccion HTML el item con id..
    modal.classList.add('show-modal');
}
function cerrarCatModal() {
    let modales = document.getElementsByClassName('cat-modal-container');
    for (let modal of modales) {
        modal.classList.remove('show-modal');
    }
}
async function editarCategoria(id) {
    if (!confirm('Confirmar modificación')) {
        return;
    }
    let datos = {};
    let idDelHtmlNombre = "txtCatNombre" + id;
    let idDelHtmlOrden = "txtCatOrden" + id;
    let idDelHtmlVisibilidad = "visibilidadCat" + id;

    datos.nombre = document.getElementById(idDelHtmlNombre).value;
    datos.orden = document.getElementById(idDelHtmlOrden).value;
    if (document.getElementById(idDelHtmlVisibilidad).checked) {
        datos.visibilidad = document.getElementById(idDelHtmlVisibilidad).value;
    } else {
        datos.visibilidad = "false";
    }

    const request = await fetch('/categorias/' + id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
        body: JSON.stringify(datos)

    });
    const categoria = await request.json();
    localStorage.idCategoriaNueva = categoria.id;
    location.reload();
}
async function categoriaDelete(id) {

    if (!confirm('¿Eliminar la categoría? Se eliminarán todos los productos y no se podrán recuperar.')) {
        return;
    }
    const request = await fetch('/categorias/' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
    });
    location.reload();
}

//Acciones del MODAL NUEVA CATEGORIA
function abrirModalCategoriaNueva() {
    let modal = document.getElementById('nueva-cat-modal');
    modal.classList.add('show-modal');
}
function cerrarCatNuevaModal() {
    let modal = document.getElementById('nueva-cat-modal');
    modal.classList.remove('show-modal');
}
async function crearNuevaCategoria() {
    let datos = {};
    datos.nombre = document.getElementById('txtCatNombreNuevo').value;

    const request = await fetch('/categorias', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
        body: JSON.stringify(datos)
    });
    const categoria = await request.json();
    localStorage.idCategoriaNueva = categoria.id;
    location.reload();
}

//Acciones del MODAL PRODUCTO
async function productoDelete(id, idCategoria) {

    if (!confirm('¿Eliminar el item? (No se podrá recuperar)')) {
        return;
    }
    const request = await fetch('/productos/' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
    });
    localStorage.idProducto = idCategoria;
    location.reload();
}
function abrirModal(id) {
    let idModal = "modal-container-" + id;
    let modal = document.getElementById(idModal);
    modal.classList.add('show-modal');
}
function cerrarModal() {
    let modales = document.getElementsByClassName('modal-container');
    for (let modal of modales) {
        modal.classList.remove('show-modal');
    }
}
async function editarProducto(id, idCategoria) {

    if (!confirm('Confirmar modificación')) {
        return;
    }
    let datos = {};

    let txtNombre = "txtNombre-" + id;
    let txtDescripcion = "txtDescripcion-" + id;
    let txtPrecio = "txtPrecio-" + id;

    datos.nombre = document.getElementById(txtNombre).value;
    datos.descripcion = document.getElementById(txtDescripcion).value;
    datos.precio = document.getElementById(txtPrecio).value;
    datos.idCategoria = idCategoria;

    const request = await fetch('/productos/' + id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
        body: JSON.stringify(datos)

    });

    location.reload();
}

//Acciones del MODAL NUEVO PRODUCTO
function abrirProductoNuevoModal(id) {
    let idModal = "producto-nuevo-modal-container" + id;
    let modal = document.getElementById(idModal);
    modal.classList.add('show-modal');
}
async function nuevoProducto(id) {
    let nombreNuevoId = "txtNombreNuevo" + id;
    let descripcionNuevoId = "txtDescripcionNuevo" + id;
    let precioNuevoId = "txtPrecioNuevo" + id;

    let datos = {};
    datos.nombre = document.getElementById(nombreNuevoId).value;
    datos.descripcion = document.getElementById(descripcionNuevoId).value;
    datos.precio = document.getElementById(precioNuevoId).value;
    datos.idCategoria = id;

    const request = await fetch('/productos', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
        body: JSON.stringify(datos)

    });
    const producto = await request.json();
    localStorage.idProducto = producto.idCategoria;
    location.reload();
}

//Comprobar si ingreso con login o no
function unauth() {
    // simulamos tiempo de carga
    setTimeout(function () {
        document.getElementById('cargando').style.display = 'none';
    }, 2000);
    if (localStorage.token == null || localStorage.user == null) {
        window.location.href = "index.html"
    } else if (localStorage.token == "" || localStorage.user == "") {
        window.location.href = "index.html"
    }

}

//Mensaje de bienvenida personalizado
function bienvenidoUser() {
    let welcomeHtml = document.getElementById('welcome-user')
    welcomeHtml.innerHTML = "Bienvenido " + localStorage.user + "!";
}

//Scroll automatico al elemento creado/eliminado
function scrollIntoChanges() {

    setTimeout(function () {
        let id = localStorage.idCategoriaNueva;
        let element = document.getElementById("(" + id + ")");
        if (element) {
            element.scrollIntoView({ block: "start", behavior: "smooth" });
            localStorage.removeItem('idCategoriaNueva');
        }
    }, 2100);
    setTimeout(function () {
        let id = localStorage.idProducto;
        let element = document.getElementById("productoCat" + id);
        if (element) {
            element.scrollIntoView({ block: "start", behavior: "smooth" });
            localStorage.removeItem('idProducto');
        }
    }, 2200);
}

