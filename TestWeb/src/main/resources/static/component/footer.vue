<template>
  <div class="footer-container">
    <span>{{ msg }} -- {{ routerPath }}</span>
  </div>
</template>

<script>
module.exports = {
  components: {
  },
  data() {
    return {
      msg: 'footer',
      routerPath: null
    }
  },
  watch: {
  },
  computed: {
    tableNames() {
      const selectedTables = this.selections.map(item=>item.table_name);
      return selectedTables;
    }
  },
  mounted() {
    console.info("footer.vue mounted");
  },
  methods: {
    handleClick(event) {
      console.info('handleClick', event);
    }
  }
}
</script>

<style scoped>
.footer-container {
  color: lightgrey;
  background-color: black;
  height: 30px;
  overflow: hidden;
}
</style>