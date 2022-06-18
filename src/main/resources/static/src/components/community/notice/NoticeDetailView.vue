<template>
  <div class="container pt-4">
    <div class="row d-flex justify-content-center pt-4">
      <div class="col-lg-10 col-md-12 col-12">
        <div class="main-content">
          <h3 class="post-title">
            <span>{{ title }}</span>
          </h3>
          <p class="post-content">{{ content }}</p>
          <div class="post-title line"></div>
          <div class="mb-2">
            <button
              class="btn btn-outline btn-back"
              type="button"
              onclick="history.back()"
            >
              뒤로가기
            </button>
            <button
              class="btn btn-outline float-end ms-2 btn-update"
              v-if="role === '관리자'"
              type="button"
              @click="noticeUpdate"
            >
              수정
            </button>

            <button
              class="btn btn-secondary btn-outline float-end"
              v-if="role === '관리자'"
              type="button"
              @click="changeToDelete"
            >
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import http from "@/common/axios.js";

export default {
  data() {
    return {
      id: this.$store.getters.getNotice.id,
      title: this.$store.getters.getNotice.title,
      content: this.$store.getters.getNotice.content,
      role: this.$store.getters.getUserRole,
    };
  },
  methods: {
    noticeUpdate() {
      this.$router.push("/notice/update/" + this.id);
    },
    changeToDelete() {
      var $this = this; // alertify.confirm-function()에서 this 는 alertify 객체
      this.$alertify.confirmWithTitle(
        "삭제 확인",
        "이 글을 삭제하시겠습니까?",
        function () {
          $this.noticeDelete(); // $this 사용
        },
        function () {
          console.log("cancel");
        }
      );
    },
    async noticeDelete() {
      try {
        let response = http.delete("/notices/" + this.id);
        let { data } = response;
        console.log(data);

        // if (data.result == "login") {
        //   this.$router.push("/login");
        // } else {
        //   this.$alertify.success("글이 삭제되었습니다.");
        //   router.push("/notices");
        // }
        this.$alertify.success("글이 삭제되었습니다.");
        this.$router.push("/notice");
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
<style scoped>
.main-content {
  background-color: #fff;
  padding: 60px;
  border-radius: 10px;
}
.post-title {
  font-size: 22px !important;
  font-weight: 500 !important;
  margin-bottom: 40px;
  position: relative;
  z-index: 1;
  text-transform: capitalize;
}
.post-title span {
  background-color: #fff;
  padding-right: 15px;
}
.post-title::before {
  position: absolute;
  left: 0;
  top: 50%;
  height: 1px;
  width: 100%;
  background-color: #eee;
  content: "";
  z-index: -1;
}
.post-title.line {
  height: 100px;
  margin-bottom: 0px;
}
.post-content {
  color: #888;
}
.btn-back {
  background-color: #51b6a9;
  color: #fff;
}
.btn-back:hover {
  background-color: #51b6a9;
  color: #eee;
}
.btn-update {
  background-color: #fa96a5;
  color: #fff;
}
.btn-update:hover {
  background-color: #fa96a5;
  color: #eee;
}
</style>
