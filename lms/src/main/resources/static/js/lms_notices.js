// 각 페이지별 #header와 #footer에 html파일 넣기
function loadHtml() {
    axios
      .get("header.html")
      .then((response) => {
        document.getElementById("header").innerHTML = response.data;
      })
      .catch((error) => {
        console.error("Header loading error:", error);
      });
    axios
      .get("footer.html")
      .then((response) => {
        document.getElementById("footer").innerHTML = response.data;
      })
      .catch((error) => {
        console.error("footer loading error:", error);
      });
  }
  // 페이지가 로드될 때 header와 footer를 로드
  window.onload = loadHtml;

// 서버에서 제품 목록을 가져올 URL을 정의합니다.
const url = "http://localhost:8080/lms/lms_events";
// axios를 사용하여 정의된 URL로 GET 요청을 보냅니다.
axios
.get(url)
.then((response)=>{
    // 서버로부터 받은 응답 전체를 콘솔에 출력합니다.
    console.log("응답 Response: ", response);
    // 응답 데이터로 제품 목록을 화면에 표시하는 함수를 호출합니다.
    displayProducts(response.data);
})
.catch((error)=>{
    // 요청 중 에러가 발생하면 콘솔에 에러 메시지를 출력합니다.
    console.log("에러 발생: ", error);
});

// 제품 목록을 화면에 표시하는 함수입니다.
function displayProducts(gameData) {
    // 제품 목록의 길이를 콘솔에 출력합니다.
    console.log(gameData.length);
    
    // 제품 목록이 하나 이상 존재하는 경우에만 실행됩니다.
    if (gameData.length > 0) {
        // .content 클래스를 가진 요소를 선택합니다.
        const content = document.querySelector(".content");
        
        // 제품 목록 데이터를 반복 처리합니다.
        gameData.forEach((data)=>{
            // 새로운 div 요소를 생성합니다.
            const game = document.createElement("div");
            // 생성한 div 요소에 'game' 클래스를 추가합니다.
            game.classList.add("game");
            
            // 새로운 img 요소를 생성합니다.
            const img = document.createElement("img");
            // 생성한 img 요소에 'image' 클래스를 추가합니다.
            img.classList.add("image");
            // img 요소의 src 속성을 데이터에서 가져온 이미지 URL로 설정합니다.
            img.src = data.image;
            // img 요소를 game div에 자식 요소로 추가합니다.
            game.appendChild(img);
            
            // 새로운 p 요소들을 생성합니다.
            const title = document.createElement("p");
            const genre = document.createElement("p");
            const price = document.createElement("p");
            
            // 각 p 요소에 텍스트 내용을 설정합니다.
            title.textContent = "게임 타이틀 : " + data.title;
            genre.textContent = "게임 장르 : " + data.genre;
            price.textContent = "게임 가격 : " + data.price + "원";
            
            // 각 p 요소를 game div에 자식 요소로 추가합니다.
            game.appendChild(title);
            game.appendChild(genre);
            game.appendChild(price);
            
            // game div에 클릭 이벤트 리스너를 추가합니다.
            game.addEventListener("click",()=>{
                // 클릭 시 해당 제품의 상세 페이지로 이동합니다.
                window.location.href = "singleProduct.html?id=" + data.id;
            })
            
            // game div를 content 요소에 자식 요소로 추가합니다.
            content.appendChild(game);
        })
    }
}