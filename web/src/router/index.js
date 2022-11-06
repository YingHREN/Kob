import { createRouter, createWebHistory } from 'vue-router'
import PkIndex from '../views/pk/PkIndex'
import RecordIndex from '../views/record/RecordIndex'
import RankListindex from '../views/ranklist/RankListindex'
import UserbotIndex from '../views/user/bots/UserbotIndex'
import NotFound from '../views/error/NotFound'
import UserAccountLoginView from '../views/user/account/UserAccountLoginView'
import UserAccountRegisterView from '../views/user/account/UserAccountRegisterView'
import store from '@/store'

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/pk/",
    meta:{
      requestAuth:true,
    }
  },
  {
    path: "/pk/",
    name: "PkIndex",
    component: PkIndex,
    meta:{
      requestAuth:true,
    }
  },
  {
    path: "/record/",
    name: "RecordIndex",
    component: RecordIndex,
    meta:{
      requestAuth:true,
    }
  },
  {
    path: "/RankList/",
    name: "RankListindex",
    component: RankListindex,
    meta:{
      requestAuth:true,
    }
  },
  {
    path: "/User/bot/",
    name: "UserbotIndex",
    component: UserbotIndex,
    meta:{
      requestAuth:true,
    }
  },
  {
    path: "/User/account/login/",
    name: "user_account_login",
    component: UserAccountLoginView,
    meta:{
      requestAuth:false,
    }
  },
  {
    path: "/User/account/register/",
    name: "user_account_register",
    component: UserAccountRegisterView,
    meta:{
      requestAuth:false,
    }
  },
  {
    path: "/404/",
    name: "NotFound",
    component: NotFound,
    meta:{
      requestAuth:false,
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/"
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next)=>{
  if(to.meta.requestAuth&&!store.state.user.is_login){
    next({name:"user_account_login"});
  }
  else{
    next();
  }
})
export default router
