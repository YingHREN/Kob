// import { isPlainObject } from "jquery";

const Bot_gameObject = [];
export class BotgameObject{
    constructor(){
        Bot_gameObject.push(this);
        this.timedelta = 0;
        this.has_called_start = false;
    }
    start(){ //只执行一次

    }
    update(){//每一帧执行一次，除了第一帧

    }

    on_destroy(){ //删除之前执行

    }
 
    destroy(){
        this.on_destroy();
        for(let i in Bot_gameObject){
            const obj = Bot_gameObject[i];
            if(obj === this){
                Bot_gameObject.splice(i);
                break;
            }
        }
    }

}
let last_timestamp;
const step = timestamp => {
    for(let obj of Bot_gameObject){
        if(!obj.has_called_start){
            obj.has_called_start = true;
            obj.start();
        }
        else{
            obj.timedelta = timestamp - last_timestamp;
            obj.update();
        }
    }
    last_timestamp = timestamp;
    requestAnimationFrame(step)
}
requestAnimationFrame(step)