<template>
    <ContentField>
        A,B,C
    </ContentField>
</template>
<script>
import ContentField from '../../../components/ContentField.vue'
import $ from 'jquery'
import {useStore} from 'vuex'

export default{
    components: {
        ContentField
    },
    setup(){
        const store = useStore();
        console.log("token="+store.state.user.token);
        // $.ajax({
        //     url: "http://127.0.0.1:3000/user/bot/add/",
        //     type: "post",
        //     data:{
        //         title:"My bot",
        //         description:"bot description",
        //         content:"Bot code",
        //     },
        //     headers:{
        //         Authorization:"Bearer "+"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjg3YTNiMTAwY2E0ZDcyYTkxZmVkYjA1ZjU3NDgxYiIsInN1YiI6IjUiLCJpc3MiOiJzZyIsImlhdCI6MTY2NzE0NDI1MSwiZXhwIjoxNjY4MzUzODUxfQ.uXWvGXJUNqX2c-F3VFYlil5fj0lTHLpxddvaknVMwYQ"
        //     },
            
        //     success(resp){
        //         console.log(resp);
        //     },
        //     error(resp){
        //         console.log(resp);
        //     }

        // })
        // $.ajax({
        //     url: "http://127.0.0.1:3000/user/bot/update/",
        //     type: "post",
        //     data:{
        //         bot_id:1, 
        //         title:"My bot3",
        //         description:"bot description3",
        //         content:"Bot code3",
        //     },
        //     headers:{
        //         Authorization:"Bearer "+"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjg3YTNiMTAwY2E0ZDcyYTkxZmVkYjA1ZjU3NDgxYiIsInN1YiI6IjUiLCJpc3MiOiJzZyIsImlhdCI6MTY2NzE0NDI1MSwiZXhwIjoxNjY4MzUzODUxfQ.uXWvGXJUNqX2c-F3VFYlil5fj0lTHLpxddvaknVMwYQ"
        //     },
            
        //     success(resp){
        //         console.log(resp);
        //     },
        //     error(resp){
        //         console.log(resp);
        //     }

        // })
        $.ajax({
            url: "http://127.0.0.1:3000/user/bot/getlist/",
            type: "get",
            headers:{
                Authorization:"Bearer "+"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjg3YTNiMTAwY2E0ZDcyYTkxZmVkYjA1ZjU3NDgxYiIsInN1YiI6IjUiLCJpc3MiOiJzZyIsImlhdCI6MTY2NzE0NDI1MSwiZXhwIjoxNjY4MzUzODUxfQ.uXWvGXJUNqX2c-F3VFYlil5fj0lTHLpxddvaknVMwYQ"
            },
            
            success(resp){
                console.log(resp);
            },
            error(resp){
                console.log(resp);
            }

        })
    }
}
</script>

<style scoped></style>