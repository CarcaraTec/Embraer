<template>
    <div>
      <button class="btn btn-primary" @click="openModal">Importar arquivo</button>
  
      <div class="modal" v-if="isModalOpen">
        <div class="modal-content">
          <h5 class="modal-title">Importar arquivo</h5>
          <div class="custom-file">
            <input type="file" class="custom-file-input" id="csvFile" ref="fileInput" accept=".csv, .xlsx" @change="handleFileUpload" />
            <label class="custom-file-label" for="csvFile">{{ fileName }}</label>
            <button class="btn btn-secondary" @click="closeModal">Fechar</button>
          </div>
          <div class="button-container">
            <button class="btn btn-primary btn-import" @click="importCSV">Importar</button>
          </div>
          <div v-if="importSuccessMessage" class="mt-4 success-message">{{ importSuccessMessage }}</div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import Papa from 'papaparse';
  
  export default {
    data() {
      return {
        isModalOpen: false,
        fileName: 'Selecione um arquivo',
        csvData: null,
        importSuccessMessage: '',
      };
    },
    methods: {
      openModal() {
        this.isModalOpen = true;
      },
      closeModal() {
        this.isModalOpen = false;
      },
      handleFileUpload(event) {
        const file = event.target.files[0];
        this.fileName = file.name;
  
        const reader = new FileReader();
        reader.onload = (e) => {
          const csvData = e.target.result;
          this.csvData = csvData;
        };
        reader.readAsText(file);
      },
      importCSV() {
        Papa.parse(this.csvData, {
          header: true,
          complete: (results) => {
            axios
              .post('http://localhost:8081/chassiBoletim/loadData', { data: results.data })
              .then((response) => {
                console.log(response.data); // resposta do backend
                // Verificar se os dados foram salvos no banco
                if (response.data.success) {
                  this.importSuccessMessage = 'Dados salvos no banco de dados com sucesso!';
                } else {
                  this.importSuccessMessage = 'Falha ao salvar os dados no banco de dados.';
                }
              })
              .catch((error) => {
                console.error(error);
                this.importSuccessMessage = 'Erro ao enviar os dados para o servidor.';
              });
          }
        });
      }
    }
  };
  </script>
  
  <style>
  .modal {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0)
  }
    .modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
}

.modal-content {
  background-color: #fff;
  border-radius: 4px;
  padding: 20px;
  max-width: 500px;
}

.modal-title {
  font-size: 18px;
  margin-top: 0;
  margin-bottom: 20px;
}

.custom-file-label {
  display: inline-block;
  padding: 10px 15px;
  cursor: pointer;
  border: 2px solid #2980b9;
  border-radius: 4px;
  background-color: #2980b9;
  color: #fff;
  transition: background-color 0.3s;
}

.custom-file-label:hover {
  background-color: #3498db;
}

.custom-file-input {
  display: none;
}

.button-container {
  margin-top: 20px;
}

.btn {
  display: inline-block;
  padding: 10px 20px;
  margin: 5px;
  border: none;
  border-radius: 4px;
  background-color: #3498db;
  color: #fff;
}

.btn:hover {
  background-color: #2980b9;
}

.table {
  width: 100%;
  margin-top: 20px;
  border: 1px solid #ddd;
  border-collapse: collapse;
}

.table th,
.table td {
  padding: 8px;
  border: 1px solid #ddd;
}

.table th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.mt-4 {
  margin-top: 16px;
}
</style>