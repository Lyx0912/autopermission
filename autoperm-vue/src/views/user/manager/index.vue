<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name: {{ name }}</div>
  </div>
</template>

<script>

import {mapGetters} from "vuex";

export default {
  name: 'userManager',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style scoped>

</style>
