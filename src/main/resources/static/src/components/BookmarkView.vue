<template>
  <div>
    <div>BookmarkView.vue</div>
    <!-- start of breadcrumb -->
    <div class="breadcrumbs">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-6 col-md-6 col-12">
            <div class="breadcrumbs-content">
              <h1 class="page-title">즐겨찾기</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end of breadcrumb -->
    <!-- Bookmark Result start -->
    <div class="bookmark section">
      <div id="wrapper" class="container">
        <div id="map" style="width: 100%; height: 500px"></div>
        <!-- start of apt info list -->
        <div id="list">
          <div class="list-group">
            <a
              v-for="(apt, index) in $store.getters.getBookmarkResultList"
              :key="index"
              class="list-group-item list-group-item-action"
              id="list-home-list"
              data-bs-toggle="list"
              href="#list-home"
              role="tab"
              aria-controls="list-home"
              @click="houseDetail(apt)"
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
        <div v-show="isHouseDetailClicked" id="deals">
          <div class="d-flex flex-column mb-2">
            <div class="d-flex justify-content-end">
              <a
                v-show="!isBookmark"
                @click="addBookmark"
                href="javascript:void(0)"
              >
                <i class="lni lni-star" style="cursor: pointer"></i>
              </a>
              <a
                v-show="isBookmark"
                @click="deleteBookmark"
                href="javascript:void(0)"
              >
                <i class="lni lni-star-filled" style="cursor: pointer"></i>
              </a>
            </div>
            <h6 class="d-flex justify-content-center m-0">
              {{ $store.getters.getSelectedBookmarkInfo.AptName }}
            </h6>
            <p class="text-muted d-flex justify-content-center m-0">
              {{ $store.getters.getSelectedBookmarkInfo.buildYear }}
            </p>
          </div>
          <div class="list-group">
            <a
              v-for="(apt, index) in aptSearchDealResult"
              :key="index"
              class="list-group-item list-group-item-action"
              id="list-home-list"
              data-bs-toggle="list"
              href="#list-home"
              role="tab"
              aria-controls="list-home"
              style="pointer-events: none; cursor: default"
            >
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
    <!-- Bookmark Result end -->
    <!-- <house-detail-modal></house-detail-modal> -->
  </div>
</template>

<script>
// import HouseDetailModal from "@/components/modals/house/HouseDetailModal.vue";
// import { Modal } from "bootstrap";
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);
import http from "@/common/axios.js";
export default {
  components: {
    // HouseDetailModal
  },
  data() {
    return {
      aptSearchDealResult: [],
      //modal
      houseDetailModal: null,
      // map
      mapContainer: null,
      mapCenter: null,
      mapOptions: {},
      map: null,
      currAptMarker: null,
      //bookmark(?)
      isBookmark: false,
      // v-show
      isHouseDetailClicked: false,
      //
      searchResultListClickCount: 0,
    };
  },
  computed: {},
  created() {
    this.getBookmarkResult();
    // this.$store.commit("UNSET_SELECTED_APT_INFO");
  },
  mounted() {
    // this.houseDetailModal = new Modal(
    //   document.getElementById("houseDetailModal")
    // );
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
    // this.getBookmarkResult();
    this.setCenter();
  },
  methods: {
    setCenter() {
      let $this = this;
      let mvLatLng = null;
      if ($this.searchResultListClickCount <= 1) {
        mvLatLng = new kakao.maps.LatLng(
          $this.$store.getters.getBookmarkResultList[0].lat,
          $this.$store.getters.getBookmarkResultList[0].lng
        );
      } else {
        mvLatLng = new kakao.maps.LatLng(
          $this.$store.getters.getSelectedBookmarkInfo.lat,
          $this.$store.getters.getSelectedBookmarkInfo.lng
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
      let $this = this;
      $this.mapContainer = document.querySelector("#map");
      $this.mapCenter = new kakao.maps.LatLng(
        $this.$store.getters.getBookmarkResultList[0].lat,
        $this.$store.getters.getBookmarkResultList[0].lng
      );
      // $this.mapCenter = new kakao.maps.LatLng($this.lat, $this.lng);
      // $this.mapCenter = new kakao.maps.LatLng(33.450701, 126.570667);
      $this.mapOptions = {
        center: $this.mapCenter,
        level: 5,
      };
      $this.map = new kakao.maps.Map($this.mapContainer, $this.mapOptions);
      // $this.currAptMarker = new kakao.maps.Marker({
      //   map: $this.map,
      //   position: $this.mapCenter,
      // });
      $this.map.setCenter(this.mapCenter);
    },
    async getBookmarkResult() {
      // console.log("[BookmarkView.vue] getBookmarkResult() userSeq: ");
      // console.log(this.$store.getters.getUserSeq);
      try {
        let { data } = await http.get(
          `/bookmarks/${this.$store.getters.getUserSeq}`
        );
        console.log("[BookmarkView.vue] getBookmarkResult() data: ");
        console.log(data);
        this.$store.commit("SET_BOOKMARK_RESULT_LIST", data);
        this.setBookmarkList();
      } catch (error) {
        console.log("[BookmarkView.vue] getBookmarkResult() error: ");
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
    async houseDetail(bookmark) {
      // console.log("[HouseView.vue] houseDetail() apt: ");
      // console.log(apt);
      this.searchResultListClickCount++;
      let url = "?dong=" + bookmark.dongName + "&aptName=" + bookmark.AptName;
      try {
        let response = await http.get("/houseDeal" + url);
        let { data } = response;

        this.aptSearchDealResult = data;
        console.log(this.aptSearchDealResult);
      } catch (e) {
        console.error(e);
      }
      this.$store.commit("SET_SELECTED_BOOKMARK_INFO", bookmark);
      console.log("[HouseView.vue] houseDetail() bookmark: ");
      console.log(bookmark);
      console.log("[HouseView.vue] houseDetail() selectedBookmarkInfo: ");
      console.log(this.$store.getters.getSelectedBookmarkInfo);
      // console.log("[HouseView.vue] houseDetail() houseDetailModal: ");
      // console.log(this.houseDetailModal);
      // this.houseDetailModal.show();
      this.isHouseDetailClicked = true;
      this.isBookmark = Object.keys(
        this.$store.getters.getBookmarkList
      ).includes(String(bookmark.houseNo));
      console.log(Object.keys(this.$store.getters.getBookmarkList));
      console.log(bookmark.houseNo);
    },
    addBookmark() {},
    async deleteBookmark() {
      let bookmarkList = this.$store.getters.getBookmarkList;
      let houseNo = this.$store.getters.getSelectedBookmarkInfo.houseNo;
      console.log(
        "[HouseView.vue] deleteBookmark() userSeq, bookmarkId, houseNo:"
      );
      console.log(this.$store.getters.getUserSeq);
      console.log(bookmarkList[houseNo]);
      console.log(this.$store.getters.getSelectedBookmarkInfo.houseNo);

      try {
        let params = {
          bookmarkId: bookmarkList[houseNo],
        };
        console.log(`/bookmarks/${bookmarkList[houseNo]}`);
        // let { data } = await http.delete(`/bookmarks/${bookmarkList[houseNo]}`);
        let { data } = await http.delete(`/bookmarks`, { params });
        console.log("[HouseView.vue] deleteBookmark() data: ");
        console.log(data);
        if (data == 1) {
          this.isBookmark = false;
          //추가한부분
          this.getBookmarkResult();
          this.initMap();
          this.isHouseDetailClicked = false;
        } else {
          console.log("[HouseView.vue] deleteBookmark() else: ");
          console.log("북마크 삭제 안됨");
        }
      } catch (error) {
        console.log("[HouseView.vue] deleteBookmark() error: ");
        console.log(error);
      }
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
  left: 20px;
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
  right: 20px;
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
</style>
