<template>
    <div class='home'>        
        <div class="w3-display-container w3-animate-opacity">
            <img src="../images/main.jpg" alt="boat" style="width:100%;min-height:350px;max-height:600px;">
        </div>
        <div class="w3-container w3-padding-64 w3-center" id="team">
            <h2>OUR TEAM</h2>
            <p>Meet the team - your fav programmers:</p>
            <div class="w3-row"><br>
                <div class="w3-quarter">
                    <img src="../images/su.gif" alt="Boss" style="width:45%" class="w3-circle w3-hover-opacity">
                    <h3>Sakaguchi Kentaro</h3>
                    <h3>(aka Jeongho Yoo)</h3>
                    <p>Project Manager</p>
                </div>

                <div class="w3-quarter">
                    <img src="../images/rain.jpg" alt="Boss" style="width:45%" class="w3-circle w3-hover-opacity">
                    <h3>Simon Park</h3>
                    <p>Fullstack Developer</p>
                </div>

                <div class="w3-quarter">
                    <img src="../images/go.jpg" alt="Boss" style="width:45%" class="w3-circle w3-hover-opacity">
                    <h3>Shawn Michael JS Won</h3>
                    <p>Fullstack Developer</p>
                </div>

                <div class="w3-quarter">
                    <img src="../images/sun.png" alt="Boss" style="width:45%" class="w3-circle w3-hover-opacity">
                    <h3>Jisoo Kim</h3>
                    <p>Fullstack Developer</p>
                </div>

            </div>
        </div>
        <div class="w3-row-padding w3-padding-64 w3-theme-l1" id="work" v-if='beforeLogin'>
            <div class="beforeLogin">
                <div id="main-signup">
                    Now join in to get more inspiration.<br>
                    Get ideas, follow artists and share your pieces. All for free!
                    <button @click="toSignup()" style="width:auto;" class="w3-button w3-xlarge w3-theme w3-hover-teal" title="Go To W3.CSS">SIGN UP</button>
                </div>

                <div id="main-signin">
                    Already have an account? 
                    <button @click="toSignin()" style="width:auto;" class="w3-button w3-xlarge w3-theme w3-hover-teal" title="Go To W3.CSS">SIGN IN</button>
                </div>                     
            </div>
        </div>
        
    </div>
</template>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
import signup from '@/views/Signup.vue';

const storage = window.sessionStorage;

export default {
    data() {
        return {
            beforeLogin: true,
        };
    },
    components: {
        signup
    },
    methods: {
      toSignup() {
        window.location = "/Signup";
      },
      toSignin() {
        window.location = "/Signin";
      },
      toHome() {
        document.getElementById('beforeLogin').style.display = 'none';
        window.location = loc;
      }
    },
    created() {
        storage.getItem('jwt-auth-token') == '' ? this.beforeLogin = true : this.beforeLogin = false;
    }
};
</script>

<style lang="scss or less" scoped>
</style>