// console.log("test");
// array [] & object {}

// let x = "budi";
// console.log(typeof x);

let array = [1, 2, 3, 4];
let a = array.map((x) => 2 * x);
// console.log(a);

// console.log(typeof array);

// array.push("Coba");
// console.log(array);

// array.pop();
// console.log(array);

// array.unshift(10);
// console.log(array);

// array.shift();
// console.log(array);

// array.map((item) => {
//   console.log(2 * item);
// });

// array.find((x) => {
//   console.log(x > 2);
// });

// array.findIndex((x) => {
//   console.log(x === 2);
// });

// array.filter((x) => {
//   console.log(x > 3);
// });

// console.log(array.findIndex((x) => x == 2));
// console.log(array.find((x) => (x = 2)));
// console.log(array.map((x) => 2 * x));

let arrayMulti = [1, 2, 3, 4, ["a", "b", "c"], true, false];
// console.log(arrayMulti);

// console.log(arrayMulti[4][1]);

// console.log(arrayMulti[5]);

let mhs = {
  nim: "21221212",
  name: "budi",
  age: 20,
  hobby: ["mancing", "games", "berenang"],
};
// console.log(mhs);

mhs.name = "budi doremi";
// console.log(mhs);

let obj = {};
obj.username = "doni";
obj.password = "12133121";
// console.log(obj);

// php
mhs["name"] = "dodi";
// console.log(mhs);

let employee = [
  {
    name: "budi",
    gender: "pria",
    prov: "jawa barat",
    city: { name: "jakarta" },
  },
  {
    name: "dodi",
    gender: "pria",
    prov: "jawa timur",
    city: { name: "surabaya" },
  },
  {
    name: "nina",
    gender: "wanita",
    prov: "jawa barat",
    city: { name: "jakarta" },
  },
  {
    name: "alex",
    gender: "pria",
    prov: "jawa barat",
    city: { name: "jakarta" },
  },
  {
    name: "ayu",
    gender: "wanita",
    prov: "jawa timur",
    city: { name: "surabaya" },
  },
];

console.log(employee);

// 1. tampilin semua list wanita

let wanita = employee.filter((x) => x.gender === "wanita");
console.log(wanita);

let wanita2 = employee.map((x) => {
  return {
    name: x.name,
    gender: x.gender,
    detail: x.gender === "wanita" ? "cewek dong" : "pria dong",
    isMan: x.gender === "pria",
  };
});
console.log(wanita2);

// 2. tampilin semua list dari jakarta
let jakarta = employee.filter((x) => x.city.name == "jakarta");
console.log(jakarta);

let z = 3;
if (z === "3") {
  console.log(true);
} else {
  console.log(false);
}
console.log(typeof z);
