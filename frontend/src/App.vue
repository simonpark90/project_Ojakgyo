<template>
  <div>
    <div>
      <Header/>
    </div>
    <div>
      <Body/>
    </div>
    <div>
      <Footer/>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Body from "@/components/Body";
import Footer from "@/components/Footer";
export default {
  name: "app",
  components: {
    Header,
    Body,
    Footer
  }
};
</script>

<style>
@font-face {
        font-family: webisfree;
        src: url('./font/Cookie-Regular.ttf');
    }
ol,
ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

blockquote,
q {
	quotes: none;
}

blockquote:before,
blockquote:after,
q:before,
q:after {
	content: '';
	content: none;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
a {
	text-decoration: none;
}

.txt-rt {
	text-align: right;
}
.txt-lt {
	text-align: left;
}
.txt-center {
	text-align: center;
}


/* text align center */

.float-rt {
	float: right;
}


/* float right */

.float-lt {
	float: left;
}


/* float left */

.clear {
	clear: both;
}


/* clear float */

.pos-relative {
	position: relative;
}


/* Position Relative */

.pos-absolute {
	position: absolute;
}


/* Position Absolute */

.vertical-base {
	vertical-align: baseline;
}


/* vertical align baseline */

.vertical-top {
	vertical-align: top;
}


/* vertical align top */

.underline {
	padding-bottom: 5px;
	border-bottom: 1px solid #eee;
	margin: 0 0 20px 0;
}


/* Add 5px bottom padding and a underline */

nav.vertical ul li {
	display: block;
}


/* vertical menu */

nav.horizontal ul li {
	display: inline-block;
}


/* horizontal menu */

img {
	max-width: 100%;
}


/*end reset*/

body {
	font-family: sans-serif;
	font-size: 100%;
	background: #ffffff;
}

.wrap {
	width: 70%;
	margin: 0 auto;
}


/* start header */

.header {
	padding: 1.3em 0em;
}

.logo {
	float: left;
}

.logo a {
	display: block;
}


/******start-nav********/

.nav {
	float: right;
	margin-top: 0.82em;
}

.nav>ul>li {
	display: inline-block;
}

.nav>ul>li.active a {
	color: #ffffff;
	background: #8C54A1;
}

.nav>ul>li>a {
	font-family: sans-serif;
	text-transform: uppercase;
	display: block;
	font-size: 1em;
	color: #444;
	padding: 0.5em 1em;
	-webkit-transition: 0.9s;
	-moz-transition: 0.9s;
	-o-transition: 0.9s;
	transition: 0.9s;
}

.nav>ul>li>a:hover {
	color: #ffffff;
	background: #8C54A1;
}


/************end-nav***********/
</style>