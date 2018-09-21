<style lang="less">
  @import './dt-detail.less';
</style>

<template>
  <div>
    <Card>
      <Button class="button-spacing" @click="gotoList()">返回</Button>
      <Row>
        <Col :xs="24" :sm="12" :md="12" :lg="8">
          <p>数据库表编号：{{ dt.dtId }}</p>
          <Divider dashed />
        </Col>
        <Col :xs="24" :sm="12" :md="12" :lg="8">
          <p>表名：{{ dt.dtName }}</p>
          <Divider dashed />
        </Col>
        <Col :xs="24" :sm="12" :md="12" :lg="8">
          <p>表名注释：{{ dt.dtNameAnnotation }}</p>
          <Divider dashed />
        </Col>
        <Col :xs="24" :sm="12" :md="12" :lg="8">
          <p>数据库表前缀：{{ dt.dtPrefix }}</p>
          <Divider dashed />
        </Col>
        <Col :xs="24" :sm="12" :md="12" :lg="8">
          <p>首字母大写实体类名：{{ dt.initialCaseEntityName }}</p>
          <Divider dashed />
        </Col>
        <Col :xs="24" :sm="12" :md="12" :lg="8">
          <p>首字母小写实体类名：{{ dt.initialLowercaseEntityName }}</p>
          <Divider dashed />
        </Col>
        <Col :xs="24" :sm="12" :md="12" :lg="8">
          <p>项目路径：{{ dt.proPath }}</p>
          <Divider dashed />
        </Col>
        <Col :xs="24" :sm="12" :md="12" :lg="8">
          <p>项目全称：{{ dt.proAllName }}</p>
          <Divider dashed />
        </Col>
        <Col :xs="24" :sm="12" :md="12" :lg="8">
          <p>项目名称：{{ dt.proName }}</p>
          <Divider dashed />
        </Col>
        <Col :xs="24" :sm="12" :md="12" :lg="24">
          <p>数据库表sql：{{ dt.dtSql }}</p>
          <Divider dashed />
        </Col>
      </Row>
    </Card>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import { getDt } from '@/view/com/pro/code/plugin/dt/dt'

export default {
  data () {
    return {
      dt: getDt()
    }
  },
  computed: {
    ...mapState('dtStore', [
    ]),
    ...mapGetters('dtStore', [
    ])
  },
  methods: {
    ...mapMutations('dtStore', [
    ]),
    ...mapActions('dtStore', [
      'getDtByPk'
    ]),
    gotoList () {
      this.$router.push({ name: 'dt-list' })
    }
  },
  created () {
    this.dt.dtId = this.$route.params.dtId
    if (this.dt.dtId !== 'add') {
      this.getDtByPk(this.dt.dtId).then(res => {
        if (res.data.isSuccess && res.data.statusCode === 200) {
          Object.assign(this.dt, res.data.data[0])
        } else {
          this.$Message.error({ content: res.data.msg, duration: 6 })
        }
      }).catch(result => {
        this.$Message.error({ content: '请求失败！状态码 ' + result.response.status + ' ' + result.response.statusText, duration: 6 })
      })
    }
  }
}
</script>
