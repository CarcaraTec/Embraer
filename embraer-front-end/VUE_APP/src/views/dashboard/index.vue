<template>
  <v-row>
    <v-col cols="12">
      <v-row>
        <v-col cols="12">
          <div v-if="role === 'admin'">
            <ProductCards />
          </div>
          <div v-if="role === 'editor'">
            <EditorComponent />
          </div>
          <div v-if="role === 'user'">
            <ProductCards />
          </div>
        </v-col>
      </v-row>
    </v-col>
    <v-col class="text-center mt-2">
      <p class="text-muted">Developed by Carcará Technology.</p>
    </v-col>
  </v-row>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import ProductPerformance from '@/components/dashboard/ProductPerformance.vue';
import ProductCards from '@/components/dashboard/ProductCards.vue';
import EditorComponent from '@/components/editor/EditorComponent.vue'
import UserComponent from '@/components/user/UserComponent.vue'
import { getUserInfo } from '@/data/logged'; // Importando a função getUserInfo do arquivo logged.ts

const role = ref(''); // Crie uma referência reativa para a propriedade role

// Fazendo a solicitação GET ao iniciar a página
async function fetchData() {
  const userRole = await getUserInfo(); // Aguardando a resolução da Promise retornada por getUserInfo()
  if (userRole === 'ROLE_USER') {
    role.value = 'user'; // Atribua o valor à referência reativa
  }
  else if (userRole === 'ROLE_EDITOR') {
    role.value = 'editor'; // Atribua o valor à referência reativa
  }
  else if(userRole === 'ROLE_ADMIN') {
    role.value = 'admin'; // Atribua o valor à referência reativa
  }
}

fetchData(); // Chamando a função para buscar os dados
</script>
