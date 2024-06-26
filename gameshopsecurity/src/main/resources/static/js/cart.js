const url = "http://localhost:8080/api/products/purchaselist";

function sessionCurrent() {
    axios
    .get("http://localhost:8080/user/current", {withCredentials: true})
    .then((response)=>{
        console.log("Response 데이터:", response.data);
        console.log("Response 상태:", response.status);
        if (response.status == 200) {
            const userId = response.data.userId;
            const authority = response.data.authority[0].authority;
            const localStorageKey = "gameshop_" + userId; // 다른 PGM 중복방지

            let cartItems = JSON.parse(localStorage.getItem(localStorageKey));

            console.log("Response userId :", userId);
            console.log("Response authority:", authority);
            console.log("JSON.parse 데이터:", cartItems);

            if (cartItems) {
                displayCart(cartItems); // 배열의 크기만큼 Row 생성
                const data = cartItems.map((game)=>{
                    // Purchase객체를 만들어서 리턴
                    return { game: game, user:{userId:userId
                        , authority:{authorityName:authority}} };
                })
                document.querySelector(".purchaseBtn")
                    .addEventListener("click", ()=>{
                    if (confirm("구매하시겠습니까?")) {
                        axios
                        .post(url, data, {withCredentials: true})
                        .then((response)=>{
                            console.log("데이터:", response.data);
                            localStorage.removeItem(localStorageKey);
                            //window.location.reload();
                        })
                        .catch((error)=>{
                            console.log("에러 발생:", error);
                        });
                    }
                });
            }
        }
    })
    .catch((error)=>{
      console.log("에러 발생:", error);
      alert("로그인해주세요.");
    })
}

function displayCart(games) {
    console.log("displayCart Start !!!");
    const tbody = document.querySelector(".cart-body");
    let totalPrice = 0;

    games.forEach((data)=>{
        // 태그 요소 생성
        const tr = document.createElement("tr");

        const imgtd = document.createElement("td");
        const title = document.createElement("td");
        const genre = document.createElement("td");
        const price = document.createElement("td");
        const deleteBtntd = document.createElement("td"); /* 컬럼생성 */

        const img = document.createElement("img");
        const deleteBtn = document.createElement("button"); /* 버튼생성 */

        // 클래스이름 생성
        imgtd.classList.add("imgtd");
        img.classList.add("image");
        deleteBtn.classList.add("deleteBtn"); /* 삭제버튼 클래스추가 */

        deleteBtn.addEventListener('click', function() {
            const row_index = deleteBtn.closest('tr').rowIndex;
            // if(row_index === 1) {
            //   alert(`첫 번째 행은 삭제할 수 없습니다.`);
            //   return;
            // }
            console.log("클릭된 행은 :" + row_index);
            document.querySelector(".cart-table").deleteRow(row_index);
        })

        const click = (btn) => {
            const row_index = btn.closest('tr').rowIndex;
            if(row_index === 1) {
              alert(`첫 번째 행은 삭제할 수 없습니다.`);
              return;
            }
            console.log("클릭된 행은 :" + row_index);
            document.querySelector(".cart-table").deleteRow(row_index);
          };

        // 테이블의 클릭 이벤트를 이벤트 위임 방식으로 처리
        // cart-table
        // document.querySelector(".cart-table").addEventListener('click', function(event) {
        //     if (event.target && event.target.classList.contains('deleteBtn')) {
        //         // 클릭된 버튼의 부모의 부모 (즉, <tr> 요소)를 찾음
        //         const row = event.target.closest('tr');
        //         console.log('클릭된 행:', row);

        //         document.querySelector(".cart-table").deleteRow(row);
        //     }
        // });



        // 태그속성추가
        img.src = data.image;
        title.textContent = data.title;
        genre.textContent = data.genre;
        price.textContent = data.price + "원";
        deleteBtn.textContent = "삭제"; /* 삭제버튼 text 추가 */

        // appendChild 부모자식 위치 설정
        imgtd.appendChild(img);
        tr.appendChild(imgtd);
        tr.appendChild(title);
        tr.appendChild(genre);
        tr.appendChild(price);
        tbody.appendChild(tr);

        deleteBtntd.appendChild(deleteBtn); /* 버튼을 컬럼에 넣음 */
        tr.appendChild(deleteBtntd);        /* 컬럼을 행에 넣음 */

        totalPrice = totalPrice + data.price;

        console.log("displayCart End !!!");
    })



    document.querySelector(".totalprice").textContent = "총 " + totalPrice + "원";
}

// 페이지 로딩시에 즉시 세션여부 확인
sessionCurrent();