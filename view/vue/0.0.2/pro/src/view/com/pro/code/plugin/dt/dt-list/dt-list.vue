<style lang="less">
  @import './dt-list.less';
</style>

<template>
  <div>
    <Card>
      <Button class="button-spacing" type="primary" @click="addDt()">新建</Button>
      <Button class="button-spacing" type="dashed" @click="upDt()" v-if="selectDataRowLength === 1">编辑</Button>
      <Button class="button-spacing" type="error" @click="delDt()" v-if="selectDataRowLength > 0">删除</Button>
      <Button class="button-spacing" @click="detailDt()" v-if="selectDataRowLength === 1">详情</Button>
      <Table :columns="columns" :data="dataSet" @on-selection-change="selectRow($event)"></Table>
      <Spin size="large" fix v-if="spinShow"></Spin>
    </Card>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'

export default {
  data () {
    return {
      columns: [
        { type: 'selection', width: 50, align: 'center', fixed: 'left' },
        { title: '表名', key: 'dtName', width: 200 },
        { title: '表名注释', key: 'dtNameAnnotation', width: 150 },
        { title: '首字母大写实体类名', key: 'initialCaseEntityName', width: 180 },
        { title: '首字母小写实体类名', key: 'initialLowercaseEntityName', width: 180 },
        { title: '项目路径', key: 'proPath', width: 500 },
        { title: '项目全称', key: 'proAllName', width: 400 },
        { title: '项目名称', key: 'proName', width: 300 }
      ]
    }
  },
  computed: {
    ...mapState('dtStore', [
      'dataSet',
      'spinShow',
      'selectDataRow',
      'selectDataRowLength'
    ]),
    ...mapGetters('dtStore', [
    ])
  },
  methods: {
    ...mapMutations([
      'addTag'
    ]),
    ...mapMutations('dtStore', [
      'setDataSet',
      'changeSpinShow',
      'setSelectDataRow'
    ]),
    ...mapActions('dtStore', [
      'getAllDt',
      'batchRemoveDt'
    ]),
    addDt () {
      const route = {
        name: 'dt-edit',
        params: {
          dtId: 'add'
        },
        meta: {
          title: '编辑数据库表'
        }
      }
      this.addTag({
        route: route,
        type: 'push'
      })
      this.$router.push(route)
      // this.$router.push({ name: 'dt-edit' })
    },
    getDataSet () {
      this.changeSpinShow(true)
      this.getAllDt().then(res => {
        if (res.data.isSuccess && res.data.statusCode === 200) {
          this.setDataSet(res.data.data)
          this.selectRow([])
          this.changeSpinShow(false)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          this.changeSpinShow(false)
        }
      }).catch(result => {
        this.$Message.error({ content: '请求失败！状态码 ' + result.response.status + ' ' + result.response.statusText, duration: 6 })
        this.changeSpinShow(false)
      })
    },
    selectRow (selection) {
      this.setSelectDataRow(selection)
    },
    delDt () {
      this.changeSpinShow(true)
      this.batchRemoveDt(this.selectDataRow).then(res => {
        if (res.data.isSuccess && res.data.statusCode === 200) {
          this.$Message.success({ content: '数据库表信息删除成功.', duration: 3 })
          this.selectRow([])
          this.getDataSet()
          this.changeSpinShow(false)
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
          this.changeSpinShow(false)
        }
      }).catch(result => {
        this.$Message.error({ content: '请求失败！状态码 ' + result.response.status + ' ' + result.response.statusText, duration: 6 })
        this.changeSpinShow(false)
      })
    },
    upDt () {
      const route = {
        name: 'dt-edit',
        params: {
          dtId: this.selectDataRow[0].dtId
        },
        meta: {
          title: '编辑数据库表'
        }
      }
      this.addTag({
        route: route,
        type: 'push'
      })
      this.$router.push(route)
    },
    detailDt () {
      const route = {
        name: 'dt-detail',
        params: {
          dtId: this.selectDataRow[0].dtId
        },
        meta: {
          title: '数据库表详情'
        }
      }
      this.addTag({
        route: route,
        type: 'push'
      })
      this.$router.push(route)
    }
  },
  mounted () {
    this.getDataSet()
  }
}
</script>
