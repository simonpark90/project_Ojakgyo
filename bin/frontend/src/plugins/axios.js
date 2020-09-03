import axios_http from 'axios'

// insert all your axios logic here

export const axios = axios_http

export default {
    install (Vue) {
        Vue.prototype.$axios = axios_http
    }
}