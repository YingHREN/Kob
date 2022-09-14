import { createRouter, createWebHistory } from 'vue-router'
import PkIndex from '../views/pk/PkIndex'
import RecordIndex from '../views/record/RecordIndex'
import RankListindex from '../views/ranklist/RankListindex'
import UserbotIndex from '../views/user/bots/UserbotIndex'
import NotFound from '../views/error/NotFound'
const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/pk/",
  },
  {
    path: "/pk/",
    name: "PkIndex",
    component: PkIndex,
  },
  {
    path: "/record/",
    name: "RecordIndex",
    component: RecordIndex,
  },
  {
    path: "/RankList/",
    name: "RankListindex",
    component: RankListindex,
  },
  {
    path: "/User/bot/",
    name: "UserbotIndex",
    component: UserbotIndex,
  },
  {
    path: "/404/",
    name: "NotFound",
    component: NotFound,
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

export default router
