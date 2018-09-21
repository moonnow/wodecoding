import dtService from '@/api/com/pro/code/plugin/dt/dtService'

export default {
  namespaced: true,
  state: {
    primaryKey: 'qwe',
    submitting: false,
    continue: null,
    dataSet: [],
    spinShow: false,
    selectDataRow: [],
    selectDataRowLength: 0
  },
  mutations: {
    changeSubmitting (state, payload) {
      state.submitting = payload
    },
    changeContinue (state, payload) {
      state.continue = payload
    },
    setDataSet (state, payload) {
      state.dataSet = payload
    },
    changeSpinShow (state, payload) {
      state.spinShow = payload
    },
    setSelectDataRow (state, payload) {
      state.selectDataRow = payload
      state.selectDataRowLength = payload.length
    }
  },
  actions: {
    saveDt ({ commit, state }, dt) {
      return new Promise((resolve, reject) => {
        dtService.save(dt).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getAllDt ({ commit, state }) {
      return new Promise((resolve, reject) => {
        dtService.getAll().then(res => {
          // commit('setDataSet', res.data.data)
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    batchRemoveDt ({ commit, state }, dtList) {
      return new Promise((resolve, reject) => {
        dtService.batchRemove(dtList).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    updateDt ({ commit, state }, dt) {
      return new Promise((resolve, reject) => {
        dtService.update(dt).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    },
    getDtByPk ({ commit, state }, primaryKey) {
      return new Promise((resolve, reject) => {
        dtService.getByPk(primaryKey).then(res => {
          resolve(res)
        }).catch(err => {
          reject(err)
        })
      })
    }
  },
  getters: {
  }
}
