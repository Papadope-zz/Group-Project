


// image modal 1
var modal = document.getElementById("myModal");
var img = document.getElementById("myImg");
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");

img.onclick = function () {
  modal.style.display = "block";
  modalImg.src = this.src;
  captionText.innerHTML = this.alt;
};
var span = document.getElementsByClassName("close")[0];
span.onclick = function () {
  modal.style.display = "none";
};

// image modal 2
var modal1 = document.getElementById("myModal1");
var img1 = document.getElementById("myImg1");
var modalImg1 = document.getElementById("img02");
var captionText1 = document.getElementById("caption1");

img1.onclick = function () {
  modal1.style.display = "block";
  modalImg1.src = this.src;
  captionText1.innerHTML = this.alt;
};
var span = document.getElementsByClassName("close1")[0];
span.onclick = function () {
  modal1.style.display = "none";
};