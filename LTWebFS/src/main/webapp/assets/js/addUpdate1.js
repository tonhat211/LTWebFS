const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);




const addImgBtn =$('.btn-add-em-img');
const yesAddImgBtn =$('.add-img-btn');
const noAddImgBtn =$('.cancel-add-img-btn');

const modalOverlays =$$('.modal__overlay');
for(let i of modalOverlays){
    i.addEventListener('click',noStop);

}





addImgBtn.addEventListener('click',showAddImg);
yesAddImgBtn.addEventListener('click',addImg);
noAddImgBtn.addEventListener('click',cancelAddImg);



function getInfo() {


    $('.ad_func-save-btn').classList.add('active');
    $('.ad_func-add-btn').classList.remove('active');

    const imgs = $$('.pro-img-item');
    var imgUrls="";
    for(let i of imgs) {
        var  temp  = i.src.split('/');
        imgUrls+= temp[temp.length - 1]+ '==';

    }


    const nameInput = $('#pro-name').value = $('.pro_name').innerText;
    const imgInput = $('#pro-imgs').value = imgUrls;
    const countryInput = $('#pro-country').value = $('.pro_country').innerText;
    const brandInput = $('#pro-brand').value = $('.pro_brand').innerText;
    const kindInput = $('#pro-kind').value = $('.pro_kind').innerText;
    const typeInput = $('#pro-type').value = $('.pro_type').innerText;
    const areaInput = $('#pro-area').value = $('.pro_area').innerText;
    const priceInput = $('#pro-price').value = parseFloat($('.pro_price').innerText);
    const amountInput = $('#pro-amount').value = parseInt($('.pro_amount').innerText);
    const yearMadeInput = $('#pro-yearMade').value = parseInt($('.pro_yearMade').innerText);
    const dateImportInput = $('#pro-dateImport').value = new  Date($('.pro_dateImport').innerText);
    const desInput = $('#pro-des').value = $('.pro_des').innerText;

}

function saveChanged() {

    $('.ad_func-save-btn').classList.remove('active');
    $('.ad_func-add-btn').classList.add('active');
}

function addProduct(){
    const proInputs = $$('.form-container input');
    for(let i of proInputs){
        if(i.value ==""){
            alert("Vui lòng nhập đầy đủ thông tin.");
            break;
        }
        else {

            const id = $('.pro_id');
            let newId =  parseInt(id.innerText.slice(id.innerText.indexOf('o')+1)) +1;
            id.innerText = `#pro${newId+1}`;


            const name = $('.pro_name').innerText =$('#pro-name').value;
            console.log($('#pro-name').value);
            // console.log($('#pro_dateImport').value);
            // const price = $('.pro_price').innerText = $('#pro-price').value;
            // const number = $('.pro_amount').innerText = $('#pro-amount').value;
            // const kind = $('.pro_kind').innerText = $('#pro-kind').value;
            // const year = $('.pro_year').innerText = $('#pro-year').value;
            // const dateIn = $('.pro_dateImport').innerText = $('#pro-dateImport').value;
            console.log($('#pro-dateImport').value);
            // const from = $('.pro_country').innerText = $('#pro-country').value;
            //
            // const brand = $('.pro_brand').innerText = $('#pro-brand').value;
            // const des = $('.pro_des').innerText = $('#pro-des').value;

        }

    }



}

function  init() {
    if($('.disabled-showing').classList.contains('active')){
        resaleBtn.style.display='block';
        stopBtn.style.display='none';

    } else {
        resaleBtn.style.display='none';
        stopBtn.style.display='block';
    }

}

function confirmStop() {
    $('.confirm-stop').classList.add('active')
}

function noStop() {


    $('.add-img-box').classList.remove('active');

}

function yesStop() {
    $('.confirm-stop').classList.remove('active');
    $('.disabled-showing').classList.add('active');
    init();
}

function confirmResale() {
    $('.confirm-resale').classList.add('active');
}

function noResale() {
    $('.confirm-resale').classList.remove('active');

}

function yesResale() {
    $('.confirm-resale').classList.remove('active');
    $('.disabled-showing').classList.remove('active');
    init();
}

function showAddImg() {
    $('.add-img-box').classList.add('active');
    console.log("hi");
}

function addImg() {
    // const imgInput = $("#em-img");
    // $('.employee-img').src = imgInput.value;
    const imgsInput = $("#pro-imgs");
    imgsInput.value  += $("#pro-img").value +"==";
    cancelAddImg();
}

function cancelAddImg() {
    $('.add-img-box').classList.remove('active');
    $("#pro-img").value = "";
}





