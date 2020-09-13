<template>
  <div id="upload">
    <ImageComponent />
  </div>
</template>

<script>
import ImageComponent from '@/components/FilePond.vue' 
const storage = window.sessionStorage;

export default {
  name: 'upload',
  components: {
    ImageComponent
  },
  created(){
    if(storage.getItem('jwt-auth-token') == '') {
      alert('전시물 작성은 로그인 된 회원만 가능합니다.')
      window.location = '/Signin';
    }
  }
}
</script>

<style>
#upload {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>