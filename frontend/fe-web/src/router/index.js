import Vue from "vue";
import Router from "vue-router";

// import * as CONFIG from '../config/index';

//frontend
import HomePage from "@/views/frontend/HomePage";


Vue.use(Router);

export default new Router({
  // scroll to top
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  },
  routes: [
    {
      path: "/",
      name: "HomePage",
      component: HomePage,
      props: {
        showSelectSpace: true
      }
    },
    //admin
    {
      path: '/admin',
      name: 'default',
      meta: {
        authRequired: true,
      },
      component: () => import('@/views/admin/dashboards'),
    },

    //=============== router For tools =======
    {
      path: "/diagram",
      name: "Diagram",
      component: () => import('@/views/frontend/tools/diagram'),
      props: {
        showSelectSpace: false
      }
    },   
  ]
});