# 개발환경
AndroidStudio기반의 Client제작<br>
Java기반의 Server제작<br>
MS sql기반의 DataBase설계

# 용도
  모바일로 현재 인기있는 상영중인 영화나 유명한 영화사의 예고편을 확인들 하거나 장르별 흥행한 순위별로 확인을 하거나 올해 영화 순위를 종류별로 보여주고 독자적인 리뷰 게시판을 이용하여 후기를 이용자들끼리의 정보를 교류 하는 용도다.

# 이용대상 
  영화를 좋아하는 누구나

# 제작의도
  여러 영화관의 어플이 있지만 전체적인 통계를 한번에 보여주는 것이 없기 때문에 제작을 해봤습니다.
  
# 구현 기술
  Android Studio를 이용하여 서버로부터 data를 받아오고 data를 전송하는 부분은 TCP 통신을 이용하였다<br>
  예고편과 같이 웹으로 보는것이 편한 부분은 WebView의 기능을 이용하여 보여준다 <br>
  client의 이미지 같은 경우는 image url에서 파싱을 한다<br>
  Java 기반 Server는 client 에서 data를 요청하면 Json을 사용하여 Naver와 한국 영화 진흥원의 openAPI의 정보를 받아 가공을 하여 client로 전송을 하거나 MS sql에 리뷰와 같이 저장이 필요한 경우 database에 정보를 전송을 하여 저장을 하고 읽어오는 기능을 한다.
  
# 주요 코드
  코드 : 서버 (클라이언트에서 커맨드를 받아, 커맨드에 맞는 응답을 해주는 코드)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43887876-022ce740-9bfb-11e8-8bb6-23b56e29bbbd.png)
  <br>코드 : 커맨드 처리 (입력받은 커맨드에 따른 데이터베이스 연결처리)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43887881-0b364ca0-9bfb-11e8-9889-9a5a8f0941f4.png)

  <br>코드 : 데이터베이스 연결1 (데이터베이스에 있는 커맨드에 맞는 영화 순위 불러오는 코드)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43887886-0e680724-9bfb-11e8-889b-0acaf4feb5a5.png)

  <br>코드 : 데이터베이스 연결2 (선택한 장르의 순위를 데이터베이스에서 불러오게 하는 코드)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43887890-115d3288-9bfb-11e8-9457-15a870ba954d.png)

  <br>코드 : 리뷰 읽기 (리뷰테이블에 저장되어 있는 게시물들을 불러오는 코드)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43887895-146742de-9bfb-11e8-9968-bdac5e0e0bb6.png)
  <br>코드 : 리뷰 쓰기 (클라이언트에서 받은 게시물을 데이터베이스의 리뷰 테이블에 저장)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43887902-17a1780c-9bfb-11e8-815d-21867b4b2a33.png)

  <br>코드 : 한국 영화 진흥원 api 읽기 (한국 영화 진흥원의 데이터베이스에서 데이터베이스 테이블을 불러옴)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43887905-1a8bd35a-9bfb-11e8-9178-a4b5bb4f246a.png)

  <br>코드 : 한국 영화 진흥원 JSON파싱 (불러온 테이블에서 원하는 데이터를 추출하는 코드)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43887908-1e3f842e-9bfb-11e8-86a5-098887395f2a.png)

  <br>코드 : 네이버 api 읽기 (네이버 api 데이터베이스에서 데이터베이스 테이블 불러오기)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43887954-3c06ed1c-9bfb-11e8-9105-54afa13d33c0.png)

  <br>코드 : 네이버 JSON 파싱 (네이버 api 데이터베이스 테이블에서 이미지 링크와 해당 영화 링크를 추출)<br> 
  ![image](https://user-images.githubusercontent.com/38156821/43887958-3fe9dc28-9bfb-11e8-83f4-77e2f20b39fc.png)

# 특징
  다른앱과는 다르게 독자적인 리뷰 시스템이 있고 한국 영화 진흥원에서 모집한 영화 순위나 빠르게 예고편 확인을 할 수 있다.
  
# 어려웠던점
  client 에서 원하는 정보를 server에 게속 요청을 하면서 정보를 실시간으로 통신하는 설계하는 과정에서 어려움을 느꼈다.

# UI 및 아이콘
  아이콘<br>
  ![image](https://user-images.githubusercontent.com/38156821/43896235-113acf50-9c13-11e8-96c7-341637f5ba15.png)

  <br>Loding UI(어플리케이션을 실행시에 발생하는 이벤트로 어플 실행중 로딩화면, 서버가 연결이 되면 메인 화면으로 넘어간다)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43896238-15a5777a-9c13-11e8-9239-8288307f3239.png)

  <br>Main UI View Flipper(메인 화면어세 최신 개봉작들을 View Flipper기술을 사용하여 슬라이드로 전환 시켜 사용자에게 시각적으로 보여준다)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43896247-1deb716e-9c13-11e8-9f13-cde0b2065a8c.png)
  
  <br>Main UIurl 연결(클릭시 네이버 영화 링크나 이동된 유튜브 체널로 넘어가 최신 예고편을 확인할수 있다.)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43896253-26326f30-9c13-11e8-8541-1436e659ed51.png)
  ![image](https://user-images.githubusercontent.com/38156821/43896261-32bb3de0-9c13-11e8-852c-5dd9aeeabf2f.png)
  ![image](https://user-images.githubusercontent.com/38156821/43896272-3ae09592-9c13-11e8-8829-df8bb10fe43a.png)

  <br>상단 Menu UI(Main UI상단에 장르별로 나누어 클릭시 해당 장르의 박스오피스 순위를 보여준다.)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43896296-4e5437b4-9c13-11e8-8bd0-4cb8cce5d9d2.png)

  <br>Sub Menu UI(Main UI 왼쪽 상단에 서버 메뉴 아이콘을 클릭시 나타나는 이벤트로 크게 5가지로 구성된다.)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43896302-52f3daea-9c13-11e8-8903-da80f676acaa.png)

  <br>Sub Menu UI - 올해의 박스오피스 순위(한국에서 개봉된 올해 영화중 실 관람객 수를 기준으로 내림차순으로 보여준다)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43896308-559e9eba-9c13-11e8-8b30-94e843371f9f.png)

  <br>Sub Menu UI - 올해의 무비 스크린 순위(올해 영화관에서 상영한 스크린 개수를 기준으로 가장 많이 스크린을 가진 영화를 내림차순으로 보여준다)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43896312-590977dc-9c13-11e8-891e-5605ed243ca5.png)

  <br>Sub Menu UI - 올해의 무비 상영 순위(전국 영화관에서 상영 횟수를 기준으로 가장 많은 회수를 기롤한 영화를 내림차순으로 보여준다)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43896317-5bf28222-9c13-11e8-8210-5c94bdec3943.png)

  <br>Sub Menu UI - 리뷰 게시판(사용자가 리뷰를 작성하고 싶은 영화가 있다면 리뷰 게시판의 글쓰기 버튼을 누르면, 리뷰 작성화면으로 전환이 되고 영화 내용을 입력후 글쓰기 버툰을 클릭시 리뷰가 작성이 되어 저장이 된다.)<br>
  ![image](https://user-images.githubusercontent.com/38156821/43896324-5f4ad794-9c13-11e8-8c3b-89e10540b471.png)
  


