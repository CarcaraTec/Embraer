<template>
  <v-menu :close-on-content-click="false">
      <template v-slot:activator="{ props }">
          <v-btn class="profileBtn custom-hover-primary" variant="text" v-bind="props" icon>
              <v-avatar size="35">
                <BellRingingIcon stroke-width="1.5" size="22" />
              </v-avatar>
          </v-btn>
      </template>
      <v-sheet rounded="md" width="200" elevation="10" class="mt-2">
          <v-list class="py-0" lines="one" density="compact">
            <!--<v-list-item v-for="(notification, index) in notifications" :key="index" @click="openNotification(notification)">
            <v-list-item-content>
              {{ notification.mensagem }}
            </v-list-item-content>
          </v-list-item>-->
          </v-list>
      </v-sheet>
  </v-menu>
  <v-dialog v-model="notificationDialogVisible" max-width="400">
      <v-card>
        <v-card-title>
          <span class="headline">Notificação</span>
        </v-card-title>
        <v-card-text>
          {{ selectedNotification.mensagem }}
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" text @click="closeNotificationDialog">Fechar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        notificationMenuVisible: false,
        notifications: [],
        selectedNotification: '',
        notificationDialogVisible: false
      };
    },
    mounted() {
      this.fetchNotifications();
    },
    methods: {
      toggleNotificationMenu() {
        this.notificationMenuVisible = !this.notificationMenuVisible;
      },
      openNotification(notification) {
        this.selectedNotification = notification;
        this.notificationDialogVisible = true;
        this.notificationMenuVisible = false;
      },
      closeNotificationDialog() {
        this.notificationDialogVisible = false;
      },
      fetchNotifications() {
        axios
          .get('http://localhost:8081/notification', {
        withCredentials: true,
      })
          .then(response => {
            this.notifications = response.data;
          })
          .catch(error => {
            console.error(error);
          });
      }
    }
  };
  </script>

<style>
.notification-menu {
  left: 17.5% !important;
  top: 9% !important;
}
</style>
  