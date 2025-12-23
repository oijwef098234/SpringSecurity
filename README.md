# 이거 고대로 가져다 쓰세요

---

## reissue 구현 방식

1. 프론트가 만료가 되기 직전에 reissue요청을 보낸다.
2. 만료가 되었다는 요청을 잡아서 reissue요청을 보낸다.

## 로그인 구현 방식

1. accessToken과 refreshToken을 둘 다 클라이언트에게 주는 방식
    - rememberMe 사용 안함
2. accessToken만 클라이언트에게 주는 방식
    - rememberMe 사용함
    - 아직 이거 reissue는 구현 안함 할거임
