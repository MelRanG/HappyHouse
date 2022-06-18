import Vue from "vue";
import Vuex from "vuex";
Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    login: {
      isLogin: false,
      //Login
      userId: "ssafy2",
      userPassword: "1234",
      userSeq: "",
      userName: "",
      userEmail: "",
      userPhone: "",
      userRole: "",
    },
    navbar: {
      isPageHouseActivated: true,
      isPageBookmarkActivated: false,
      isPageCommunityActivated: false,
    },
    // house
    house: {
      aptSearchResult: [],
      selectedAptInfo: {},
    },
    // bookmark
    bookmark: {
      bookmarkResultList: [],
      bookmarkList: {},
      selectedBookmarkInfo: {},
    },
    // board
    board: {
      id: 0,
      title: "",
      content: "",
      writer: "",
      sameUser: false,
    },
    // notice
    notice: {
      id: 0,
      title: "",
      content: "",
      writer: "",
    },
  },
  getters: {
    // login & user
    getIsLogin: function (state) {
      return state.login.isLogin;
    },
    getUserSeq: function (state) {
      return state.login.userSeq;
    },
    getUserId: function (state) {
      return state.login.userId;
    },
    getUserName: function (state) {
      return state.login.userName;
    },
    getUserEmail: function (state) {
      return state.login.userEmail;
    },
    getUserPhone: function (state) {
      return state.login.userPhone;
    },
    getUserRole: function (state) {
      return state.login.userRole;
    },
    // navbar
    getIsPageHouseActivated(state) {
      return state.navbar.isPageHouseActivated;
    },
    getIsPageBookmarkActivated(state) {
      return state.navbar.isPageBookmarkActivated;
    },
    getIsPageCommunityActivated(state) {
      return state.navbar.isPageCommunityActivated;
    },
    // house
    getAptSearchResult(state) {
      return state.house.aptSearchResult;
    },
    getSelectedAptInfo(state) {
      return state.house.selectedAptInfo;
    },
    // bookmark
    getBookmarkResultList(state) {
      return state.bookmark.bookmarkResultList;
    },
    getBookmarkList(state) {
      return state.bookmark.bookmarkList;
    },
    getSelectedBookmarkInfo(state) {
      return state.bookmark.selectedBookmarkInfo;
    },
    // board
    getBoard(state) {
      return state.board;
    },
    //notice
    getNotice(state) {
      return state.notice;
    },
  },
  mutations: {
    // login & user
    SET_LOGIN(state, payload) {
      state.login.isLogin = payload.isLogin;
      state.login.userSeq = payload.userSeq;
      state.login.userId = payload.userId;
      state.login.userName = payload.userName;
      state.login.userEmail = payload.userEmail;
      state.login.userPhone = payload.userPhone;
      state.login.userRole = payload.userRole;
    },
    SET_LOGOUT(state) {
      state.login.isLogin = false;
      state.login.userSeq = "";
      state.login.userId = "";
      state.login.userName = "";
      state.login.userEmail = "";
      state.login.userPhone = "";
      state.login.userRole = "";
    },
    SET_USER_UPDATE(state, payload) {
      state.login.userEmail = payload.userEmail;
      state.login.userPhone = payload.userPhone;
    },
    // navbar
    SET_IS_PAGE_HOUSE_ACTIVATED(state) {
      state.navbar.isPageHouseActivated = true;
      state.navbar.isPageBookmarkActivated = false;
      state.navbar.isPageCommunityActivated = false;
    },
    SET_IS_PAGE_BOOKMARK_ACTIVATED(state) {
      state.navbar.isPageHouseActivated = false;
      state.navbar.isPageBookmarkActivated = true;
      state.navbar.isPageCommunityActivated = false;

    },
    SET_IS_PAGE_COMMUNITY_ACTIVATED(state) {
      state.navbar.isPageHouseActivated = false;
      state.navbar.isPageBookmarkActivated = false;
      state.navbar.isPageCommunityActivated = true;
    },
    UNSET_NAVBAR_ACTIVATED(state) {
      state.navbar.isPageHouseActivated = false;
      state.navbar.isPageBookmarkActivated = false;
      state.navbar.isPageCommunityActivated = false;
    },
    // house
    SET_APT_SEARCH_RESULT(state, payload) {
      state.house.aptSearchResult = payload;
    },
    SET_SELECTED_APT_INFO(state, payload) {
      state.house.selectedAptInfo = payload;
    },
    UNSET_SELECTED_APT_INFO(state) {
      state.house.selectedAptInfo = null;
    },
    // bookmark
    SET_BOOKMARK_RESULT_LIST(state, payload) {
      state.bookmark.bookmarkResultList = payload;
    },
    SET_BOOKMARK_LIST(state, payload) {
      state.bookmark.bookmarkList = payload;
    },
    SET_SELECTED_BOOKMARK_INFO(state, payload) {
      state.bookmark.selectedBookmarkInfo = payload;
    },
    UNSET_SELECTED_BOOKMARK_INFO(state) {
      state.bookmark.selectedBookmarkInfo = null;
    },
    // board
    SET_BOARD(state, payload) {
      state.board.id = payload.id;
      state.board.title = payload.title;
      state.board.content = payload.content;
      state.board.writer = payload.writer;
      state.board.sameUser = payload.sameUser;
    },
    //notice
    SET_NOTICE(state, payload) {
      state.notice.id = payload.id;
      state.notice.title = payload.title;
      state.notice.content = payload.content;
      state.notice.writer = payload.writer;
    },
  },
  actions: {
  },
})