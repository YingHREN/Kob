<template>
  <NavBar />
  <router-view></router-view>
</template>
<script>
import NavBar from './components/NavBar.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
// import $ from 'jquery'
export default{
    components:{
    NavBar
    },
    setup(){
      // $.ajax({
      //   crossDomain:true,
      //   url: "http://localhost:3000/user/account/token/",
      //   type:"post",
      //   // contentType: "application/json;charset=UTF-8",
      //   // data: JSON.stringify({
      //   //   username: 'ryh',
      //   //   password: 'Top'
      //   // }),
      //   data :{
      //     username:"ryh",
      //     password:"Top",
      //   },
      //   success(resp){
      //     console.log(resp);
      //   },
      //   error(resp){
      //     console.log(resp);
      //   }
      // });

      // $.ajax({
      //   url: "http://localhost:3000/user/account/info/",
      //   type:"get",
      //   headers:{
      //     Authorization:"Bearer "+"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkYmQzNDg0YTRiNjM0YjhmYTAzYjZmN2Q0NWM3ZTI2ZCIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY2Njg4NDg3NywiZXhwIjoxNjY4MDk0NDc3fQ.mE-7QbI1xppcqZ265kQShoDkIMxtapQBlaLQVD98pLg"
      //   },
      //   success(resp){
      //     console.log(resp);
      //   },
      //   error(resp){
      //     console.log(resp);
      //   }
      // })

      // $.ajax({
      //   url: "http://localhost:3000/user/account/register/",
      //   type:"post",
      //   data :{
      //     username:"ryh2",
      //     password:"ww",
      //     confirmedpassword:"ww",
      //   },
      //   success(resp){
      //     console.log(resp);
      //   },
      //   error(resp){
      //     console.log(resp);
      //   }
      // })
    }
}
</script>
<style>
  body{
    background-image: url("@/assets/images/background.png");
    background-size: cover;
  }
</style>
