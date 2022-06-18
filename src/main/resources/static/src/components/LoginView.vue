<template>
  <div>
    <div>LoginView.vue</div>
    <!-- start of breadcrumb -->
    <div class="breadcrumbs">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-6 col-md-6 col-12">
            <div class="breadcrumbs-content">
              <h1 class="page-title">로그인</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end of breadcrumb -->
    <!-- start of section -->
    <section class="login section">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 offset-lg-3 col-md-8 offset-md-2 col-12">
            <div class="form-head">
              <h4 class="title">로그인</h4>
              <form method="post">
                <div class="form-group">
                  <label>아이디</label>
                  <input
                    v-model="$store.state.login.userId"
                    name="userId"
                    type="text"
                  />
                </div>
                <div class="form-group">
                  <label>비밀번호</label>
                  <input
                    v-model="$store.state.login.userPassword"
                    name="userPassword"
                    type="password"
                  />
                </div>
                <div class="check-and-pass">
                  <div class="row align-items-center">
                    <div class="col-lg-6 col-md-6 col-12"></div>
                    <div class="col-lg-6 col-md-6 col-12"></div>
                  </div>
                </div>
                <div class="button">
                  <button @click="login" type="button" class="btn">
                    로그인
                  </button>
                </div>
                <p class="outer-link">
                  계정이 없으신가요?
                  <a @click="changePageRegister" style="cursor: pointer"
                    >계정 만들기</a
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
  created() {
    this.$store.commit("UNSET_NAVBAR_ACTIVATED");
  },
  methods: {
    async login() {
      let loginObj = {
        userId: this.$store.state.login.userId,
        userPassword: this.$store.state.login.userPassword,
      };
      console.log("[LoginView.vue] login() loginObj: ");
      console.log(loginObj);
      try {
        let { data } = await http.post("/login", loginObj);
        console.log("[LoginView.vue] login() data: ");
        console.log(data);

        if (data.result == "success") {
          this.$store.commit("SET_LOGIN", {
            isLogin: true,
            userSeq: data.userSeq,
            userId: data.userId,
            userName: data.userName,
            userEmail: data.userEmail,
            userPhone: data.userPhone,
            userRole: data.userRole,
          });
          this.$router.push("/");
        } else {
          console.log("[LoginView.vue] login() fail: ");
        }
      } catch (error) {
        console.log("[LoginView.vue] login() error: ");
        console.log(error);
        if (error.response.status == "404") {
          this.$alertify.error("아이디 또는 비밀번호를 확인하세요.");
        } else {
          this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
        }
      }
    },
    changePageRegister() {
      this.$router.push("/register");
    },
  },
};
</script>

<style></style>
