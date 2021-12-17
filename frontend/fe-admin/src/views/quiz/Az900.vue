<template>
  <div>           
     <CCard v-for="(item,index) in listQuestion" :key="item.id">
      <CCardHeader>
        Question # {{ index + 1}}<br/>  
       
      </CCardHeader>
      <CCardBody>
        <CRow><p v-html="checkHTML(item.quiz.content)"></p></CRow>
        <CRow v-for="(item2,index1) in item.lstAnswer" :key="item2.id">
          <span v-if="index1  == 0"> A </span>
          <span v-else-if="index1  == 1"> B </span> 
          <span v-else-if="index1  == 2"> C </span> 
          <span v-else-if="index1  == 3"> D </span>          
          . {{item2.content }} 
        </CRow>

      </CCardBody>
    </CCard>  
   
  </div>
</template>

<script>
import * as CONFIG from "@/config/index"
import Pagination from "@/components/Pagination";
import Resource from "@/common/api";

const quizResource = new Resource("learn/api/quiz/quiz-answer")

export default {
  name: 'Az900',
  data () {
    return {
        loading: true,
        listQuestion : null,
        params:{
            
            currentPage : 1,
            rowsPerPage : 300
        },
        total : 0,
    }
  },
  created() {
    this.getListQuestion();
  },

  methods: {
    async getListQuestion(){
      console.log("[mrd-Az900] getListQuestion");
      this.loading = true;
      const {data} = await quizResource.list(this.params);
      this.listQuestion = data.data.data;
      console.log(this.listQuestion)
      this.total = data.data.total;
      this.loading = false;

    },    

    checkHTML(text){
      return text.replaceAll(".",". </br>").replaceAll(":",": </br>");
    }

  }
}
</script>
