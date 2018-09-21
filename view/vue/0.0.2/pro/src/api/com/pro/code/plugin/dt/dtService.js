import axios from 'axios'

import { getParameter, getHeaders, getUrl } from '@/api/com/pro/tool/toolService'

const url = '/pro/code/plugin/dt'

export default {
  save: (dt) => {
    let parameter = getParameter()
    parameter.action = 'save'
    return axios.post(url + getUrl(parameter), dt, getHeaders())
  },
  getAll: () => {
    let parameter = getParameter()
    parameter.action = 'get_all'
    return axios.post(url + getUrl(parameter), getHeaders())
  },
  batchRemove: (dtList) => {
    let parameter = getParameter()
    parameter.action = 'batch_remove'
    let httpOptions = getHeaders()
    httpOptions.data = dtList
    return axios.delete(url + getUrl(parameter), httpOptions)
  },
  update: (dt) => {
    let parameter = getParameter()
    parameter.action = 'update'
    return axios.put(url + getUrl(parameter), dt, getHeaders())
  },
  getByPk: (primaryKey) => {
    let parameter = getParameter()
    parameter.action = 'get_by_pk'
    parameter.primaryKey = primaryKey
    return axios.get(url + getUrl(parameter), getHeaders())
  }
}
