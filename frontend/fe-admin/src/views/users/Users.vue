<template>
  <CRow>

      <CCard class="card-user">
        <CCardHeader>
          Users
        </CCardHeader>
        <CCardBody>
          <el-table
            v-loading="loading"
            :data="list"
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

            <el-table-column label="username" width="150" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.username }}</span>
              </template>
            </el-table-column>

            <el-table-column label="fullname" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.fullName }}</span>
              </template>
            </el-table-column>

            <el-table-column label="email" width="250" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.email }}</span>
              </template>
            </el-table-column>

            <el-table-column label="mobile" width="250" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.mobile }}</span>
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

import usersData from './UsersData'

const userSearchResource = new Resource("identity/api/user/search")


export default {
  name: 'Users',
  components: {
    Pagination,
  },
  data () {
    return {
      params:{
        currentPage : 1,
        rowsPerPage : 5
      },
      total : 0,
      items: usersData,
      list:null,
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
    this.getListUser();
  },

  methods: {

    async getListUser(){
      console.log("[mrd] getListUser");
      this.loading = true;
      const {data} = await userSearchResource.list(this.params);
      this.list = data.data.data;
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