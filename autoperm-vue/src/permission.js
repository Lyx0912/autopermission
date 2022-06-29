import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'
import Layout from "@/layout";

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // no redirect whitelist

export function activeRouter(permissions){
  let root = []
  console.log(permissions)
  permissions.forEach(perm=>{
    let obj =   {
        path: perm.path,
        component : Layout,
        meta: { title: perm.permName, icon: perm.icon },
      }
      if(perm.children !== ''){
        perm.children.forEach(chil=>{
          obj.children = []
          obj.children.push({
            path: chil.path,
            name: 'userManager',
            component: () => import('@/views/user/manager/index'),
            meta: { title: chil.permName, icon: chil.icon }
          })
        })
      }

    // 如果有子菜单
    // if(perm.children.length > 0){
    //   selectChildren(perm.children,obj.children);
    // }
    router.options.routes.push(obj)
    console.log(router.options.routes)
    root.push(obj)
  })

  router.addRoutes(root)
}
function selectChildren(children,arr){
    children.forEach(item=>{
      let obj = {
        path : item.path,
        name : item.permName,
        component: () => import("@/views"+item.path),
        meta: { title: item.permName, icon: 'dashboard' },
        children:[]
      }
      arr.push(obj)
      // 如果有子菜单
      if(item.children.length > 0){
        selectChildren(item.children,obj.children);
      }
    })
}

router.beforeEach(async(to, from, next) => {

  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done()
    } else {
      const hasGetUserInfo = store.getters.name
      if (hasGetUserInfo) {
        next()
      } else {
        try {
          // get user info
          await store.dispatch('user/getInfo').then(()=>{
            // 发起请求，构建路由和菜单
            store.dispatch('permission/createRoutes').then(menus=>{
              router.addRoutes(menus) // 动态添加可访问路由表
              next({ ...to, replace: true }) // hack方法 确保addRoutes已完成
            })
          })
          next()
        } catch (error) {
          // remove token and go to login page to re-login
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
