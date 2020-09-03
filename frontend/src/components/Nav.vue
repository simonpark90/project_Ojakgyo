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