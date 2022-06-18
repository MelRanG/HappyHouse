<template>
  <div class="container pt-4">
    <div class="row d-flex justify-content-center pt-4">
      <div class="col-lg-10 col-md-12 col-12">
        <div class="main-content">
          <h3 class="post-title">
            <span>{{ title }}</span>
            <button
              type="button"
              class="btn-like float-end ps-2"
              @click="recommend"
              style="width: 26px"
            >
              <span class="heart-shape">{{ heart }}</span>
            </button>
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
              v-show="sameUser"
              class="btn btn-outline float-end ms-2 btn-update"
              type="button"
              @click="boardUpdate"
            >
              수정
            </button>

            <button
              v-show="sameUser"
              class="btn btn-secondary btn-outline float-end"
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
      id: this.$store.getters.getBoard.id,
      title: this.$store.getters.getBoard.title,
      content: this.$store.getters.getBoard.content,
      sameUser: this.$store.getters.getBoard.sameUser,
      heart: "♡",
    };
  },
  methods: {
    boardUpdate() {
      this.$router.push("/board/update/" + this.id);
    },
    changeToDelete() {
      var $this = this; // alertify.confirm-function()에서 this 는 alertify 객체
      this.$alertify.confirmWithTitle(
        "삭제 확인",
        "이 글을 삭제하시겠습니까?",
        function () {
          $this.boardDelete(); // $this 사용
        },
        function () {
          console.log("cancel");
        }
      );
    },
    async boardDelete() {
      try {
        let response = http.delete("/boards/" + this.id);
        let { data } = response;
        console.log(data);
        this.$alertify.success("글이 삭제되었습니다.");
        this.$router.push("/board");
      } catch (error) {
        console.error(error);
      }
    },

    async recommend() {
      let params = {
        boardId: this.id,
        userId: this.$store.getters.getUserId,
      };

      try {
        let response = await http.post("/boards/recommend", params);
        let { data } = response;
        console.log("data: " + data.result);
        data.result == 1 ? (this.heart = "♥") : (this.heart = "♡");
      } catch (error) {
        console.error(error);
      }
    },
    async selectRecommend() {
      let params = {
        boardId: this.id,
        userId: this.$store.getters.getUserId,
      };
      try {
        console.log("/boards/recommend" + { params });
        let response = await http.get("/boards/recommend", { params });
        let { data } = response;
        console.log("data: " + data.result);
        data.result == 1 ? (this.heart = "♥") : (this.heart = "♡");
      } catch (error) {
        console.error(error);
      }
    },
  },
  created() {
    this.selectRecommend();
  },
};
</script>
<style scoped>
.btn-like .heart-shape {
  display: inline;
  color: red;
}
.btn-like {
  border: none;
  background-color: #fff;
}
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
