<template>
  <div class="container">
    <div class="sidebar">
      <button id="cadastro" class="btn btn-primary btn-block" @click="showComponent(1)">Cadastro</button>
      <button class="btn btn-primary btn-block" @click="showComponent(2)">Componente 2</button>
      <button class="btn btn-primary btn-block">3</button>
      <button class="btn btn-primary btn-block">4</button>
      <button class="btn btn-primary btn-block">5</button>
    </div>
    <div class="content">
      <CreateUserComponent v-if="activeComponent === 1" />
      <ListUserComponent v-if="activeComponent === 2" />
    </div>
  </div>
</template>

<script setup>
import CreateUserComponent from '@/components/admin/CreateUserComponent.vue';
import ListUserComponent from '@/components/admin/ListUserComponent.vue';
</script>

<script>
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';

export default {
  name: 'Sidebar',
  data() {
    return {
      activeComponent: null
    };
  },
  methods: {
    showComponent(componentNumber) {
      this.activeComponent = componentNumber;
      console.log(this.activeComponent);
    }
  }
};
</script>

<style>
.container {
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  height: 100vh;
}

.sidebar {
  width: 200px;
  padding: 10px;
  background-color: #f8f9fa;
}

.btn {
  display: block;
  width: 100%;
  text-align: left;
  margin-bottom: 10px;
}
</style>
