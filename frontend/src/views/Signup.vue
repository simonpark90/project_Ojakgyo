<template>
  <div class="about">
    <div id="id01" class="modal">
        <span onclick="document.getElementById('id01').style.display='none';window.location='/';" class="close" title="Close Modal">&times;</span>
        <ValidationObserver v-slot="{ invalid }">        
            <form class="modal-content">
                <div class="container">
                    <h1 class = "signup">Sign Up</h1>
                    <br><hr>
                    <validation-provider rules = "required|email" v-slot = "v">
                        <label class = "labelForMagin" id = "emailLabel" for="email"><b>Email</b></label><span class = "vali-err">{{v.errors[0]}}</span><br>
                        <span id = "emailSpan"></span>
                        <input id="email" type="text" @keyup = "emailCheck()" placeholder="Enter Email" name="email" v-model.trim ="info.email" required>
                    </validation-provider>
                <ValidationObserver>
                    <validation-provider rules = "required|alpha_num|confirmed:confirmation|min:8|max:16|" v-slot = "v">
                        <label class = "labelForMagin" for="psw"><b>Password</b></label><span class = "vali-err">{{v.errors[0]}}</span>
                        <input id="psw" type="password" placeholder="Enter Password" name="psw" v-model ="info.psw" required>
                    </validation-provider>
                    <validation-provider vid = "confirmation" v-slot = "v">
                        <label class = "labelForMagin" for="psw-repeat"><b>Repeat Password</b></label> <span class = "vali-err">{{v.errors[0]}}</span>
                        <input type="password" placeholder="Repeat Password" v-model = "info.psw2" name="psw-repeat" required>
                    </validation-provider>
                </ValidationObserver>

                <validation-provider rules = "required|alpha_num" v-slot = "v">
                    <label class = "labelForMagin" for="name"><b> name</b></label><span class = "vali-err">{{v.errors[0]}}</span>
                    <input id="name" type="text" placeholder="Enter Name" name="name" v-model.trim ="info.name" required>
                </validation-provider>

                <validation-provider rules = "required|alpha_num|min:4|max:12" v-slot = "v">
                    <label class = "labelForMagin" for="nickname"><b>nickname</b></label><span class = "vali-err">{{v.errors[0]}}</span>
                    <input id="nickname" type="text" placeholder="Enter nickname" name="nickname" v-model.trim ="info.nickname" required>
                </validation-provider>

                <validation-provider rules = "required|numeric|min:11|max:11" v-slot = "v">
                    <label class = "labelForMagin" for="phone"><b>phone</b></label><span class = "vali-err">{{v.errors[0]}}</span>
                    <input id="phone" type="text" placeholder="Enter phone" name="phone" v-model.trim ="info.phone" required>
                </validation-provider>
                    <div class="clearfix">
                        <button type="button" onclick="document.getElementById('id01').style.display='none';window.location='/';"
                            class="cancelbtn">Cancel</button>
                        <button type="submit" :disabled="invalid" @click="submitForm()" class="signupbtn">Sign Up</button>
                    </div>
                </div>
            </form>
        </ValidationObserver>
    </div>
  </div>
</template>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
import { ValidationProvider, ValidationObserver} from 'vee-validate/dist/vee-validate.full.esm';
let modal = document.getElementById('id01');
window.onclick = event => {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
export default {
  name: 'signup',
  data(){
    return {
      info: {
              email: '',
              psw: '',
              name: '',
              nickname: '',
              phone: '',
              psw2:''
        }
      }
    },
   methods: {
        submitForm() {
        this.$axios({
            method: 'post',
            url: 'http://localhost:80/members',
            data: {
                email:this.info.email,
                name:this.info.name,
                nickname:this.info.nickname,
                password:this.info.psw,
                phone:this.info.phone
            }
        }).then(function(response){
            alert('회원 가입 완료!')
            window.location = '/';
        })
    },
        emailCheck(){
            if(String(this.info.email).indexOf('@')==-1) return 1;
            this.$axios({
            method: 'get',
            url: 'http://localhost:80/members/' + this.info.email + '/isdupl',
            data :  {
                email : this.info.email
            }
            }).then(function(response){
            if(response.data==1){
                document.getElementById('emailSpan').innerText = "Already used"
                document.getElementById('emailLabel').style.color = "Red"
            }else{
                document.getElementById('emailSpan').innerText = "Available email account"
                document.getElementById('emailSpan').style.color = "Green"
                document.getElementById('emailLabel').style.color = "Green"
            }
        }).catch(e => {
            console.log('catch')
        })
        }
    },
    mounted(){
        document.getElementById('id01').style.display='block'
    },
    components :{
        ValidationProvider,
        ValidationObserver
    }


}
</script>

<style scoped>
    b {
        font-weight: bold;
    }
    button.signupbtn {
        font-size: 17px;
    }

    button.cancelbtn {
        font-size: 17px;
    }

    label.labelForMagin {
       margin : 0px 0px 0px 5px; 
    }

    h1.signup {
        font-size: 30px;
        font-weight: bold;
    }

    span.vali-err {
       color : red;
       margin : 0px 0px 0px 20px;
    }

    body {
        font-family: Arial, Helvetica, sans-serif;
    }

    * {
        box-sizing: border-box;
    }

    /* Full-width input fields */
    input[type=text],
    input[type=password] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    /* Add a background color when the inputs get focus */
    input[type=text]:focus,
    input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }

    /* Set a style for all buttons */
    button {
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
        opacity: 0.9;
    }

    button:hover {
        opacity: 1;
    }

    /* Extra styles for the cancel button */
    .cancelbtn {
        padding: 14px 20px;
        background-color: #f44336;
    }

    /* Float cancel and signup buttons and add an equal width */
    .cancelbtn,
    .signupbtn {
        float: left;
        width: 50%;
    }

    /* Add padding to container elements */
    .container {
        padding: 16px;
    }

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

    /* Style the horizontal ruler */
    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
    }

    /* The Close Button (x) */
    .close {
        position: absolute;
        right: 35px;
        top: 15px;
        font-size: 40px;
        font-weight: bold;
        color: #f1f1f1;
    }

    .close:hover,
    .close:focus {
        color: #f44336;
        cursor: pointer;
    }

    /* Clear floats */
    .clearfix::after {
        content: "";
        clear: both;
        display: table;
    }

    /* Change styles for cancel button and signup button on extra small screens */
    @media screen and (max-width: 300px) {

        .cancelbtn,
        .signupbtn {
            width: 100%;
        }
    }
</style>