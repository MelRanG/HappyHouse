<template>
  <div id="app">
    <nav-bar></nav-bar>
    <router-view></router-view>
    <footer-view></footer-view>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import FooterView from "@/components/FooterView.vue";
export default {
  name: "App",
  components: {
    NavBar,
    FooterView,
  },
  methods: {},
};
</script>

<style scoped></style>
