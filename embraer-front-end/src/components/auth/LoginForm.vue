<template>
  <div class="container">
    <form class="form-signin" method="POST" action="http://localhost:8081/users/login">
      <h2 class="form-signin-heading">Login</h2>
      <input type="text" class="form-control" name="username" placeholder="Nome de usuário" required autofocus>
      <input type="password" class="form-control" name="password" placeholder="Senha" required>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
    </form>
  </div>
</template>
  
<script lang="ts">
import 'bootstrap/dist/css/bootstrap.min.css';

export default {
  name: 'LoginForm',
};
</script>
  
  <style>
  /* Estilos adicionais personalizados */
  body {
       padding-top: 40px;
       padding-bottom: 40px;
       background-color: #f5f5f5;
     }
 
     .form-signin {
       max-width: 330px;
       padding: 15px;
       margin: 0 auto;
     }
 
     .form-signin .form-control {
       position: relative;
       box-sizing: border-box;
       height: auto;
       padding: 10px;
       font-size: 16px;
     }
 
     .form-signin .form-control:focus {
       z-index: 2;
     }
 
     .form-signin input[type="text"] {
       margin-bottom: 10px;
       border-bottom-left-radius: 0;
       border-bottom-right-radius: 0;
     }
 
     .form-signin input[type="password"] {
       margin-bottom: 10px;
       border-top-left-radius: 0;
       border-top-right-radius: 0;
     }
 </style>