<template>
    <ContentField>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="register">
                    <div class="mb-3">
                        <label for="username" class="form-label">User Name</label>
                        <input v-model = "username" type="text" class="form-control" id="username" placeholder="please input your username">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="please input your password">
                    </div>
                    <div class="mb-3">
                        <label for="confirmedPassword" class="form-label">Confirm Password</label>
                        <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="please input your password again">
                    </div>
                    <div class="error_message">{{error_message}}</div>
                    <button type="submit" class="btn btn-primary">submit</button>
                </form>
            </div>
        </div>
    </ContentField>
    
</template>
<script>
import ContentField from '../../../components/ContentField.vue'
import {ref} from 'vue'
// import router from '@/router'
import $ from 'jquery' 
import router from '@/router'
// success(){ router.push({ name:'home'}); }, import router from '../../../router/index' error(){ error_message.value = "Username or password wrong"; }
export default{
    components: {
        ContentField
    },
    setup(){
        let username = ref('');
        let password = ref('');
        let confirmedPassword = ref('');
        let error_message = ref('');
        // console.log("sdsd");
        const register = () => {
            console.log(confirmedPassword.value);
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/register/",
                type: "post",
                data: {
                    username: username.value,
                    password: password.value,
                    confirmedPassword: confirmedPassword.value,
                },
                success(resp) {
                    console.log(resp);
                    if(resp.error_message === 'success'){
                        router.push({name:"user_account_login"})
                    }
                    else{
                        error_message.value = resp.error_message;
                    }
                }
            })
        }
        return {
            username,
            password,
            confirmedPassword,
            error_message,
            register,
        }
    }
}

</script>
<style scoped>
button{
    width:100%;
}
div.error_message{
    color:red;
}
</style>