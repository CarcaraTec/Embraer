<template>
    <div class="container">
      <div class="row mt-5 justify-content-center">
        <div class="col-md-8">
          <div class="card shadow">
            <div class="card-header bg-primary2 text-white">
              <h5 class="font-weight-bold mb-0">CONSULTATION</h5>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="chassi" class="font-weight-bold">Chassi:</label>
                <div class="input-group">
                  <select v-model="chassi" id="select-chassi" class="form-select form-select-sm custom-select-width">
                    <option v-for="option in chassiOptions" :value="option.idChassi" :key="option.idChassi">{{ option.idChassi }}</option>
                  </select>
                  <div class="input-group-append">
                    <button @click="pesquisar" class="btn btn-primary " type="button" style="margin-left: 5px;">Search</button>
                  </div>
                </div>
              </div>
              <div class="d-flex justify-content-between" style="margin-top: 8px;">
                <div class="w-50">
                  
                </div>
                <div class="w-50 d-flex justify-content-end">
                  
                </div>
              </div>
              <div class="d-flex justify-content-end">
                <button @click="gerarPDF" class="btn btn-primary btn-sm " type="button" style="margin-top: 10px">Generate PDF</button>
              </div>
              <div class="table-responsive">
                <table class="table">
              <thead>
                <tr>
                  <th>Item</th>
                  <th>Installed</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in itens" :key="item.idItem">
                  <td>{{ item.nome }}</td>
                  <td>{{ item.status }}</td>
                </tr>
              </tbody>
            </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import { ref } from 'vue';
  import pdfMake from 'pdfmake/build/pdfmake';
  import pdfFonts from 'pdfmake/build/vfs_fonts';
  import 'bootstrap/dist/css/bootstrap.css'
  import 'bootstrap/dist/js/bootstrap.js'
  
  pdfMake.vfs = pdfFonts.pdfMake.vfs;

  export default {
    data() {
      return {
        nomes:[],
          category:[],
          idChassi: null,
          checkedYes: false,
          checkedNo: false,
          idChassi: '',
          chassiOptions: []
      };
    },
    mounted() {
          axios.get('http://localhost:8081/chassi', {
            withCredentials: true,
          })
            .then(response => {
              this.chassiOptions = response.data;
            })
            .catch(error => {
              console.log(error);
            });
        },
    setup() {
      const itens = ref([]);
      const carregando = ref(true);
      const chassi = ref('');
  
      const consultarChassi = async () => {
        try {
          const response = await axios.get(`http://localhost:8081/item/logica?idChassi=${chassi.value}`, {
            withCredentials: true,
          });
          itens.value = response.data;
          carregando.value = false;
          console.log(response.data);
        } catch (error) {
          console.error(error);
          carregando.value = false;
        }
      };
  
      const pesquisar = () => {
        carregando.value = true;
        consultarChassi();
      };
  
      const gerarPDF = () => {
  const docDefinition = {
    header: {
      text: `Chassi: ${chassi.value} - Embraer`,
      style: 'header',
    },
    content: [
      {
        style: 'table',
        table: {
          headerRows: 1,
          widths: ['*', '*'],
          body: [
            [
              { text: 'Item', style: 'tableHeader' },
              { text: 'Installed', style: 'tableHeader' },
            ],
            ...itens.value.map((item) => [
              item.nome,
              {
                text: item.status === '✔' ? 'Aplicable' : 'Not Aplicable',
                style: item.status === '✔' ? 'aplicable' : 'notAplicable',
              },
            ]),
          ],
        },
      },
    ],
    styles: {
      header: {
        fontSize: 18,
        bold: true,
        alignment: 'center',
        margin: [0, 0, 0, 10],
      },
      table: {
        margin: [0, 10, 0, 10],
      },
      tableHeader: {
        bold: true,
        fontSize: 12,
        color: 'white',
        fillColor: '#0a008f',
        alignment: 'center',
      },
      aplicable: {
        alignment: 'center',
        color: 'green',
      },
      notAplicable: {
        alignment: 'center',
        color: 'red',
      },
    },
  };

  pdfMake.createPdf(docDefinition).download('Embraer.pdf');
};


  
      return {
        itens,
        carregando,
        chassi,
        pesquisar,
        gerarPDF,
      };
    },
  };
  </script>
  
  
  <style>
  .bg-primary2 {
    background-color: #0066b1;
  }
 
  .btn-primary {
    color: #fff;
    background-color: #0066b1;
    border-color: #fff;
  }
 
  .logo-container {
    background-color:#0066b1;
    color: #FFF;
  }
 
  .fa-solid {
    margin-right: 20px;
    margin-left: 20px;
  }
 </style>
   