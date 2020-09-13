<template>
  <transition name="modal" appear>
    <div class="modal modal-overlay" @click.self="$emit('close')">
      <div class="modal-window">
        <div class="modal-body">
            <div class='modal-art'>
              <img :src="require(`@/assets/upload/${selectedImage}`)"  style="width:97%;height:auto"/>
            </div>
            <div class='modal-compo'>
                <div class='modal-author'>{{author}} 님의 작품</div>
                <div class='modal-title'>{{title}}</div>
                <div class='modal-content'>{{content}}</div>
                <div class='modal-comment'>
                  <div class='modal-comment-body' v-if='!moreComment'>
                    <div class='modal-comment-all' v-for="(comment, i) in comments" :key="i">
                      {{comment.writer}} : {{comment.content}}  
                      <button class='modal-comment-delete' v-if="(comment.writer==nickname)" @click="deleteComment(comment.id)">x</button>
                    </div>
                    <div>
                      <input class='modal-comment-input' type='text' v-model='inputComments' placeholder='댓글 달기...' style='width:80%'/>
                      <button class='modal-comment-btn' @click='inputComment'>게시</button></div> 
                  </div>
                  <div class='modal-comment-view' v-if="moreComment"><button class='modal-comment-viewbtn' @click="showComment">댓글 보기</button></div>
                </div>
            </div>
        </div>
        <footer class="modal-footer">
          <slot name="footer">
          </slot>
        </footer>
      </div>
    </div>
  </transition>
</template>

<script>
const storage = window.sessionStorage;

export default {
  name: 'Modal',
  props: ['selectedImage','title','content','author','like','artworkId'],
  data(){
    return{
      moreComment: true,
      comments: [],
      inputComments: '',
      nickname: storage.getItem('nickname')
    }
  },
  methods:{
    showComment(){
      this.moreComment = false;
      this.comments=[];
      this.$axios.get('http://localhost:80/comments/'+this.artworkId,{
      }).then(res =>{
        for(let i=0;i<res.data.dtolist.length;i++){
          this.comments.push(res.data.dtolist[i]);
          console.log(this.comments);
        }
        console.log(this.comments);
      }).catch(res =>{
        alert('불러오기 실패'+ res.data);
      })
    },
    inputComment(){
      this.$axios.post('http://localhost:80/comments',{
          'artworkId': this.artworkId,
          'memberEmail': storage.getItem('login_email'),
          'content': this.inputComments
      },{
        params: {
          'artworkId': this.artworkId,
          'memberEmail': storage.getItem('login_email'),
          'content': this.inputComments
        }
      }).then(res=>{
        console.log(res);
        this.inputComments='';        
        this.showComment();
      })
    },
    deleteComment(commentId){
      this.$axios.delete('http://localhost:80/comments/'+commentId,{
      }).then(res=>{
        console.log(res);
        this.showComment();
      })
    }
  }
  }
</script>

<style lang="stylus" scoped>

.modal {
  &.modal-overlay {
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    z-index: 30;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
  }

  &-window {
    background: #fff;
    border-radius: 30px; //모서리 둥글기
    overflow: hidden;
  }

  &-body {
    padding: 20px 20px;
    margin: 5px 5px;
    width : 900px;
    height: 670px;
  }

  &-footer {
    background: #ccc;
    padding: 10px;
    text-align: right;
  }

  &-art{
      float: left;
      width: 50%;
      height : 100%;
  }
  
  &-compo{
      float: right;
      margin-top: 20px;
      width: 50%;
  }
  &-title{
      margin-top: 20px;
      font-size: 20px;
  }
  &-content{
      margin-top: 20px;
  }
  &-comment{
      margin-top: 20px;
  }
  &-comment-view{
    float: right;
  }
  
  &-comment-btn{
    border: #fbfbfb solid 4px;
    border-radius: 10px;
    cursor:pointer;
    color: #8C54A1
    background-color: rgba(0,0,0,0);
    font-size:14px;
    -webkit-transition: all 0.3s;
    -moz-transition: all 0.3s;
    transition: all 0.3s;
    
  }

  &-comment-input{
    font-family : 'ourFont'
    margin-top:15px;
    color:#3c3c3c;
    font-weight:500;
    border-radius: 0;
    background-color: #ffffff;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    -ms-box-sizing: border-box;
    box-sizing: border-box;
    border: 3px solid rgba(0,0,0,0);
  }
  &-comment-input:focus{
    background: #fff;
    box-shadow: 0;
    border-color: #8C54A1;
    border-radius: 10px;
    outline: none;
  }
  
  &-comment-btn:hover{
    background-color: #8C54A1;
    color: white;
  }

  &-comment-viewbtn{
    border: #fbfbfb solid 4px;
    border-radius: 10px;
    cursor:pointer;
    color: #8C54A1
    background-color: rgba(0,0,0,0);
    font-size:14px;
    -webkit-transition: all 0.3s;
    -moz-transition: all 0.3s;
    transition: all 0.3s;
  }

  &-comment-delete{
    cursor:pointer;
    background-color: transparent !important;
    background-image: none !important;
    border-color: transparent;
    border: none;
    color: tomato;
  }
}

// 오버레이 트랜지션
.modal-enter-active, .modal-leave-active {
  transition: opacity 0.4s;

  // 오버레이에 포함되어 있는 모달 윈도의 트랜지션
  .modal-window {
    transition: opacity 0.4s, transform 0.4s;
  }
}

// 딜레이가 적용된 모달 윈도가 제거된 후에 오버레이가 사라짐
.modal-leave-active {
  transition: opacity 0.6s ease 0.4s;
}

.modal-enter, .modal-leave-to {
  opacity: 0;

  .modal-window {
    opacity: 0;
    transform: translateY(-20px);
  }
}


</style>