<template>
    <div class='upload'>
      <div class='upload-preView' v-if='step==1' >
        <div class='button-wrapper'>
          <span class='label'>
            Upload File
            <input type="file" name="upload-box" id="upload-box" class="upload-box" placeholder="Upload File" @change='onFileChanged'>
          </span>
        </div>
      </div>
      <div class='upload-postView' v-if='step ==2'>
        <div class='upload-postView-left'>
          <p><img class='upload-postView-image' :src='newImageSrc'></p>
        </div>
        <div class='upload-postView-right'>
          <p><input class='upload-postView-title' type='text' v-model='title' placeholder='작품 제목'></p>
          <p><textarea class='upload-postView-content' placeholder='작품 내용' v-model='content'/></p>
          <p><button class='upload-button-cancel' @click="cancel"> Cancel </button></p>
          <p><button class='upload-button-upload' @click="upload"> Upload </button></p>
        </div>
      </div>
    </div>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: 'Upload',
  data(){
    return{
      selectedFile: '',
      content: '',
      title: '',
      newImageSrc: '',
      step: 1
    }
  },
  methods: {
    onFileChanged(event) {
      const reader = new FileReader();
      let file = event.target.files;
      reader.readAsDataURL(file[0])
      reader.onload = event =>{
        this.newImageSrc = event.target.result;
      }
      this.selectedFile = event.target.files[0];
      this.step = 2;
    },
    upload(){
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      formData.append('content', this.content);
      formData.append('title', this.title);
      this.$axios.post('http://localhost:80/files/uploadby/'+storage.getItem('login_email'),formData).then(res=>{
        console.log(res);
        alert('업로드가 성공했습니다.');
      }).catch(res=>{
        console.log(res);
        alert('업로드에 실패하셨습니다.');
      });
      this.step = 1;
  },
    cancel(){
      this.selectedFile = '';
      this.content = '';
      this.title = '';
      this.newImageSrc = '';
      this.step = 1;
  }
  }
}
</script>

<style lang="stylus" scoped>
body{
  font-family: 'Open Sans', sans-serif;
  height:100%;
  text-align:center;
  position:relative;
}

.button-wrapper {
  position: relative;
  width: 150px;
  text-align: center;
  margin: 20% auto;
}

.button-wrapper span.label {
  position: relative;
  z-index: 0;
  display: inline-block;
  width: 100%;
  background: #8C54A1;
  cursor: pointer;
  color: #fff;
  padding: 10px 0;
  text-transform:uppercase;
  font-size:12px;
}

#upload-box {
    display: inline-block;
    position: absolute;
    z-index: 1;
    width: 100%;
    height: 50px;
    top: 0;
    left: 0;
    opacity: 0;
    cursor: pointer;
}

.upload-postView{
  margin-top: 30px;
}

.upload-postView-left{
  float: left;
  margin-left: 300px;
}

.upload-postView-image{
  width: 500px;
  height: auto;
  border-radius: 30px;
}

.upload-postView-right{
  margin-right:300px;
}

.upload-postView-title{
  color:#3c3c3c;
  font-weight:500;
  font-size: 22px;
  border-radius: 0;
  line-height: 22px;
  background-color: #ffffff;
  padding: 13px 13px 13px 54px;
  margin-bottom: 10px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  -ms-box-sizing: border-box;
  box-sizing: border-box;
  border: 3px solid rgba(0,0,0,0);
}

.upload-postView-title:focus{
  background: #fff;
  box-shadow: 0;
  border-color: #8C54A1;
  border-radius: 10px;
  outline: none;
}

.upload-postView-content{
  font-family : 'ourFont'
  color:#3c3c3c;
  font-weight:500;
  font-size: 20px;
  border-radius: 0;
  line-height: 30px;
  background-color: #ffffff;
  padding: 13px 13px 13px 54px;
  margin-bottom: 10px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  -ms-box-sizing: border-box;
  box-sizing: border-box;
  border: 3px solid rgba(0,0,0,0);
}

.upload-postView-content:focus{
  background: #fff;
  box-shadow: 0;
  border-color: #8C54A1;
  border-radius: 10px;
  outline: none;
}

textarea {
  width: 500px;
  height: 500px;
  line-height: 150%;
  resize:vertical;
}

.upload-button-upload{
  float:right; /* 플롯 중요(::after 가상요소 이용)*/
  width: 200px;
  border: #fbfbfb solid 4px;
  border-radius: 10px;
  cursor:pointer;
  color: #8C54A1
  background-color: rgba(0,0,0,0);
  font-size:14px;
  padding-top:22px;
  padding-bottom:22px;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  transition: all 0.3s;
  margin-top:-4px;
  font-weight:700;
}

.upload-button-upload:hover{
  background-color: #8C54A1;
  color: white;
}

.upload-button-cancel{
  float:right; /* 플롯 중요(::after 가상요소 이용)*/
  width: 200px;
  border: #fbfbfb solid 4px;
  border-radius: 10px;
  cursor:pointer;
  color: #8C54A1
  background-color: rgba(0,0,0,0);
  font-size:14px;
  padding-top:22px;
  padding-bottom:22px;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  transition: all 0.3s;
  margin-top:-4px;
  font-weight:700;
}

.upload-button-cancel:hover{
  background-color: #8C54A1;
  color: white;
}
</style>