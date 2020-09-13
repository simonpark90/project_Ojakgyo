<template>
  <div class='signIn'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <div class="login-form">
      <div class='example'>
        <h1>SIGN IN</h1>
        <div class="form-group">
          <input type="email" name="email" v-model='email' placeholder="E-mail Address">
          <span class="input-icon"><i class="fa fa-envelope"></i></span>
        </div>
        <div class="form-group">
          <input type="password" name="psw" v-model='password' placeholder="Password">
          <span class="input-icon"><i class="fa fa-lock"></i></span>
        </div>      
        <button class="login-btn" @click="signin">SIGN IN</button>      
        <a class="reset-psw" href="#"><Find></Find></a>
        <div class="seperator"><b>or</b></div>
        <p>Sign in with your social media account</p>
        <!-- Social login buttons -->
        <div class="social-icon">
          <button type="button"><i class="fa fa-google"></i></button>
          <button type="button"><i class="fa fa-facebook"></i></button>
        </div>
      </div>
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
        signin(){
            storage.setItem("jwt-auth-token","")
            storage.setItem("login_email","");

            this.$axios.post('http://localhost:80/auth',{
                email: this.email,
                password: this.password
            }).then(res=>{
                if(res.data.status){
                    this.nickname=res.data.data.nickname;
                    storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"]);
                    storage.setItem("login_email", res.data.data.email);
                    storage.setItem("nickname",res.data.data.nickname);
                    alert(res.data.data.nickname+'님 환영합니다.');
                    this.$EventBus.$emit('completeSign');
                    location.href = '/'
                }
            }).catch(error=>{
                alert("로그인 실패"+error);
            })
        },
        signout(){
            storage.setItem("jwt-auth-token","");
            storage.setItem("login_email","");
            storage.setItem("nickname","")

            this.email='';
            this.password='';
            this.nickname='';
            alert("로그아웃 되셨습니다.");
            this.$EventBus.$emit('completeSign');
            location.href = '/';
        }
    },
    created(){
        this.$EventBus.$on('signout',() =>{
            this.signout();
        })
    }
}
</script>

<style lang='stylus' scoped>
.signIn {
  height : 700px;  
  background-image: url('../assets/signInBackground.jpg');
  background-size: 100% 100%;
  font-family: ourFont;
  text-align: center;
}

.login-form h1 {
  font-size: 36px;
  text-align: center;
  color: #8C54A1;
  margin-bottom: 30px;
  font-weight: normal;
}

.login-form .social-icon {
    width: 100%;
    font-size: 20px;
    padding-top: 20px;
    color: #fff;
    text-align: center;
    float: left;
}

.login-form {
    background-color: rgba(213,213,213,0.5);
    width: 450px;
    border-radius: 6px;
    margin-top: 50px;
    display: inline-block;
    padding: 15px 30px 30px;
    box-sizing: border-box;
}

.form-group {
  float: left;
  width: 100%;
  margin: 0 0 15px;
  position: relative;
}

.login-form input {
    width: 100%;
    padding: 5px;
    height: 56px;
    border-radius: 74px;
    border: 1px solid #8C54A1;
    box-sizing: border-box;
    font-size: 15px;
    padding-left: 75px;
}

.login-form input:focus{
  background: #fff;
  box-shadow: 0;
  border-color: #8C54A1;
  border-radius: 10px;
  outline: none;
}

.login-form .form-group .input-icon {
    font-size: 15px;
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    align-items: center;
    position: absolute;
    border-radius: 25px;
    bottom: 0;
    height: 100%;
    padding-left: 35px;
    color: #666;
}

.login-form .login-btn {
  background: #8C54A1;
  padding: 11px 50px;
  border-color: #45aba6;
  color: #fff;
  text-align: center;
  margin: 0 auto;
  font-size: 20px;
  border: 1px solid #8C54A1;
  border-radius: 44px;
  width: 100%;
  height: 56px;
  cursor: pointer;
}

.login-form .login-btn:hover{
  background-color: #4B2354;
  color: white;
  box-shadow: 0 0 30px #8C54A1;
}

.login-form .reset-psw {
  float: left;
  width: 100%;
  text-decoration: none;
  color: #8C54A1;
  font-size: 14px;
  text-align: center;
  margin-top: 11px;
}

.login-form .social-icon button {
    font-size: 20px;
    color: white;
    height: 50px;
    width: 50px;
    background: #8C54A1;
    border-radius: 60%;
    margin: 0px 10px;
    border: none;
    cursor: pointer;
}

.login-form button:hover{
  opacity: 0.9;
}

.login-form .seperator {
  float: left;
  width: 100%;
  border-top: 1px solid #ccc;
  text-align: center;
  margin: 50px 0 0;
}

.login-form .seperator b {
  width: 40px;
  height: 40px;
  font-size: 16px;
  text-align: center;
  line-height: 40px;
  background: #fff;
  display: inline-block;
  border: 1px solid #e0e0e0;
  border-radius: 50%;
  position: relative;
  top: -22px;
}

.login-form p {
  float: left;
  width: 100%;
  text-align: center;
  font-size: 16px;
  margin: 0 0 10px;
}

@media screen and (max-width:767px) {
.login-form {
    width: 90%;
    padding: 15px 15px 30px;
}
}
</style>