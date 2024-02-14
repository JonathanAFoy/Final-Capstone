<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">&nbsp;|&nbsp;Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>
#nav {
  text-align: center;
}

a{
  text-decoration: none;
}
</style>
