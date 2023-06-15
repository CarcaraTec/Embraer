<template>
  <div :class="['sidebar', { 'sidebar-expanded': isExpanded }]">
    <div class="toggle-button" @click="toggleSidebar">
      <span class="toggle-icon"></span>
    </div>
    <div class="sidebar-content">
      <button id="cadastro" class="square" @click="showComponent(1)">Cadastro</button>
      <button class="square" @click="showComponent(2)">Componente 2</button>
      <button class="square">3</button>
      <button class="square">4</button>
      <button class="square">5</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Sidebar',
  data() {
    return {
      isExpanded: false
    };
  },
  methods: {
    toggleSidebar() {
      this.isExpanded = !this.isExpanded;
    },
    showComponent(componentNumber) {
      this.activeComponent = componentNumber;
      console.log(this.activeComponent);
    }
  }
};
</script>

<style>
.sidebar {
  width: 70px;
  background-color: #0066b1;
  transition: width 0.3s;
}

.sidebar.sidebar-expanded {
  width: 200px;
}

.toggle-button {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 70px;
  cursor: pointer;
}

.toggle-icon {
  width: 16px;
  height: 16px;
  border: 2px solid #fff;
  border-radius: 50%;
  transition: transform 0.3s;
}

.sidebar-expanded .toggle-icon {
  transform: rotate(-180deg);
}

.sidebar-content {
  padding-top: 20px;
}

.square {
  width: 100%;
  height: 50px;
  background-color: #0066b1;
  color: white;
  text-align: center;
  line-height: 50px;
  font-size: 16px;
  margin-bottom: 5px;
  border-radius: 10px;
  transition: font-size 0.3s;
}

.sidebar-expanded .square {
  font-size: 18px;
}
</style>
