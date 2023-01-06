// console.log("hello");

// var, let, const
// hoisting

// var = global
// x = 30;
// console.log(x);

// const x = 10;
// x = 11;
// console.log(x);

let y = 10;
y = 20;
// console.log(y);

// let p3 = document.getElementsByClassName("p3");
// console.log(p3);

let judul = document.getElementById("judul");
judul.style.backgroundColor = "green";
judul.style.color = "white";
judul.innerHTML = "MCC72 - Java";

let container = document.getElementById("container");

let p = container.getElementsByTagName("p");
// console.log(p);

// p[1].style.backgroundColor = "red";

for (let i = 0; i < p.length; i++) {
  p[i].style.backgroundColor = "red";
  p[i].style.color = "white";
  p[i].style.fontWeight = "Bold";
}

// let test = document.querySelectorAll(".list:nth-child(1)");
// // console.log(test);

// test[0].style.backgroundColor = "aqua";
// test[0].innerHTML = "Coba inner HTML";

let test = document.querySelector(".list:nth-child(1)");

// test.onclick = function () {
//   test.innerHTML = "coba";
//   test.style.backgroundColor = "blue";
// };

// test.onclick = function () {
//   test.style.color = "white";
//   test.style.fontWeight = "Bold";
// };

test.addEventListener("click", function () {
  test.innerHTML = "coba";
  test.style.backgroundColor = "blue";
});

test.addEventListener("click", function () {
  test.style.color = "white";
  test.style.fontWeight = "Bold";
});

let p5 = document.querySelector("p.p5");
p5.style.backgroundColor = "yellow";

// jquery
$(".list:nth-child(2)").on("click", function () {
  $(".list:nth-child(2)").html("Belajar jquery");
});

// $(".list:nth-child(2)").on("click", function () {
//   $(".list:nth-child(2)").html("Ini adalah item ke-2");
// });

$(".list:nth-child(2)").on("mouseenter", function () {
  $(".list:nth-child(2)").css("background-color", "red");
  $(".list:nth-child(2)").css("color", "black");
});

$(".list:nth-child(2)").on("mouseleave", function () {
  $(".list:nth-child(2)").css("background-color", "green");
  $(".list:nth-child(2)").css("color", "white");
});
