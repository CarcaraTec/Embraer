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
                    <option v-for="option in chassiOptions" :value="option.idChassi" :key="option.idChassi">{{ option.idChassi }}</option>
                  </select>
                  <div class="input-group-append">
                    <button @click="searchLog" class="btn btn-primary" type="button" style="margin-left: 5px;">Search</button>
                    <button @click="save" class="btn btn-primary" type="button" data-bs-toggle="modal"
                      data-bs-target="#exampleModal">Save</button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                      aria-hidden="true">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Modifications:</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body">
                            <table class="table table-bordered table-striped mt-4">
                              <tr>
                                <th class="text-center">SB</th>
                                <th class="text-center">CHANGE</th>
                              </tr>
                              <tr v-for="(mudanca,index) in mudancas">
                                <td class="text-center">{{ mudancas[index].SB }}</td>
                                <td class="text-center">{{ mudancas[index].MUDANCA }}</td>
                              </tr>
                            </table>
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" @click="alter" class="btn btn-primary" data-bs-dismiss="modal">Save changes</button>
                          </div>
                        </div>
                      </div>
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
                        <th class="text-center">SB</th>
                        <th class="text-center">Incorporated</th>
                        <th class="text-center">Applicable</th>
                        <th class="text-center">Not Applicable</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(nome,index) in nomes">
                        <td class="text-center">{{ nomes[index].idBoletim }}</td>
                        <td align="center"><input type="radio" v-bind:checked="nomes[index].status === 'INCORPORATED'"
                            id="statusCheckbox" v-bind:name="'statusRadio'+nomes[index].idBoletim"></td>
                        <td align="center"><input type="radio" v-bind:checked="nomes[index].status === 'APPLICABLE'"
                            id="statusCheckbox" v-bind:name="'statusRadio'+nomes[index].idBoletim"></td>
                        <td align="center"><input type="radio" id="statusCheckbox"
                            v-bind:name="'statusRadio'+nomes[index].idBoletim"></td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import 'bootstrap/dist/css/bootstrap.css'
  import 'bootstrap/dist/js/bootstrap.js'

  import axios from 'axios';
  
  export default {
    data() {
      return {
        items: [],
        nomes: [],
        idChassi: "",
        idChassi: [],
        tableData: [],
        incorporated: false,
        applicable: false,
        mudancas: [],
        chassiOptions: []
      };
    },
    mounted() {
      axios.get('http://localhost:8081/chassi',{
          withCredentials: true,
        })
        .then(response => {
          this.chassiOptions = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    methods: {
      searchLog() {
        this.incorporated = true;
        this.applicable = true;
  
        const checkbox1 = document.getElementById('checkbox1');
        checkbox1.checked = true;
  
        const checkbox2 = document.getElementById('checkbox2');
        checkbox2.checked = true;
  
        this.search();
      },
      search() {
        this.nomes = [];
        console.log(this.incorporated);
  
        axios
          .get('http://localhost:8081/chassiBoletim/findBoletim?idChassi=' + this.idChassi,{
          withCredentials: true,
        })
          .then(response => {
            for (var i = 0; i < response.data.length; i++) {
              if (response.data[i].status == 'INCORPORATED') {
                
                if (this.incorporated == true) {

                  this.nomes.push(response.data[i]);
                }
              }
              if (response.data[i].status == 'APPLICABLE') {
                if (this.applicable == true) {
                  this.nomes.push(response.data[i]);
                }
              }
            }
            console.log(this.nomes);
          })
          .catch(error => {
            console.error(error);
          });
      },
      incorporatedFunction(event) {
        if (event.target.checked) {
          this.incorporated = true;
 
        } else {
          this.incorporated = false;

        }

        this.search();
      },
      applicableFunction(event) {
        if (event.target.checked) {
          this.applicable = true;

        } else {
          this.applicable = false;

        }

        this.search();
      },
      save() {
  this.tableData = [];
  this.mudancas = [];

  const rows = Array.from(this.$el.querySelectorAll('#table-1 tbody tr'));

  rows.forEach((row) => {
    const idBoletim = row.cells[0].textContent;
    const status1 = row.cells[1].querySelector('input').checked;
    const status2 = row.cells[2].querySelector('input').checked;
    const status3 = row.cells[3].querySelector('input').checked;

    const rowData = {
      idChassi: this.nomes[row.rowIndex - 1].idChassi,
      idBoletim: idBoletim,
      status1: status1,
      status2: status2,
      status3: status3,
    };

    if (this.nomes[row.rowIndex - 1].status === 'APPLICABLE' && !status2) {
      let json = { SB: idBoletim, MUDANCA: 'APPLICABLE ➜ INCORPORATED' };
      this.mudancas.push(json);
      this.tableData.push(rowData);
    }

    if (this.nomes[row.rowIndex - 1].status === 'INCORPORATED' && !status1) {
      let json = { SB: idBoletim, MUDANCA: 'INCORPORATED ➜ APPLICABLE' };
      this.mudancas.push(json);
      this.tableData.push(rowData);
    }
  });
}
,
      alter() {
        console.log(this.mudancas);
        axios.post('http://localhost:8081/chassiBoletim/saveChassi', this.tableData,{
          withCredentials: true,
        })
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.error(error);
          });
      }
    }
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
  