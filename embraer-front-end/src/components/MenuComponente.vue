<template>
    <div>
      <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e9f1fd;">
        <div class="container">
          <a class="navbar-brand" href="#" style="color: #1264a3;">Menu</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item">
                <router-link to="/" class="nav-link" style="color: #1264a3;">Voltar</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/consultar" class="nav-link" style="color: #1264a3 ;">Consultar</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/editor" class="nav-link" style="color: #1264a3;">Editor</router-link>
              </li>
            
            </ul>
          </div>
        </div>
      </nav>
      <router-view></router-view>
    </div>
  </template>
  
  <script lang="ts">

import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';

export default {
  
  name: 'MenuComponente',
    /*created() {
    this.buscarDadosUsuario();
  },
  methods: {
    async buscarDadosUsuario() {
      try {
        const response = await axios.get('http://localhost:8081/users/user', {
          withCredentials: true, // Habilita o envio do cookie na solicitação
        });
        console.log(response.data);
        // Faça o processamento necessário com os dados da resposta
      } catch (error) {
        console.error(error);
      }
    },
  },*/
};
</script>
  