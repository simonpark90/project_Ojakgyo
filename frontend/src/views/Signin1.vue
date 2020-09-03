<template>
<div class='sign'>
    <div>
        <img alt='Vue logo' src='../assets/jisoo2.jpg'>
    </div>
    <div>
        <input type='text' id='email' v-model='email' placeholder="email" required>
    </div>
    <div>
        <input type='text' id='password' v-model='password' placeholder="password" required>
    </div>
    <div>
        <button @click="signin">Sign IN </button>
    </div>
    <div>
        <Find></Find>
    </div>
</div>
</template>

<script>
import Find from "@/components/FindID.vue";
const storage = window.sessionStorage;

export default {
    name: 'sign',
    components:{
        Find
    },
    data() {
        return {
            email: '',
            password: '',
            nickname: '',
            status:'',
            token: ''
        }
    },
    methods: {
        signin: function(){
            storage.setItem("jwt-auth-token","")
            storage.setItem("login_email","");

            this.$axios.post('http://localhost:80/api/logincheck/signin',{
                email: this.email,
                password: this.password
            }).then(res=>{
                if(res.data.status){
                    this.nickname=res.data.data.nickname;
                    storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"]);
                    storage.setItem("login_email", res.data.data.email);
                    alert(res.data.data.nickname+'님 환영합니다.'); //닉네임으로 확인
                    this.$EventBus.$emit('completeSign');
                }
            }).catch(function(error){
                alert("로그인 실패"+error);
            })
        },
        signout: function(){
            storage.setItem("jwt-auth-token","");
            storage.setItem("login_email","");

            this.email='';
            this.password='';
            this.nickname='';
            alert("로그아웃 되셨습니다.");
            this.$EventBus.$emit('completeSign');
            location.href = '/';  //경로 지정 해야함
        }
    },
    created(){
        this.$EventBus.$on('signout',() =>{
            this.signout();
        })
    }
}
</script>

<style scoped>

</style>