<template>
    <div class="container">
      <div class="row mt-5 justify-content-center">
        <div class="col-md-8">
          <div class="card shadow">
            <div class="card-header bg-primary2 text-white">
              <h5 class="font-weight-bold mb-0">EDITOR</h5>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="chassi" class="font-weight-bold">Chassi:</label>
                <div class="input-group">
                  <select v-model="idChassi" id="select-chassi" class="form-select form-select-sm custom-select-width">
                    <option v-for="option in chassiOptions" :value="option.idChassi" :key="option.idChassi">
                      {{ option.idChassi }}
                    </option>
                  </select>
                  <div class="input-group-append">
                    <button @click="searchLog" class="btn btn-primary" type="button" style="margin-left: 5px;">
                      Search
                    </button>
                    <button class="btn btn-primary" type="button" @click="openModal">
                      Save
                    </button>
                  </div>
                </div>
              </div>
  
              <div class="d-flex justify-content-end" style="margin-top: 10px">
                <div class="form-check form-switch">
                  <input class="form-check-input" type="checkbox" id="checkbox1" @change="incorporatedFunction">
                  <label class="form-check-label" for="checkbox1">Incorporated</label>
                </div>
  
                <div class="form-check form-switch" style="margin-left: 10px">
                  <input class="form-check-input" type="checkbox" id="checkbox2" @change="applicableFunction">
                  <label class="form-check-label" for="checkbox2">Applicable</label>
                </div>
              </div>
  
              <div class="table-responsive">
                <table id="table-1" class="table table-bordered table-striped mt-4">
                  <thead class="bg-primary2 text-white">
                    <tr>
                      <th class="text-center text-white">SB</th>
                      <th class="text-center text-white">Incorporated</th>
                      <th class="text-center text-white">Applicable</th>
                      <th class="text-center text-white">Not Applicable</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(nome, index) in nomes" :key="index">
                      <td class="text-center">{{ nome.idBoletim }}</td>
                      <td align="center">
                        <input type="radio" :checked="nome.status === 'INCORPORATED'" :name="'statusRadio' + nome.idBoletim">
                      </td>
                      <td align="center">
                        <input type="radio" :checked="nome.status === 'APPLICABLE'" :name="'statusRadio' + nome.idBoletim">
                      </td>
                      <td align="center">
                        <input type="radio" :name="'statusRadio' + nome.idBoletim">
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Modal -->
      <div class="modal" ref="exampleModal" tabindex="-1" role="dialog">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Modifications:</h5>
              <button type="button" class="close" @click="closeModal">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <table class="table table-bordered table-striped mt-4">
                <tr>
                  <th class="text-center">SB</th>
                  <th class="text-center">CHANGE</th>
                </tr>
                <tr v-for="(change, index) in modifiedChanges" :key="index">
                  <td class="text-center">{{ change.SB }}</td>
                  <td class="text-center">{{ change.MUDANCA }}</td>
                </tr>
              </table>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" @click="closeModal">Close</button>
              <button type="button" class="btn btn-primary" @click="alter">Save changes</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        nomes: [],
        idChassi: null,
        modifiedChanges: [],
        chassiOptions: [],
        incorporated: false,
        applicable: false,
      };
    },
    mounted() {
      this.fetchChassis();
    },
    methods: {
      fetchChassis() {
        axios
          .get('http://localhost:8081/chassi', {
            withCredentials: true,
          })
          .then((response) => {
            this.chassiOptions = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
      },
      searchLog() {
        this.incorporated = true;
        this.applicable = true;
        this.search();
      },
      search() {
        this.nomes = [];
  
        axios
          .get('http://localhost:8081/chassiBoletim/findBoletim?idChassi=' + this.idChassi, {
            withCredentials: true,
          })
          .then((response) => {
            for (let i = 0; i < response.data.length; i++) {
              if (response.data[i].status === 'INCORPORATED' && this.incorporated) {
                this.nomes.push(response.data[i]);
              }
              if (response.data[i].status === 'APPLICABLE' && this.applicable) {
                this.nomes.push(response.data[i]);
              }
            }
          })
          .catch((error) => {
            console.error(error);
          });
      },
      incorporatedFunction(event) {
        this.incorporated = event.target.checked;
        this.search();
      },
      applicableFunction(event) {
        this.applicable = event.target.checked;
        this.search();
      },
      openModal() {
        this.$refs.exampleModal.style.display = 'block';
      },
      closeModal() {
        this.$refs.exampleModal.style.display = 'none';
      },
      save() {
  const rows = this.$el.querySelector('#table-1').rows;
  this.modifiedChanges = [];

  for (let i = 1; i < rows.length; i++) {
    const row = rows[i];
    const rowData = {
      idChassi: this.nomes[i - 1].idChassi,
      idBoletim: row.cells[0].innerText,
      status1: row.cells[1].querySelector('input').checked,
      status2: row.cells[2].querySelector('input').checked,
      status3: row.cells[3].querySelector('input').checked,
    };

    if (
      this.nomes[i - 1].status === 'APPLICABLE' &&
      !rowData.status2
    ) {
      const change = {
        SB: rowData.idBoletim,
        MUDANCA: 'APPLICABLE ➜ INCORPORATED',
      };
      this.modifiedChanges.push(change);
    }

    if (
      this.nomes[i - 1].status === 'INCORPORATED' &&
      !rowData.status1
    ) {
      const change = {
        SB: rowData.idBoletim,
        MUDANCA: 'INCORPORATED ➜ APPLICABLE',
      };
      this.modifiedChanges.push(change);
    }
  }

  this.openModal();
},
      alter() {
        axios
          .post('http://localhost:8081/chassiBoletim/alter', this.modifiedChanges, {
            withCredentials: true,
          })
          .then((response) => {
            console.log(response.data);
            this.closeModal();
          })
          .catch((error) => {
            console.error(error);
          });
      },
    },
  };
  </script>
  
  <style scoped>
  .bg-primary2 {
    background-color: #010870;
  }
  </style>