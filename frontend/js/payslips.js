import * as pdfMake from './dist/pdfmake.js';
import * as pdfFonts from './dist/vfs_fonts.js';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

let allTableData = [];
let sizeCell = 28;

function generatePaySlipPDF(container, naglowekData, wartKol, zaOkres, imieNazwisko, prcNumber, frmNip, frmNazwa, pesel, etat, joName) {
    console.log(naglowekData);
    console.log(JSON.parse(wartKol));
    console.log("etat " + etat);
    console.log("prcNumber " + prcNumber);
    console.log("pesel " + pesel);
    let varKol = JSON.parse(wartKol);
    let nagDat = JSON.parse(naglowekData);
    let nrPesel = JSON.parse(pesel);
    console.log("1");

    var kol1 = "", kol2 = "", kol3 = "", kol4 = "", kol5 = "", kol6 = "", kol7 = "", kol8 = "", kol8Plus = "", kolB = "", kolN = "", kolP = "", kolF = "", kolSZ = "";
    var kol1T = [], kol2T = [], kol3T = [], kol4T = [], kol5T = [], kol6T = [], kolFT = [];
    var sumK1 = 0, sumK2 = 0, sumK3 = 0, sumK4 = 0, sumK5 = 0, sumK6 = 0, sumK7 = 0;
    var sumSkladekP = 0;
    var sumCalkoPracownik123, sumCalkoPracownik45;
    for (var i = 0; i < varKol.length; i++) {
        // if(varKol[i].kolumna == "KOL1" || varKol[i].kolumna == "KOL2"){
        //     kol1 += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
        //     sumK1 += varKol[i].skWartosc;
        // }
        //console.log("1a");
        if(varKol[i].kolumna === "KOL_1"){
            let sizeTextSpace = sizeCell - (varKol[i].dskSkrot.length + varKol[i].skWartosc.toFixed(2).length);
            let textSpace = "";
            for (var j=0; j<sizeTextSpace;j++){
                textSpace += " ";
            }
            kol1 += varKol[i].dskSkrot + textSpace + varKol[i].skWartosc.toFixed(2) + "\n";
            kol1T.push([{text: varKol[i].dskSkrot , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  varKol[i].skWartosc.toFixed(2), fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
            sumK1 += varKol[i].skWartosc;
            if (varKol[i].dskSkrot.includes("Płaca zasad")){
                kol1T.push([{text: " " , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  "", fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
            }
        }
        //console.log("1b");
        if(varKol[i].kolumna === "KOL_2"){
            let sizeTextSpace = sizeCell - (varKol[i].dskSkrot.length + varKol[i].skWartosc.toFixed(2).length);
            let textSpace = "";
            for (var j=0; j<sizeTextSpace;j++){
                textSpace += " ";
            }
            kol2 += varKol[i].dskSkrot + textSpace + varKol[i].skWartosc.toFixed(2) + "\n";
            kol2T.push([{text: varKol[i].dskSkrot , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  varKol[i].skWartosc.toFixed(2), fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
            sumK2 += varKol[i].skWartosc;
        }
        //console.log("1c");
        if(varKol[i].kolumna === "KOL_3"){
            let textSpace = "";

            if (varKol[i].dskSkrot === "Zal.podatkow"){
                kol3 += "\n";
                kol3T.push([{text: " " , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  "", fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
            }

            if ( varKol[i].dskSkrot && varKol[i].skWartosc) {
                let sizeTextSpace = sizeCell - (varKol[i].dskSkrot.length + varKol[i].skWartosc.toFixed(2).length);
                for (var j=0; j<sizeTextSpace;j++){
                    textSpace += " ";
                }
            }

            kol3 += varKol[i].dskSkrot + textSpace + varKol[i].skWartosc.toFixed(2) + "\n";

            var lBold = false;
            if (varKol[i].dskSkrot === "SumaSkładekP"){
                lBold = true;
            }

            kol3T.push([{text: varKol[i].dskSkrot , fontSize: 8, alignment: 'left', bold: lBold, border: [false, false, false, false]}, {text:  varKol[i].skWartosc.toFixed(2), fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);

            if (varKol[i].dskSkrot === "Emerytalna P"){
                sumSkladekP += varKol[i].skWartosc;
            }
            if (varKol[i].dskSkrot === "Rentowa P"){
                sumSkladekP += varKol[i].skWartosc;
            }
            if (varKol[i].dskSkrot === "Chorobowa P"){
                sumSkladekP += varKol[i].skWartosc;
                kol3 += "Suma skladek P  " + sumSkladekP.toFixed(2) + "\n\n";
                //kol3T.push([{text: "Suma skladekP"  , fontSize: 8, alignment: 'left', bold: false, border: [false, false, false, false]}, {text:  varKol[i].skWartosc.toFixed(2), fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
                //kol3T.push([{text: " " , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  "", fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
            }
            sumK3 += varKol[i].skWartosc.toFixed(2);


            if (varKol[i].dskSkrot === "SumaSkładekP"){
                kol3 += "\n";
                kol3T.push([{text: " " , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  "", fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
            }


        }
        //console.log("1d");
        if(varKol[i].kolumna === "KOL_4"){
            let sizeTextSpace = sizeCell - (varKol[i].dskSkrot.length + varKol[i].skWartosc.toFixed(2).length);
            let textSpace = "";
            //console.log( varKol[i].dskSkrot + " " + sizeTextSpace);
            if (sizeTextSpace === 16) sizeTextSpace += 9;
            for (var j=0; j<sizeTextSpace;j++){
                textSpace += " ";
            }
            kol4 += varKol[i].dskSkrot + textSpace + varKol[i].skWartosc.toFixed(2) + "\n";
            kol4T.push([{text: varKol[i].dskSkrot , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  varKol[i].skWartosc.toFixed(2), fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
            sumK4 += varKol[i].skWartosc;
        }
        //console.log("1e");
        if(varKol[i].kolumna === "KOL_5"){
            let sizeTextSpace = sizeCell - (varKol[i].dskSkrot.length + varKol[i].skWartosc.toFixed(2).length);
            let textSpace = "";
            for (var j=0; j<sizeTextSpace;j++){
                textSpace += " ";
            }
            kol5 += varKol[i].dskSkrot + textSpace+ varKol[i].skWartosc.toFixed(2) + "\n";
            kol5T.push([{text: varKol[i].dskSkrot , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  varKol[i].skWartosc.toFixed(2), fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
            sumK5 += varKol[i].skWartosc;
        }


        //console.log("1f");
        if(varKol[i].kolumna === "KOL_6") {
            let sizeTextSpace = sizeCell - (varKol[i].dskSkrot.length + varKol[i].skWartosc.toFixed(2).length);
            let textSpace = "";
            for (var j=0; j<sizeTextSpace;j++){
                textSpace += " ";
            }
            kol6 += varKol[i].dskSkrot + textSpace + varKol[i].skWartosc.toFixed(2) + "\n";
            kol6T.push([{text: varKol[i].dskSkrot , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  varKol[i].skWartosc.toFixed(2), fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
            sumK6 += varKol[i].skWartosc;
        }

        if(varKol[i].kolumna === "KOL_F" && payslipsData.angaz === "F") { // tylko dla fizycznych
            kolF += varKol[i].dskSkrot + "  " + varKol[i].skWartosc.toFixed(2) + "\n";
            kol6T.push([{text: varKol[i].dskSkrot , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  varKol[i].skWartosc.toFixed(2), fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
        }


        //console.log("1g");
        if(varKol[i].kolumna === "KOL_B") { //brutto
            kolB += varKol[i].dskSkrot + " " + varKol[i].skWartosc.toFixed(2) + "\n";
        }

        if(varKol[i].kolumna === "KOL_N") { // netto
            kolN += varKol[i].dskSkrot + " " + varKol[i].skWartosc.toFixed(2) + "\n";
        }

        if(varKol[i].kolumna === "KOL_P") { // netto
            if ( varKol[i].skWartosc !== 0 ){
                kolP += varKol[i].dskSkrot + " " + varKol[i].skWartosc.toFixed(2) + "\n";
            }
        }

        if(varKol[i].kolumna === "KOL_SZ") { // stawka zaszeregowania
            if (kolSZ.length === 0){
                kolSZ += varKol[i].dskSkrot + " " + varKol[i].skWartosc.toFixed(2) + "\n";
            }
        }
        // if(varKol[i].kolumna == "KOL8"){
        //     var wartosc = varKol[i].opis;
        //     if(wartosc.charAt(0) == ","){
        //         wartosc = "0" + varKol[i].opis;
        //     }
        //
        //     if(i != varKol.length-1){
        //         kol8 += varKol[i].dskSkrot + " " + wartosc + "\n";
        //     }else{
        //         kol8Plus = varKol[i].dskSkrot + " " + wartosc;
        //     }
        //
        // }
    }
    //console.log("2a");
    sumCalkoPracownik123 = sumK1 + sumK2 + sumK3;
    sumCalkoPracownik45 = sumCalkoPracownik123 - (sumK4 + sumK5);

    if (kol1T.length === 0 ){
        kol1T.push([{text: "" , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  "", fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
    }

    if (kol2T.length === 0 ){
        kol2T.push([{text: "" , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  "", fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
    }

    if (kol3T.length === 0 ){
        kol3T.push([{text: "" , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  "", fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
    }

    if (kol4T.length === 0 ){
        kol4T.push([{text: "" , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  "", fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
    }

    if (kol5T.length === 0 ){
        kol5T.push([{text: "" , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  "", fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
    }

    if (kol6T.length === 0 ){
        kol6T.push([{text: "" , fontSize: 8, alignment: 'left', border: [false, false, false, false]}, {text:  "", fontSize: 8, alignment: 'right', border: [false, false, false, false]}]);
    }

    //console.log("2b");


    let tableBody = {
        unbreakable: true,
        table: {
            //headerRows: 1,
            widths: ['*', '*', '*', '*', '*', '*', '*'],
            //style: 'payLips',

            body: [
                [
                    {

                        text: frmNazwa + ", NIP: " + frmNip +  ", Jedno. Organiz.: " + joName +
                            "\nLista płac numer: " + nagDat.LSTnumber + " za m-c " + zaOkres + ", data wypłaty " + nagDat.dataWypl,
                        colSpan: 7,
                        border: [false, true, false, false],
                        fillColor: '#DCDCDC',
                        fontSize: 11
                    },
                    {}, {}, {}, {}, {}, {}
                ],
                //nagłówki kolumn
                [
                    {text: "Dane pracownika", bold: true, fontSize: 10},
                    {text: "Wynagrodzenie", bold: true, fontSize: 10},
                    {text: "Dodatki inne", bold: true, fontSize: 10},
                    {text: "Potrącenia obowiązkowe", bold: true, fontSize: 10},
                    {text: "Potrącenia nieobowiązkowe", bold: true, fontSize: 10},
                    {text: "Inne składki", bold: true, fontSize: 10},
                    {text: "Składniki opisowe", bold: true, fontSize: 10}
                ],
                //zawartość kolumn + kol8
                [
                    {text: [{text: imieNazwisko, bold: true}
                            ,{text:  "\nNumer: "+ prcNumber}
                            ,{text:  "\nPesel "+ pesel}
                            ,{text:  etat !== 'undefined' ? "\nEtat: " + etat : ""}
                            ,{text:  "\n"+ kolSZ}
                        ] , fontSize: 9},

                    {table: { body: kol1T }, layout: {
                            paddingLeft: function(i, node) { return 0; },
                            paddingRight: function(i, node) { return 10; },
                            paddingTop: function(i, node) { return 0; },
                            paddingBottom: function(i, node) { return 0; },
                        }},
                    {table: { body: kol2T }, layout: {
                            paddingLeft: function(i, node) { return 0; },
                            paddingRight: function(i, node) { return 10; },
                            paddingTop: function(i, node) { return 0; },
                            paddingBottom: function(i, node) { return 0; },
                        }},
                    {table: { body: kol3T }, layout: {
                            paddingLeft: function(i, node) { return 0; },
                            paddingRight: function(i, node) { return 10; },
                            paddingTop: function(i, node) { return 0; },
                            paddingBottom: function(i, node) { return 0; },
                        }},
                    {table: { body: kol4T }, layout: {
                            paddingLeft: function(i, node) { return 0; },
                            paddingRight: function(i, node) { return 10; },
                            paddingTop: function(i, node) { return 0; },
                            paddingBottom: function(i, node) { return 0; },
                        }},
                    {table: { body: kol5T }, layout: {
                            paddingLeft: function(i, node) { return 0; },
                            paddingRight: function(i, node) { return 10; },
                            paddingTop: function(i, node) { return 0; },
                            paddingBottom: function(i, node) { return 0; },
                        }},
                    {table: { body: kol6T }, layout: {
                            paddingLeft: function(i, node) { return 0; },
                            paddingRight: function(i, node) { return 10; },
                            paddingTop: function(i, node) { return 0; },
                            paddingBottom: function(i, node) { return 0; },
                        }},
                    // {
                    //     text: [imieNazwisko + "\nNumer " + prcNumber + "\n" + kol8],
                    //     fontSize: 8,
                    //     bold: true
                    // },
                    // {text: kol1, fontSize: 8}, //
                    // {text: kol2, fontSize: 8},
                    // {text: kol3, fontSize: 8},
                    // {text: kol4, fontSize: 8},
                    // {text: kol5, fontSize: 8},
                    // {text: kol6, fontSize: 8}, //
                ],
                //ostatni wiersz
                [
                    {text: ""},
                    {text: " " + kolB, bold: true, alignment: 'left', fontSize: 10}, //brutto
                    {text: ""},
                    {text: " " + kolN, bold: true, alignment: 'left', fontSize: 10}, //netto
                    {text: " " + kolP, bold: true, alignment: 'left', fontSize: 10}, //przelew
                    {text: ""},
                    {text: ""}
                ],
                [
                    {
                        text: "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " +
                            "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " +
                            "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -",
                        colSpan: 7,
                        border: [false, false, false, false]
                    },
                    {}, {}, {}, {}, {}, {}
                ],
            ],
        }
    };

    //console.log("3");

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
        //console.log("4");
        const docDefinition = JSON.parse(JSON.stringify(baseDoc));
        docDefinition.footer = baseDoc.footer;
        docDefinition.content.push(body);

        return docDefinition;
    };

    const docDefinition = doc(tableBody);
    //console.log(docDefinition);
    pdfMake.createPdf(docDefinition).download('file.pdf', function () {
        alert('your pdf is done');
    });



}

function test(){
    alert('Hello! I am an alert box!!');
}


window.generatePaySlipPDF = generatePaySlipPDF;