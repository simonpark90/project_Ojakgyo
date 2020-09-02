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
  components: { // 가져온 component 들을 등록합니다.
    Header,
    Body,
    Footer
  }
};
</script>