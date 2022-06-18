<template>
  <div class="search-form wow fadeInUp" data-wow-delay=".7s">
    <div class="row justify-content-center">
      <div class="col-lg-7 col-md-3 col-12 p-0">
        <div class="search-input">
          <input
            id="sido"
            type="text"
            v-model="aptName"
            @keyup.enter="getAptInfoList()"
          />
        </div>
      </div>
      <div class="col-lg-2 col-md-2 col-2 p-0">
        <div class="search-btn button">
          <button class="btn" @click="getAptInfoList()">
            <li class="lni lni-search-alt"></li>
            검색
          </button>
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
      aptName: "",
    };
  },
  methods: {
    async getAptInfoList() {
      try {
        let response = await http.get("/houseInfos/aptName/" + this.aptName);
        // console.log("response: " + response);
        let { data } = response;
        console.log("[AptNameView.vue] getAptInfoList() data: ");
        console.log(data);
        this.$store.commit("SET_APT_SEARCH_RESULT", data);
        this.$emit("call-parent-click-btn-search");
      } catch (e) {
        console.error(e);
      }
    },
  },
};
</script>

<style></style>
