<template>
  <v-row>
    <v-col cols="12" lg="3" sm="6" v-for="card in items" :key="card.title">
      <v-card elevation="10" class="withbg" rounded="md">
        <v-img :src="card.photo" height="100%" class="rounded-t-md" @click="openModal(card)"></v-img>
        <div class="d-flex justify-end mr-4 mt-n5">
          <v-btn size="40" icon class="bg-primary d-flex">
            <router-link :to="`/chassi/`+card.title">
              <v-avatar size="30" class="text-white">
                <InfoCircleIcon size="25" />
              </v-avatar>
            </router-link>
            <v-tooltip activator="parent" location="bottom">
              Show more details
            </v-tooltip>
          </v-btn>
        </div>
        <v-card-item class="pt-0">
          <h6 class="text-h8" v-text="card.title"></h6>
          <div class="d-flex align-center justify-space-between mt-1">
            <div>
              <!-- Conteúdo adicional do card -->
            </div>
          </div>
        </v-card-item>
      </v-card>
    </v-col>

    <!-- Modal -->
    <v-dialog v-model="modalOpen" max-width="500px">
      <v-card>
        <!-- Conteúdo do modal -->
        <v-card-title>{{ selectedCard.title }}</v-card-title>
        <v-card-text>
          <!-- Exiba as informações adicionais do card selecionado -->
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" text @click="modalOpen = false">Fechar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { productsCard, setUpdateFunction } from '@/data/installed/installedData';

const modalOpen = ref(false); // Variável de controle para abrir/fechar o modal
const selectedCard = ref({}); // Variável para armazenar o card selecionado
const items = ref([]);

// Função para abrir o modal ao clicar no card
function openModal(card) {
  selectedCard.value = card;
  modalOpen.value = true;
}

// Função para atualizar os cards no componente Vue
function updateCards(newCards) {
  items.value = newCards;
}

// Registra a função de atualização dos cards
setUpdateFunction(updateCards);

// Executa a função fetchData ao montar o componente
onMounted(async () => {
  await fetchData();
});

</script>

<style>
/* Estilos do componente */
</style>
