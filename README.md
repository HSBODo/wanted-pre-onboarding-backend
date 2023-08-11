# wanted-pre-onboarding-backend
성명:한수빈
## 개발 스펙
  *  언어: java(17)
  *  프레임워크: SpringBoot(3.1.2)
  *  ORM: JPA
  *  데이터베이스: MySql(8.0)
  *  서버: AWS Ec2(WAS,DB)
  *  도메인:https://www.pointman.shop
  *  CI/CD: GitHub Action(docker)
  *  테스트: Junit4
## 아키텍처 구조
![image](https://github.com/HSBODo/wanted-pre-onboarding-backend/assets/86594565/6813cab8-1004-4b66-968d-4aa32ba244bf)
## CI/CD 배포 과정
![image](https://github.com/HSBODo/wanted-pre-onboarding-backend/assets/86594565/86eb0e6e-f214-4f73-b11f-4b5886ff9637)
## DB 테이블 구조
![image](https://github.com/HSBODo/wanted-pre-onboarding-backend/assets/86594565/1e922090-7f38-4dbd-a7cf-91a060398f32)



## API 명세 및 실행방법
  * 실행방법
    * PostMan을 통하여 API명세와 같이 호출하여 사용  (https://www.postman.com/)
    * 자세한 API명세는 여기서 확인해 주세요 (https://documenter.getpostman.com/view/22940487/2s9XxyRtbx#5c83a693-1a57-45ad-b922-7ace27e7e269)  
### 1. 회원가입
![image](https://github.com/HSBODo/wanted-pre-onboarding-backend/assets/86594565/a3ee546e-deef-4f37-b608-15c84aa7617c)
* 이메일 검증은 정규 표현식 사용
* 비밀번호 검증 조건은 String.length 사용
<br>
<br>
<br>

### 2. 로그인
![image](https://github.com/HSBODo/wanted-pre-onboarding-backend/assets/86594565/234856a0-5336-4faf-aaa1-2955b42cef4b)
* 비밀번호 암호화 알고리즘 AES/CBC/PKCS5Padding 사용
* Cipher클래스를 사용하여 암호화 및 복호화
* 검증 완료 후 JWT토큰 발행  
<br>
<br>
<br>

### 3. 게시글 작성
![image](https://github.com/HSBODo/wanted-pre-onboarding-backend/assets/86594565/bcee0762-dc19-41fa-9a7b-3d348d6cfdb5)
* 게시글 저장은 JPA를 사용하여 DB에 저장 
<br>
<br>
<br>

### 4. 게시글 목록
![image](https://github.com/HSBODo/wanted-pre-onboarding-backend/assets/86594565/6f38e3b7-73fc-4a42-94ce-d2d01472d207)
* 동적으로 페이지 처리 
* limit 페이지당 글개수
* page 페이지 번호
<br>
<br>
<br>

### 5. 게시글 조회
![image](https://github.com/HSBODo/wanted-pre-onboarding-backend/assets/86594565/23ec3aa5-bfe2-4ae5-b8b2-ce25d5495bcf)
* PathVariable 방식 게시글 조회
* PK id로 게시글 조회
<br>
<br>
<br>

### 6. 게시글 수정
![image](https://github.com/HSBODo/wanted-pre-onboarding-backend/assets/86594565/355ac010-d622-4957-a09f-138d6d707836)
* 게시글 작성자 검증 후 수정
<br>
<br>
<br>

### 7. 게시글 삭제
![image](https://github.com/HSBODo/wanted-pre-onboarding-backend/assets/86594565/eeda59de-6b66-40b1-a3bf-e1bbc74251f7)
* 게시글 작성자 검증 후 삭제







