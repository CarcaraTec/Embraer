<template>
    <v-row>
      <v-col cols="12" lg="3" sm="6" v-for="card in productsCard" :key="card.title">
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
            <h6 class="text-h6" v-text="card.title"></h6>
            <div class="d-flex align-center justify-space-between mt-1">
              <div>
                <!-- Conteúdo adicional do card -->
              </div>
            </div>
          </v-card-item>
        </v-card>
      </v-col>
  
      <!-- Modal -->
      <v-dialog v-model="modalOpen" max-width="500px" @click:outside="clearItens">
        <v-card>
          <v-card-title>
            {{ selectedCard.title }}
          </v-card-title>
          <div class="table-label">
            <div class="table-container">
              <div class="scrollable-table">
                <table class="table">
                  <tr>
                    <th>Item</th>
                    <th>Installed</th>
                  </tr>
                  <tr v-for="item in itens" :key="item.idItem">
                    <td>
                      <router-link :to="`/item/${item.idItem}`">
                        {{ item.nome }}
                      </router-link>
                    </td>
                    <td>
                      <router-link :to="`/item/${item.idItem}`">
                        {{ item.status }}
                      </router-link>
                    </td>
                  </tr>
                </table>
              </div>
            </div>
          </div>
          <v-card-actions>
            <v-btn color="primary" text @click="modalOpen = false">Fechar</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </template>
  
  <script setup lang="ts">
  import { productsCard } from '@/data/dashboard/dashboardData';
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  const modalOpen = ref(false); // Variável de controle para abrir/fechar o modal
  const selectedCard = ref({}); // Variável para armazenar o card selecionado
  const itens = ref([]);
  
  // Função para abrir o modal ao clicar no card
  function openModal(card) {
    selectedCard.value = card;
    modalOpen.value = true;
    consultarChassi(card.title);
  }
  
  // Função para consultar os itens
  function consultarChassi(title) {
    axios
      .get(`http://localhost:8081/item/logica?idChassi=${title}`, {
        withCredentials: true,
      })
      .then((response) => {
        itens.value = response.data;
      });
  }
  
  // Função para limpar o valor de itens.value
  function clearItens() {
    itens.value = [];
  }
  
  onMounted(() => {
    // Aqui você pode colocar alguma lógica para definir o card inicialmente selecionado
    // e chamar a função consultarChassi() para preencher os itens iniciais
  });
  </script>
  
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
  