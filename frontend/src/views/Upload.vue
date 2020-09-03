<template>
    <div class='upload'>
        <div v-if='!artImage'>
            <h2>Select an image</h2>
            <input type='file' @change='onFileChange'>
        </div>
    <div v-else>
    <img :src='artImage' />
    <button @click='removeImage'>Remove image</button>
  </div>
</div>
</template>

<script>
export default {
    name: 'Upload',
    data(){
        return{
            artImage: '',
            artTitle: '',
            artContent: '',
            artAuthor: ''
        }
    },
    methods: {
        fileSelect: function(){
            this.artImage = this.$refs.artImage.files[0];
        },
        onFileChange(e){
            let files = e.target.files || e.dataTransfer.files;
            if(!files.length) return;
            this.createImage(files[0]);
        },
        createImage(file){
            let artImage = new Image();
            let reader = new FileReader();
            let vm = this;

            reader.onload = (e)=>{
                vm.artImage = e.target.result;
            };
            reader.readAsDataURL(file);
        },
        removeImage: function(e){
            this.artImage = '';
        }
    }
}
</script>