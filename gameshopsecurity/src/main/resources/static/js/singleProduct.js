// const url = "http://localhost:8080/products/1";
const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get("id");
console.log("Game ID: ", id);

const url = "http://localhost:8080/products/"+ id;

axios
.get(url)
.then((response)=>{
    console.log("데이터: ", response.data);
    displaySingleProduct(response.data);
})
.catch((error)=>{
    console.log("에러 발생: ", error);
});

function displaySingleProduct(data) {
    const product = document.querySelector(".product");

/*
    <p>게임 타이틀: data.title</p>
    <p>게임 장르: data.genre</p>
    <p>게임 가격: data.price</p>
    <p>data.text</p>
*/

    // 태그 요소 생성
    const game  = document.createElement("div");
    const img   = document.createElement("img");
    const title = document.createElement("p");
    const genre = document.createElement("p");
    const price = document.createElement("p");
    const text  = document.createElement("p");

    const lowBox   = document.createElement("div");
    const left     = document.createElement("div");
    const right    = document.createElement("div");
    const cartBtn  = document.createElement("div");

    // 클래스이름 생성
    game.classList.add("game");
    img.classList.add("image");

    lowBox.classList.add("low-box");
    cartBtn.classList.add("cartBtn");

    // 태그속성 추가
    img.src = data.image;
    title.textContent = "게임 타이틀: " + data.title;
    genre.textContent = "게임 장르: " + data.genre;
    price.textContent = "게임 가격: " + data.price + "원";
    text.textContent  = "게임 설명: " + data.text;
    game.style.setProperty("box-shadow", "initial", "important");
    game.style.setProperty("transform", "initial", "important");
    game.style.setProperty("cursor", "initial", "important");

    cartBtn.textContent = "장바구니담기";

    // appendChild 부모자식 위치 설정
    right.appendChild(cartBtn);
    left.appendChild(title);
    left.appendChild(genre);
    left.appendChild(price);
    left.appendChild(text);

    lowBox.appendChild(left);
    lowBox.appendChild(right);

    game.appendChild(img);
    game.appendChild(lowBox);
    
    product.appendChild(game);

    document.querySelector(".cartBtn").addEventListener("click", ()=>{
        console.log("cartBtn 클릭");
        sessionCurrent(data); // 클로저함수
    });

}

// 전역 위치이기 때문에 cartBtn 이 만들어 지기 전에 실행 되어서 작동이 안됨
// "cartBtn" 요소가 DOM에 추가되기 전에 실행되기 때문에 클릭 이벤트가 설정되지 않습니다.
// document.querySelector(".cartBtn").addEventListener("click", ()=>{
//     console.log("cartBtn 클릭");
//     sessionCurrent();
// });

function sessionCurrent(data) {
    /*  withCredentials 옵션은 단어 그대로, 다른 도메인(Cross Origin)에 요청을 보낼 때
       요청에 인증(credential) 정보를 담아서 보낼 지를 결정하는 항목 */
    axios
    .get("http://localhost:8080/user/current", {withCredentials: true})
    .then((response)=>{
        console.log("SingleProduct 세션확인 데이터: ", response.data);
        console.log("SingleProduct 세션 Status: ", response.status);
        if (response.status == 200) {
            const userId = response.data.userId; // 권한 관련 수정하였음

            console.log("유저 ID: " + userId);

            const localStorageKey = "gameshop_" + userId; // 다른 PGM 중복방지

            //let cartItems = JSON.parse(localStorage.getItem(userId));
            let cartItems = JSON.parse(localStorage.getItem(localStorageKey));
            if (!cartItems) { // null 이면 초기화
                cartItems = [];
            }
            cartItems.push(data); // data => Game Data
            //localStorage.setItem(userId, JSON.stringify(cartItems));
            localStorage.setItem(localStorageKey, JSON.stringify(cartItems));
        }
    })
    .catch((error)=>{
        console.log("SingleProduct 세션확인 에러 발생: ", error);
        alert("로그인해 주세요.");
    })
}