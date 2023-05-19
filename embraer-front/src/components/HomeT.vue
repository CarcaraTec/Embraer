<template>
  <div class="logo-container p-3 d-flex justify-content-between">
    <span></span>
    <img src="../assets/logo.embraer.png" />
    <div>
      <i class="fa-solid fa-bell"></i>
      <i class="fa-solid fa-user"></i>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

 export default defineComponent({
    name: 'HomeT'
})
</script>

<style>
.logo-container {
  background-color: rgb(11, 27, 91);
  color: #fff;
}

.btn-group button {
  background-color: rgb(11, 27, 91);
  margin-right: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.fa-solid {
  margin-right: 20px;
  margin-left: 20px;
}
</style>