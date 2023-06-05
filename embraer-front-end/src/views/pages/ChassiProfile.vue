<template>
    <v-row>
      <v-col cols="12" md="12">
        <UiParentCard :title="pageTitle">
          <div class="pa-7 pt-1">
            <p class="text-body-1">This is a sample page</p>
          </div>
        </UiParentCard>
      </v-col>
    </v-row>
  
    <v-row>
      <v-col cols="12" md="9">
        <UiParentCard title="Inventory">
          <div class="pa-7 pt-1">
            <InstalledItens />
          </div>
        </UiParentCard>
        <SBEditor/>
      </v-col>
    </v-row>
  </template>
  
  <script setup lang="ts">
  import { ref, computed } from 'vue';
  import { useRoute } from 'vue-router';
  import UiParentCard from '@/components/shared/UiParentCard.vue';
  import InstalledItens from '@/components/chassi/InstalledItens.vue';
  import SBEditor from '@/components/chassi/SBEditor.vue';
 
  
  const route = useRoute();
  const pageTitle = computed(() => `Chassi ${route.params.id}`);
  </script>
  
  <style>
  .inventory-container {
    max-width: 75%; /* Defina o valor em porcentagem desejado */
    margin: 0 auto; /* Centraliza horizontalmente */
  }
  </style>
  