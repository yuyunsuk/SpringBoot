const urlCurrent =  "http://localhost:8080/user/current"
const urlLecture =  "http://localhost:8080/user/lecture"

axios.get(urlCurrent)
.then((response)=>{
  console.log("응답 Response: ", response);
  displayUser("http://localhost:8080/user/id/" + response.data.userId);
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

  graphHeightSet()

function displayUser(UserData){
axios.get(UserData, {withCredentials: true})
.then((response)=>{
  
  graphHeightSet()

})
.catch((error)=>{
  console.log("에러 발생: ", error);
});
}

function graphHeightSet(){
 
  const classValue1 = 4;
  const classValue2 = 0;
  const classValue3 = 0;
  const classValue4 = 0;
  const classValue5 = 0;
  const classValue6 = 0;
  const classValue7 = 10;
 
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