import Vue from 'vue'
import Router from 'vue-router'
import Preview from "../components/Preview";
import Dashboard from "../components/Dashboard";
import AddPage from "../components/AddPage";
import NavigationPage from "../components/NavigationPage";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Dashboard',
      component: Dashboard,
      children:[{
        path:'/preview',
        name:'Preview',
        component:Preview
      },{
        path: '/addPage',
        name:'addPage',
        component: AddPage
      },
        {
          path: '/navigator',
          name:'NavigationPage',
          component: NavigationPage
        }]
    },
  ]
})
