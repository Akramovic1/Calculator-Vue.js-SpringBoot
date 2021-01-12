<template>
  <body>
    <div class="calculator">
    <div class="display">{{previous || '0'}}</div>
    <div class="display">{{current || '0'}}</div>
    <button @click="clear" class="btn operator2">C</button>
    <button @click="del" class="btn operator2">DEL</button>
    <button @click="ClearCE" class="btn operator2">CE</button>
    <button @click="divide" class="btn operator">/</button>
    <button @click="inverse" class="btn operator">1/x</button>
    <button @click="append('7')" class="btn">7</button>
    <button @click="append('8')" class="btn">8</button>
    <button @click="append('9')" class="btn">9</button>
    <button @click="times" class="btn operator">x</button>
    <button @click="squaring" class="btn operator">x²</button>
    <button @click="append('4')" class="btn">4</button>
    <button @click="append('5')" class="btn">5</button>
    <button @click="append('6')" class="btn">6</button>
    <button @click="minus" class="btn operator">-</button>
    <button @click="root" class="btn operator">√x</button>
    <button @click="append('1')" class="btn">1</button>
    <button @click="append('2')" class="btn">2</button>
    <button @click="append('3')" class="btn">3</button>
    <button @click="add" class="btn operator">+</button>
    <button @click="percent" class="btn operator">%</button>
    <button @click="append('0')" class="btn">0</button>
    <button @click="sign" class="btn operator">+/-</button>
    <button @click="dot" class="btn">.</button>
    <button @click="equal" class="btn operator equal">=</button>
    </div>
  </body>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      previous: '',
      current: '',
      operator: '',
      operatorClicked: false,
      equalClicked:false,
      result:'',
      flag:true,
    }
  },
  methods: {
    clear() {
      this.current = '';
      this.previous= '';
    },
    del: function () {
      this.current = this.current.slice('', -1);
    },
    ClearCE: function () {
      this.current = '';
    },
    sign(){
        axios.get("http://localhost:8082/sign" ,{
            params :{
              first:this.current
            }
          }).then(response => {this.current = response.data})
    },
    percent() {
      axios.get("http://localhost:8082/percent" ,{
            params :{
              first:this.current
            }
          }).then(response => {this.current = response.data})
    },
    squaring(){
       axios.get("http://localhost:8082/squaring" ,{
            params :{
              first:this.current
            }
          }).then(response => {this.current = response.data})
    },
    root(){
      axios.get("http://localhost:8082/root" ,{
            params :{
              first:this.current
            }
          }).then(response => {this.current = response.data})
    },
    inverse(){
      axios.get("http://localhost:8082/inverse" ,{
            params :{
              first:this.current
            }
          }).then(response => {this.current = response.data})
    },

    append(number) {
      if (this.equalClicked){
        this.current = '';
        this.equalClicked = false;
      }
      if(this.current==="ERROR!"){
        this.current='';
      }
      if (this.operatorClicked) {
        this.current = '';
        this.operatorClicked = false;
      }
      this.current = `${this.current}${number}`;
    },
    dot() {
      if (this.current.indexOf('.') === -1) {
        this.append('.');
      }
    },
    setPrevious() {
      this.previous = this.current;
      this.current = '';
    },
    divide() {
      if(this.previous=== ''){
        this.previous = this.current;
        this.current='';
        this.operator = '/';
      } else {
        axios.get("http://localhost:8082/divided" ,{
            params :{
              first:this.previous,second:this.current
            }
          }).then(response => {this.current = response.data
            this.previous = '';
            this.divide();
            this.operator = '/';})}

    },
    times() {
      if(this.previous=== ''){
        this.previous = this.current;
        this.current='';
        this.operator = '*';
      } else {axios.get("http://localhost:8082/multiply" ,{
            params :{
              first:this.previous,second:this.current
            }
          }).then(response => {this.current = response.data
            this.previous = '';
            this.times();
            this.operator = '*';})}
    },
    minus() {
      this.operator = '-';
      if(this.previous=== ''){
        this.previous = this.current;
        this.current='';
      } else {axios.get("http://localhost:8082/subtract" ,{
            params :{
              first:this.previous,second:this.current
            }
          }).then(response => {
            this.current = response.data
            this.previous = '';
            this.minus();
            this.operator = '-';})
            }
    },
   add() {

      if(this.previous=== ''){
        this.previous = this.current;
        this.current='';
        this.operator = '+';

      } else {
        axios.get("http://localhost:8082/add" ,{
            params :{
              first:this.previous,second:this.current
            }
          }).then(response => {
            this.current = response.data;
            this.previous = '';
            this.add();
            this.operator = '+';})}
   },
    equal() {
      if(!(this.current ==='')){
        if(this.operator === '+'){
          this.add();
        } else if (this.operator === '-'){
          this.minus();
        } else if (this.operator === '*'){
          this.times();
        } else if (this.operator === '/'){
          this.divide();
        }
    }
      this.current = '';
      this.result = '';
      this.previous = '';
      this.operator='';
      this.equalClicked = true;
    }
  }
}
</script>

<style scoped>

body {
  background-position: center;
  height: 100vh;
  display: flex;
  font-size: 15px;
  align-items: flex-end;
  justify-content: center;
  background: url("https://images.pexels.com/photos/3137056/pexels-photo-3137056.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
  background-size: cover;
}

.calculator {
  padding: 40px;
  width: 400px;
  font-size: 40px;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  grid-auto-rows: minmax(70px, auto);

}
.display {
  border-radius:50px ;
  grid-column: 1 / 6;
  padding: 20px;
  background-color: #333;
  color: white;
}

.equal {
  grid-column: 4 / 6;
}
.btn {
  background-color: #4CAF50;
  color: white;
  padding: 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  border-radius: 100px
  
}
.btn:hover{
  background-color: skyblue;
  color: black;
}
.operator {
  background-color: orange;
  color: white;
}
.operator:hover{
  background-color: rgb(238, 81, 81);
  color: black;
}
.operator2{
  background-color: rgb(78, 68, 216);
  color: white;
}
.operator2:hover{
  background-color: rgb(222, 243, 128);
  color: black;
}
</style>