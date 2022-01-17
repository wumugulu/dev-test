<template>
  <div>
    <div class="header-container">
      <h3 style="text-align: center;">{{ msg }}</h3>
    </div>
  </div>
</template>

<script>
module.exports = {
  components: {
  },
  data() {
    return {
      msg: 'header',
    }
  },
  computed: {
  },
  mounted() {
    console.info("header.vue mounted");
  },
  methods: {
    doToggleHeader(event) {
      // console.info('doToggleHeader', event);
      // this.showHeader = !this.showHeader;
    }
  }
}
</script>

<style scoped>
.header-container{
  height: 50px;
  overflow: hidden;
}
</style>