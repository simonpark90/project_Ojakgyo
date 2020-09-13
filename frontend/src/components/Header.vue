<template>
    <div class="Header">
        <div class = "logo">
            <a href = "/"><img src="../assets/home.png" alt=""></a>
        </div>
        <Nav/>
    </div>
</template>

<script>
import Nav from "@/components/Nav.vue";


export default {
    name: "Header",
    components:{
        Nav
    }
}
</script>

<style scoped>
.logo {
    float: left;
    width: 300px;
    height : 150px;
    }
</style>