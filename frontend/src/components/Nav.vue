<template>
    <div class="wrap">
      <div class="header">
        <div class="nav">
          <ul>
            <li>
              <router-link to ="/Artwork" style = "font-family: webisfree;font-size:25px">ArtWorks</router-link>
            </li>
            <li>
             <router-link to ="/Upload" style = "font-family: webisfree;font-size:25px">Upload</router-link>
            <li>
             <router-link to ="/Signin" v-if="!check" style = "font-family: webisfree;font-size:25px">Sign in</router-link>
              <router-link to ="/Profile" v-if="check" style = "font-family: webisfree;font-size:25px">My profile</router-link>
            </li>
            <li>
              <router-link to ="/Signup" v-if="!check" style = "font-family: webisfree;font-size:25px">Sign up</router-link>
              <router-link to ="/Signin" v-if="check" @click.native="nav_signout" style = "font-family: webisfree;font-size:25px">Sign out</router-link>
            </li>
            <div class="clear"></div>
          </ul>
        </div>
        <div class="clear"></div>
      </div>

  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
   name : 'Wrap',
   data(){
      return{
         check: false
      }
   },methods: {
      nav_signout(){
         this.check = false;
         this.$EventBus.$emit('signout');
      }
   },
   watch: {
      signCheck(){
         storage.getItem("jwt-auth-token")!= '' ? this.check=true : this.check=false
      }
   },
   created(){
      this.$EventBus.$on('completeSign',() => {
         this.check = !this.check;
      });
      storage.getItem("jwt-auth-token")!= '' ? this.check=true : this.check=false
   }
}
</script>

<style scoped>
.nav{
   margin-top:13px;
   font-size: 20px;   
}
</style>