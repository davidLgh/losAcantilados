
//CALL DATA MENU
$(document).ready(function () {

  cargarCategoriasHtml();
  cargarProductosHtml();

});

async function cargarCategoriasHtml() {
  const request = await fetch('/categorias/home', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });
  const categorias = await request.json();

  let listaDeCategorias = '';
  for (let categoria of categorias) {
    /*CATEGORIA HTM*/
    //si visibilidad es true mostrar en index sino no
    let categoriaHtml = '<div class="col-xs-12 col-sm-6 clase-cat" id="completeCat' + categoria.id + '"><div class="menu-section" id="(' + categoria.id + ')" value="(' + categoria.id + ')"><h2 class="menu-section-title">' + categoria.nombre + '</h2> <hr><div id="productoCat' + categoria.id + '" class="menu-item"></div></div></div>'

    if (categoria.visibilidad != "false") {
      listaDeCategorias += categoriaHtml;
    }

  }
  document.querySelector('.container .clase-cat').outerHTML = listaDeCategorias;
}
async function cargarProductosHtml() {
  const request = await fetch('/productos/home', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'

    },
  });
  const productos = await request.json();

  for (let producto of productos) {
    //comprobar que el div de la categoria existe en el DOM
    let catTarget = "(" + producto.idCategoria + ")";
    if (document.body.contains(document.getElementById(catTarget))) {
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

      let nodoPadre = document.getElementById(productoCat);
      nodoPadre.appendChild(productoHtmlItemName);
      nodoPadre.appendChild(productoHtmlItemPrice);
      nodoPadre.appendChild(productoHtmlItemDescription);

    }

  }
}
