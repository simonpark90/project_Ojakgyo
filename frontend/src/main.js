import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import VueAxios from './plugins/axios'

import "./css/w3.css";    
import "./css/w3-theme-black.css"; 

Vue.use(VueAxios);

Vue.prototype.$EventBus = new Vue();
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
