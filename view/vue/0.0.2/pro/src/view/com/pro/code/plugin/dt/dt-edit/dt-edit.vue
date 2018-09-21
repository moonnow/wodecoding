<style lang="less">
  @import './dt-edit.less';
</style>

<template>
  <div>
    <Card>
      <Form ref="dtForm" :model="dt" :rules="dtFormValidate" :label-width="120">
        <Row type="flex" justify="center">
          <Col :xs="24" :sm="20" :md="18" :lg="14">
            <FormItem label="数据库表sql：" prop="dtSql">
              <Input v-model="dt.dtSql" type="textarea" :rows="6" placeholder="输入数据库表sql"></Input>
            </FormItem>
          </Col>
          <Col :xs="24" :sm="20" :md="18" :lg="14">
            <FormItem label="数据库表前缀：" prop="dtPrefix">
              <Input v-model="dt.dtPrefix" placeholder="输入数据库表前缀"></Input>
            </FormItem>
          </Col>
          <Col :xs="24" :sm="20" :md="18" :lg="14">
            <FormItem label="项目路径：" prop="proPath">
              <Input v-model="dt.proPath" placeholder="输入项目路径"></Input>
            </FormItem>
          </Col>
          <Col :xs="24" :sm="20" :md="18" :lg="14">
            <FormItem label="项目全称：" prop="proAllName">
              <Input v-model="dt.proAllName" placeholder="输入项目全称"></Input>
            </FormItem>
          </Col>
          <Col :xs="24" :sm="20" :md="18" :lg="14">
            <FormItem label="项目名称：" prop="proName">
              <Input v-model="dt.proName" placeholder="输入项目名称"></Input>
            </FormItem>
          </Col>
          <Col :xs="24" :sm="20" :md="18" :lg="14">
            <FormItem>
              <Button class="button-spacing" @click="gotoList()">取消</Button>
              <Button class="button-spacing" @click="reset()">重置</Button>
              <Button class="button-spacing" type="primary" @click="saveAdd()" :loading="submitting">保存并继续录入</Button>
              <Button class="button-spacing" type="primary" @click="save()" :loading="submitting">保存</Button>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Card>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import { getDt } from '@/view/com/pro/code/plugin/dt/dt'

export default {
  data () {
    return {
      dt: getDt(),
      dtFormValidate: {
        dtSql: [
          { required: true, message: '数据库表sql不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    ...mapState('dtStore', [
      'submitting',
      'continue'
    ]),
    ...mapGetters('dtStore', [
    ])
  },
  methods: {
    ...mapMutations('dtStore', [
      'changeSubmitting',
      'changeContinue'
    ]),
    ...mapActions('dtStore', [
      'saveDt',
      'getDtByPk',
      'updateDt'
    ]),
    gotoList () {
      this.$router.push({ name: 'dt-list' })
    },
    save () {
      this.changeContinue(false)
      this.submit()
    },
    saveAdd () {
      this.changeContinue(true)
      this.submit()
    },
    reset () {
      this.$refs.dtForm.resetFields()
    },
    submit () {
      this.changeSubmitting(true)
      this.$refs.dtForm.validate((valid) => {
        if (valid) {
          if (this.dt.dtId !== 'add') {
            this.updateDt(this.dt).then(res => {
              if (res.data.isSuccess && res.data.statusCode === 200) {
                this.$Message.success({ content: '数据库表信息修改成功.', duration: 3 })
                this.reset()
                this.dt = getDt()
                this.dt.dtId = 'add'
                this.changeSubmitting(false)
                if (!this.continue) {
                  this.gotoList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                this.changeSubmitting(false)
              }
            }).catch(result => {
              this.$Message.error({ content: '请求失败！状态码 ' + result.response.status + ' ' + result.response.statusText, duration: 6 })
              this.changeSubmitting(false)
            })
          } else {
            this.saveDt(this.dt).then(res => {
              if (res.data.isSuccess && res.data.statusCode === 200) {
                this.$Message.success({ content: '数据库表信息保存成功.', duration: 3 })
                this.reset()
                this.dt = getDt()
                this.dt.dtId = 'add'
                this.changeSubmitting(false)
                if (!this.continue) {
                  this.gotoList()
                }
              } else {
                this.$Message.error({ content: res.data.msg, duration: 6 })
                this.changeSubmitting(false)
              }
            }).catch(result => {
              this.$Message.error({ content: '请求失败！状态码 ' + result.response.status + ' ' + result.response.statusText, duration: 6 })
              this.changeSubmitting(false)
            })
          }
        } else {
          setTimeout(() => this.changeSubmitting(false), 3000)
        }
      })
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
