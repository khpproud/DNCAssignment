# DNCAssignment
Drama & Company Android Assignment

기능 요구사항
- Github 사용자를 검색(https://api.github.com/search/users)
- 즐겨찾기한 사용자를 로컬 DB에 저장
- 즐겨찾기한 사용자를 로컬 DB에서 검색

화면
1. Github 사용자 검색(API)
  - 검색 API에 사용되는 필드는 사용자 이름으로 제한
  - 검색 결과는 최대 100개 까지만 보여줌(page = 1, per_page = 100)
  - 아이템 뷰에는 아바타 이미지, 사용자 이름, 즐겨찾기 아이콘 표시
  - 검색 결과는 사용자 이름 순으로 정렬, 사용자의 초성이나 알파벳을 기준으로 헤더를 붙여줌
  - 아이템 뷰를 누르면 즐겨찾기로 추가, 이미 즐겨찾기 된 경우는 즐겨찾기에서 삭제
2. 로컬 즐겨찾기 검색화면
  - 기본 화면은 API 사용자 검색화면과 동일
  - 아이템 뷰를 누르면 해당 사용자의 즐겨찾기 여부를 갱신하고 화면에서도 삭제
  
기술스택
- Clean Architecture (Domain, Data, Presentation)
- Android Architecture Components(Livedata, ViewModel, Room...)
- Coroutines for Asynchronous 
- Dagger Hilt for DI
- Retrofit for Restful
- Coli for Image Prosessing
