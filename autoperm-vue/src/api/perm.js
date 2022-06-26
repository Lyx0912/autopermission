import request from '@/utils/request'

export const getRouters = () => {
  return request({
    url: '/autoperm/permisson/createRouter',
    method: 'get'
  })
}

export const list = () => {
  return request({
    url: '/autoperm/permisson/list',
    method: 'get'
  })
}

// 菜单详情
export function getInfo(id){
  return request({
    url: '/autoperm/permisson/info/'+id,
    method: 'get',
  })
}


// 添加菜单
export function add(perm){
  return request({
    url: '/autoperm/permisson/add',
    method: 'put',
    data: perm
  })
}

// 更新菜单
export function update(perm){
  return request({
    url: '/autoperm/permisson/update',
    method: 'put',
    data: perm
  })
}

// 删除菜单
export function remove(ids){
  return request({
    url: '/autoperm/permisson/remove/'+ids,
    method: 'delete',
  })
}
