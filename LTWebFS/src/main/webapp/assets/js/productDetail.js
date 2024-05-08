const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const showSliders = $$(".prd__slidebar-indicator-item");
const sliders = $$(".prd__sliderbar-item");
const slideLeft = $(".move-left");
const slideRight = $(".move-right");


slideLeft.onclick = function () {
    changeSliderToLeft(sliders, showSliders);
}
slideRight.onclick = function () {
    changeSliderToRight(sliders, showSliders);
}





function changeSliderToRight(sliders, showSliders) {

    var index;
    var len = sliders.length;
    for (var i= 0; i < len; i++) {
        if(sliders[i].classList.contains('active')) {
            index = i;
            break;
        }
    }
    sliders[index].classList.remove('active');
    showSliders[index].classList.remove('active');
    if(index === (len-1)){
        index = 0;
        sliders[index].classList.add('active');
        showSliders[index].classList.add('active');

    } else {
        sliders[++index].classList.add('active');
        showSliders[index].classList.add('active');

    }


}

function changeSliderToLeft(sliders, showSliders) {
    var index;
    var len = sliders.length;
    for (var i= 0; i < len; i++) {
        if(sliders[i].classList.contains('active')) {
            index = i;
            break;
        }
    }
    sliders[index].classList.remove('active');
    showSliders[index].classList.remove('active');

    if(index === 0){
        index = len-1;
        sliders[index].classList.add('active');
        showSliders[index].classList.add('active');

    } else {
        sliders[--index].classList.add('active');
        showSliders[index].classList.add('active');

    }


}

function plusQty() {
    const qty = document.querySelector('.product-qty-input');
    let num = parseInt(qty.value);
    num++;
    qty.value = num;
}

function minusQty() {
    // console.log("1");
    const qty = document.querySelector('.product-qty-input');
    let num = parseInt(qty.value);
    num--;
    if(num>=0) {
        qty.value = num;
    }
    else {
        qty.value = 0;
    }
}


const plusBtn = document.querySelector('.btn-plus-qty');


plusBtn.addEventListener('click', plusQty);


const minusBtn = document.querySelector('.btn-minus-qty');

minusBtn.addEventListener('click', minusQty);

