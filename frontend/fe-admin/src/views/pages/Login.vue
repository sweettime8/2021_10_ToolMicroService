<template>
  <div class="c-app flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <CCol md="8">
          <CCardGroup>
            <CCard class="p-4">
              <CCardBody>
                <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
                  <h1>Login</h1>
                  <p class="text-muted">Sign In to your account</p>
                  <el-form-item prop="username">
                    <el-input v-model="loginForm.username" name="username" type="text" auto-complete="on" placeholder="username" />
                    
                  </el-form-item>
                  <el-form-item prop="password">
                    <el-input
                      v-model="loginForm.password"
                      :type="pwdType"
                      name="password"
                      auto-complete="on"
                      placeholder="password"
                      @keyup.enter.native="handleLogin"
                    />
                  </el-form-item>
                  <CRow>
                    <CCol col="6" class="text-left">
                      <CButton color="info" class="px-4" @click="handleLogin">Login</CButton>
                    </CCol>
                    <CCol col="6" class="text-right">
                      <CButton color="link" class="px-0" >Forgot password?</CButton>
                      <CButton color="link" class="d-lg-none">Register now!</CButton>
                    </CCol>
                  </CRow>
                </el-form>
              </CCardBody>
            </CCard>
            <CCard
              text-color="dark"
              class="text-center py-5 d-md-down-none"
              body-wrapper
            >
              <CCardBody>
                <h2>Sign up</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                <CButton
                  color="info"
                  variant="outline"
                  size="lg"
                >
                  Register Now!
                </CButton>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>

import * as CONFIG from "@/config/index";
import Vue from 'vue';
import Resource from "@/common/api";
import axios from "axios";

export default {
  name: 'Login',
  data() {
    const validatePass = (rule, value, callback) => {
      if (value.length < 4) {
        callback(new Error('Password cannot be less than 4 digits'));
      } else {
        callback();
      }
    };

    return {
      loginForm: {
        username: 'nghduc91',
        password: 'nghduc91',
      },
      loginRules: {
        password: [{ required: true, trigger: 'blur', validator: validatePass }],
      },
      loading: false,
      pwdType: 'password',
      redirect: undefined,
      otherQuery: {},
    };

  },
  created(){
    this.checkLogin();
  },

  methods: {
    async checkLogin(){
      console.log("mrd checklogin")
      let accessToken = window.$cookies.get('accessToken');
      if(accessToken != null){
        window.location.href = `${CONFIG.CLIENT_URL}/#/dashboard`;
      }
    },

    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) { 
          axios.post(CONFIG.API_URL + `/identity/api/authen/login`, this.loginForm).then((response) => {
              if (response.data.status === 200 && response.data.data != null) {
                  this.$notify({
                    title: 'Thành công',
                    message: 'Đăng nhập thành công',
                    type: 'success'
                  });
                  let resData = response.data.data.data;
                  window.$cookies.set("accessToken", resData.accessToken, CONFIG.COOKIES_EXPIRED_TIME);
                  window.location.href = `${CONFIG.CLIENT_URL}/#/dashboard`;
              }else{
                  this.$notify({
                    title: 'Có lỗi xảy ra',
                    message: response.data.message,
                    type: 'error'
                  });
              }
              })
              .catch((err) => {
              console.log(err);
              });        
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }, 

  },
}
</script>
