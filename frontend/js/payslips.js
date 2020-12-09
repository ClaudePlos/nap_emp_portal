import * as pdfMake from './dist/pdfmake.js';
import * as pdfFonts from './dist/vfs_fonts.js';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

let allTableData = [];


function generatePaySlipPDF(container, naglowekData, wartKol, zaOkres, imieNazwisko, prcNumber, frmNip, frmNazwa) {
    console.log(naglowekData);
    console.log(JSON.parse(wartKol));
    console.log(zaOkres);
    let varKol = JSON.parse(wartKol);
    let nagDat = JSON.parse(naglowekData);

    var kol1 = "", kol2 = "", kol3 = "", kol4 = "", kol5 = "", kol6 = "", kol7 = "", kol8 = "", kol8Plus = "",
        kolB = "", kolN = "", kolP = "";
    var sumK1 = 0, sumK2 = 0, sumK3 = 0, sumK4 = 0, sumK5 = 0, sumK6 = 0, sumK7 = 0;
    var sumSkladekP = 0;
    var sumCalkoPracownik123, sumCalkoPracownik45;
    for (var i = 0; i < varKol.length; i++) {
        // if(varKol[i].kolumna == "KOL1" || varKol[i].kolumna == "KOL2"){
        //     kol1 += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
        //     sumK1 += varKol[i].skWartosc;
        // }

        if (varKol[i].kolumna == "KOL_1") {
            kol1 += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
            sumK1 += varKol[i].skWartosc;
        }

        if (varKol[i].kolumna == "KOL_2") {
            kol2 += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
            sumK2 += varKol[i].skWartosc;
        }

        if (varKol[i].kolumna == "KOL_3") {
            kol3 += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
            if (varKol[i].dskSkrot == "Emerytalna P") {
                sumSkladekP += varKol[i].skWartosc;
            }
            if (varKol[i].dskSkrot == "Rentowa P") {
                sumSkladekP += varKol[i].skWartosc;
            }
            if (varKol[i].dskSkrot == "Chorobowa P") {
                sumSkladekP += varKol[i].skWartosc;
                kol3 += "Suma skladek P " + sumSkladekP.toFixed(2) + "\n\n";
            }
            sumK3 += varKol[i].skWartosc;
        }

        if (varKol[i].kolumna == "KOL_4") {
            kol4 += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
            sumK4 += varKol[i].skWartosc;
        }

        if (varKol[i].kolumna == "KOL_5") {
            kol5 += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
            sumK5 += varKol[i].skWartosc;
        }

        if (varKol[i].kolumna == "KOL_6") {
            kol6 += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
            sumK6 += varKol[i].skWartosc;
        }

        if (varKol[i].kolumna == "KOL_B") { //brutto
            kolB += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
        }

        if (varKol[i].kolumna == "KOL_N") { // netto
            console.log(varKol[i].dskSkrot);
            kolN += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
        }

        if (varKol[i].kolumna == "KOL_P") { // netto
            kolP += varKol[i].dskSkrot + " " + varKol[i].skWartosc + "\n";
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
    sumCalkoPracownik123 = sumK1 + sumK2 + sumK3;
    sumCalkoPracownik45 = sumCalkoPracownik123 - (sumK4 + sumK5);

    let tableBody = {
        unbreakable: true,
        table: {
            //headerRows: 1,
            widths: ['*', '*', '*', '*', '*', '*', '*'],
            //style: 'payLips',

            body: [
                [
                    {
                        //", Jedno. Organiz.: " + lSalary.skKodAndDesc +
                        text: frmNazwa + ", NIP: " + frmNip +
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
                //zawartość kolumn
                [
                    {
                        text: [imieNazwisko + "\nNumer " + prcNumber + "\n" + kol8],
                        fontSize: 8,
                        bold: true
                    },
                    {text: kol1, fontSize: 8}, //
                    {text: kol2, fontSize: 8},
                    {text: kol3, fontSize: 8},
                    {text: kol4, fontSize: 8},
                    {text: kol5, fontSize: 8},
                    {text: kol6, fontSize: 8}, //
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

        const docDefinition = JSON.parse(JSON.stringify(baseDoc));
        docDefinition.footer = baseDoc.footer;
        docDefinition.content.push(body);

        return docDefinition;
    };
    // var docDefinition = {
    //     content: [
    //         {
    //             alignment: 'center',
    //             text: 'PPRA',
    //             style: 'header',
    //             fontSize: 23,
    //             bold: true,
    //             margin: [0, 10],
    //         },
    //         {
    //             margin: [0, 0, 0, 10],
    //             layout: {
    //                 fillColor: function (rowIndex, node, columnIndex) {
    //                     return (rowIndex % 2 === 0) ? '#ebebeb' : '#f5f5f5';
    //                 }
    //             },
    //             table: {
    //                 widths: ['100%'],
    //                 heights: [20,10],
    //                 body: [
    //                     [
    //                         {
    //                             text: 'SETOR: ADMINISTRATIVO',
    //                             fontSize: 9,
    //                             bold: true,
    //                         }
    //                     ],
    //                     [
    //                         {
    //                             text: 'FUNÇÃO: DIRETOR DE ENSINO',
    //                             fontSize: 9,
    //                             bold: true
    //                         }
    //                     ],
    //                 ],
    //             }
    //         },
    //         {
    //             style: 'tableExample',
    //             layout: {
    //                 fillColor: function (rowIndex, node, columnIndex) {
    //                     return (rowIndex === 0) ? '#c2dec2' : null;
    //                 }
    //             },
    //             table: {
    //                 widths: ['30%', '10%', '25%', '35%'],
    //                 heights: [10,10,10,10,30,10,25],
    //                 headerRows: 1,
    //                 body: [
    //                     [
    //                         {
    //                             text: 'AGENTE: Não Identificados',
    //                             colSpan: 3,
    //                             bold: true,
    //                             fontSize: 9
    //                         },
    //                         {
    //                         },
    //                         {
    //                         },
    //                         {
    //                             text: 'GRUPO: Grupo 1 - Riscos Físicos',
    //                             fontSize: 9,
    //                             bold: true
    //                         }
    //                     ],
    //                     [
    //                         {
    //                             text: 'Limite de Tolerância:',
    //                             fontSize: 9,
    //                             bold: true
    //                         },
    //                         {
    //                             text: 'Meio de Propagação:',
    //                             colSpan: 3,
    //                             fontSize: 9,
    //                             bold: true
    //                         },
    //                         {
    //                         },
    //                         {
    //                         }
    //                     ],
    //                     [
    //                         {
    //                             text: [
    //                                 'Frequência: ',
    //                                 {
    //                                     text: 'Habitual',
    //                                     bold: false
    //                                 }
    //                             ],
    //                             fontSize: 9,
    //                             bold: true
    //                         },
    //                         {
    //                             text: [
    //                                 'Classificação do Efeito: ',
    //                                 {
    //                                     text: 'Leve',
    //                                     bold: false
    //                                 }
    //                             ],
    //                             colSpan: 3,
    //                             fontSize: 9,
    //                             bold: true
    //                         },
    //                         {
    //                         },
    //                         {
    //                         }
    //                     ],
    //                     [
    //                         {
    //                             text: 'Tempo de Exposição:',
    //                             colSpan: 2,
    //                             fontSize: 9,
    //                             bold: true
    //                         },
    //                         {
    //                         },
    //                         {
    //                             text: 'Medição:',
    //                             colSpan: 2,
    //                             fontSize: 9,
    //                             bold: true
    //                         },
    //                         {
    //                         }
    //                     ],
    //                     [
    //                         {
    //                             text: 'Fonte Geradora:',
    //                             border: [true, true, false, false],
    //                             colSpan: 2,
    //                             fontSize: 9,
    //                             bold: true
    //                         },
    //                         {
    //                         },
    //                         {
    //                             text: 'Téc. Utilizada:',
    //                             border: [false, true, true, false],
    //                             colSpan: 2,
    //                             fontSize: 9,
    //                             bold: true
    //                         },
    //                         {
    //                         }
    //                     ],
    //                     [
    //                         {
    //                             text: 'Conclusão:',
    //                             border: [true, false, true, true],
    //                             colSpan: 4,
    //                             fontSize: 9,
    //                             bold: true
    //                         },
    //                         {
    //                         },
    //                         {
    //                         },
    //                         {
    //                         }
    //                     ],
    //                     [
    //                         {
    //                             text: 'EPIs/EPCs:',
    //                             border: [true, true, false, true],
    //                             colSpan: 3,
    //                             fontSize: 9,
    //                             bold: true
    //                         },
    //                         {
    //                         },
    //                         {
    //                         },
    //                         {
    //                             text: 'CAs:',
    //                             border: [false, true, true, true],
    //                             fontSize: 9,
    //                             bold: true
    //                         }
    //                     ],
    //                 ]
    //             }
    //         }
    //     ]
    // }
    //var docDefinition = { content:['hello prcId: ' + par1 + " frmId:" + par2 ]};
    const docDefinition = doc(tableBody);
    pdfMake.createPdf(docDefinition).download('file.pdf', function () {
        alert('your pdf is done');
    });



}

function test(){
    alert('Hello! I am an alert box!!');
}


window.generatePaySlipPDF = generatePaySlipPDF;