<template>
  <div class="table-label">
   <div class="table-container">
    <div class="scrollable-table">
      <table class="table">
    <tr>
      <th> Item </th>
      <th> Installed </th>
    </tr>
    <tr v-for="item in itens" :key="item.idItem">
      <td>{{ item.nome }}</td>
      <td>{{ item.status }}</td>
    </tr>
  </table>
</div>
</div>
</div>
</template>
<style>
  .table-container {
    margin: 0 auto;
    width: 65%;
  }
  .scrollable-table {
    max-height: 400px;
    overflow-y: auto;
  }

  .table {
    width: 100%;
  }
</style>
<script lang="ts">
  import axios from 'axios';
  import { ref } from 'vue';
  import { onMounted } from 'vue';

  export default {
    setup() {
      const itens = ref();

      const consultarChassi = () => {
        axios.get('http://localhost:8081/item/logica?idChassi=10000270', {
          withCredentials: true,
        })
          .then(response => {
            itens.value = response.data;
            console.log(response.data);
          });
      };

      onMounted(() => {
        consultarChassi();
      });

      return {
        itens
      };
    }
  };
</script>