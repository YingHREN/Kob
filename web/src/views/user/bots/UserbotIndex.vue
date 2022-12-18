<template>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="card">
                    <div class="card-body" style = "margin-top: 20px;">
                        <img :src="$store.state.user.photo" alt="" style="width: 100%;">
                    </div>
                </div>
            </div>
            <div class="col-9">
                <div class="card" style="margin-top: 20px;" >
                    <div class="card-header">
                    <span style="font-size: 120%;">My Bot</span> 
                    <button type="button" class="btn btn-primary float-end" data-bs-toggle="modal" data-bs-target="#add_bot_button">
                        Create Bot
                    </button>
                        <!-- Modal -->
                        <div class="modal fade" id="add_bot_button" tabindex="-1">
                        <div class="modal-dialog modal-xl">
                            <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" >Create Bot</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form>
                                <div class="mb-3">
                                    <label for="add_bot_title" class="form-label">Name</label>
                                    <input v-model="botadd.title" type="text" class="form-control" id="add_bot_title" placeholder="Please input Bot name">
                                </div>
                                <div class="mb-3">
                                    <label for="add_bot_description" class="form-label">Description</label>
                                    <textarea  v-model="botadd.description" class="form-control" id="add_bot_description" rows="3" placeholder="Please input Bot description"></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="add_bot_code" class="form-label">Code</label>
                                    <textarea v-model="botadd.content" class="form-control" id="add_bot_code" rows="7" placeholder="Please input Bot description"></textarea>
                                </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <div class="error-message">{{botadd.error_message}}</div>
                                <button type="button" class="btn btn-primary" @click="add_bot">Submit</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            </div>
                            </div>
                        </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Bot Name</th>
                                    <th>Create Time</th>
                                    <th>Operation</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="bot in bots" :key="bot.id">
                                    <td>{{bot.title}}</td>
                                    <td>{{bot.createtime}}</td>
                                    <td>
                                        <button type="button" class="btn btn-secondary" style="margin-right: 10px;" data-bs-toggle="modal" :data-bs-target="'#update-bot-modal-' + bot.id">Update</button>
                                        <button type="button" class="btn btn-danger" @click="remove_bot(bot)">Delete</button>
                                        <div class="modal fade" :id="'update-bot-modal-' + bot.id" tabindex="-1">
                                        <div class="modal-dialog modal-xl">
                                            <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Create Bot</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form>
                                                <div class="mb-3">
                                                    <label for="add_bot_title" class="form-label">Name</label>
                                                    <input v-model="botadd.title" type="text" class="form-control" id="add_bot_title" placeholder="Please input Bot name">
                                                </div>
                                                <div class="mb-3">
                                                    <label for="add_bot_description" class="form-label">Description</label>
                                                    <textarea  v-model="botadd.description" class="form-control" id="add_bot_description" rows="3" placeholder="Please input Bot description"></textarea>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="add_bot_code" class="form-label">Code</label>
                                                    <textarea v-model="botadd.content" class="form-control" id="add_bot_code" rows="7" placeholder="Please input Bot description"></textarea>
                                                </div>
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="error-message">{{botadd.error_message}}</div>
                                                <button type="button" class="btn btn-primary" @click="add_bot">Submit</button>
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                            </div>
                                            </div>
                                        </div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

import{ref, reactive} from 'vue'
import $ from 'jquery'
import { useStore } from 'vuex';
import {Modal} from "bootstrap/dist/js/bootstrap";

export default{

    setup(){        
        const store = useStore();
        let bots = ref([]);
        const botadd = reactive({
            title:"",
            description:"",
            content:"",
            error_message:"",
        });

        const refresh_bots = () =>{
            $.ajax({
                url:"http://127.0.0.1:3000/user/bot/getlist/",
                type:"get",
                headers:{
                    Authorization: "Bearer "+store.state.user.token,
                },
                success(resp){
                    bots.value = resp;
                }
            })
        }
        refresh_bots();
        const add_bot = () =>{
            botadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/add/",
                type: "post",
                data:{
                    title: botadd.title,
                    description: botadd.description,
                    content: botadd.content,
                },
                headers:{
                    Authorization: "Bearer "+store.state.user.token,
                },
                success(resp){
                    if(resp.error_message==="success"){
                        botadd.title = "";
                        botadd.description = "";
                        botadd.content = "";
                        Modal.getInstance("#add_bot_button").hide();
                        refresh_bots();
                    }else{
                        botadd.error_message = resp.error_message;
                    }
                }
            })
        }

        const remove_bot = (bot) => {
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/remove/",
                type: "post",
                data:{
                   bot_id: bot.id,
                },
                headers:{
                    Authorization: "Bearer "+store.state.user.token,
                },
                success(resp){
                    if(resp.error_message === "success"){
                        refresh_bots();
                    }
                }
            })
        }

        const update_bot = (bot) =>{
            botadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3000/user/bot/update/",
                type: "post",
                data:{
                    bot_id: bot.id,
                    title: botadd.title,
                    description: botadd.description,
                    content: botadd.content,
                },
                headers:{
                    Authorization: "Bearer "+store.state.user.token,
                },
                success(resp){
                    if(resp.error_message==="success"){
                        Modal.getInstance("#add_bot_button").hide();
                        refresh_bots();
                    }else{
                        botadd.error_message = resp.error_message;
                    }
                }
            })
        }

        return {
            bots,
            botadd,
            add_bot,
            update_bot,
            remove_bot,
        }
    }
}
</script>

<style scoped>
div.error-message{
    color: red;
}
</style>


<!-- $.ajax({
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

}) -->