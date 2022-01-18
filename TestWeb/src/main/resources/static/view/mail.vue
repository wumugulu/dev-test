<template>
  <div>
    <div style="background-color: #c2e7b0;flex-grow: 0;">
      <h3 style="margin: 0;">{{ msg }}</h3>
    </div>

    <div class="my-table--toolbar" style="flex-grow: 0;">
      邮箱:
      <el-input size="mini" v-model="keyword" clearable style="width: 150px;"></el-input>

      <el-button size="mini" type="primary" icon="el-icon-refresh" @click="doMailRefresh"
                 style="cursor: pointer;">
        搜索
      </el-button>
    </div>

    <div ref="tableContainer" style="flex-grow: 1;overflow: auto;">
      <el-table ref="tablePart" :data="tableData" :max-height="maxTableHeight" border stripe >
        <el-table-column prop="id" label="ID" width="80"></el-table-column>
        <el-table-column prop="userId" label="用户id" width="150"></el-table-column>
        <el-table-column prop="email" label="邮件" width="200"></el-table-column>
        <el-table-column prop="status" label="状态" width="60"></el-table-column>
        <el-table-column prop="lastSend" label="时间" width="180"></el-table-column>
        <el-table-column prop="subject" label="主题" width="200"></el-table-column>
        <el-table-column prop="content" label="内容" width="500"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
module.exports = {
  name: 'pageOrder',
  components: {
  },
  data() {
    return {
      msg: 'this is module mail',
      maxTableHeight: 500,
      keyword: '',
      tableData: []
    }
  },
  computed: {
  },
  mounted() {
    console.info("mail.vue mounted");
  },
  methods: {
    doMailRefresh() {
      netRequest({
        method: 'post',
        action: 'MAIL_QUERY',
        param: {
          keyword: this.keyword
        }
      }).then(res => {
        console.log('1', res);
        // console.log('2', res.data);
        const ret = res.data;
        if (ret.code != 0) {
          this.$message({type: 'warning', message: '出错啦! ' + ret.code + ': ' + ret.msg});
        } else {
          this.tableData = ret.data;
        }
      }).catch(err => {
        this.$message({type: 'error', message: '出异常啦! ' + err });
      })
    }
  }
}
</script>

<style scoped>
.triangle-topright {
  width: 0;
  height: 0;
  border-top: 100px solid red;
  border-left: 100px solid transparent;
}
</style>
