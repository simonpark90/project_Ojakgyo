<template>
    <div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#">Ojackgyo</a></h1>
		</div>
		<div id="nav">
			<ul>
				<!-- <li><a href="#" accesskey="3" title="">ArtWorks</a></li> -->
                <router-link to ="/">ArtWorks</router-link>
                <router-link to ="/Signin">Sign in</router-link>
                <router-link to ="/Signup">Sign up</router-link>
			</ul>
		</div>
	</div>
    </div>
</template>

<script>
export default {
    name : "Header-wrapper"
}
</script>