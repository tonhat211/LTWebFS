const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const updateBtn = $('.btn-up-pro');
const saveChangedBtn =$('.ad_func-save-btn');
const stopBtn =$('.btn-stop-pro');
const resaleBtn =$('.btn-resale-pro');
const yesResaleBtn =$('.yes-resale-confirm');
const noResaleBtn =$('.no-resale-confirm');
const modalOverlay =$('.modal__overlay');
const yesStopBtn =$('.yes-confirm');
const cancelStopBtn =$('.no-confirm');



updateBtn.addEventListener('click',getInfo);
saveChangedBtn.addEventListener('click',saveChanged);
stopBtn.addEventListener('click',confirmStop);
yesStopBtn.addEventListener('click',yesStop);
modalOverlay.addEventListener('click',noStop);
cancelStopBtn.addEventListener('click',noStop);
resaleBtn.addEventListener('click',confirmResale);
yesResaleBtn.addEventListener('click',yesResale);
noResaleBtn.addEventListener('click',noResale);



function getInfo() {
    const nameInput = $('#pro-name').value = $('.pro_name').innerText;
    const pricelInput = $('#pro-price').value = $('.pro_price').innerText;
    const numberInput = $('#pro-number').value = $('.pro_number').innerText;
    const kindInput = $('#pro-kind').value = $('.pro_kind').innerText;
    const yearInput = $('#pro-year').value = $('.pro_year').innerText;
    const dateInInput = $('#pro-date-in').value = $('.pro_date-in').innerText;
    const fromInput = $('#pro-from').value = $('.pro_from').innerText;
    const desInput = $('#pro-des').value = $('.pro_des').innerText;


    $('.ad_func-save-btn').classList.add('active');
    $('.ad_func-add-btn').classList.remove('active');
}

function saveChanged() {
    const name = $('.pro_name').innerText =$('#pro-name').value;
    const price = $('.pro_price').innerText = $('#pro-price').value;
    const number = $('.pro_number').innerText = $('#pro-number').value;
    const kind = $('.pro_kind').innerText = $('#pro-kind').value;
    const year = $('.pro_year').innerText = $('#pro-year').value;
    const dateIn = $('.pro_date-in').innerText = $('#pro-date-in').value;
    const from = $('.pro_from').innerText = $('#pro-from').value;
    const des = $('.pro_des').innerText = $('#pro-des').value;


    const inputs = $$('.form-container input');
    for(let i of inputs) {
        i.value = "";
    }
    $('#pro-des').value="";


    $('.ad_func-save-btn').classList.remove('active');
    $('.ad_func-add-btn').classList.add('active');
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
    $('.confirm-stop').classList.remove('active');

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





