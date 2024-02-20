<template>
  <div id="capstone-app">
    <div id="nav">
    <div id="nav-style">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    </div>
    <router-view />
  </div>
</template>

<style scoped>
#nav {
  display: flex;
  justify-content: center;
}

#nav-style {
  font-family: Arial, Helvetica, sans-serif;
  text-align: center;
  width: 25%;
  height: 25%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 10px 20px;
  background: rgb(68, 118, 255);
  box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.2);
  border-radius: 15px;
}

a{
  text-decoration: none;
  color: white;
}
</style>
