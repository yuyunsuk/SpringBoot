const url = "http://localhost:8080/products";

/* axios.post */
/* axios.put */
/* axios.delete */

/* then() 함수는 promise 와 promise 를 연결해 주는 함수 */
/* axios.get(url).then(할일).then(할일).then().catch(에러처리); */

/* promise 객체는 const x = 처럼 리턴을 받을 수 있는 구조가 아님 */
/* 각 단계 결과의 리턴은 매개변수로 들어감 */
/* CORS 에러(Cross Origin Resource Sharing) */
axios.get(url) /* get 방식으로 보냄 */
.then((response)=>{ /* 받은 데이터 처리, 매개변수 용도는  */
    console.log("응답 Response: ", response);
    displayProducts(response.data); /* 아래 함수 사용 */
})
.catch((response)=>{
    console.log("에러 발생: ", error)
}); /* 받은 데이터가 없는 경우 에러 처리 */

function displayProducts(gameData) {
    console.log(gameData.length);
    if (gameData.length > 0) {
        const content = document.querySelector(".content");
        
        gameData.forEach((data)=>{
            const game = document.createElement("div");
            content.appendChild(game);
            game.classList.add("game");

            const img = document.createElement("img");
            game.appendChild(img);
            img.classList.add("image");
            img.src = data.image;

            const title = document.createElement("p");
            const genre = document.createElement("p");
            const price = document.createElement("p");
            title.textContent = "게임 타이틀: " + data.title;
            genre.textContent = "게임   장르: " + data.genre;
            price.textContent = "게임   가격: " + data.price + "원";
            game.appendChild(title);
            game.appendChild(genre);
            game.appendChild(price);

            game.addEventListener("click", ()=>{
                window.location.href = "singleProduct.html?id=" + data.id;
            })

        })
    }

}