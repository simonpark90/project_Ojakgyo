<template>
   <div class="signin">
    <h3>직원 로그인 화면</h3>
   
      {{message}}<br><br>
      
      아이디 : <input type="email" name="email" id="email" v-model="email" >
      비번 : <input type="password" name="login" id="pass" v-model="password" > 
      <input type="submit" @click="login" value="로그인" id="login">
      <button id="logout" @click="logout">로그아웃</button>
      
      <br><hr><br>
   
        <button id="getInfo" @click="getInfo">상세 정보 확인</button>
   
      <br><hr><br>
      
      <table>
         <tr><td id="status">1. 로그인 or 로그아웃 상태  : {{status}} </td></tr>
         <tr><td id="token">2. 토큰 정보 : {{token}} </td></tr>
         <tr><td id="info">3. 로그인 후 검색된 직원 정보  : {{info}} </td></tr>
         <tr><td id="detailInfo">4. 로그인 후 상세 정보 보기  : {{detailInfo}} </td></tr>
      </table>
        
      <br><hr><br>
      <a v-if="result" href="http://www.google.com">인증된 직원만 사용하는 서비스</a>
  </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: 'Signin',
  data(){
    return{
          email: '',
          password: '',
          message: '로그인 하세요',
          status: '',
          token: '',
          info: '',
          detailInfo: '',
          result: false
      }}
      ,methods: {
        setInfo: function(status,token,info){
          this.status = status;
          this.token = token;
          this.info = info;
          this.result = true;
        },
        setDetailInfo: function(status, token, info, detailInfo){
          this.status = status;
          this.token = token;
          this.info = info;
          this.detailInfo = detailInfo
        },
        logout: function(){
          storage.setItem("jwt-auth-token","");
          storage.setItem("login_email","");

          this.email = "";
          this.password = "";

          this.message = "로그인 해주세요";
          this.result = false;

          this.setDetailInfo("로그아웃 성공","","")
        },
        getInfo: function(){
          alert(storage.getItem("jwt-auth-token"));
          this.$axios.post('http://localhost:80/api/info',{
            email: this.email,
            password: this.password
          },
          { 
            headers: {
               "jwt-auth-token" : storage.getItem("jwt-auth-token"), 
              // 'Access-Control-Allow-Header' : storage.getItem("jwt-auth-token")
            }
          }).then(res => {
            this.setDetailInfo("정보조회성공", storage.getItem("jwt-auth-token"), this.info, JSON.stringify(res.data))
          }).catch(e => {
            alert('catch')
            this.setDetailInfo("정보 조회 실패", "", e.response.data.msg);
          })
        },
        login: function(){

          alert('본 애플리케이션이 실행되고 있는 출처 : '+location.origin);

          // const headers = new Headers({
          // // 'Content-Type': 'application/json',
          
          // });
          // fetch('http://localhost:80/api/logincheck/signin', { headers });

          // const headers = new Headers({
          //   'Content-Type': 'text/xml',
          // });
          // fetch('https://evanmoon.tistory.com/rss', { headers });

          storage.setItem("jwt-auth-token","");
          storage.setItem("login_email","");

          this.$axios.post('http://localhost:80/api/logincheck/signin',{
            email: this.email,
            password: this.password
          }).then(res=>{
            if(res.data.status){
              this.email='';
              this.password='',
              this.message= res.data.data.email+"로 로그인 되었습니다.";
              this.setInfo("로그인 성공", res.headers["jwt-auth-token"], JSON.stringify(res.data.data));

              alert('resheaders'+res.headers["jwt-auth-token"]);
              alert(JSON.stringify(res.data.data));


              storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"]);
              storage.setItem("login_email", res.data.data.email);
            }else{
              this.setInfo("","","");
              this.message = '로그인 하세요';
            }

          }).catch(e => {
            this.setInfo("실패", "", JSON.stringify(e.response || e.message));
          });
        },
        init: function(){
          if(storage.getItem("jwt-auth-token")){
            this.message = storage.getItem("login_email") + "로 로그인 되었습니다.";
          }else{
            storage.setItem("jwt-auth-token","")
          }
        },
        mounted: function(){
          this.init();
        }
        
      }
    }
</script>