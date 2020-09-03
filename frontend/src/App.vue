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
    <img src="./assets/1.jpg" alt />
  </div>
</template>

<script>

import Header from "@/components/Header";
import Body from "@/components/Body";
import Footer from "@/components/Footer";

export default {
  name: "app",
  components: {
    // 가져온 component 들을 등록합니다.
    Header,
    Body,
    Footer
  }
};
</script>

<style>
html,
body,
div,
span,
applet,
object,
iframe,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
pre,
a,
abbr,
acronym,
address,
big,
cite,
code,
del,
dfn,
em,
img,
ins,
kbd,
q,
s,
samp,
small,
strike,
strong,
sub,
sup,
tt,
var,
b,
u,
i,
dl,
dt,
dd,
ol,
nav ul,
nav li,
fieldset,
form,
label,
legend,
table,
caption,
tbody,
tfoot,
thead,
tr,
th,
td,
article,
aside,
canvas,
details,
embed,
figure,
figcaption,
footer,
header,
hgroup,
menu,
nav,
output,
ruby,
section,
summary,
time,
mark,
audio,
video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

article,
aside,
details,
figcaption,
figure,
footer,
header,
hgroup,
menu,
nav,
section {
	display: block;
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


/* start editing from here */

a {
	text-decoration: none;
}

.txt-rt {
	text-align: right;
}


/* text align right */

.txt-lt {
	text-align: left;
}


/* text align left */

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
	font-family: 'Lato', sans-serif;
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
	background: #1bbc9b;
}

.nav>ul>li>a {
	font-family: 'Lato', sans-serif;
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
	background: #1bbc9b;
}


/************end-nav***********/
</style>