const url = "http://localhost:8080/api/products/purchaselist";

/* 객체배열 하드코딩 */
const data = [
    {
      "game":{
        "id": 1
      },
      "user":{
        "userId": "tom12"
      }
    },
    {
      "game":{
        "id": 2
      },
      "user":{
        "userId": "tom12"
      }
    }
]

function sessionCurrent() {
  /*  withCredentials 옵션은 단어 그대로, 다른 도메인(Cross Origin)에 요청을 보낼 때
     요청에 인증(credential) 정보를 담아서 보낼 지를 결정하는 항목 */
  axios
  .get("http://localhost:8080/user/current", {withCredentials: true})
  .then((response)=>{
      console.log("SingleProduct 세션확인 데이터: ", response.data);
      console.log("SingleProduct 세션 Status: ", response.status);
      if (response.status == 200) {
          const userId = response.data;
          let cartItems = JSON.parse(localStorage.getItem(userId));
          
          // if (!cartItems) { // null 이면 초기화
          //     cartItems = [];
          // }
          // cartItems.push(data); // data => Game Data
          // localStorage.setItem(userId, JSON.stringify(cartItems));

          if (cartItems) { // 데이터가 있으면
              // cartItems 는 게임 데이터(id, title, 장르, price 등)들의 배열, data 는 구매 배열, map 새로운 데이터 배열을 만듬
              const data = cartItems.map((game)=>{
                  // Purchase(구매) 객체를 만들어서 리턴
                  return { game: game, user:{userId:userId}};
              })
              document.querySelector(".purchaseBtn").addEventListener("click", ()=>{
                  if (confirm("구매하시겠습니까?")) {
                      axios
                      .post(url, data, {withCredentials: true})
                      .then((response)=>{
                          console.log("구매 데이터: ", response.data);
                          localStorage.removeItem(userId);
                      })
                      .catch((error)=>{
                          console.log("구매 에러코드: ", error);
                      });
                  }
              })
          }
      }
  })
  .catch((error)=>{
      console.log("SingleProduct 세션확인 에러 발생: ", error);
      alert("로그인해 주세요.");
  })
}

// /* 자바스크립트는 =>, 자바는 -> */
// document.querySelector(".purchaseBtn").addEventListener("click", ()=>{
//     if (confirm("진짜 구매하시겠습니까?")) {
//         axios
//         .post(url, data, {withCredentials: true})
//         .then((response)=>{
//             console.log("구매 데이터: ", response.data);
//         })
//         .catch((error)=>{
//             console.log("구매 에러코드: ", error);
//         });
//     }
// })

// 페이지 로딩시에 즉시 세션여부 확인
sesstionCurrent();