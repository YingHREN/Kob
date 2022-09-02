<template>
  <div>
    <div>Bot name: {{Botname}}</div>
    <div>Bot rating: {{Botrating}}</div>
  </div>
  <router-view></router-view>
</template>
<script>
import $ from 'jquery';
import {ref} from 'vue';
export default{
    name:"App",
    setup: () => {
      let Botname = ref("");
      let Botrating = ref("");
    $.ajax({
      url: "http://localhost:3000/pk/getBotinfo/",
      type: "get",
      success: resp=>{
        Botname.value = resp.name;
        Botrating.value = resp.rating;
      }
    }
    )
    return {
      Botname,
      Botrating
    }
  }
}
</script>
<style>
  body{
    background-image: url("@/assets/background.png");
    background-size: cover;
  }
</style>
