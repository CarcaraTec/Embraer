<template>
    <div class="container">
      <table class="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" :key="user.id" @click="abrirModal(user)">
            <td>{{ user.id }}</td>
            <td>{{ user.name }}</td>
          </tr>
        </tbody>
      </table>
  
      <!-- Modal -->
      <div class="modal" v-if="modalOpen">
        <div class="modal-content">
          <span class="close" @click="fecharModal">&times;</span>
          <h2>Detalhes do Usuário</h2>
          <p>ID: {{ modalUser.id }}</p>
          <p>Name: {{ modalUser.name }}</p>
          <h3>Chassis do Usuário:</h3>
          <ul>
            <li v-for="chassi in modalUser.chassis" :key="chassi.id">{{ chassi.idChassi }}</li>
          </ul>
          <div class="chassi-form">
            <label for="chassiId">ID do Chassi:</label>
            <input type="text" id="chassiId" v-model="chassiId" />
            <button @click="vincularChassi">Vincular Novo Chassi</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  const users = ref([]);
  const modalOpen = ref(false);
  const modalUser = ref(null);
  const chassiId = ref('');
  
  onMounted(() => {
    listarUsuarios();
  });
  
  function listarUsuarios() {
    axios
      .get(`http://localhost:8081/users/listUsers/FBAD7101-E99F-454A-E053-2F18000AD43B`, {
        withCredentials: true,
      })
      .then((response) => {
        users.value = response.data;
      });
  }
  
  function abrirModal(user) {
    modalUser.value = user;
    buscarChassisUsuario(user.id);
    modalOpen.value = true;
  }
  
  function buscarChassisUsuario(userId) {
    axios
      .get(`http://localhost:8081/chassi/listPropChassi/${userId}`, {
        withCredentials: true,
      })
      .then((response) => {
        modalUser.value.chassis = response.data;
      });
  }
  
  function atualizarChassisUsuario(userId) {
    axios
      .get(`http://localhost:8081/chassi/listPropChassi/${userId}`, {
        withCredentials: true,
      })
      .then((response) => {
        modalUser.value.chassis = response.data;
      });
  }
  
  function fecharModal() {
    modalOpen.value = false;
    chassiId.value = '';
  }
  
  function vincularChassi() {
    const userId = modalUser.value.id;
    const idChassi = chassiId.value;
  
    const data = {
      idChassi: idChassi,
      proprietario: userId
    };
  
    axios
      .post('http://localhost:8081/chassi/signChassi', data, {
        withCredentials: true
      })
      .then((response) => {
        console.log(response.data);
        atualizarChassisUsuario(userId); // Atualiza a lista de chassis do usuário
      });
  }
  </script>
  
  <style>
  .container {
    width: 80%;
    margin: 0 auto;
  }
  
  .table {
    width: 100%;
  }
  
  .modal {
    display: block;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4);
  }
  
  .modal-content {
    background-color: #fefefe;
    margin: 10% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 50%;
  }
  
  .close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
    cursor: pointer;
  }
  
  .close:hover,
  .close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
  }
  
  .chassi-form {
    margin-top: 20px;
  }
  
  .chassi-form label {
    display: block;
    margin-bottom: 5px;
  }
  
  .chassi-form input[type="text"] {
    width: 100%;
    padding: 5px;
    margin-bottom: 10px;
  }
  
  .chassi-form button {
    padding: 5px 10px;
    background-color: #4caf50;
    color: white;
    border: none;
    cursor: pointer;
  }
  
  .chassi-form button:hover {
    background-color: #45a049;
  }
  </style>
  