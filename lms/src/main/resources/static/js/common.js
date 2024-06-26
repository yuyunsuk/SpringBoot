document.addEventListener("DOMContentLoaded", () => {
  sessionCurrent();
});

/* 세션확인 */
async function sessionCurrent() {
  try {
    const response = await axios.get("http://localhost:8080/user/current", { withCredentials: true });
    
    console.log("데이터: ", response);
    
    if (response.status == 200) {
      console.log("세션 유지");
      await loadAdminInnerHTML(); // await를 사용하여 loadAdminInnerHTML()이 완료될 때까지 기다립니다.

      const login = document.getElementById("login");
      const logout = document.getElementById("logout");

      // 로그인 상태이면
      login.classList.add("hidden");
      logout.classList.remove("hidden") // 로그아웃 표시
    } else {
      await loadAdminInnerHTML(); // await를 사용하여 loadAdminInnerHTML()이 완료될 때까지 기다립니다.

      const login = document.getElementById("login");
      const logout = document.getElementById("logout");

      // 세션이 없거나 로그아웃 상태인 경우
      logout.classList.add("hidden");
      login.classList.remove("hidden"); // 로그인 표시
    }
  } catch (error) {
    console.log("에러 발생: ", error);
  }
}

async function loadAdminInnerHTML() {
  try {
    // 기존의 admin 요소를 가져옵니다.
    const adminElement = document.getElementById('header');
    // 기존의 내용을 비웁니다.
    adminElement.innerHTML = '';

    const response = await axios.get("header.html"); // 로그인 상태이면 로그아웃 해더

    console.log("Header loading OK!!!");
    adminElement.innerHTML = response.data;
  } catch (error) {
    console.log("Header loading error:", error);
  }
}
