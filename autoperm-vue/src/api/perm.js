import request from '@/utils/request'

export const getRouters = () => {
  return request({
    url: '/autoperm/user/createRouter',
    method: 'get'
  })
}
