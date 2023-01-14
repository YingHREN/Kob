import { BotgameObject } from "./BotgameObject";
import { Snake } from "./Snake";
import { wall } from "./wall";
export class GameMap extends BotgameObject{
    constructor(ctx, parent, store){
        super();
        
        this.ctx = ctx;
        this.parent = parent;
        this.store = store;
        this.L = 0;
        this.cols = 14;
        this.rows = 13;
        this.inner_walls_count = 15;
        this.walls = [];
        this.snakes = [
            new Snake({id:0, color:"#4876EC", r: this.rows-2, c:1 }, this),
            new Snake({id:1, color:"#F94848", r: 1, c:this.cols-2 }, this),
        ]
    }
    
    createwalls(){
        const g = this.store.state.pk.gamemap;
        
        for(let r=0; r<this.rows; r++){
            for(let c=0; c<this.cols; c++){
                if(g[r][c]){
                    this.walls.push(new wall(r, c, this));
                }
            }
        }
    }

    check_valid(cell){
        for(const wall of this.walls){
            if(wall.r===cell.r&&wall.c===cell.c){
                console.log(wall.r);
                console.log(wall.c);
                return false;
            }
        }
        console.log("sdsd");
        for(const snake of this.snakes){
            let k=snake.cells.length;
            if(!snake.check_tail_increasing()){ 
                k--;
            } 
            for(let i=0; i<k; i++){
                if(snake.cells[i].r === cell.r&&snake.cells[i].c === cell.c){
                    return false;
                }
            }
        }
        return true;
    }
    add_listening_event(){
        this.ctx.canvas.focus();
        this.ctx.canvas.addEventListener("keydown", e=>{
            let d = -1;
            if(e.key === 'w'){ d=0;}
            else if(e.key==='d'){d=1;}
            else if(e.key==='s'){d=2;}
            else if(e.key==='a'){d=3;}
            if(d>=0){
                this.store.state.pk.socket.send(JSON.stringify({
                    event:"move",
                    direction: d,
                }));
            }
        });
    }

    check_ready(){ //判断两条蛇是否都准备下一回合
        for(const snake of this.snakes){
            //  
            if(snake.status !== "idle") return false;
            if(snake.direction === -1) return false;
            
        }
        // console.log("sdsd");
        return true;
    }

    start(){
        this.createwalls();
        this.add_listening_event();
    }
    update_size(){
         this.L = parseInt(Math.min(this.parent.clientWidth/this.cols, this.parent.clientHeight/this.rows));
         this.ctx.canvas.width = this.L * this.cols;
         this.ctx.canvas.height = this.L * this.rows;
    }   
    next_step(){//将蛇的状态变为走下一步
        for(const snake of this.snakes){
            snake.next_step();
        }
    } 
    update_move(){

    }
    update(){
        this.update_size();
        if(this.check_ready()){
            console.log("tttt")
            this.next_step();
        }
        this.render();
    }

    
    render(){
        const color_even = "#AAD751", color_odd = "#A2D149";
        for(let r=0; r<this.rows; r++){
            for(let c=0; c<this.cols; c++){
                if((r+c)%2 == 0){
                    this.ctx.fillStyle = color_even;
                }
                else{
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c*this.L, r*this.L, this.L, this.L);
            }
        }
        // this.ctx.fillStyle = 'green';
        // this.ctx.fillRect (0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
    }

}