<template>
  <div style="border: 0 solid blue;height: 100%;display: flex;flex-direction: column;">
    <div style="background-color: cornsilk;flex-grow: 0;">
      <h3 style="margin: 0;">{{ msg }}</h3>
    </div>

    <div class="my-table--toolbar" style="flex-grow: 0;">
      <span>welcome to register ~~~</span>
    </div>

    <div ref="tableContainer" style="flex-grow: 1;overflow: auto;display: flex;justify-content: center;align-items: center;">
      <div style="zwidth:400px;margin-top:30px;border:1px solid gray;padding: 20px;border-radius: 10px;">
        <el-form label-width="80px">
          <el-form-item label="邮箱" >
            <el-input size="mini" v-model="user.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="用户名" >
            <el-input size="mini" v-model="user.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item >
            <el-button size="mini" type="primary" icon="el-icon-refresh" style="cursor: pointer;"
                       @click="doUserReg()">
              注册
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>


<script>
module.exports = {
  name: 'pageList',
  components: {
  },
  data() {
    return {
      msg: 'this is module reg',
      user: {
        username: '',
        email: ''
      },
      projectListData: [],
      currentProjectName: null,
      applicationListData: [],
      currentApplicationName: null,
      profileListData: [],
      currentProfileName: null,
      totalSize: 0,
      configListData: [],
      maxTableHeight: 200,
      dialogFormShow: false,
      dialogFormTitle: '',
      currentRow: null,
      formData: {}
    }
  },
  computed: {
    tableNames() {
      const selectedTables = this.selections.map(item=>item.table_name);
      return selectedTables;
    }
  },
  mounted() {
    console.info("reg.vue mounted");
  },
  methods: {
    doUserReg(){
      console.info('doUserReg', this.user);
      netRequest({
        action: 'USER_REG',
        param: this.user
      }).then(res => {
        const ret = res.data;
        if (ret.code != 0) {
          this.$message({type: 'warning', message: '出错啦! ' + ret.code + ': ' + ret.msg});
        } else {
          this.$message({type: 'success',message: '注册新用户成功! '});
        }
      }).catch(err => {
        this.$message({type: 'error', message: '出异常啦! ' + err });
      })
    },














    doGetMaxTableHeight(){
      this.$nextTick(()=>{
        console.info('tableContainer.clientHeight', this.$refs.tableContainer.clientHeight, this);
        this.maxTableHeight = this.$refs.tableContainer.clientHeight;
      });
    },
    doHideDialog() {
      this.dialogFormShow = false;
    },
    doShowDialog() {
      console.info('doShowDialog');
      if (this.currentRow) {
        this.formData = {
          nodeId: this.currentRow.pzxid,
          projectName: this.currentRow.projectName,
          applicationName: this.currentRow.applicationName,
          activeProfile: this.currentRow.activeProfile,
          configClass: this.currentRow.configClass,
          configName: this.currentRow.configName,
          configValue: this.currentRow.configValue
        }
      } else {
        this.formData = {
          projectName: this.currentProjectName,
          applicationName: this.currentApplicationName,
          activeProfile: this.currentProfileName
        }
      }
      this.dialogFormShow = true;
    },
    doAddRow() {
      this.dialogFormTitle = "新增配置...";
      this.currentRow = null;
      this.doShowDialog();
    },
    doUpdateRow(index, row) {
      this.dialogFormTitle = "修改配置...";
      this.currentRow = row;
      this.doShowDialog();
    },
    doDialogOk() {
      if (this.currentRow)
        this.submitUpdateRow()
      else
        this.submitAddRow()
    },
    submitAddRow() {
      console.info("submitAddRow", this.formData);
      let data = this.formData;
      netRequest({
        action: 'add',
        param: this.formData
      }).then(res => {
        console.log('ret', res);
        console.log('ret.data', res.data);
        if (res.status==200 && res.data==1) {
          this.$message({type: 'success',message: '新增数据成功! '});
          this.doHideDialog();
          this.doRefreshConfigData();
        } else {
          this.$message({type: 'warning', message: '新增数据失败啦! '});
        }
      }).catch(err => {
        this.$message({type: 'error', message: '新增数据异常! ' + err});
      })
    },
    submitUpdateRow() {
      console.info("submitUpdateRow", this.formData);
      let data = this.formData;
      netRequest({
        action: 'update',
        param: data
      }).then(res => {
        console.log('res', res);
        console.log('res.data', res.data);
        if (res && res.data == 1) {
          this.$message({type: 'success',message: '修改数据成功! '});
          this.doHideDialog();
          this.doRefreshConfigData();
        } else {
          this.$message({type: 'warning', message: '修改数据失败! '});
        }
      }).catch(err => {
        this.$message({type: 'error', message: '修改数据异常! ' + err});
      })
    },
    doRemoveRow(index, row) {
      console.info('doRemoveRow', index, row);
      this.currentRow = row;
      this.$confirm('确认删除记录？')
          .then(_ => {
            this.submitRemoveRow();
          })
          .catch(_ => {
            console.info('catch')
          });
    },
    submitRemoveRow() {
      let data = this.currentRow;
      netRequest({
        action: 'remove',
        param: data
      }).then(res => {
        console.log('ret', res);
        if (res.status==200 && res.data==1) {
          this.$message({type: 'success',message: '删除数据成功! '});
          this.doHideDialog();
          this.doRefreshConfigData();
        } else {
          this.$message({type: 'warning', message: '删除数据失败! '});
        }
      }).catch(err => {
        this.$message({type: 'error', message: '删除数据异常! ' + err});
      })
    },
    doPageSizeChange(event) {
      console.info('doPageSizeChange', event);
    },
    doPageNumChange(event) {
      console.info('doPageNumChange', event);
    },
    doRefreshProjectData() {
      netRequest({
        action: 'listProject',
        param: {}
      }).then(res => {
        const ret = res.data;
        if (ret) {
          this.projectListData = ret;
        } else {
          this.$message({type: 'warning',message: '读取数据失败! ' + ret.code + ': ' + ret.msg});
        }
      }).catch(err => {
        this.$message({type: 'error', message: '读取数据异常! ' + err});
      });
    },
    doProjectChange(value) {
      console.info('doProjectChange', value);
      this.currentApplicationName = null;
      this.currentProfileName = null;
      this.doRefreshApplicationData();
    },
    doRefreshApplicationData() {
      const data = {
        projectName: this.currentProjectName
      }
      netRequest({
        action: 'listApplication',
        param: data
      }).then(res => {
        const ret = res.data;
        if (ret) {
          this.applicationListData = ret;
        } else {
          this.$message({type: 'warning',message: '读取数据失败! ' + ret.code + ': ' + ret.msg});
        }
      }).catch(err => {
        this.$message({type: 'error', message: '读取数据异常! ' + err});
      });
    },
    doApplicationChange(value) {
      console.info('doApplicationChange', value);
      this.currentProfileName = null;
      this.doRefreshProfileData();
    },
    doRefreshProfileData() {
      const data = {
        projectName: this.currentProjectName,
        applicationName: this.currentApplicationName
      }
      netRequest({
        action: 'listProfile',
        param: data
      }).then(res => {
        console.log('res', res);
        console.log('res.data', res.data);
        const ret = res.data;
        if (ret) {
          this.profileListData = ret;
        } else {
          this.$message({type: 'warning',message: '读取数据失败! ' + ret.code + ': ' + ret.msg});
        }
      }).catch(err => {
        this.$message({type: 'error', message: '读取数据异常! ' + err});
      });
    },
    doProfileChange(value) {
      console.info('doProfileChange', value);
      this.doRefreshConfigData();
    },
    doRefreshConfigData() {
      const data = {
        projectName: this.currentProjectName,
        applicationName: this.currentApplicationName,
        activeProfile: this.currentProfileName
      }
      netRequest({
        action: 'listConfig',
        param: data
      }).then(res => {
        console.log('res', res);
        console.log('res.data', res.data);
        const ret = res.data;
        if (ret) {
          this.configListData = ret;
          this.configListData.push(...ret);
          this.configListData.push(...ret);
          this.configListData.push(...ret);
          this.totalSize = this.configListData.length;
        } else {
          this.$message({type: 'warning',message: '读取数据失败! ' + ret.code + ': ' + ret.msg});
        }
      }).catch(err => {
        this.$message({type: 'error', message: '读取数据异常! ' + err});
      });
    },
    formatterTime(row, column, cellValue, index) {
      let time = new Date(cellValue);
      return time.toLocaleString();
    }
  }
}
</script>

<style scoped>
.el-form-item {
  margin-bottom: 5px;
}
.el-tag--mini{
  cursor: pointer;
}
/*设置table组件下面page组件的样式*/
.el-pagination {
  border: 1px solid #EBEEF5;
}


</style>
