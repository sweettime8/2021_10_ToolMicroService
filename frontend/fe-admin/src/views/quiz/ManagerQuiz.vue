<template>
  <CRow>

      <CCard class="card-user">
        <CCardHeader>
          <h2>Manager Quiz</h2>
        </CCardHeader>
        <CCardBody>
          <el-table
            v-loading="loading"
            :data="listQuestion"
            border
            stripe
            fit
            highlight-current-row
            style="width: 100%"
          >
            <el-table-column label="STT" width="50" align="center" >
              <template slot-scope="scope">
                <span>{{
                  (params.currentPage - 1) * params.rowsPerPage + scope.$index + 1
                }}</span>
              </template>
            </el-table-column>

            <el-table-column label="Exam Subject" width="150" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.username }}</span>
              </template>
            </el-table-column>

            <el-table-column label="Content" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.content}}</span>
              </template>
            </el-table-column>


            <el-table-column label="status" width="150" align="center">
              <template slot-scope="scope">
                <span v-if="scope.row.status == 1">
                  <CBadge :color="getBadge(1)">
                    Active
                  </CBadge>
                </span>
                <span v-else>
                  <CBadge :color="getBadge(0)">
                    InActive
                  </CBadge>
                </span> 
              </template>
            </el-table-column> 

            <el-table-column label="Action" width="150" align="center">
              <template >
                <el-button type="primary" icon="el-icon-edit" circle></el-button>
                <el-button type="danger" icon="el-icon-delete" circle></el-button>               
              </template>
            </el-table-column>

          </el-table>
          <pagination
            v-show="total > 5"
            :total="total"
            :page.sync="params.currentPage"
            :limit.sync="params.rowsPerPage"
            @pagination="getListUser"
          />
          
        </CCardBody>
      </CCard>
  </CRow>
</template>

<script>
import * as CONFIG from "@/config/index"
import Pagination from "@/components/Pagination";
import Resource from "@/common/api";

const quizResource = new Resource("learn/api/quiz")


export default {
  name: 'Users',
  components: {
    Pagination,
  },
  data () {
    return {
        loading: true,
        listQuestion : null,
        params:{           
            currentPage : 1,
            rowsPerPage : 15
        },
        total : 0,
    }
  },
  watch: {
    $route: {
      immediate: true,
      handler (route) {
        if (route.query && route.query.page) {
          this.activePage = Number(route.query.page)
        }
      }
    }
  },
  created(){
    this.getListQuestion();
  },

  methods: {

    async getListQuestion(){
      console.log("[mrd] getListUser");
      this.loading = true;
      const {data} = await quizResource.list(this.params);
      this.listQuestion = data.data.data;
      this.total = data.data.total;
      this.loading = false;

    }, 
    
    getBadge (status) {
      switch (status) {
        case 1: return 'success'
        case 0: return 'secondary'
        default: 'primary'
      }
    },
    rowClicked (item, index) {
      this.$router.push({path: `users/${index + 1}`})
    },

  }
}
</script>

<style>
.card-user{
  width: 100%;
}
.abc {
  color: black;
  width: 100%;
  height: 500px
}
</style>