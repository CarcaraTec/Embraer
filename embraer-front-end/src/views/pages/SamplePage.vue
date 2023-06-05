<template>
    <div class="container">
      <div class="search-container">
        <label for="chassi-input">Chassi:</label>
        <div class="input-group">
          <input type="text" id="chassi-input" v-model="chassi" class="form-control" />
          <button @click="pesquisar" class="btn btn-primary rounded-pill">Pesquisar</button>

        </div>
        <button @click="gerarPDF" class="btn btn-primary rounded-pill">Gerar PDF</button>
      </div>
      <div class="table-container">
        <div class="scrollable-table">
          <template v-if="carregando">
            <p>Carregando itens...</p>
          </template>
          <template v-else>
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
          </template>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import { ref } from 'vue';
  import pdfMake from 'pdfmake/build/pdfmake';
  import pdfFonts from 'pdfmake/build/vfs_fonts';
  
  pdfMake.vfs = pdfFonts.pdfMake.vfs;
  
  export default {
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
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.navbar {
  background-color: #0a008f;
  padding: 5px;
  width: 80%;
  margin: 0 auto;
}

.navbar-list {
  display: flex;
  list-style-type: none;
  margin: 0;
  padding: 0;
}

.navbar-item {
  margin-right: 10px;
  cursor: pointer;
  color: #ffffff;
}

.search-container {
  margin-top: 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 80%;
  margin: 10px auto;
}

.input-group {
  display: flex;
  margin-left: 10px;
}

.table-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: calc(100vh - 200px); /* Ajuste a altura da tabela como desejado */
}

.scrollable-table {
  max-height: 600px;
  overflow-y: auto;
}

.table {
  width: 100%;
}

.btn-primary {
  margin-left: 10px;
}

</style>
