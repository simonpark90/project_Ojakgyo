<template>

  <div class="header_bg">
    <div class="wrap">
      <div class="header">
        <!--------start-logo------>
        <div class="logo">
          <a href="index.html">
            <img src="images/logo.png" alt />
          </a>
        </div>
        <!--------end-logo--------->
        <!----start-nav-------->
        <div class="nav">
          <ul>
            <li>
              <a href="#home" class="scroll">Home</a>
            </li>
            <li class="active">
              <a href="#portfolio" class="scroll">Portfolio</a>
            </li>
            <li>
              <a href="#about" class="scroll">About</a>
            </li>
            <li>
              <a href="#contact" class="scroll">Contact</a>
            </li>
            <div class="clear"></div>
          </ul>
        </div>
        <!-----end-nav-------->
        <div class="clear"></div>
      </div>

    <div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#">Ojackgyo</a></h1>
		</div>
		<div id="nav">
			<ul>
				<!-- <li><a href="#" accesskey="3" title="">ArtWorks</a></li> -->
                <router-link to ="/Artworks">ArtWorks</router-link>
                <router-link to ="/Signin">Sign in</router-link>
                <router-link to ="/Signup">Sign up</router-link>
			</ul>
		</div>
	</div>

    </div>
  </div>
</template>

<script>
export default {
  name: "Header-wrapper"
};
</script>

<style scoped>
</style>