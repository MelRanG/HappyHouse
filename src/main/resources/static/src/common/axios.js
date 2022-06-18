import axios from 'axios';

//axios.create()하면 axios 객체가 만들어지는데 그 객체를 export
export default axios.create({
  baseURL: 'http://localhost:8080', //이거 뒤에 붙음
  headers: {
    "Content-Type": "application/json"
  },
  //쿠키로 저장되는 세션아이디가 갱신이 되지 않도록?
  // widthCredentials의 default: false, false인 경우 sessionid를 매번 신규로 발급
  //서버세션? 서브세션?을 안쓰도록 하는것이 axios의 기본 아이디어 ?
  withCredentials: true,  //true로 해야 sessinid가 고정됨. false로 하면 network의 login의 headers의 response headers의 set-cookie의 jsseionid가 달라짐.
});