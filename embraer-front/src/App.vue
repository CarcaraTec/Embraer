<template>
  <main>
    <div>
      <HomeT />
    </div>
  </main>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import HomeT from './components/HomeT.vue'

export default defineComponent({
  name: 'App',
  components: {
    HomeT
  }
});

</script>

<style>

</style>