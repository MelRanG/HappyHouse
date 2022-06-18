<template>
  <div>
    <!-- <div>UserDetail.vue</div> -->
    <!-- start of section -->
    <section class="login registration section">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 offset-lg-3 col-md-8 offset-md-2 col-12">
            <div class="form-head">
              <h4 class="title">회원정보</h4>
              <form action="#!" method="post">
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
                  <label>이메일</label>
                  <input
                    name="userEmail"
                    type="email"
                    disabled
                    v-model="$store.getters.getUserEmail"
                    style="color: grey"
                  />
                </div>
                <div class="form-group">
                  <label>전화번호</label>
                  <input
                    name="userPhone"
                    type="text"
                    disabled
                    v-model="$store.getters.getUserPhone"
                    style="color: grey"
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
                  <!-- <button type="submit" class="btn" > -->
                  <button
                    @click="changePageUserUpdate"
                    type="button"
                    class="btn mb-1"
                  >
                    회원정보 수정
                  </button>
                  <button
                    @click="changePageUserDelete"
                    type="button"
                    class="btn"
                  >
                    회원탈퇴
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
  created() {
    this.$store.commit("UNSET_NAVBAR_ACTIVATED");
  },
  methods: {
    changePageUserUpdate() {
      this.$router.push("/user/update");
    },
    changePageUserDelete() {
      this.delete();
    },
    async delete() {
      try {
        let { data } = await http.delete(
          `/users/${this.$store.getters.getUserId}`
        );
        if (data.result == "success") {
          this.$store.commit("SET_LOGOUT");
          alert("회원 탈퇴가 완료되었습니다.");
          this.$router.push("/");
        } else {
          console.log("[UserDetail.vue] delete() fail: ");
        }
      } catch (error) {
        console.log("[UserDetail.vue] delete() error: ");
        console.log(error);
        this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
      }
    },
  },
};
</script>

<style></style>
