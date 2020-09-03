<template>
    <div class ="artwork">
        <h3>아트워크 출력 화면</h3>
    
        <div v-if="step == 1" style="width:500px; margin: 0 auto;">
            
             <!-- <Post v-for="(post, i) in postData" v-bind:postData="postData" v-bind:key="i" /> -->
            <p><input type="file" id="file" class="inputfile" v-on:change="upload"><label for="file" class="input-puls">+</label></p>
        </div>

        <div v-if="step == 2">
        
            <p><img v-bind:src="newImageSrc"></p>
        
            <input type= "text" v-model="newAuthor" placeholder="작가">
       
            <textarea placeholder="작품의 설명을 입력해주세요." v-model="newContent"></textarea>
         
            <button type="button" v-on:click="updata()">저장</button>
        </div>

    </div>
</template>

<script>
import data from '@/assets/data.js'
// import Post from '@/components/Post.vue'

export default {
    data(){
        return {
            
            postData : data,
            newAuthor : '',
            newImageSrc : '',
            newContent : '',
            step :1
        }
    },
    //components:{
        //Post
    // },
 
    methods:{
        updata(){
            var newData = {
                author : this.newAuthor,
                img : this.newImageSrc,
                content : this.newContent                
            }
            data.unshift(newData);
            this.step = 1 ;
        },
        upload (e){
            let file = e.target.files;
            let reader = new FileReader();

            reader.readAsDataURL(file[0])
            reader.onload = e =>{
                this.newImageSrc = e.target.result;
            }
            this.step = 2;
        }
    }
  
}
</script>
<style>
    img.textarea{width:100%;}
</style>