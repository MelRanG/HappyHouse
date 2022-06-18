<template>
  <div>
    <div>HouseView.vue</div>
    <!-- Start Hero Area -->
    <section class="hero-area overlay">
      <div class="container">
        <div class="row">
          <div class="col-lg-10 offset-lg-1 col-md-12 col-12">
            <div class="hero-text text-center">
              <div class="section-heading">
                <div class="aptSearchForm">
                  <div class="d-flex justify-content-center align-items-center">
                    <!-- <div class="ms-2 me-2">검색 기준:</div> -->
                    <input
                      class="form-check-input m-1"
                      type="radio"
                      name="searchCategory"
                      value="주소"
                      v-model="standard"
                    />
                    <div class="me-4">주소로 검색</div>
                    <input
                      class="form-check-input m-1"
                      type="radio"
                      name="searchCategory"
                      value="아파트명"
                      v-model="standard"
                    />
                    <div>아파트명으로 검색</div>
                  </div>
                  <address-view v-if="standard === '주소'"></address-view>
                  <apt-name-view
                    v-on:call-parent-click-btn-search="clickBtnSearch"
                    v-if="standard === '아파트명'"
                  ></apt-name-view>
                </div>
              </div>
              <!-- End Search Form -->
            </div>
          </div>
        </div>
        <div class="section house" style="padding-top: 10px">
          <!-- v-show="isHouseSearchClicked" -->
          <div id="wrapper" class="mt-4">
            <div id="map" style="width: 100%; height: 500px"></div>
            <!-- start of apt info list -->
            <div id="list">
              <div class="list-group">
                <a
                  v-for="(apt, index) in aptSearchResult"
                  :key="index"
                  class="list-group-item list-group-item-action"
                  id="list-home-list"
                  data-bs-toggle="list"
                  role="tab"
                  aria-controls="list-home"
                  @click="houseDetail(apt)"
                  href="javascript:void(0)"
                >
                  <div class="d-flex w-100 justify-content-between">
                    <p class="mb-1">{{ apt.AptName }}</p>
                  </div>
                  <small
                    class="d-flex justify-content-start text-muted align-bottom"
                    >{{ apt.sidoName }}&nbsp;{{ apt.gugunName }}&nbsp;{{
                      apt.dongName
                    }}</small
                  >
                </a>
              </div>
            </div>
            <!-- end of apt info list -->

            <!-- start of apt deal list -->
            <!-- <div id="deals"> -->
            <div v-show="isHouseDetailClicked" id="deals">
              <div class="d-flex flex-column mb-2">
                <div class="d-flex justify-content-end">
                  <a
                    v-show="!isBookmark && $store.getters.getIsLogin"
                    @click="addBookmark"
                    href="javascript:void(0)"
                  >
                    <i class="lni lni-star" style="cursor: pointer"></i>
                  </a>
                  <a
                    v-show="isBookmark && $store.getters.getIsLogin"
                    @click="deleteBookmark"
                    href="javascript:void(0)"
                  >
                    <i class="lni lni-star-filled" style="cursor: pointer"></i>
                  </a>
                  <div
                    v-show="!$store.getters.getIsLogin"
                    style="width: 26px; height: 26px"
                  ></div>
                </div>
                <h6 class="d-flex justify-content-center m-0">
                  {{ $store.getters.getSelectedAptInfo.AptName }}
                </h6>
                <p class="text-muted d-flex justify-content-center m-0">
                  {{ $store.getters.getSelectedAptInfo.buildYear }}
                </p>
              </div>
              <div class="list-group">
                <a
                  v-for="(apt, index) in aptSearchDealResult"
                  :key="index"
                  class="list-group-item list-group-item-action"
                  id="list-home-list"
                  data-bs-toggle="list"
                  href="javascript:void(0)"
                  aria-controls="list-home"
                  style="pointer-events: none; cursor: default"
                >
                  <!-- role="tab" -->
                  <div class="d-flex w-100 justify-content-between">
                    <p class="mb-1">
                      {{ apt.dealYear }}/{{ apt.dealMonth }}/{{ apt.dealDay }}
                    </p>
                  </div>
                  <small class="d-flex justify-content-start text-muted"
                    >{{ apt.floor }}층</small
                  >
                  <small class="d-flex justify-content-start text-muted"
                    >{{ apt.area }} m2</small
                  >
                  <small class="d-flex justify-content-start text-muted"
                    >{{ apt.dealAmount }}(만)원</small
                  >
                </a>
              </div>
            </div>
            <!-- end of apt deal list -->
          </div>
        </div>
      </div>
    </section>
    <!-- End Hero Area -->
    <!--애니메이션 -->
    <!-- End Hero Area -->
    <!--애니메이션 -->
    <!-- <div id="loader">
      <div class="face">
        <svg
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          x="0px"
          y="100px"
          width="270px"
          height="270px"
          viewBox="0.032 0 270 270"
          overflow="visible"
          enable-background="new 0.032 0 270 270"
          xml:space="preserve"
        >
          <defs></defs>
          <g id="supervisior">
            <g class="head">
              <path
                fill="#58585A"
                d="M135,10c33.389,0,64.778,13.002,88.388,36.612S260,101.611,260,135s-13.003,64.779-36.612,88.388
      S168.389,260,135,260s-64.778-13.002-88.388-36.612C23.002,199.779,10,168.389,10,135s13.002-64.779,36.612-88.388
      C70.222,23.002,101.611,10,135,10 M135,0C60.442,0,0,60.441,0,135s60.442,135,135,135s135-60.441,135-135S209.558,0,135,0L135,0z"
              />
            </g>
            <g id="eyeLeft">
              <g class="eyeLeft">
                <circle
                  class="eye"
                  fill="none"
                  stroke="#58585A"
                  stroke-width="4"
                  stroke-linecap="round"
                  stroke-miterlimit="10"
                  cx="66.063"
                  cy="127.51"
                  r="18.849"
                />
                <circle
                  class="pupil"
                  fill="#58585A"
                  cx="66.063"
                  cy="127.511"
                  r="8.949"
                />
              </g>
              <path
                class="closedLeft"
                fill="none"
                stroke="#58585A"
                stroke-width="4"
                stroke-linecap="round"
                stroke-miterlimit="10"
                d="
   M47.238,127.972c0.247,10.194,8.57,18.387,18.824,18.387s18.579-8.193,18.826-18.387"
              />
            </g>
            <g id="eyeRight">
              <g class="eyeRight">
                <circle
                  class="eye"
                  fill="none"
                  stroke="#58585A"
                  stroke-width="4"
                  stroke-linecap="round"
                  stroke-miterlimit="10"
                  cx="203.936"
                  cy="127.51"
                  r="18.849"
                />
                <circle
                  class="pupil"
                  fill="#58585A"
                  cx="203.935"
                  cy="127.511"
                  r="8.949"
                />
              </g>
              <path
                class="closedRight"
                fill="none"
                stroke="#58585A"
                stroke-width="4"
                stroke-linecap="round"
                stroke-miterlimit="10"
                d="
   M185.11,127.972c0.247,10.194,8.571,18.387,18.824,18.387c10.255,0,18.579-8.193,18.826-18.387"
              />
            </g>
            <g class="mouth">
              <polyline
                class="bar"
                fill="none"
                stroke="#58585A"
                stroke-width="4"
                stroke-linecap="round"
                stroke-miterlimit="10"
                points="
   111.532,232.832 135.032,232.832 158.532,232.832 "
              />
              <path
                class="smile"
                fill="none"
                stroke="#58585A"
                stroke-width="4"
                stroke-linecap="round"
                stroke-miterlimit="10"
                d="
   M62.485,168.615c0.951,35.593,33.016,64.198,72.511,64.198c39.503,0,71.566-28.605,72.519-64.198"
              />
            </g>
          </g>
        </svg>
      </div>
      <div class="loading-bar"></div>
    </div>
    <div id="refresher">
      <p class="refresh">Happy House</p>
    </div> -->

    <!--애니메이션끝 -->
    <!-- <house-detail-modal></house-detail-modal> -->
  </div>
</template>
<script>
import AddressView from "@/components/house/AddressView.vue";
import AptNameView from "@/components/house/AptNameView.vue";
// import HouseDetailModal from "@/components/modals/house/HouseDetailModal.vue";
// import { Modal } from "bootstrap";
import http from "@/common/axios.js";
// import util from "@/common/util.js";
// import Pagination from "@/components/Pagination.vue";

// 삭제
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
  components: {
    AddressView,
    AptNameView,
    // HouseDetailModal
  },
  data() {
    return {
      aptSearchDealResult: [],
      standard: "아파트명",
      //modal
      houseDetailModal: null,
      // map
      mapContainer: null,
      mapCenter: null,
      mapOptions: {},
      map: null,
      currAptMarker: null,
      infowindow: null,
      //bookmark(?)
      isBookmark: false,
      // v-show
      isHouseSearchClicked: false,
      isHouseDetailClicked: false,
      //
      searchResultListClickCount: 0,
    };
  },
  computed: {
    aptSearchResult() {
      return this.$store.getters.getAptSearchResult;
    },
    lat() {
      if (this.$store.getters.getSelectedAptInfo.lat) {
        return this.$store.getters.getSelectedAptInfo.lat;
      } else {
        // return 37.5642135;
        return this.getCurrLat();
      }
    },
    lng() {
      if (this.$store.getters.getSelectedAptInfo.lng) {
        return this.$store.getters.getSelectedAptInfo.lng;
      } else {
        // return 127.0016985;
        return this.getCurrLng();
      }
    },
  },
  created() {
    this.getBookmarkResult();
    this.$store.commit("SET_IS_PAGE_HOUSE_ACTIVATED");
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=862543d3a85d87f973559583a7fb2f1c&libraries=services";
      document.head.appendChild(script);
    }
  },
  updated() {
    this.setCenter();
    // this.initMap();
  },
  methods: {
    setCenter() {
      let $this = this;
      let mvLatLng = null;
      console.log("[HouseView.vue] setCenter() $this.searchCount: ");
      console.log($this.searchResultListClickCount);

      if ($this.searchResultListClickCount <= 1) {
        mvLatLng = new kakao.maps.LatLng(
          $this.$store.getters.getAptSearchResult[0].lat,
          $this.$store.getters.getAptSearchResult[0].lng
        );
      } else {
        mvLatLng = new kakao.maps.LatLng(
          $this.$store.getters.getSelectedAptInfo.lat,
          $this.$store.getters.getSelectedAptInfo.lng
        );
        $this.updateMap(mvLatLng);
        $this.currAptMarker = new kakao.maps.Marker({
          map: $this.map,
          position: mvLatLng,
        });
      }
      $this.map.setCenter(mvLatLng);
    },
    updateMap(mvLatLng) {
      console.log("initMap() called");
      let $this = this;
      $this.mapContainer = document.querySelector("#map");
      $this.mapCenter = mvLatLng;
      $this.mapOptions = {
        center: $this.mapCenter,
        level: 5,
      };
      $this.map = new kakao.maps.Map($this.mapContainer, $this.mapOptions);
      $this.map.setCenter($this.mapCenter);
    },
    initMap() {
      console.log("initMap() called");
      let $this = this;
      $this.mapContainer = document.querySelector("#map");
      $this.mapCenter = new kakao.maps.LatLng(
        37.5663174209601,
        126.977829174031
      );
      $this.mapOptions = {
        center: $this.mapCenter,
        level: 5,
      };
      $this.map = new kakao.maps.Map($this.mapContainer, $this.mapOptions);
      $this.map.setCenter($this.mapCenter);
    },
    getCurrLat() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (pos) {
          console.log("[HouseView.vue] getCurrLat() lat: ");
          console.log(pos.coords.latitude);
          return pos.coords.latitude;
        });
      } else {
        console.log("[HouseView.vue] getCurrLat() lat: ");
        console.log("33");
        alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.");
        alert("위치 서비스를 이용하실 수 없습니다.");
        return 33;
      }
    },
    getCurrLng() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (pos) {
          console.log("[HouseView.vue] getCurrLng() lng: ");
          console.log(pos.coords.longitude);
          return pos.coords.longitude;
        });
      } else {
        console.log("[HouseView.vue] getCurrLng() lng: ");
        console.log("140");
        alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.");
        alert("위치 서비스를 이용하실 수 없습니다.");
        return 140;
      }
    },
    getCurrPos() {
      let $this = this;
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (pos) {
          $this.lat = pos.coords.latitude;
          $this.lng = pos.coords.longitude;
        });
      } else {
        alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.");
        alert("위치 서비스를 이용하실 수 없습니다.");
        $this.lat = 33;
        $this.lng = 140;
      }
    },
    async houseDetail(apt) {
      this.searchResultListClickCount++;
      let url = "?dong=" + apt.dongName + "&aptName=" + apt.AptName;
      try {
        let response = await http.get("/houseDeal" + url);
        let { data } = response;

        this.aptSearchDealResult = data;
        console.log(this.aptSearchDealResult);
      } catch (e) {
        console.error(e);
      }

      this.$store.commit("SET_SELECTED_APT_INFO", apt);
      this.isHouseDetailClicked = true;
      this.isBookmark = Object.keys(
        this.$store.getters.getBookmarkList
      ).includes(String(apt.houseNo));
      console.log(Object.keys(this.$store.getters.getBookmarkList));
      console.log(apt.houseNo);
    },
    async addBookmark() {
      console.log("[HouseView.vue] addBookmark() userSeq, houseNo:");
      console.log(
        this.$store.getters.getUserSeq,
        this.$store.getters.getSelectedAptInfo.houseNo
      );
      let params = {
        userSeq: this.$store.getters.getUserSeq,
        houseNo: this.$store.getters.getSelectedAptInfo.houseNo,
      };
      try {
        let { data } = await http.post("/bookmarks", params);
        console.log("[HouseView.vue] addBookmark() data: ");
        console.log(data);
        if (data == 1) {
          this.isBookmark = true;
          this.getBookmarkResult();
        } else {
          console.log("[HouseView.vue] addBookmark() else: ");
          console.log("북마크 추가 안됨");
        }
      } catch (error) {
        console.log("[HouseView.vue] addBookmark() error: ");
        console.log(error);
      }
    },
    async getBookmarkResult() {
      try {
        let { data } = await http.get(
          `/bookmarks/${this.$store.getters.getUserSeq}`
        );
        console.log("[HouseView.vue] getBookmarkResult() data: ");
        console.log(data);
        this.$store.commit("SET_BOOKMARK_RESULT_LIST", data);
        this.setBookmarkList();
      } catch (error) {
        console.log("[HouseView.vue] getBookmarkResult() error: ");
        console.log(error);
      }
    },
    setBookmarkList() {
      let bookmarkList = {};
      let bookmarkResultList = this.$store.getters.getBookmarkResultList;
      bookmarkResultList.forEach((element) => {
        bookmarkList[element.houseNo] = element.bookmarkId;
      });
      console.log("[BookmarkView] setBookmarkList() bookmarkList: ");
      console.log(bookmarkList);
      this.$store.commit("SET_BOOKMARK_LIST", bookmarkList);
    },
    async deleteBookmark() {
      let bookmarkList = this.$store.getters.getBookmarkList;
      let houseNo = this.$store.getters.getSelectedAptInfo.houseNo;
      console.log(
        "[HouseView.vue] deleteBookmark() userSeq, bookmarkId, houseNo:"
      );
      console.log(this.$store.getters.getUserSeq);
      console.log(bookmarkList[houseNo]);
      console.log(this.$store.getters.getSelectedAptInfo.houseNo);

      try {
        let params = {
          bookmarkId: bookmarkList[houseNo],
        };
        console.log(`/bookmarks/${bookmarkList[houseNo]}`);
        let { data } = await http.delete(`/bookmarks`, { params });
        console.log("[HouseView.vue] deleteBookmark() data: ");
        console.log(data);
        if (data == 1) {
          this.isBookmark = false;
        } else {
          console.log("[HouseView.vue] deleteBookmark() else: ");
          console.log("북마크 삭제 안됨");
        }
      } catch (error) {
        console.log("[HouseView.vue] deleteBookmark() error: ");
        console.log(error);
      }
    },
    clickBtnSearch() {
      this.isHouseSearchClicked = true;
    },
  },
};
</script>

<style scoped>
#wrapper {
  position: relative;
}

#map {
  border-radius: 5px;
}

#list {
  position: absolute;
  top: 10px;
  left: 10px;
  bottom: 10px;
  width: 340px;
  z-index: 100;
  background-color: rgba(255, 244, 244, 0.4);
  overflow-y: scroll;
  border: none;
}

#deals {
  position: absolute;
  top: 10px;
  right: 10px;
  bottom: 10px;
  width: 270px;
  z-index: 100;
  background-color: rgba(255, 255, 255, 0.9);
  overflow-y: scroll;
  border: none;
}

#deals h6 {
  color: #555;
  line-height: 30px;
}
#deals p {
  color: #888;
  line-height: 20px;
  font-size: x-small;
}

/* width */
::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  /* background: #f1f1f1; */
  background: none;
  border-radius: 10px;
}

/* Handle */
::-webkit-scrollbar-thumb {
  /* background: #888; */
  background: #ddd;
  border-radius: 10px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  /* background: #555; */
  background: #aaa;
}

#list .list-group-item {
  margin-bottom: 5px;
  border: none;
  border-radius: 5px;
  background-color: rgba(255, 255, 255, 0.7);
}
#list .list-group-item p {
  font-size: 1.1em;
  font-weight: bold;
  color: #51b6a9;
}
#list .list-group-item:focus {
  background-color: rgba(250, 150, 165, 0.7);
  border: none;
  border-radius: 5px;
}

i {
  margin-top: 10px;
  margin-right: 10px;
  color: #fa96a5;
}

#deals >>> .list-group {
  width: 240px;
  margin-left: 10px;
}

/* ㅇㄴㅁㅇㅅ */
#loader {
  margin-top: 20px;

  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: #e9e8e3;
  z-index: 99;
  -webkit-animation: offscreen 0.5s 15s forwards;
  animation: offscreen 0.5s 6.5s forwards;
}
#refresher {
  position: relative;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  z-index: 1;
  opacity: 0;
  -webkit-animation: show 0.13s 14s forwards;
  animation: show 0.13s 14s forwards;
}
.refresh {
  color: #e9e8e3;
  font-family: "Open Sans Condensed", sans-serif;
  font-size: 2rem;
  text-transform: uppercase;
  letter-spacing: 0.1rem;
}
.face {
  width: 300px;
  height: 300px;
}
/*
----------------------------------------------
SVG 
----------------------------------------------
*/
.head {
  transform-origin: 135px 135px;
  transform: scale(0);
  -webkit-animation: popup 0.6s 1s cubic-bezier(0.95, 0.05, 0.795, 0.035)
    forwards;
  animation: popup 0.6s 1s cubic-bezier(0.95, 0.05, 0.795, 0.035) forwards;
}
#eyeLeft,
#eyeRight {
  transform: scale(0);
}
#eyeLeft {
  transform-origin: 66px 127px;
  -webkit-animation: popup 0.5s 1.2s cubic-bezier(0.95, 0.05, 0.795, 0.035)
    forwards;
  animation: popup 0.5s 1.2s cubic-bezier(0.95, 0.05, 0.795, 0.035) forwards;
}
#eyeRight {
  transform-origin: 203px 127px;
  -webkit-animation: popup 0.5s 1.25s cubic-bezier(0.95, 0.05, 0.795, 0.035)
    forwards;
  animation: popup 0.5s 1.25s cubic-bezier(0.95, 0.05, 0.795, 0.035) forwards;
}
.eyeLeft {
  transform-origin: 66px 127px;
  -webkit-animation: downup 4.2s 1.6s cubic-bezier(0.44, 0, 0.01, 0.99) forwards;
  animation: downup 4.2s 1.6s cubic-bezier(0.44, 0, 0.01, 0.99) forwards;
}
.eyeRight {
  transform-origin: 203px 127px;
  -webkit-animation: downup 4.2s 1.6s cubic-bezier(0.44, 0, 0.01, 0.99) forwards;
  animation: downup 4.2s 1.6s cubic-bezier(0.44, 0, 0.01, 0.99) forwards;
}
.eyeLeft > .pupil {
  transform-origin: 66px 127px;
  -webkit-animation: follow 4.2s 1.6s forwards;
  animation: follow 4.2s 1.6s forwards;
}
.eyeRight > .pupil {
  transform-origin: 203px 127px;
  -webkit-animation: follow 4.2s 1.6s forwards;
  animation: follow 4.2s 1.6s forwards;
}
.closedLeft {
  transform-origin: 66px 127px;
  -webkit-animation: blink 4.2s 1.6s cubic-bezier(0.44, 0, 0.01, 0.99) forwards;
  animation: blink 4.2s 1.6s cubic-bezier(0.44, 0, 0.01, 0.99) forwards;
}
.closedRight {
  transform-origin: 203px 127px;
  -webkit-animation: blink 4.2s 1.6s cubic-bezier(0.44, 0, 0.01, 0.99) forwards;
  animation: blink 4.2s 1.6s cubic-bezier(0.44, 0, 0.01, 0.99) forwards;
}
.mouth {
  transform-origin: 135px 233px;
  -webkit-animation: mouth 4.2s 1.6s cubic-bezier(0.44, 0, 0.01, 0.99) forwards;
  animation: mouth 4.2s 1.6s cubic-bezier(0.44, 0, 0.01, 0.99) forwards;
}
.bar {
  transform-origin: 135px 233px;
  transform: scale(0);
  -webkit-animation: patient 4.9s 1s ease-in forwards;
  animation: patient 4.9s 1s ease-in forwards;
}
.smile {
  transform-origin: 135px 233px;
  transform: scale(0);
  -webkit-animation: happy 5s 1s ease-in forwards;
  animation: happy 5s 1s ease-in forwards;
}
/*
----------------------------------------------
LOADING BAR 
----------------------------------------------
*/
.loading-bar {
  position: relative;
  margin-top: 0rem;
  width: 250px;
  background: #c1c0bc;
}
.loading-bar:before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 0;
  background: #58585a;
  -webkit-animation: load 4.9s 1s forwards;
  animation: load 4.9s 1s forwards;
}
.loading-bar,
.loading-bar:before {
  height: 4px;
  border-radius: 5px;
}
/*
----------------------------------------------
ANIMATIONS
----------------------------------------------
*/
@keyframes popup {
  0% {
    transform: scale(0);
  }
  80% {
    transform: scale(1);
  }
  90% {
    transform: scale(0.75);
  }
  100% {
    transform: scale(1);
  }
}
@keyframes downup {
  0% {
    transform: translateY(0);
  }
  15% {
    transform: translateY(40px);
  }
  50% {
    opacity: 1;
  }
  50.7% {
    opacity: 0;
  }
  51.4% {
    opacity: 1;
  }
  98% {
    transform: translateY(40px);
  }
  100% {
    transform: translateY(0);
  }
}
@keyframes mouth {
  0% {
    transform: translateY(-20px);
  }
  15% {
    transform: translateY(0px);
  }
  100% {
    transform: translateY(0px);
  }
}
@keyframes blink {
  0% {
    transform: translateY(0);
  }
  15% {
    transform: translateY(40px);
  }
  50% {
    opacity: 1;
  }
  98% {
    transform: translateY(40px);
  }
  100% {
    transform: translateY(0);
  }
}
@keyframes follow {
  0% {
    transform: translate(0, 0);
  }
  15% {
    transform: translate(-4px, 8px);
  }
  95.5% {
    transform: translate(-2px, 10px);
  }
  98% {
    transform: translate(8px, 10px);
  }
  100% {
    transform: translate(0, 0);
  }
}
@keyframes patient {
  0% {
    transform: scale(0);
  }
  3% {
    transform: scale(0);
  }
  3.5% {
    transform: scale(1);
  }
  4% {
    transform: scale(0.75);
  }
  4.5% {
    transform: scale(1);
  }
  99% {
    transform: scale(1);
  }
  100% {
    transform: scale(0);
  }
}
@keyframes happy {
  0% {
    transform: scale(0);
  }
  98% {
    transform: scale(0);
  }
  100% {
    transform: scale(1);
  }
}
@keyframes load {
  0% {
    width: 0;
  }
  97% {
    width: 50px;
  }
  100% {
    width: 250px;
  }
}
@keyframes offscreen {
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(-200%);
  }
}
@keyframes show {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
</style>
