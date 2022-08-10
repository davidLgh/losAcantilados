async function generatePDF(){
    //convert HTML + CSS --> PNG (html2canvas)
    //add PNG to PDF (jsPDF)
    //download PDF
    document.getElementById("downloadButton").innerHTML = "Espere mientras se descarga el archivo..";


    var downloading = window.document.getElementsByTagName("body")[0];
    var doc = new jsPDF('p', 'pt','a4');

    await html2canvas(downloading,{
        allowTaint: true,
        useCORS: true,
        foreignObjectRendering: true,
        with: 800
    }).then((canvas) => {
        //Cnvas (convert to PNG) NOMBRE / TIPO / CORD X (margen horiz) / CORD Y (margen verti) / ANCHO / ALTO
        doc.addImage(canvas.toDataURL("image/png"), 'PNG');
    })

    doc.save("MenuLosAcantilados.pdf");



    document.getElementById("downloadButton").innerHTML = "Descargar";
}
/*
async function generatePDF(){
    //convert HTML + CSS --> PNG (html2canvas)
    //add PNG to PDF (jsPDF)
    //download PDF
    document.getElementById("downloadButton").innerHTML = "Espere mientras se descarga el archivo..";


    var downloading = window.document.getElementsByTagName("body")[0];
    var doc = new jsPDF('p', 'pt','a4');

    await html2canvas(downloading,{
        allowTaint: true,
        useCORS: true,
        with: 800
    }).then((canvas) => {
        //Cnvas (convert to PNG) NOMBRE / TIPO / CORD X (margen horiz) / CORD Y (margen verti) / ANCHO / ALTO
        doc.addImage(canvas.toDataURL("image/png"), 'PNG', 1 , 1 , 800 , 800 );
    })

    doc.save("MenuLosAcantilados.pdf");



    document.getElementById("downloadButton").innerHTML = "Descargar";
}
*/