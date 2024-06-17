const urlCurrent =  "http://localhost:8080/user/current"
const urlRegi = "http://localhost:8080/course/registration"

axios.get(urlCurrent)
.then((response)=>{
  console.log("응답 Response: ", response);
  userDataSet(response.data.userId);
})
.catch((error)=>{
  console.log("에러 발생: ", error);
});

axios.get(urlRegi)
.then((response)=>{
  console.log("응답 Response: ", response);
})
.catch((error)=>{
  console.log("에러 발생: ", error);
});

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
  window.onload = loadHtml;

  userDataSet(urlRegi)

function userDataSet(responseId){

  const MyLectureList = usergraphSet(responseId);
  console.log(MyLectureList.length);

  
      const classValue1 = 2;
      const classValue2 = 0;
      const classValue3 = 0;
      const classValue4 = 0;
      const classValue5 = 0;
      const classValue6 = 0;
      const classValue7 = 0;
     
      const graph1 = document.querySelector(".graphNum1");
      graph1.textContent = classValue1;
      const graph2 = document.querySelector(".graphNum2");
      graph2.textContent = classValue2;
      const graph3 = document.querySelector(".graphNum3");
      graph3.textContent = classValue3;
      const graph4 = document.querySelector(".graphNum4");
      graph4.textContent = classValue4;
      const graph5 = document.querySelector(".graphNum5");
      graph5.textContent = classValue5;
      const graph6 = document.querySelector(".graphNum6");
      graph6.textContent = classValue6;
      const graph7 = document.querySelector(".graphNum7");
      graph7.textContent = classValue7;
    
      const element1 = document.getElementById('classGraphBar1').style.height = (classValue1 * 30) + "px";
      const element2 = document.getElementById('classGraphBar2').style.height = (classValue2 * 30) + "px";
      const element3 = document.getElementById('classGraphBar3').style.height = (classValue3 * 30) + "px";
      const element4 = document.getElementById('classGraphBar4').style.height = (classValue4 * 30) + "px";
      const element5 = document.getElementById('classGraphBar5').style.height = (classValue5 * 30) + "px";
      const element6 = document.getElementById('classGraphBar6').style.height = (classValue6 * 30) + "px";
      const element7 = document.getElementById('classGraphBar7').style.height = (classValue7 * 30) + "px";
}

function displayLecture(lecture) {
  const tbody = document.querySelector(".studying-body");
games.forEach((data)=>{
  // 태그 요소 생성
  const tr = document.createElement("tr");
  const imgtd = document.createElement("td");
  const title = document.createElement("td");
  const genre = document.createElement("td");
  const price = document.createElement("td");
  const img = document.createElement("img");
  const deleteBox = document.createElement("div")
  const deleteBtn = document.createElement("div");
  // 클래스이름 생성
  imgtd.classList.add("imgtd");
  img.classList.add("image");
  // 태그속성추가
  img.src = data.image;
  title.textContent = data.title;
  genre.textContent = data.genre;
  price.textContent = data.price
  // appendChild 부모자식 위치 설정
  imgtd.appendChild(img);
  tr.appendChild(imgtd);
  tr.appendChild(title);
  tr.appendChild(genre);
  tr.appendChild(price);
  tr.appendChild(deleteBtn);
  tbody.appendChild(tr);
  })
}

function usergraphSet(responseId){
  console.log(responseId);
  let myLecture = [];
  axios.get(urlRegi)
  .then((response)=>{
    console.log("응답 Response: ", response);
    console.log(response.data);
    console.log(response.data[0].user);
    for (let i = 0; i < response.data.length; i++){
      if (response.data[i].user.userId === responseId){
        myLecture.push(response.data)[i];
        console.log(myLecture);
        console.log(myLecture.length);
      }
    }
  })
  return myLecture;
}

document.querySelector("#edumenu1").addEventListener("click", () => {
  document.querySelector(".userEduGraphContainer").classList.remove("hidden");
  document.querySelector(".userEduStudyingContainer").classList.add("hidden");
  // document.querySelector(".userDelete-box").classList.add("hidden");
});

document.querySelector("#edumenu2").addEventListener("click", () => {
  document.querySelector(".userEduGraphContainer").classList.add("hidden");
  document.querySelector(".userEducation-box").classList.remove("hidden");
  // document.querySelector(".userDelete-box").classList.add("hidden");

  graphHeightSet()
});

document.querySelector("#edumenu3").addEventListener("click", () => {
  document.querySelector(".userEduGraphContainer").classList.add("hidden");
  document.querySelector(".userEduStudyingContainer").classList.add("hidden");
  // document.querySelector(".userDelete-box").classList.remove("hidden");
});