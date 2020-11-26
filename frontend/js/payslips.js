import * as pdfMake from './dist/pdfmake.js';
import * as pdfFonts from './dist/vfs_fonts.js';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

function load(){
    alert('Hello! I am an alert box!!');
}

function generatePaySlipPDF(container, par1, par2) {
    console.log('Test'+ par1);
    //alert('Hello! I am an alert box!! ' + pdfMake);
    var docDefinition = { content:['hello prcId: ' + par1 + " frmId:" + par2 ]};
    pdfMake.createPdf(docDefinition).download('file.pdf', function() { alert('your pdf is done'); });
}


window.generatePaySlipPDF = generatePaySlipPDF;