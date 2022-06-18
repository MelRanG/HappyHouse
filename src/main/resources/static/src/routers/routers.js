import Vue from "vue";
import VueRouter from "vue-router";
Vue.use(VueRouter)
import HouseView from "@/components/HouseView.vue";
import BookmarkView from "@/components/BookmarkView.vue";

import NoticeView from '@/components/community/NoticeView.vue';
import NoticeInsert from '@/components/community/notice/NoticeInsertView.vue';
import NoticeList from '@/components/community/notice/NoticeListView.vue';
import NoticeDetail from '@/components/community/notice/NoticeDetailView.vue';
import NoticeUpdate from '@/components/community/notice/NoticeUpdateView.vue';

import BoardView from '@/components/community/BoardView.vue';
import BoardInsert from '@/components/community/board/BoardInsertView.vue';
import BoardList from '@/components/community/board/BoardListView.vue';
import BoardDetail from '@/components/community/board/BoardDetailView.vue';
import BoardUpdate from '@/components/community/board/BoardUpdateView.vue';

import LoginView from '@/components/LoginView.vue';
import RegisterView from '@/components/RegisterView.vue';
import UserView from '@/components/UserView.vue';
import UserDetail from '@/components/user/UserDetail.vue';
import UserUpdate from '@/components/user/UserUpdate.vue';

import store from "@/store/store.js";

export default new VueRouter({
  routes: [
    {
      path: '/',
      component: HouseView,
    },
    {
      name: 'HouseView',
      path: '/house',
      component: HouseView,
    },
    {
      name: 'BookmarkView',
      path: '/bookmark',
      component: BookmarkView,
      beforeEnter: (to, from, next) => {
        if (!store.state.login.isLogin) {
          next("/login")
        } else {
          return next();
        }
      },
    },
    {
      path: '/community',
      component: NoticeView,
      redirect: '/notice',
    },
    {
      name: 'NoticeView',
      path: '/notice',
      component: NoticeView,
      redirect: '/notice/list',
      children: [
        {
          name: 'NoticeList',
          path: 'list',
          component: NoticeList,
        },
        {
          name: 'NoticeInsert',
          path: 'insert',
          component: NoticeInsert,
        },
        {
          name: 'NoticeDetail',
          path: 'detail/:id',
          component: NoticeDetail,
        },
        {
          name: 'NoticeUpdate',
          path: 'update/:id',
          component: NoticeUpdate,
        },
      ]
    },
    {
      name: 'BoardView',
      path: '/board',
      component: BoardView,
      redirect: '/board/list',
      children: [
        {
          name: 'BoardList',
          path: 'list',
          component: BoardList,
        },
        {
          name: 'BoardInsert',
          path: 'insert',
          component: BoardInsert,
        },
        {
          name: 'BoardDetail',
          path: 'detail/:id',
          component: BoardDetail,
        },
        {
          name: 'BoardUpdate',
          path: 'update/:id',
          component: BoardUpdate,
        },
      ]
    },
    {
      name: 'LoginView',
      path: '/login',
      component: LoginView,
    },
    {
      name: 'RegisterView',
      path: '/register',
      component: RegisterView,
    },
    {
      name: 'UserView',
      path: '/user',
      component: UserView,
      redirect: '/user/detail',
      beforeEnter: (to, from, next) => {
        if (!store.state.login.isLogin) {
          next("/login")
        } else {
          return next();
        }
      },
      children: [
        {
          name: 'UserDetail',
          path: 'detail',
          component: UserDetail,
        },
        {
          name: 'UserUpdate',
          path: 'update',
          component: UserUpdate,
        },

      ],

    }
  ]
})