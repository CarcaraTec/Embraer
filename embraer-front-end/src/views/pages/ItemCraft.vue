<script setup lang="ts">
import { ref } from 'vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';

import CraftTable from '@/components/craft/CraftTable.vue';
</script>
<template>
    <v-row>
        <v-col cols="12" md="12">
            <UiParentCard title="Item Craft"> 
                <div class="pa-7 pt-1"><p class="text-body-1">Item Craft </p>
                
                    <CraftTable/>
                </div>
                
            </UiParentCard>
        </v-col>
    </v-row>
</template>
