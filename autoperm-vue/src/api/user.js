import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/autoperm/user/login',
    method: 'post',
    data:data
  })
}

export function getInfo(token) {
  return request({
    url: '/autoperm/user/info',
    method: 'get',
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
