import request from '@/utils/request'

export function allRole() {
  return request({
    url: '/autoperm/role/allRoles',
    method: 'get',
  })
}

export function list(params) {
  return request({
    url: '/autoperm/role/list',
    method: 'get',
    params:params
  })
}

export function remove(rid) {
  return request({
    url: '/autoperm/role/remove/'+rid,
    method: 'delete',
  })
}

export function add(uid,rid) {
  return request({
    url: '/autoperm/userrole/add',
    method: 'put',
    params:{
      uid:uid,
      rid:rid
    }
  })
}
