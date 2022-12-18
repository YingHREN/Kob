<template>
    <PlayGround v-if="$store.state.pk.status=== 'playing'" />
    <MatchGround v-if="$store.state.pk.status==='matching'"/>
</template>
<script>
// import ContentField from '../../components/ContentField.vue'
import PlayGround from '../../components/PlayGround.vue'
import MatchGround from '@/components/MatchGround.vue'
import { onMounted, onUnmounted } from 'vue'
import {useStore} from 'vuex'

export default{
    components: {
         PlayGround,
         MatchGround,
    },
    setup(){
        const store = useStore();
        const SocketUrl = `ws://127.0.0.1:3000/websocket/${store.state.user.token}/`;
        
        let socket = null;
        onMounted(()=>{
            store.commit("updateOpponent",{
                username:"My Opponent",
                opponent_photo:"https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            })
            socket = new WebSocket(SocketUrl);
            socket.onopen = () => {
                console.log("connected!");
                store.commit("updateSocket", socket);
            }

            socket.onmessage = msg =>{
                const data = JSON.parse(msg.data);
                console.log(data);
                if(data.event === "start-matching"){
                    store.commit("updateOpponent", {
                        username: data.opponent_username,
                        photo:data.opponent_photo,
                    });
                    setTimeout(() => {
                       store.commit("updateStatus", "playing"); 
                    }, 2000);
                    store.commit("updateGamemap", data.gamemap);
                }
            }
            socket.onclose = () =>{
                console.log("disconnected!");
            }
        });
        
        onUnmounted(()=>{
            socket.close(); 
        })
    }
}
</script>

<style scoped></style>