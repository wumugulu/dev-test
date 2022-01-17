<template>
  <div>
    <el-tabs v-model="activeTabName" type="card" ztab-click="doTabsClick" ztab-remove="doTabsRemove" >
      <el-tab-pane label="用户管理" name="user">
        <comp-user></comp-user>
      </el-tab-pane>
      <el-tab-pane label="邮件管理" name="mail">
        <comp-mail></comp-mail>
      </el-tab-pane>
      <el-tab-pane label="新人注册" name="reg">
        <comp-reg></comp-reg>
      </el-tab-pane>
    </el-tabs>

<!--    <div class="test-transition"></div>
    <el-input v-model="value"></el-input>

    <el-button @click="handleClick(1)">用户列表</el-button>
    <el-button @click="handleClick(2)">邮件列表</el-button>
    <el-button @click="handleClick(3)">注册新人</el-button>
    <br>
    <h3>{{ msg }}</h3>
    <br>
    <h3 v-for="item in 30" :key="item">
      {{ item }}
    </h3>-->
  </div>
</template>


<script>
module.exports = {
  name: 'pageHome',
  components: {
    compUser: httpVueLoader('../view/user.vue'),
    compMail: httpVueLoader('../view/mail.vue'),
    compReg: httpVueLoader('../view/reg.vue'),
  },
  data() {
    return {
      msg: 'this is Home',
      activeTabName: 'user',
      value: ''
    }
  },
  computed: {
  },
  mounted() {
    console.info("home.vue mounted")
  },
  methods: {
  }
}
</script>

<style scoped>
.my-table--toolbar {
  padding: 5px;
  border: 1px solid #EBEEF5;
}
</style>
