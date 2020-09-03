<template>
    <div>

        <P><img v-bind:src="postData.img"></P>
        <P>Author: {{postData.author}}</P>
        <P>{{postData.content}}</P>
    

    </div>
</template>

<script>
export default {
    nema : 'post',
    props : {
        'postData' : Object
    }
}
</script>