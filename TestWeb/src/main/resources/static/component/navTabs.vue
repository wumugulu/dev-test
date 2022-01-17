<template>
  <div ref="tabsContainer" style="height: 100%;">
    <el-tabs v-model="activeTabName" type="card" editable
             @edit="doTabsEdit" @tab-click="doTabsClick" @tab-remove="doTabsRemove" >
      <el-tab-pane v-for="(item, index) in tabsData" :key="item.name"
                   :label="item.title" :name="item.name"
                   :style="'height:'+maxTabPaneHeight+'px;overflow: auto;'"
                   style="zbackground-color: #dd6161;" >
        <span slot="label" style="display: inline-block;">
          <i :class="item.icon"></i> {{ item.title }}
        </span>

        <!--<div style="height: 30px;line-height: 30px;border: 1px solid green;">
          {{ activeTabName }} - {{ item.path }} ({{ item.content }})  <input />
        </div>-->
        <component :is="item.component"></component>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
module.exports = {
  components: {
    compHomePage: httpVueLoader('../view/home.vue')
  },
  data() {
    return {
      maxTabPaneHeight: 500,
      tabsData: [{
        name: '0',
        title: 'welcome',
        icon: 'el-icon-s-home',
        content: '天下无贼 home',
        component: 'compHomePage'
      }],
      activeTabName: '0'
    }
  },
  watch: {
  },
  computed: {
    activeComponentName() {
      console.info('navTabs.vue computed a:', this.activeTabName, this.tabsData);
      let findedTabsItem =  this.tabsData.find(item=>{
        return item.name == this.activeTabName;
      });
      console.info('navTabs.vue computed b:', findedTabsItem);
      if (findedTabsItem)
        return findedTabsItem.component;
    }
  },
  mounted() {
    console.info("navMenu.vue mounted");
    this.doGetMaxTabPaneHeight();
    this.doWatchContentViewChangeEvent();
    this.activeTabName = this.tabsData[0].name;
  },
  methods: {
    doGetMaxTabPaneHeight(){
      this.$nextTick(()=>{
        console.info('tabsContainer.clientHeight', this.$refs.tabsContainer.clientHeight, this);
        console.info('$el.clientHeight', this.$el.style.height, this);
        this.maxTabPaneHeight = this.$refs.tabsContainer.offsetHeight - 46 - 30;
      });
    },
    doWatchContentViewChangeEvent() {
      Event.$on('changeContentViewViaMenu', menuItem => {
        console.info('navTabs.vue doWatchContentViewChangeEvent', menuItem)
        if (menuItem.id == this.activeTabName)
          return;

        console.info('changeTab via Menu')
        let findedTab = this.tabsData.find(item => {
          return item.name == menuItem.id;
        });

        if (findedTab)
          this.activeTabName = menuItem.id;
        else
          this.doTabsAdd(menuItem);
      });
    },
    doRouterChange(to) {
      let findedTab = this.tabsData.find(item => {
        return item.name == to.path;
      });

      if (findedTab)
        this.activeTabName = to.path;
      else
        this.doTabsAdd(to);
    },
    doTabsAdd(menuItem) {
      console.info('doTabsAdd', menuItem);
      this.tabsData.push({
        name: menuItem.id,
        title: menuItem.label || "noName",
        icon: menuItem.icon,
        content: 'content of ' + menuItem.label,
        component: menuItem.component
      })
      this.activeTabName = menuItem.id;
    },
    doTabsEdit(targetName, operation) {
      console.info('navTabs.vue doTabsEdit', targetName, operation);
    },
    doTabsClick(pane, pointer) {
      console.info('navTabs.vue doTabsClick', pane.name, pane, this.activeTabName);
      Event.$emit('changeContentViewViaTabs', pane.name);
    },
    doTabsRemove(tabName) {
      console.info('navTabs.vue doTabsRemove', tabName);
      //if (tabName == this.tabsData[this.tabsData.length-1].name);
      const findedIndex = this.tabsData.findIndex((item, index, arr) => {
        return item.name == tabName;
      });

      console.info('findedIndex', findedIndex);
      if (this.activeTabName == tabName){
        let dstTabName = null;
        if (findedIndex == this.tabsData.length-1) {
          dstTabName = this.tabsData[findedIndex-1].name;
        } else {
          dstTabName = this.tabsData[findedIndex+1].name;
        }
        this.activeTabName = dstTabName;

        console.info('dstTabName', dstTabName);
        Event.$emit('changeContentViewViaTabs', dstTabName);
      }
      this.tabsData.splice(findedIndex, 1);
    }
  }
}

</script>

<style scoped>
/*not need tabs content div*/
.el-tabs__header{
  margin-bottom: 5px;
}
/*first tab can not be close*/
.el-tabs__nav .el-tabs__item:nth-child(1) span{
  display: none;
}
/*hide the new tab button */
.el-tabs__new-tab{
  display: none;
}
</style>
