<template>
  <div class='findPassword'>
    <button onclick="document.getElementById('find_Password').style.display='block'" style='width:auto;'>Find Your Id</button>
    
    <div id='find_Password' class='modal'>
        <span @click='close' class='close'
            title='Close Modal'>&times;</span>
        <div class='modal-content'>
            <div class='container'>
                <h1>Find Your Id</h1>

                <p v-if="!(show)">Please fill in this form</p>
                <p v-if="show">찾으시는 아이디는 {{email}}</p>
                <hr>
                <label for='name'><b>Name</b></label>
                <input id='name' type='text' placeholder='Enter name' name='name' v-model.trim ='name' required>

                <label for='phone'><b>Phone Number</b></label>
                <input id='phone' type='text' placeholder='Enter PhoneNumber' name='phone' v-model ='phone' required>

                <div class='clearfix'>
                    <button type='button' @click='close' class='cancelbtn'>Cancel</button>
                    <button type='submit' @click='find' class='signupbtn'>Find</button>
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
      find: function(){
          this.email= '없습니다.';
          this.$axios.get('http://localhost:80/member/findid/'+this.name+'/'+this.phone,{
          }).then(res=>{
              if(res.data.status){
                  this.email= res.data.email
              }
          }).catch(function(){
              alert("아이디가 없습니다.");
          })
              this.show = true;
      },
      close: function(){
          this.name='';
          this.phone='';
          this.email= '없습니다.';
          this.show = false;
          document.getElementById('find_Password').style.display='none';
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
        width: 80%;
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


</style>