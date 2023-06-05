<template>

    <div>
      <div>{{ itens[0].nome }}</div>

      <div v-for="item in itens" :key="item.idLogica">
        <div class="card">
          <div v-if="item.input1">{{ item.input1 }}</div>
          <div v-else>Input 1 não fornecido</div>
          <div>{{ item.operacao }}</div>
          <div v-if="item.input2">{{ item.input2 }}</div>
          <div v-else>Input 2 não fornecido</div>
          <div v-if="item.dependencia">
            Dependência: {{ item.dependencia }}
          </div>
          <div>
            Nível: {{ item.nivel }} | No raiz: {{ item.noRaiz }}
          </div>
          <div>Caminho da hierarquia: {{ item.caminhoHierarquia }}</div>
        </div>
      </div>
    </div>
  </template>
  
  <style>
  .card {
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
  }
  </style>
  
  
  
  
  
  
  
  <script lang="ts">
  import axios from 'axios';
  
  export default {
    data() {
      return {
        itens:[{
        input1:'a',
        operacao:'a'
      }
        ]
      };
    },
    mounted() {
      // Extrai o ID da URL
      const id = this.$route.params.id;

      axios
        .get(`http://localhost:8081/item/hierarquiaByIdItem/${id}`, {
          withCredentials: true,
        })
        .then((response) => {
          this.itens = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };
</script>

  