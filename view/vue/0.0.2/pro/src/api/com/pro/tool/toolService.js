export const getParameter = () => {
  return {
    pro: +new Date(),
    action: '',
    primaryKey: '',
    rows: 10,
    page: 1,
    token: ''
  }
}

export const getHeaders = () => {
  return {
    headers: {
      'Content-Type': 'application/json'
    }
  }
}

export const getUrl = (parameter) => {
  let url = '?pro=' + parameter.pro
  if (parameter.action && parameter.action !== '') {
    url = url + '&action=' + parameter.action
  }
  if (parameter.primaryKey && parameter.primaryKey !== '') {
    url = url + '&primaryKey=' + parameter.primaryKey
  }
  if (parameter.rows && parameter.rows != null) {
    url = url + '&rows=' + parameter.rows
  }
  if (parameter.page && parameter.page != null) {
    url = url + '&page=' + parameter.page
  }
  if (parameter.token && parameter.token !== '') {
    url = url + '&token=' + parameter.token
  }
  return url
}

export const getPagingUrl = (parameter, rows, page) => {
  let url = '?pro=' + parameter.pro
  if (parameter.action && parameter.action !== '') {
    url = url + '&action=' + parameter.action
  }
  if (parameter.primaryKey && parameter.primaryKey !== '') {
    url = url + '&primaryKey=' + parameter.primaryKey
  }
  if (parameter.rows && parameter.rows != null) {
    if (rows > 0) {
      parameter.rows = rows
    }
    url = url + '&rows=' + parameter.rows
  }
  if (parameter.page && parameter.page != null) {
    if (page > 0) {
      parameter.page = page
    }
    url = url + '&page=' + parameter.page
  }
  if (parameter.token && parameter.token !== '') {
    url = url + '&token=' + parameter.token
  }
  return url
}
