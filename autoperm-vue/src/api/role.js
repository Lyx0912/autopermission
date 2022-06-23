import request from '@/utils/request'

export function allRole() {
  return request({
    url: '/autoperm/role/allRoles',
    method: 'get',
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
