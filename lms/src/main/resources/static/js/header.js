/* 세션확인 */
function sessionCurrent() {
  axios
    .get("http://localhost:8080/user/current", { withCredentials: true })
    .then((response) => {
      console.log("데이터: ", response);
      if (response.status == 200) {
        console.log("세션 유지");
        if (response.status == 200) {
          const authorityName = response.data.authority.authority; // => "ROLE_USER", "ROLE_ADMIN"
          console.log("authorityName: ", authorityName);
            //response.data.userId + "님, 환영합니다.";
          //window.location.href = "main.html";
        }
      }
    })
    .catch((error) => {
      console.log("에러 발생: ", error);
    });
}

// js 파일이 로드될때 호출됨 (전역위치)
sessionCurrent();
