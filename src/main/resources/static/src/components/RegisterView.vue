<template>
  <div>
    <div>RegisterView.vue</div>
    <!-- start of breadcrumb -->
    <div class="breadcrumbs">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-6 col-md-6 col-12">
            <div class="breadcrumbs-content">
              <h1 class="page-title">회원가입</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end of breadcrumb -->
    <!-- start of section -->
    <section class="login registration section">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 offset-lg-3 col-md-8 offset-md-2 col-12">
            <div class="form-head">
              <h4 class="title">회원가입</h4>
              <form v-on:submit.prevent>
                <div class="form-group">
                  <label>아이디</label>
                  <input
                    name="userId"
                    type="text"
                    v-model="$store.state.login.userId"
                    :class="{
                      'is-valid': validateId,
                      'is-invalid': !validateId,
                    }"
                  />
                </div>
                <div class="form-group">
                  <label>이름</label>
                  <input
                    name="userName"
                    type="text"
                    v-model="$store.state.login.userName"
                    :class="{
                      'is-valid': validateName,
                      'is-invalid': !validateName,
                    }"
                  />
                </div>
                <div class="form-group">
                  <label>비밀번호</label>
                  <input
                    name="userPassword"
                    type="password"
                    v-model="userPassword"
                    :class="{
                      'is-valid': validatePassword,
                      'is-invalid': !validatePassword,
                    }"
                  />
                </div>
                <div class="form-group">
                  <label>비밀번호 확인</label>
                  <input
                    name="userPasswordCheck"
                    type="password"
                    v-model="userPasswordCheck"
                    :class="{
                      'is-valid': validatePasswordCheck,
                      'is-invalid': !validatePasswordCheck,
                    }"
                  />
                </div>
                <div class="form-group">
                  <label>회원구분</label>
                  <input
                    class="radioRegistUserCategory"
                    type="radio"
                    name="userCategory"
                    value="일반회원"
                    v-model="$store.state.login.userRole"
                    checked
                  />
                  <span>&nbsp;일반회원&nbsp;&nbsp;</span>
                  <!-- <input
                    class="radioRegistUserCategory"
                    type="radio"
                    name="userCategory"
                    value="관리자"
                    v-model="$store.state.login.userRole"
                  />
                  <span>&nbsp;관리자</span> -->
                </div>
                <div class="button">
                  <button @click="register" class="btn">회원등록</button>
                </div>
                <p class="outer-link">
                  이미 계정이 있으신가요?
                  <a @click="changePageLogin" style="cursor: pointer">
                    로그인 하기</a
                  >
                </p>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- end of section -->
  </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);
import http from "@/common/axios.js";
export default {
  data() {
    return {
      userPassword: "",
      userPasswordCheck: "",
    };
  },
  computed: {
    validateId() {
      if (this.$store.getters.getUserId.length >= 4) return true;
      else return false;
    },
    validateName() {
      if (this.$store.getters.getUserName.length >= 2) return true;
      else return false;
    },
    validatePassword() {
      if (this.userPassword.length >= 4) return true;
      else return false;
      // var patternEngAtListOne = new RegExp(/[a-zA-Z]+/);
      // var patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/);
      // var patternNumAtListOne = new RegExp(/[0-9]+/);
      //
      // if( patternEngAtListOne.test( this.userPassword )
      //     && patternSpeAtListOne.test( this.userPassword )
      //     && patternNumAtListOne.test( this.userPassword )
      //     && this.userPassword.length >= 8
      // ){
      //     return true;
      // }
      // else return false;
    },
    validatePasswordCheck() {
      if (this.userPassword == this.userPasswordCheck) return true;
      else return false;
    },
  },
  created() {
    this.$store.commit("UNSET_NAVBAR_ACTIVATED");
  },
  methods: {
    async register() {
      if (document.querySelectorAll("form .is-invalid").length > 0) {
        alert("입력이 올바르지 않습니다.");
        return;
      }
      let registerObj = {
        userId: this.$store.getters.getUserId,
        userName: this.$store.getters.getUserName,
        userPassword: this.userPassword,
      };
      if (this.$store.getters.getUserRole == "관리자") {
        registerObj["userRoleId"] = 1;
      } else if (this.$store.getters.getUserRole == "일반회원") {
        registerObj["userRoleId"] = 2;
      }
      console.log("[RegieterView.vue] register() registerObj: ");
      console.log(registerObj);
      try {
        let { data } = await http.post("/users", registerObj);
        console.log("[RegieterView.vue] register() data: ");
        console.log(data);
        if (data.result == "success") {
          this.$store.commit("SET_LOGOUT");
          alert("회원가입이 완료되었습니다. 로그인 후 서비스를 이용해주세요.");
          this.$router.push("/login");
        }
      } catch (error) {
        console.log("[RegieterView.vue] register() error: ");
        console.log(error);
        // if (error.response.status == "404") {
        //   this.$alertify.error("아이디 또는 비밀번호를 확인하세요.");
        // } else {
        this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
        // }
      }
    },
    changePageLogin() {
      this.$router.push("/login");
    },
  },
};
</script>

<style></style>
