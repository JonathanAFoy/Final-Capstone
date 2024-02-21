<template>
<div class="search-box-container">
    <input id="search-box" type="text" v-model="searchTerm" v-on:keyup="updateSearchTerm"/>
    <label for="search-box">Filter</label>
  </div>
</template>

<script>
export default {
    name: 'search-box',
    data() {
        return {
            searchTerm: ''
        }
    },
    methods: {
        updateSearchTerm() {
            // update searchTerm in store so other components can use it
            // this.searchTerm = this.searchTerm.toUpperCase();

            /* DO NOT DO THIS!!!! */
            // this.$store.state.searchTerm = this.searchTerm;
            this.$store.commit('UPDATE_SEARCH_TERM',  this.searchTerm);
        }
    }
}
</script>

<style>
div.search-box-container>label {
    padding-left: 10px;
    font-weight: bold;
}
</style>