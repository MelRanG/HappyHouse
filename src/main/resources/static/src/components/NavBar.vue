<template>
  <div>
    <!-- <div>NavBar.vue</div> -->
    <!-- Start Header Area -->
    <header class="header navbar-area">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-12">
            <div class="nav-inner">
              <nav class="navbar navbar-expand-lg">
                <a
                  @click="changePageIndex"
                  class="navbar-brand mt-2"
                  style="cursor: pointer"
                >
                  <!-- <a class="navbar-brand mt-2" href="index.html"> -->
                  <h1 style="color: #fa96a5">햅삐하우쓰</h1>
                  <!-- <img src="assets/images/logo/logo.svg" alt="Logo" /> -->
                </a>
                <button
                  class="navbar-toggler mobile-menu-btn"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#navbarSupportedContent"
                  aria-controls="navbarSupportedContent"
                  aria-expanded="false"
                  aria-label="Toggle navigation"
                >
                  <span class="toggler-icon"></span>
                  <span class="toggler-icon"></span>
                  <span class="toggler-icon"></span>
                </button>
                <div
                  class="collapse navbar-collapse sub-menu-bar"
                  id="navbarSupportedContent"
                >
                  <ul id="nav" class="navbar-nav ms-auto">
                    <li class="nav-item">
                      <a
                        @click="changePageHouse"
                        class="text-decoration-none"
                        :class="{
                          active: $store.getters.getIsPageHouseActivated,
                        }"
                        aria-label="Toggle navigation"
                        style="cursor: pointer"
                        >거래검색</a
                      >
                      <!-- href="javascript:void(0)" -->
                    </li>
                    <li class="nav-item">
                      <a
                        @click="changePageBookmark"
                        class="text-decoration-none"
                        :class="{
                          active: $store.getters.getIsPageBookmarkActivated,
                        }"
                        aria-label="Toggle navigation"
                        style="cursor: pointer"
                        >즐겨찾기</a
                      >
                      <!-- href="javascript:void(0)" -->
                    </li>
                    <li class="nav-item">
                      <a
                        @click="changePageCommunity"
                        class="dd-menu collapsed text-decoration-none"
                        :class="{
                          active: $store.getters.getIsPageCommunityActivated,
                        }"
                        data-bs-toggle="collapse"
                        data-bs-target="#submenu-1-3"
                        aria-controls="navbarSupportedContent"
                        aria-label="Toggle navigation"
                        aria-expanded="false"
                        style="cursor: pointer"
                        >커뮤니티</a
                      >
                      <!-- href="javascript:void(0)" -->
                      <ul class="sub-menu collapse" id="submenu-1-3">
                        <li class="nav-item">
                          <a
                            @click="changePageNotice"
                            class="text-decoration-none"
                            style="cursor: pointer"
                            >공지사항</a
                          >
                          <!-- href="javascript:void(0)" -->
                        </li>
                        <li class="nav-item">
                          <a
                            @click="changePageBoard"
                            class="text-decoration-none"
                            style="cursor: pointer"
                            >자유게시판</a
                          >
                          <!-- href="javascript:void(0)" -->
                        </li>
                      </ul>
                    </li>
                  </ul>
                </div>
                <!-- navbar collapse -->
                <div v-show="!$store.getters.getIsLogin" class="login-button">
                  <ul>
                    <li>
                      <a
                        @click="changePageLogin"
                        class="text-decoration-none"
                        href="javascript:void(0)"
                        ><i class="lni lni-enter"></i>로그인</a
                      >
                    </li>
                    <li>
                      <a
                        @click="changePageRegister"
                        class="text-decoration-none"
                        href="javascript:void(0)"
                        ><i class="lni lni-user"></i>회원가입</a
                      >
                    </li>
                  </ul>
                </div>
                <div v-show="$store.getters.getIsLogin" class="logout-button">
                  <ul>
                    <li>
                      <a
                        @click="changePageLogout"
                        class="text-decoration-none"
                        href="javascript:void(0)"
                        ><i class="lni lni-enter"></i>로그아웃</a
                      >
                    </li>
                    <li>
                      <a
                        @click="changePageUser"
                        class="text-decoration-none"
                        href="javascript:void(0)"
                        ><i class="lni lni-user"></i>회원정보</a
                      >
                    </li>
                  </ul>
                </div>
              </nav>
              <!-- navbar -->
            </div>
          </div>
        </div>
        <!-- row -->
      </div>
      <!-- container -->
    </header>
    <!-- End Header Area -->
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
      pageHouseActivated: false,
      pageBookmarkActivated: false,
      pageCommunityActivated: false,
    };
  },
  computed: {
    isPageHouseActivated() {
      return this.pageHouseActivated;
    },
    isPageBookmarkActivated() {
      return this.pageBookmarkActivated;
    },
    isPageCommunityActivated() {
      return this.pageCommunityActivated;
    },
  },
  methods: {
    changePageIndex() {
      this.$store.commit("SET_IS_PAGE_HOUSE_ACTIVATED");
      this.$router.push("/");
    },
    changePageHouse() {
      this.$store.commit("SET_IS_PAGE_HOUSE_ACTIVATED");
      this.$router.push("/house");
    },
    changePageBookmark() {
      this.$store.commit("SET_IS_PAGE_BOOKMARK_ACTIVATED");
      // this.$store.commit("UNSET_SELECTED_APT_INFO");
      this.$router.push("/bookmark");
    },
    changePageCommunity() {
      this.$store.commit("SET_IS_PAGE_COMMUNITY_ACTIVATED");
      this.$router.push("/community");
    },
    changePageNotice() {
      this.$store.commit("SET_IS_PAGE_COMMUNITY_ACTIVATED");
      this.$router.push("/notice");
    },
    changePageBoard() {
      this.$store.commit("SET_IS_PAGE_COMMUNITY_ACTIVATED");
      this.$router.push("/board");
    },
    changePageLogin() {
      this.$store.commit("UNSET_NAVBAR_ACTIVATED");
      this.$router.push("/login");
    },
    changePageRegister() {
      this.$store.commit("UNSET_NAVBAR_ACTIVATED");
      this.$router.push("/register");
    },
    changePageLogout() {
      this.$store.commit("SET_IS_PAGE_HOUSE_ACTIVATED");
      this.logout();
      this.$router.push("/");
    },
    changePageUser() {
      this.$store.commit("UNSET_NAVBAR_ACTIVATED");
      this.$router.push("/user");
    },
    async logout() {
      let logoutObj = {
        userId: this.$store.getters.getUserId,
        userName: this.$store.getters.getUserName,
        userEmail: this.$store.getters.getUserEmail,
        userPhone: this.$store.getters.getUserPhone,
      };
      if (this.$store.getters.getUserRole == "관리자") {
        logoutObj["userRoleId"] = 1;
      } else if (this.$store.getters.getUserRole == "일반회원") {
        logoutObj["userRoleId"] = 2;
      }
      console.log("[NavBar.vue] logout() logoutObj: ");
      console.log(logoutObj);
      try {
        await http.post("/logout", logoutObj);
        alert("로그아웃이 완료되었습니다.");
        this.$store.commit("SET_LOGOUT");
        this.$router.push("/");
      } catch (error) {
        console.log("[NavBar.vue] logout() error: ");
        console.log(error);
        this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
      }
    },
  },
};
</script>

<style></style>
