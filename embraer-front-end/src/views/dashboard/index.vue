<template>
  <v-row>
    <v-col cols="12">
      <v-row>
        <v-col cols="12">
          <div v-if="role === 'user'">
            <ProductCards />
          </div>
          <div v-else>
            <!-- Conteúdo a ser exibido quando role não for 'user' -->
          </div>
        </v-col>
        <v-col cols="12" lg="8">
          <ProductPerformance />
        </v-col>
      </v-row>
    </v-col>
    <v-col class="text-center mt-2">
      <p class="text-muted">Design and Developed by <a flat variant="text" href="https://adminmart.com/" target="_blank" class="pl-1 text-primary">AdminMart.com</a></p>
    </v-col>
  </v-row>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import ProductPerformance from '@/components/dashboard/ProductPerformance.vue';
import ProductCards from '@/components/dashboard/ProductCards.vue';
import { getUserInfo } from '@/data/logged'; // Importando a função getUserInfo do arquivo logged.ts

const role = ref(''); // Crie uma referência reativa para a propriedade role

// Fazendo a solicitação GET ao iniciar a página
async function fetchData() {
  const userRole = await getUserInfo(); // Aguardando a resolução da Promise retornada por getUserInfo()
  if (userRole === 'ROLE_USER') {
    role.value = 'user'; // Atribua o valor à referência reativa
  }
}

fetchData(); // Chamando a função para buscar os dados
</script>
