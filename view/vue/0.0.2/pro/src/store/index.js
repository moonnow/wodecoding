import Vue from 'vue'
import Vuex from 'vuex'

import user from './module/user'
import app from './module/app'
import dtStore from './com/pro/code/plugin/dt/dtStore'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user,
    app
  }
})

store.registerModule('dtStore', dtStore)

export default store
