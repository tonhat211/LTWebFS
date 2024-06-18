const p$ = document.querySelector.bind(document);
const p$$ = document.querySelectorAll.bind(document);
const containerPrdE = p$(".product__container");
const filterTitles = p$$(".subnav__filter-title");
const filterLis = p$$(".filter-item");
// const cancelFilter = p$(".cancel-filter");
const filterItems= p$$(".subnav__filter-item");
const productItems= p$$(".product-item");

const showSliders = p$$(".prd__slidebar-indicator-item");
const sliders = p$$(".prd__sliderbar-item");
const slideLeft = p$(".move-left");
const slideRight = p$(".move-right");

const kindbtns = p$$(".kindBtn");
// for(let i of kindbtns){
//     // i.addEventListener('click', chooseKind());
//     i.onclick = function (){
//         chooseKind();
//     }
// }

function chooseKind(){
    var kind = this.innerText;
    console.log("truoc");
    console.log(kind);
    // window.location = 'product?'  +kind;
    console.log("sau");
}


slideLeft.onclick = function () {
    changeSliderToLeft(sliders, showSliders);
}
slideRight.onclick = function () {
    changeSliderToRight(sliders, showSliders);
}

function openProductDetail() {
    window.location = 'productDetail.html';
    // window.location = "https://www.google.com.vn";
}

for(let i of productItems){
    i.addEventListener('click', openProductDetail);
}

autoSlide();

function autoSlide() {
    setInterval(function() {
        changeSliderToRight(sliders,showSliders)
    }, 3000);
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

// showProInKind("A");

// const kindProducts = $$(".subnav__kind-item");
// radioCusActive(kindProducts);

function radioCusActive(items) {
    for(let i=0; i< items.length;i++) {
        items[i].onclick = function () {
            // remove active cua tat ca items
            for(let j=0; j< items.length;j++) {
                items[j].classList.remove("active");
            }
            items[i].classList.add("active");
            showProInKind(items[i].innerText);
        }

    }
}


function toggleActive(items) {
    for(let i=0; i< items.length;i++) {
        items[i].onclick = function () {
            items[i].classList.toggle("active");
            console.log("1");
        }
    }
}







function filterInit() {


    for(let i=0; i< filterTitles.length;i++) {
        filterTitles[i].onclick = function () {
            // remove active cua tat ca filtertitles
            for(let j=0; j< filterTitles.length;j++) {
                if(j === i)
                    continue;
                filterTitles[j].parentElement.classList.remove("active");
            }

            const thisParent = this.parentElement;
            thisParent.classList.toggle("active");
        }

    }

    // for(let i=0; i< filterTitles.length;i++) {
    //     filterTitles[i].addEventListener("blur" , function () {
    //         console.log(1);
    //     });
    // }

    // cancelFilter.onclick = function () {
    //     // console.log("ok");
    //     for(let i=0; i<filterItems.length;i++) {
    //         filterItems[i].querySelector(".filter-chosen").innerText="Tất cả";
    //         // console.log("1");
    //     }
    // }






    for(let i=0;i<filterLis.length;i++){
        filterLis[i].onclick = function () {
            const thisParent = this.parentElement.parentElement;
            thisParent.classList.remove("active");
            thisParent.querySelector(".filter-chosen").innerText = filterLis[i].innerText;
            // console.log(thisParent);
        }
    }
}
filterInit();

const removeActive = function (items) {
    for(let i =0; i<items.length;i++) {
        items[i].classList.remove("active");

    }
}

function brandInit() {
    const brands = p$$(".brand-item");
    toggleActive(brands);

    const allBrand = p$(".all-brand");
    allBrand.onclick = function () {
        removeActive(brands);
    }
}

// brandInit();

function showProInKind(kind) {
    var kindd = "pro" + kind;
    const productList = p$$(".oneProduct");
    for (let i of productList){
        if(i.classList.contains(kindd))
            i.style.display = "block";
        else
            i.style.display = "none";
    }
}

