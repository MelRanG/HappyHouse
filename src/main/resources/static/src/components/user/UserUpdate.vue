<template>
  <div>
    <!-- <div>UserUpdate.vue</div> -->
    <!-- start of section -->
    <section class="login registration section">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 offset-lg-3 col-md-8 offset-md-2 col-12">
            <div class="form-head">
              <h4 class="title">회원정보 수정</h4>
              <form v-on:submit.prevent>
                <div class="form-group">
                  <label>아이디</label>
                  <input
                    name="userId"
                    type="text"
                    disabled
                    v-model="$store.getters.getUserId"
                    style="color: grey"
                  />
                </div>
                <div class="form-group">
                  <label>이름</label>
                  <input
                    name="userName"
                    type="text"
                    disabled
                    v-model="$store.getters.getUserName"
                    style="color: grey"
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
                  <label>이메일</label>
                  <input
                    name="userEmail"
                    type="email"
                    v-model="$store.state.login.userEmail"
                    :class="{
                      'is-valid': validateEmail,
                      'is-invalid': !validateEmail,
                    }"
                  />
                </div>
                <div class="form-group">
                  <label>전화번호</label>
                  <input
                    name="userPhone"
                    type="text"
                    v-model="$store.state.login.userPhone"
                    :class="{
                      'is-valid': validatePhone,
                      'is-invalid': !validatePhone,
                    }"
                  />
                </div>
                <div class="form-group">
                  <label>회원구분</label>
                  <input
                    name="userRole"
                    type="text"
                    disabled
                    v-model="$store.getters.getUserRole"
                    style="color: grey"
                  />
                </div>

                <div class="button">
                  <button @click="update" class="btn">
                    회원정보 수정 완료
                  </button>
                </div>
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
    validateEmail() {
      let regexp =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      if (regexp.test(this.$store.getters.getUserEmail)) return true;
      else return false;
    },
    validatePhone() {
      if (this.$store.getters.getUserPhone.length >= 9) return true;
      else return false;
    },
  },
  created() {
    this.$store.commit("UNSET_NAVBAR_ACTIVATED");
  },
  methods: {
    async update() {
      if (document.querySelectorAll("form .is-invalid").length > 0) {
        alert("입력이 올바르지 않습니다.");
        return;
      }
      let updateObj = {
        userId: this.$store.getters.getUserId,
        userPassword: this.userPassword,
        userName: this.$store.getters.getUserName,
        userEmail: this.$store.getters.getUserEmail,
        userPhone: this.$store.getters.getUserPhone,
      };
      if (this.$store.getters.getUserRole == "관리자") {
        updateObj["userRoleId"] = 1;
      } else if (this.$store.getters.getUserRole == "일반회원") {
        updateObj["userRoleId"] = 2;
      }
      console.log("[UserUpdate.vue] update() updateObj: ");
      console.log(updateObj);
      try {
        // let { data } = await http.put(
        //   `/users/${this.$store.getters.getUserId}`
        // );
        let { data } = await http.put(
          `/users/${this.$store.getters.getUserId}`,
          updateObj
        );
        console.log("[UserUpdate.vue] update() data: ");
        console.log(data);
        if (data.result == "success") {
          this.$store.commit("SET_USER_UPDATE", {
            userEmail: data.userEmail,
            userPhone: data.userPhone,
          });
          this.$alertify.success("회원정보 수정이 완료되었습니다.");
          this.$router.push("/user");
        } else {
          console.log("[UserUpdate.vue] update() fail: ");
        }
      } catch (error) {
        console.log("[UserUpdate.vue] update() error: ");
        console.log(error);
        // if (error.response.status == "404") {
        //   this.$alertify.error("아이디 또는 비밀번호를 확인하세요.");
        // } else {
        this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
        // }
      }
    },
  },
};
</script>

<style></style>
