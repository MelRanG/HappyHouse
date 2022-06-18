<template>
  <div class="container pt-4">
    <div class="row d-flex justify-content-center pt-4">
      <div class="col-lg-10 col-md-12 col-12">
        <div class="main-content">
          <!-- <h4 class="pt-4">글 수정</h4> -->
          <div class="mb-4">
            <label class="form-label post-title mb-2" for="inputUpdateTitle"
              >제목</label
            >
            <input
              type="text"
              class="form-control"
              id="inputUpdateTitle"
              v-model="title"
            />
          </div>
          <div class="pb-4">
            <label class="form-label post-title mb-2" for="divEditorUpdate"
              >내용</label
            >
            <div id="divEditorUpdate"></div>
          </div>
          <div class="">
            <button
              @click="boardUpdate"
              class="btn btn-outline float-end btn-complete ms-2"
              type="button"
            >
              완료
            </button>

            <button
              class="btn btn-outline float-end btn-back"
              type="button"
              onclick="history.back()"
            >
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Vue from "vue";
import CKEditor from "@ckeditor/ckeditor5-vue2";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import VueAlertify from "vue-alertify";

Vue.use(CKEditor).use(VueAlertify);

import http from "@/common/axios.js";

export default {
  data() {
    return {
      id: this.$store.getters.getBoard.id,
      title: this.$store.getters.getBoard.title,
      CKEditor: "",
    };
  },
  methods: {
    async boardUpdate() {
      let params = {
        id: this.id,
        title: this.title,
        content: this.CKEditor.getData(),
        writer: this.$store.getters.getUserId,
      };
      console.log(params);
      try {
        let response = await http.put("/boards/" + this.id, params);
        let { data } = response;
        console.log(data);
        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          this.$alertify.success("글이 수정되었습니다.");
          this.$router.push("/board");
        }
      } catch (error) {
        console.error(error);
      }
    },
  },
  async mounted() {
    try {
      this.CKEditor = await ClassicEditor.create(
        document.querySelector("#divEditorUpdate")
      );
      this.CKEditor.setData(this.$store.getters.getBoard.content);
    } catch (error) {
      console.error(error);
    }
  },
};
</script>
<style scoped>
* {
  color: #777;
}
.ck.ck-editor {
  max-width: 1500px;
}
.pb-4 >>> .ck-editor__editable {
  min-height: 300px;
}
.main-content {
  background-color: #fff;
  padding: 60px 60px 90px;
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
.btn-complete {
  background-color: #fa96a5;
  color: #fff;
}
.btn-complete:hover {
  background-color: #fa96a5;
  color: #eee;
}
</style>
