<template>
  <div id="register" class="text-center">
    
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <!-- <label for="username">Username</label> -->
        <input type="text" id="username" placeholder="Enter your username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <!-- <label for="password">Password</label> -->
        <input type="password" id="password" placeholder="Enter your password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <!-- <label for="confirmPassword">Confirm Password</label> -->
        <input type="password" id="confirmPassword" placeholder="Confirm your password" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
    
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
input {
  text-align: center;
  padding: 8px;
  width: 30%;
  margin-bottom: 10px;
  border: 2px solid #ccc;
  border-radius: 4px;
}

#register {
  border-radius: 15px;
  text-align: center;
  margin: 50px auto;
  max-width: 500px;
  background-color: #F7F9FF;
  padding: 30px;
  box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.2);
}

button {
  padding: 5px;
  background-color: #3498db;
  color: #fff;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100px;
}

button:hover {
  background-color: #2980b9;
}

.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}
</style>
