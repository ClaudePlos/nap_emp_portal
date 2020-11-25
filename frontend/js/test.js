import * as pdfMake from './dist/pdfmake.js';
import * as pdfFonts from './dist/vfs_fonts.js';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

function load(){
    alert('Hello! I am an alert box!!');
}

function createHandsontable(container, language, data) {
    console.log('Test'+ language);
    //alert('Hello! I am an alert box!! ' + pdfMake);
    var docDefinition = { content:['hello world']};
    pdfMake.createPdf(docDefinition).download('file.pdf', function() { alert('your pdf is done'); });
}


window.createHandsontable = createHandsontable;