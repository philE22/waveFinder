var currentIndex = 0;
var beforeIndex = 0;
var buttons = document.getElementsByClassName("show-image-btn");
var prevButton = document.getElementById("prev-btn");
var nextButton = document.getElementById("next-btn");
var image = document.getElementById("image");
var allButtons = document.getElementsByClassName("all-btn");
var header = document.getElementById("header");

for (var i = 0; i < buttons.length; i++) {
    (function(index) {
        buttons[index].addEventListener("click", function() {
        beforeIndex = currentIndex;
        currentIndex = index;
        image.src = this.dataset.imageSrc;
        });
    })(i);
}

prevButton.addEventListener("click", function() {
    beforeIndex = currentIndex;
    currentIndex--;
    if (currentIndex < 0) {
        currentIndex = buttons.length - 1;
    }
    image.src = buttons[currentIndex].dataset.imageSrc;
});

nextButton.addEventListener("click", function() {
    beforeIndex = currentIndex;
    currentIndex++;
    if (currentIndex > buttons.length - 1) {
        currentIndex = 0;
    }
    image.src = buttons[currentIndex].dataset.imageSrc;
});

for (var i = 0; i < allButtons.length; i++) {
    allButtons[i].addEventListener("click", function() {
        buttons[beforeIndex].classList.remove("bg-primary");
        buttons[currentIndex].classList.add("bg-primary");
        header.innerText = buttons[currentIndex].innerText;
    });
}