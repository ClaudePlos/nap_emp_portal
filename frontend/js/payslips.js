import * as pdfMake from './dist/pdfmake.js';
import * as pdfFonts from './dist/vfs_fonts.js';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

let allTableData = [];


function generatePaySlipPDF(container, par1, par2) {
    console.log('Test'+ par1);


    let doc = (body) => { // w przyszlosci dodac bottom
        const baseDoc = {
            pageSize: 'A4',
            pageOrientation: 'landscape',
            footer: function (currentPage, pageCount) {
                return {
                    text: 'Strona ' + currentPage.toString() + ' z ' + pageCount,
                    alignment: 'center'
                }
            },
            // styles: {
            //     titleHeading: {
            //         fontSize: 16
            //     },
            //     payLips: {
            //         fontSize: 8
            //     },
            // },
            content: [
                {
                    columns: []
                },
            ],

        };

        const docDefinition = doc(allTableData);
        //var docDefinition = { content:['hello prcId: ' + par1 + " frmId:" + par2 ]};
        pdfMake.createPdf(docDefinition).download('file.pdf', function() { alert('your pdf is done'); });

    };
}


function test(){
    alert('Hello! I am an alert box!!');
}


window.generatePaySlipPDF = generatePaySlipPDF;