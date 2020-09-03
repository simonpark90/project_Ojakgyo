<template>
    <div class='artwork'>
        <div v-for = '(img,idx) in images' :key="idx">
            <img :src="require(`@/assets/${img}`)" />
        </div>
    </div>
</template>

<script>
export default {
    name: 'Artwork',
    data(){
        return{
            images: ['jisoo1.jpg','jisoo2.jpg','jisoo3.jpg','jisoo4.jpg','jisoo5.jpg','jisoo6.jpg',
            'jisoo7.jpg','jisoo8.jpg','jisoo9.jpg','jisoo10.jpg',]
        }
    }
}
</script>