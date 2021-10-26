import 'core-js/stable'
import Vue from 'vue'
import App from './App'
import router from './router'
import CoreuiVue from '@coreui/vue'
import { iconsSet as icons } from './assets/icons/icons.js'
import store from './store/store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'
import VueCookies from 'vue-cookies'
import * as CONFIG from "@/config/index"

Vue.config.performance = true
Vue.use(CoreuiVue)
Vue.use(ElementUI);
Vue.use(ElementUI, { locale })
Vue.prototype.$log = console.log.bind(console)
Vue.use(VueCookies)

router.beforeEach( (to, from, next) => {
  let check = false;
  console.log("[main.js] to.name : " + to.name);
  console.log("[main.js] accessToken = Vue.$cookies.get('accessToken') : " + Vue.$cookies.get('accessToken'));
  if(to.name !== 'Login'){
    check = Vue.$cookies.isKey('accessToken')
  }else{
    check = true;
  }
  console.log("[main.js] check : " + check);
  if(!check){
    window.location.href = CONFIG.CLIENT_URL
  }else{  
      next();      
  }

})


new Vue({
  el: '#app',
  router,
  store,
  icons,
  template: '<App/>',
  components: {
    App
  }
})
