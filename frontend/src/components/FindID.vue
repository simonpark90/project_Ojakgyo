<template>
  <div class='findID'>
    <p onclick="document.getElementById('find_Id').style.display='block'">
    <a style='fontSize:16px; color: black;'>Forgot Id? Click here</a>
    </p>
    <div id='find_Id' class='modal'>
        <span @click='close' class='close'
            title='Close Modal'>&times;</span>
        <div class='modal-content'>
            <div class='container'>
                <div class='container-title'>Find Your Id</div>
                <div class='container-head'>
                <p v-if="!show">Please fill in this form</p>
                <div v-if="show"><h1>찾으시는 아이디는 {{email}}</h1></div>
                </div>
                <hr>
                <div class='container-form'>
                <input id='name' type='text' placeholder='Enter name' name='name' v-model.trim ='name' required />
                </div>
                <div class='container-form'>
                <input id='phone' type='text' placeholder='Enter PhoneNumber' name='phone' v-model ='phone' required />
                </div>
                <div class='clearfix'>
                    <button type='submit' @click='find' class='btn'>Find</button>
                    <button type='button' @click='close' class='btn'>Cancel</button>
                </div>
            </div>
        </div>
    </div>
  </div>
</template>

<script src='https://unpkg.com/axios/dist/axios.min.js'></script>
<script>
let modal = document.getElementById('find_Id');

window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = 'none';
            }
        }

export default {
  name: 'FindID',
  data(){
      return{
        name:'',
        phone:'',
        email:'없습니다.',
        show: false
      }
  },
  methods: {
      find(){
          this.email= '없습니다.';
          this.$axios.get('http://localhost:80/members/info/'+this.name+'/'+this.phone,{
          }).then(res=>{
              this.email= res.data;
          }).catch(function(){
              alert("아이디가 없습니다.");
          })
              this.show = true;
      },
      close(){
          this.name='';
          this.phone='';
          this.email= '없습니다.';
          this.show = false;
          document.getElementById('find_Id').style.display='none';
      }
  }

}
</script>

<style>
    /* The Modal (background) */
    .modal {
        display: none;
        /* Hidden by default */
        position: fixed;
        /* Stay in place */
        z-index: 1;
        /* Sit on top */
        left: 0;
        top: 0;
        width: 100%;
        /* Full width */
        height: 100%;
        /* Full height */
        overflow: auto;
        /* Enable scroll if needed */
        background-color: #474e5d;
        padding-top: 50px;
    }
    /* Modal Content/Box */
    .modal-content {
        background-color: #fefefe;
        margin: 5% auto 15% auto;
        /* 5% from the top, 15% from the bottom and centered */
        border: 1px solid #888;
        width: 40%;
        /* Could be more or less, depending on screen size */
    }

    .close {
        position: absolute;
        right: 35px;
        top: 15px;
        font-size: 40px;
        font-weight: bold;
        color: #f1f1f1;
    }

    .container-title{
        font-size: 30px;
        margin-top: 15px;
        margin-bottom: 15px;
    }

    .container-head {
        font-size: 20px;
        margin-top: 15px;
        margin-bottom: 15px;
    }

    .container-form {
        float: left;
        width: 100%;
        margin: 0 0 15px;
        position: relative;
    }

    .login-form input {
        width: 80%;
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

    .btn{
        background: #8C54A1;
        border-color: #45aba6;
        color: #fff;
        text-align: center;
        margin: 0 auto;
        font-size: 15px;
        border: 1px solid #8C54A1;
        width: 50%;
        height: 35px;
        cursor: pointer;
    }

    .btn.btn:hover {
        background-color: #4B2354;
        color: white;
        box-shadow: 0 0 30px #8C54A1;
    }
</style>