import axios_http from 'axios'

export const axios = axios_http

export default {
    install (Vue) {
        Vue.prototype.$axios = axios_http
    }
}