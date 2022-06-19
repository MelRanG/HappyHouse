# HappyHouse
사용자가 원하는 지역이나 아파트의 거래내역을 조회할 수 있다.

## 기능
- 사용자가 동 or 아파트명으로 검색하면 해당 아파트 정보를 불러온다.
- 사용자가 불러온 아파트 정보를 클릭하면 최근 거래 내역이 조회된다.
- 클릭한 아파트 정보에 즐겨찾기를 추가할 수 있다.
- 관리자로 인증된 유저는 공지사항을 작성할 수 있다.
- 유저는 커뮤니티 게시판에 글 생성, 수정, 삭제를 할 수 있다.
- 유저는 커뮤니티 게시판글에 추천을 할 수 있다.

## 기술
- Vue.js
- SpringBoot
- Mybatis
- Mysql

## 담당
![image](https://user-images.githubusercontent.com/62234293/174473796-5be7727a-7a9a-4d9c-a84b-fe559af7ffaf.png)

## 자주 발생한 이슈 및 성능 개선

### 이슈
/boards, {params}
/boards, params
/boards/ + this.id, params
/boards/, {params}

Vue에서 요청을 보낼 때 위처럼 비슷한 형식으로 보내서 많이 헷갈렸다.
에러코드를 분석하면서 400번대 에러의 경우 클라이언트에서 보내는 url문제임을 인지했고 500번대 에러의 경우 서버에서 잘못 받았다는 것을 알고난 후 빠르게 해결했다.

### 성능개선
추천을 조회할 때 Recommend 테이블에 기록된 게시글 번호를 조회해서 추천 수를 카운트 하는 방식으로 설계했었다.
하지만 조회하는 게시글마다 select문을 추가로 수행해야 하기 때문에 성능에 좋은 영향을 끼치지 않을 것이라고 판단해서 게시글 테이블에 recommend_count라는 칼럼을 추가해서
추천할 때마다 count를 증가시키고 감소시키는 방식으로 설계했다.

![image](https://user-images.githubusercontent.com/62234293/174474244-5bfdb463-9304-4409-8d35-ba8f99231e80.png)


## 결과
![image](https://user-images.githubusercontent.com/62234293/174473866-e0f2a50e-1e14-4b32-984a-cf8c9efbbf5f.png)
![image](https://user-images.githubusercontent.com/62234293/174473870-701c998d-2ca9-4d58-b264-dbbf66bca471.png)
![image](https://user-images.githubusercontent.com/62234293/174473881-1af94bb6-bd28-4492-936d-193e2fe6ee40.png)
![image](https://user-images.githubusercontent.com/62234293/174473901-950e05c6-8898-433b-b3bd-37b2eea6c098.png)
![image](https://user-images.githubusercontent.com/62234293/174473904-ddc5cdc3-473e-4bc5-b984-ff2c05819f4a.png)
