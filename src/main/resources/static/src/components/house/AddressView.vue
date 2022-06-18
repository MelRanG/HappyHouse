<template>
  <div class="search-form wow fadeInUp" data-wow-delay=".7s">
    <div class="row justify-content-center">
      <div class="col-lg-3 col-md-3 col-12 p-0">
        <div class="search-input">
          <label for="category"><li class="lni lni-chevron-down"></li></label>
          <select id="sido" v-model="SIDO" @change="getSidoList()">
            <option value="0">시/도</option>
            <option
              v-for="(sido, index) in sidoList"
              :key="index"
              :value="sido.code"
            >
              {{ sido.name }}
            </option>
          </select>
        </div>
      </div>
      <div class="col-lg-3 col-md-3 col-12 p-0">
        <div class="search-input">
          <label for="category"><li class="lni lni-chevron-down"></li></label>
          <select id="gugun" v-model="GUGUN" @change="getGugunList()">
            <option value="0">구/군</option>
            <option
              v-for="(gugun, index) in gugunList"
              :key="index"
              :value="gugun.code"
            >
              {{ gugun.name }}
            </option>
          </select>
        </div>
      </div>
      <div class="col-lg-3 col-md-3 col-12 p-0">
        <div class="search-input">
          <label for="category"><li class="lni lni-chevron-down"></li></label>
          <select id="dong" v-model="DONG">
            <option value="0">동</option>
            <option
              v-for="(dong, index) in dongList"
              :key="index"
              :value="dong.name"
            >
              {{ dong.name }}
            </option>
          </select>
        </div>
      </div>
      <div class="col-lg-2 col-md-2 col-12 p-0">
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
      SIDO: 0,
      GUGUN: 0,
      DONG: 0,
      sidoList: [],
      gugunList: [],
      dongList: [],
    };
  },
  methods: {
    async getSidoList() {
      try {
        let response = await http.get("/sido");
        console.log("response: " + response);
        let { data } = response;
        console.log("data: " + data);
        this.sidoList = data;
        this.GUGUN = 0;
        this.DONG = 0;
        this.getGugunList();
      } catch (e) {
        console.error(e);
      }
    },
    async getGugunList() {
      try {
        let response = await http.get("/gugun/" + this.SIDO);
        console.log("response: " + response);
        let { data } = response;
        console.log("data: " + data);
        this.gugunList = data;
        this.DONG = 0;
        this.getDongList();
      } catch (e) {
        console.error(e);
      }
    },
    async getDongList() {
      try {
        let response = await http.get("/dong/" + this.GUGUN);
        console.log("response: " + response);
        let { data } = response;
        console.log("dongData: " + data);
        this.dongList = data;
        //아파트리스트 받아오는 함수
      } catch (e) {
        console.error(e);
      }
    },

    async getAptInfoList() {
      if (this.DONG == 0) return;
      try {
        let response = await http.get("/houseInfos/address/" + this.DONG);
        console.log("response: " + response);
        let { data } = response;
        console.log("data: " + data);
        this.$store.commit("SET_APT_SEARCH_RESULT", data);
      } catch (e) {
        console.error(e);
      }
    },
  },
  created() {
    this.getSidoList();
  },
};
</script>

<style></style>
