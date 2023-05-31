import { createRouter, createWebHistory } from 'vue-router';
import FormularioComponenteVue from '../components/FormularioComponente.vue';
import MenuComponenteVue from '@/components/MenuComponente.vue';
import EditorComponenteVue from '@/components/EditorComponente.vue';
import ConsultarComponente from '@/components/ConsultarComponente.vue'

const routes = [
  {
    path: '/',
    name: 'Login',
    component: FormularioComponenteVue,
  },
  {
    path: '/menu',
    name: 'Home',
    component: MenuComponenteVue,
  },
  {
    path: '/consultar',
    name: 'consultar',
    component: ConsultarComponente,
  },
  {
    path: '/editor',
    name: 'editor',
    component: EditorComponenteVue,
  },
  
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;