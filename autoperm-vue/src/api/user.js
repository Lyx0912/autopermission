import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/autoperm/user/login',
    method: 'post',
    data:data
  })
}

export function list(queryParams){
  return request({
    url: '/autoperm/user/list',
    method:'get',
    params:queryParams
  })
}

// 删除角色
export function removeRole(id,role){
  return request({
    url: '/autoperm/user/removeRole',
    method:'delete',
    params:{
      role : role,
      id:id
    }
  })
}

// 更新用户
export function update(user){
  return request({
    url: '/autoperm/user/edit',
    method:'put',
    data : user
  })
}

// 删除用户
export function rm(ids) {
  return request({
    url: '/autoperm/user/remove/'+ids,
    method: 'delete',
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
