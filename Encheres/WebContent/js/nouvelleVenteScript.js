/**
 * 
 */


var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();
 if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 

today = yyyy+'-'+mm+'-'+dd;
document.getElementById("dateDebutEncheres").setAttribute("min", today);

document.getElementById("dateFinEncheres").setAttribute("min", getvalueOf(document.getElementById("dateDebutEncheres")));
document.getElementById("dateFinEncheres").setAttribute("max", getvalueOf(document.getElementById("dateDebutEncheres")).addDays(100));

Date.prototype.addDays = function(days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}
