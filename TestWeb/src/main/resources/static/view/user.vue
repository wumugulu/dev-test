<template>
  <div style="height: 100%;display: flex;flex-direction: column;">
    <div style="background-color: coral;flex-grow: 0;">
      <h3 style="margin: 0;">{{ msg }}</h3>
    </div>

    <div class="my-table--toolbar" style="flex-grow: 0;">
      状态:
      <el-select size="mini" v-model="isDeletedValue" placeholder="请选择">
        <el-option v-for="item in deletedKeyList" :key="item.key"
                   :label="item.key" :value="item.value">
        </el-option>
      </el-select>
<!--      Email:
      <el-input v-model="mailKey" size="mini" :clearable="true" style="width: 180px;"></el-input>
      Username:
      <el-input v-model="nameKey" size="mini" :clearable="true" style="width: 180px;"></el-input>-->
      <el-button size="mini" type="primary" icon="el-icon-refresh" @click="doUserRefresh"
                 style="cursor: pointer;">
        刷新
      </el-button>

      <span style="float: right">
        <el-button size="mini" type="primary" icon="el-icon-refresh" @click="doUserDeleteBatch"
                         style="cursor: pointer;">
          批量删除
        </el-button>
      </span>

    </div>

    <div ref="tableContainer" style="flex-grow: 1;overflow: auto;">
      <el-table ref="tablePart" :data="tableData" :max-height="maxTableHeight" border stripe
      @selection-change="doUserSelect">
        <el-table-column type="selection" width="40"></el-table-column>
        <el-table-column prop="id" label="ID" width="50"></el-table-column>
        <el-table-column prop="email" label="邮件" width="180"></el-table-column>
        <el-table-column prop="username" label="用户名" width="150"></el-table-column>
<!--        <el-table-column prop="username" label="用户名" width="150"></el-table-column>-->
        <el-table-column prop="password" label="密码" width="150"></el-table-column>
        <el-table-column zzprop="status" label="锁定" width="90">
          <template slot-scope="scopeData">
            <el-switch v-model="scopeData.row.status" size="mini" disabled :active-value="1" active-color="orange" :inactive-value="0" />
          </template>
        </el-table-column>
        <el-table-column zzprop="isDeleted" label="删除" width="80">
          <template slot-scope="scopeData">
            <el-switch v-model="scopeData.row.isDeleted" size="mini" disabled :active-value="1" active-color="orange" :inactive-value="0" />
          </template>
        </el-table-column>
        <el-table-column prop="lastLogin" label="最后登录" width="200"></el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template slot-scope="scopeData">
            <el-tag size="mini" type="warning" style="cursor: pointer;"
                    @click="doShowEdit(scopeData.row)" >
              <i class="el-icon-edit"></i>编辑
            </el-tag>
            <el-tag size="mini" type="warning" style="cursor: pointer;"
                    @click="doUserLock(scopeData.$index, scopeData.row, scopeData.row.status == 0 ? 1 : 0)" >
              <i class="el-icon-edit"></i>{{ scopeData.row.status == 0 ? '锁定' : '解锁' }}
            </el-tag>
            <el-tag size="mini" type="danger" style="cursor: pointer;"
                    @click="doUserDelete(scopeData.$index, scopeData.row)">
              <i class="el-icon-delete"></i>{{ scopeData.row.isDeleted == 0 ? '删除' : '恢复' }}
            </el-tag>
          </template>
        </el-table-column>

      </el-table>
    </div>

    <el-dialog title="修改用户信息" :visible="showEditDialog" zzwidth="40%" zzheight="50%"
               :show-close="false">
      <el-form label-width="80px">
        <el-form-item label="用户id" style="display: none;">
          <el-input size="mini" v-model="currentUser.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="邮箱" >
          <el-input size="mini" v-model="currentUser.email" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户名" >
          <el-input size="mini" v-model="currentUser.username" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="密码" >
          <el-input size="mini" v-model="currentUser.password" autocomplete="off" minlength="6" maxlength="20"></el-input>
        </el-form-item>
        <el-form-item label="锁定" >
          <el-checkbox v-model="currentUser.status">已锁定</el-checkbox>
<!--          <el-input size="mini" v-model="currentUser.status" autocomplete="off"></el-input>-->
        </el-form-item>
        <el-form-item label="删除" >
          <el-checkbox v-model="currentUser.isDeleted">已删除</el-checkbox>
<!--          <el-input size="mini" v-model="currentUser.isDeleted" autocomplete="off"></el-input>-->
        </el-form-item>
        <el-form-item label="最后登录" >
          <el-date-picker v-model="currentUser.lastLogin" size="mini" type="datetime" placeholder="选择日期和时间"
                          align="right" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
<!--          <el-input size="mini" v-model="currentUser.lastLogin" autocomplete="off"></el-input>-->
        </el-form-item>
        <el-form-item label="登录次数" >
          <el-input-number size="mini" v-model="currentUser.loginTimes"></el-input-number>
<!--          <el-input size="mini" v-model="currentUser.loginTimes" autocomplete="off"></el-input>-->
        </el-form-item>
        <el-form-item label="注册时间" >
          <el-input size="mini" v-model="currentUser.createTime" autocomplete="off" disabled></el-input>
        </el-form-item>

<!--        <el-form-item label="活动区域" :label-width="formLabelWidth">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>-->
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="showEditDialog=false">取消</el-button>
        <el-button size="mini" type="primary" @click="doUserUpdate">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
module.exports = {
  name: 'pageUser',
  components: {
  },
  data() {
    return {
      msg: 'this is module user',
      isDeletedValue: 0,
      nameKey: '',
      mailKey: '',
      maxTableHeight: 500,
      deletedKeyList: [
          {key: '全部', value: null},
          {key: '正常用户', value: 0},
          {key: '已删除用户', value: 1}
      ],
      tableData: [],
      selectedUsers: [],
      currentUser: {},
      showEditDialog: false
    }
  },
  computed: {
    tableNames() {
      const selectedTables = this.selections.map(item=>item.table_name);
      return selectedTables;
    }
  },
  mounted() {
    console.info("user.vue mounted");
  },
  methods: {
    doUserRefresh() {
      netRequest({
        method: 'get',
        action: 'USER_LIST',
        param: {
          isDeleted: this.isDeletedValue
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
    },
    doUserLock(index, row, newStatus) {
      console.info('doUserLock', index, row);
      netRequest({
        action: 'USER_LOCK',
        param: {
          id: row.id,
          status: newStatus
        }
      }).then(res => {
        const ret = res.data;
        if (ret.code != 0) {
          this.$message({type: 'warning', message: '出错啦! ' + ret.code + ': ' + ret.msg});
        } else {
          this.$message({type: 'success',message: '更新用户成功! '});
          this.doUserRefresh();
        }
      }).catch(err => {
        this.$message({type: 'error', message: '出异常啦! ' + err });
      })
    },
    doUserDelete(index, row) {
      console.info('doUserDelete', index, row);
      netRequest({
        method: 'post',
        action: row.isDeleted == 0 ? 'USER_DELETE' : 'USER_RECOVERY',
        param: {
          id: row.id,
        }
      }).then(res => {
        const ret = res.data;
        if (ret.code != 0) {
          this.$message({type: 'warning', message: '出错啦! ' + ret.code + ': ' + ret.msg});
        } else {
          this.$message({type: 'success',message: '更新用户成功! '});
          this.doUserRefresh();
        }
      }).catch(err => {
        this.$message({type: 'error', message: '出异常啦! ' + err });
      })
    },
    doUserSelect(selection){
      console.info('doUserSelect', selection);
      this.selectedUsers = selection.map(item=>item.id);
    },
    doUserDeleteBatch() {
      console.info('doUserSelect', this.selectedUsers);
      // this.selectedUsers.push("abc");
      netRequest({
        method: 'post',
        action: 'USER_DELETE_BATCH',
        param: {
          ids: this.selectedUsers.join(','),
        }
      }).then(res => {
        const ret = res.data;
        if (ret.code != 0) {
          this.$message({type: 'warning', message: '出错啦! ' + ret.code + ': ' + ret.msg});
        } else {
          this.$message({type: 'success',message: '批量删除用户成功! '});
          this.doUserRefresh();
        }
      }).catch(err => {
        this.$message({type: 'error', message: '出异常啦! ' + err });
      })
    },
    doShowEdit(row){
      console.info('doShowEdit...', row);
      this.currentUser = {...row}; //Object.assign({}, row)
      this.currentUser.status = this.currentUser.status==1?true:false;
      this.currentUser.isDeleted = this.currentUser.isDeleted==1?true:false;
      this.showEditDialog = true;
    },
    doUserUpdate(){
      console.info('doShowEdit...', this.currentUser);
      netRequest({
        method: 'json',
        action: 'USER_UPDATE',
        param: {
          id: this.currentUser.id,
          password: this.currentUser.password,
          status: this.currentUser.status ? 1 : 0,
          isDeleted: this.currentUser.isDeleted ? 1 : 0,
          lastLogin: this.currentUser.lastLogin,
          loginTimes: this.currentUser.loginTimes
        }
      }).then(res => {
        console.log('1', res);
        // console.log('2', res.data);
        const ret = res.data;
        if (ret.code != 0) {
          this.$message({type: 'warning', message: '出错啦! ' + ret.code + ': ' + ret.msg});
        } else {
          this.$message({type: 'success',message: '编辑用户成功! '});
          this.doUserRefresh();
          this.showEditDialog = false;
        }
      }).catch(err => {
        this.$message({type: 'error', message: '出异常啦! ' + err });
      })
    }
  }
}
</script>

<style scoped>
.el-form-item {
  margin-bottom: 1px;
}
</style>
