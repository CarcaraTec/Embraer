<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { productPerformance } from '@/data/dashboard/dashboardData';

const isProductPerformanceReady = ref(false);

onMounted(async () => {
  // Simule um atraso para ilustrar o carregamento assíncrono
  await new Promise((resolve) => setTimeout(resolve, 2000));
  // Aqui você pode fazer a lógica de carregar os dados de productPerformance

  // Após o carregamento, marque a propriedade como pronta
  isProductPerformanceReady.value = true;
});
</script>

<template>
  <v-card elevation="10" class="">
    <v-card-item class="pa-6">
      <v-card-title class="text-h5 pt-sm-2 pb-7">Recent Transactions</v-card-title>
      <v-table class="month-table">
        <thead>
          <tr>
            <th class="text-subtitle-1 font-weight-bold">Id</th>
            <th class="text-subtitle-1 font-weight-bold">Assigned</th>
            <th class="text-subtitle-1 font-weight-bold">Name</th>
            <th class="text-subtitle-1 font-weight-bold">Priority</th>
            <th class="text-subtitle-1 font-weight-bold text-right">Budget</th>
          </tr>
        </thead>
        <tbody>
          <template v-if="isProductPerformanceReady">
            <tr v-for="item in productPerformance" :key="item.name" class="month-item">
              <!-- Conteúdo do template -->
            </tr>
          </template>
          <template v-else>
            <tr>
              <td colspan="5" class="text-center">Loading...</td>
            </tr>
          </template>
        </tbody>
      </v-table>
    </v-card-item>
  </v-card>
</template>
