// exam01.js
// 
let json = `[{"mem_id":1,"first_name":"Dasie","last_name":"Dickon","email":"ddickon0@cnet.com","gender":"Female","address":"0 Vahlen Point"},
{"mem_id":2,"first_name":"George","last_name":"Mealiffe","email":"gmealiffe1@pagesperso-orange.fr","gender":"Female","address":"72 Roth Park"},
{"mem_id":3,"first_name":"Ed","last_name":"Petto","email":"epetto2@imdb.com","gender":"Male","address":"81517 Lighthouse Bay Center"},
{"mem_id":4,"first_name":"Marcel","last_name":"Caunter","email":"mcaunter3@sourceforge.net","gender":"Male","address":"5581 Canary Terrace"},
{"mem_id":5,"first_name":"Sarine","last_name":"Janauschek","email":"sjanauschek4@arstechnica.com","gender":"Female","address":"7643 Rowland Junction"},
{"mem_id":6,"first_name":"Lindsy","last_name":"Elcom","email":"lelcom5@zdnet.com","gender":"Female","address":"7912 American Plaza"},
{"mem_id":7,"first_name":"Brook","last_name":"Weldon","email":"bweldon6@live.com","gender":"Male","address":"35 Katie Court"},
{"mem_id":8,"first_name":"Ronni","last_name":"Naulty","email":"rnaulty7@dyndns.org","gender":"Female","address":"7 Schiller Circle"},
{"mem_id":9,"first_name":"Ossie","last_name":"Razzell","email":"orazzell8@webeden.co.uk","gender":"Male","address":"18 Hovde Park"},
{"mem_id":10,"first_name":"Kira","last_name":"Khilkov","email":"kkhilkov9@cyberchimps.com","gender":"Female","address":"3 Lotheville Terrace"}]`;

let obj=JSON.parse(this.response);
//let obj = JSON.parse(json);
console.log(obj); //[{},{},{},{} {} ......{}]

for (let row of obj) {
    console.log(row);
}


