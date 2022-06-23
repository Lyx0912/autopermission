import request from '@/utils/request'

export const getRouters = () => {
  return request({
    url: '/autoperm/permisson/createRouter',
    method: 'get'
  })
}
