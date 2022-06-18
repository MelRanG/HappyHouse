<template>
  <div class="container">
    <div class="row d-flex justify-content-center pt-3 pb-3">
      <div class="col-lg-7 col-md-3 col-12 p-0">
        <!-- <div class="input-group mb-3 mt-3"> -->
        <div class="search-input mb-3 mt-3">
          <input
            v-model="searchWord"
            @keydown.enter="boardList"
            type="text"
            class="form-control"
            placeholder="Search"
          />
        </div>
      </div>
      <div class="col-lg-2 col-md-2 col-2 p-3">
        <div class="search-btn button">
          <!-- <button @click="boardList" class="btn btn-success" type="button"> -->
          <button
            @click="boardList"
            class="btn p-2"
            type="button"
            style="width: 100%"
          >
            <li class="lni lni-search-alt"></li>
            검색
          </button>
        </div>
      </div>
    </div>
    <div class="row d-flex justify-content-center">
      <div class="col-lg-10 col-md-12 col-12">
        <div class="main-content">
          <div class="dashboard-block mt-0">
            <!-- <h3 class="block-title"></h3> -->
            <div class="my-items">
              <div class="item-list-title">
                <!-- <div class="row align-items-center"> -->
                <div class="row">
                  <div
                    class="col-lg-2 col-md-2 col-12 d-flex justify-content-center"
                  >
                    <p>번호</p>
                  </div>
                  <div
                    class="col-lg-4 col-md-2 col-12 d-flex justify-content-center"
                  >
                    <p>제목</p>
                  </div>
                  <div
                    class="col-lg-2 col-md-2 col-12 d-flex justify-content-center"
                  >
                    <p>작성자</p>
                  </div>
                  <div
                    class="col-lg-2 col-md-2 col-12 d-flex justify-content-center"
                  >
                    <p>작성일</p>
                  </div>
                  <div
                    class="col-lg-2 col-md-2 col-12 d-flex justify-content-center"
                  >
                    <p>추천</p>
                  </div>
                </div>
              </div>

              <div
                class="single-item-list mb-0"
                style="cursor: pointer"
                v-for="(board, index) in list"
                :key="index"
                @click="boardDetail(board.id)"
              >
                <div class="row align-items-center">
                  <div
                    class="col-lg-2 col-md-2 col-12 d-flex justify-content-center"
                  >
                    <p>{{ board.id }}</p>
                  </div>
                  <div class="col-lg-4 col-md-2 col-12">
                    <p>{{ board.title }}</p>
                  </div>
                  <div
                    class="col-lg-2 col-md-2 col-12 d-flex justify-content-center"
                  >
                    <p>{{ board.writer }}</p>
                  </div>
                  <div
                    class="col-lg-2 col-md-2 col-12 d-flex justify-content-center"
                  >
                    <p>{{ board.regDate.date | makeDateStr(".") }}</p>
                  </div>
                  <div
                    class="col-lg-2 col-md-2 col-12 d-flex justify-content-center"
                  >
                    <p>{{ board.recommendCount }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="d-flex justify-content-end mt-2">
          <button
            class="btn mb-2"
            @click="changeToPageInsert"
            style="background-color: #fa96a5; color: #fff"
          >
            글쓰기
          </button>
        </div>
      </div>
    </div>

    <pagination
      style="cursor: pointer"
      :listRowCount="listRowCount"
      :pageLinkCount="pageLinkCount"
      :currentPageIndex="currentPageIndex"
      :totalListItemCount="totalListItemCount"
      v-on:call-parent-move-page="movePage"
    >
    </pagination>
  </div>
</template>

<script>
import http from "@/common/axios.js";
import Pagination from "@/components/Pagination.vue";

export default {
  components: { Pagination },
  data() {
    return {
      list: [],
      limit: 10,
      offset: 0,
      searchWord: "",

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,
      totalListItemCount: 0,
    };
  },

  methods: {
    async boardList() {
      let params = {
        limit: this.limit,
        offset: this.offset,
        searchWord: this.searchWord,
      };

      try {
        let response = await http.get("/boards", { params });
        let { data } = response;
        console.log(data);

        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          this.list = data.list;
          this.totalListItemCount = data.count;
        }
      } catch (error) {
        console.error(error);
      }
    },
    async boardDetail(id) {
      let response = await http.get("/boards/" + id);
      let { data } = response;
      if (data.result == "login") {
        this.$router.push("/login");
      } else {
        this.$store.commit("SET_BOARD", data);
        this.$router.push("/board/detail/" + id);
      }
    },
    movePage(pageIndex) {
      this.offset = (pageIndex - 1) * this.listRowCount;
      this.currentPageIndex = pageIndex;
      this.boardList();
    },
    changeToPageInsert() {
      this.$router.push("/board/insert");
    },
  },
  created() {
    this.boardList();
  },
  filters: {
    makeDateStr: function (date, separator) {
      return (
        date.year +
        separator +
        (date.month < 10 ? "0" + date.month : date.month) +
        separator +
        (date.day < 10 ? "0" + date.day : date.day)
      );
    },
  },
};
</script>

<style></style>
