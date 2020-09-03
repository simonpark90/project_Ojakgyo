<template>
    <div id='header-wrapper'>
	<div id='header' class='container'>
		<div id='logo'>
			<h1><a href='#'>Ojackgyo</a></h1>
		</div>
		<div id='nav'>
			<ul>
                <router-link to ='/Artwork'>ArtWorks</router-link>
                <router-link to ='/Upload'>Upload</router-link>
				<router-link to ='/Signin1' v-if='this.check'>Sign in</router-link>
				<router-link to ='/' v-if='!this.check'>My profile</router-link>     <!-- 경로 지정해줘야함 -->
                <router-link to ='/Signup' v-if='this.check'>Sign up</router-link> 
				<button @click="nav_signout" v-if="!this.check">Sign out</button>
			</ul>
		</div>
	</div>
    </div>
</template>

<script>
export default {
	name : 'Header-wrapper',
	data(){
		return{
			check: true
		}
	},methods: {
		nav_signout: function(){
			this.$EventBus.$emit('signout');
		}
	},
	created(){
		this.$EventBus.$on('completeSign',() => {
			this.check = !this.check;
		})
	}
}
</script>