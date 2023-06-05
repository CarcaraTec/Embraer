<template>
    <div class="logo">
        <RouterLink to="/">
            
            <img src="@/assets/images/logos/800px-Embraer_logo.png" width="174" height="26" />
        </RouterLink>
    </div>
</template>
<script setup lang="ts">


import { ref } from 'vue';
import { RouterLink } from 'vue-router';
const primary = ref('rgb(var(--v-theme-primary))');
const secondary = ref('rgb(var(--v-theme-secondary))');
</script>
