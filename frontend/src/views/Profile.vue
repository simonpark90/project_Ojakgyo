<template>
  <div id='profile'>
    <div class="container">
      <div class="profile-contents">
        <div class = "box" style = "background : #BDBDBD;">
          <img class = "profile" src="../assets/47A4923792B6458C8D6DC277179E8B75_LL.jpg" alt="">
        </div>
        <p style="font-size:30px; padding : 0px 0px 0px 50px;">{{nickname}}</p>             
        <p style="font-size:24px; padding : 0px 0px 0px 10px;">E-mail : {{email}}</p>
        <br>
      </div>
      <stack
              :column-min-width="300"
              :gutter-with="15"
              :gutter-hight="15"
              monitor-images-loaded
      >
        <stack-item
                v-for="(image, i) in images"
                :key="i"
                style="transition: transform 300ms"
        >

          <img :src="require(`@/assets/upload/${image}`)" :alt="image.alt_description" @click='openmodal(image)' />
        </stack-item>
      </stack>
      <Modal @close="closeModal" v-if="modal" >
        <p>삭제 하시겠습니까?</p>
        <template slot="footer">
          <button @click="deleteImage(artworkId)">삭제</button>
          <button @click="deleteCansle">취소</button>
        </template>
      </Modal>
    </div>
  </div>
</template>

<script>
import { Stack, StackItem } from 'vue-stack-grid';
import Modal from '@/components/Delete.vue'

const storage = window.sessionStorage;

export default {
    name: 'profile',
    components: {
        Stack,
        StackItem,
        Modal
    },
    data(){
        return{
            images:[],
            clickedImage:'',
            email: '',
            nickname:'',
            artworkId:'',
            memberId:'',
            modal : false
        }
    },
    methods:{
        fetchData(){
          this.email =storage.getItem("login_email");
          this.nickname = storage.getItem('nickname');
        },
        showall(){
            this.images=[];
            this.$axios.get('http://localhost:80/artworks/show/'+this.email, {}).then(res=>{
        for(let i=0;i<res.data.dtoList.length;i++){
          this.images.push(res.data.dtoList[i].imageName);
        }
      }).catch(res=>{
        alert('실패'+res);
      })
      },
    openmodal(image){
      this.clickedImage = image;
      this.$axios.get('http://localhost:80/artworks/detail/' + this.clickedImage, {}).then(res=>{
        this.artworkId = res.data.artwork.id;
        this.memberId = res.data.member.id
      })
     this.modal = true;
    },
    closeModal(){
      this.modal = false;
    },
    deleteImage(artworkId){
      this.artwrokId = artworkId;
      this.$axios.delete('http://localhost:80/artworks/delete/'+this.artwrokId,{}).then(res=>{
          this.showall();
          console.log(res);
          alert('삭제 성공');
          this.modal = false;
      }).catch(res =>{
        console.log(res);
        alert("삭제 실패");
      })
    },
    deleteCansle(){
      this.modal = false;
    }
  },
  created(){
      this.fetchData()
    },
  mounted(){
      this.showall()
    }
}
</script>

<style>
.container {
  width: 80vw;
  margin: 0 auto;
}
.box {
  width: 200px;
  height: 200px; 
  border-radius: 70%;
  overflow: hidden;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.container {
  width: 80vw;
  margin: 0 auto;
}
.button-wrapper {
  display: flex;
  justify-content: center;
  margin-bottom: 25px;
}
.btn {
  font-size: 18px;
  background-color: #42b983;
  color: white;
  padding: 10px 20px;
}
.btn:not(:last-child) {
  margin-right: 10px;
}
img {
  width: 100%;
  height: auto;
  border-radius: 12px;
}
</style>