<template>
    <div>
      <h1 class="text-center">Cadastro de Usuário</h1>
      <form @submit.prevent="cadastrarUsuario" class="mt-4">
        <div class="form-group row">
          <label for="name" class="col-sm-2 col-form-label text-right">Nome:</label>
          <div class="col-sm-10">
            <input type="text" id="name" v-model="usuario.name" class="form-control" required>
          </div>
        </div>
        <div class="form-group row">
          <label for="username" class="col-sm-2 col-form-label text-right">Usuário:</label>
          <div class="col-sm-10">
            <input type="text" id="username" v-model="usuario.username" class="form-control" required>
          </div>
        </div>
        <div class="form-group row">
          <label for="password" class="col-sm-2 col-form-label text-right">Senha:</label>
          <div class="col-sm-10">
            <input type="password" id="password" v-model="usuario.password" class="form-control" required>
          </div>
        </div>
        <div class="form-group row">
          <label for="roles" class="col-sm-2 col-form-label text-right">Funções:</label>
          <div class="col-sm-10">
            <select id="roles" v-model="usuario.roles" class="form-control" multiple required>
              <option v-for="role in roles" :value="role" :key="role.id">{{ role.name }}</option>
            </select>
          </div>
        </div>
        <div class="form-group row justify-content-end">
          <div class="col-sm-10">
            <button type="submit" class="btn btn-primary">Cadastrar</button>
          </div>
        </div>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        usuario: {
          name: '',
          username: '',
          password: '',
          roles: []
        },
        roles: [
          { id: 'FBAD716F-79E7-CFA5-E053-2F18000A1E4F', name: 'Admin' },
          { id: 'FBAD7101-E99F-454A-E053-2F18000AD43B', name: 'Editor' },
          { id: '81E47E1C-A566-11EC-B909-0242AC120002', name: 'User' }
        ]
      };
    },
    methods: {
      cadastrarUsuario() {
        fetch('http://localhost:8081/users/create', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.usuario)
        })
        .then(response => {
          if (response.ok) {
            // Usuário cadastrado com sucesso!
            alert('Usuário cadastrado com sucesso!');
            // Limpar os campos do formulário
            this.usuario.name = '';
            this.usuario.username = '';
            this.usuario.password = '';
            this.usuario.roles = [];
          } else {
            // Houve um erro ao cadastrar o usuário
            alert('Erro ao cadastrar o usuário.');
          }
        })
        .catch(error => {
          console.error(error);
          // Tratar o erro adequadamente
          alert('Ocorreu um erro ao cadastrar o usuário.');
        });
      }
    }
  };
  </script>
  
  <style scoped>
  /* Importar Bootstrap */
  @import url('https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css');
  
  /* Estilização adicional */
  h1 {
    margin-top: 40px;
    margin-bottom: 20px;
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  label {
    font-weight: bold;
  }
  
  .text-right {
    text-align: right;
  }
  
  .btn-primary {
    width: 100%;
  }
  </style>
  