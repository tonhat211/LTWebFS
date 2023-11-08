const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const updateBtn = $('.btn-up-em');
const saveChangedBtn =$('.ad_func-save-btn');
const stopBtn =$('.btn-stop-em');
const modalOverlay =$('.modal__overlay');
const yesStopBtn =$('.yes-confirm');
const cancelStopBtn =$('.no-confirm');
const sendPwdBtn =$('.btn-pwd-em');

updateBtn.addEventListener('click',getInfo);
saveChangedBtn.addEventListener('click',saveChanged);
stopBtn.addEventListener('click',confirmStop);
yesStopBtn.addEventListener('click',yesStop);
modalOverlay.addEventListener('click',noStop);
cancelStopBtn.addEventListener('click',noStop);
sendPwdBtn.addEventListener('click',sendPwd);


function getInfo() {
    const nameInput = $('#em-name').value = $('.em_name').innerText;
    const acclevelInput = $('#em-account-level').value = $('.em_account-level').innerText;
    const partInput = $('#em-part').value = $('.em_part').innerText;
    const positionInput = $('#em-position').value = $('.em_position').innerText;
    const branchInput = $('#em-branch').value = $('.em_branch').innerText;
    const birthdayInput = $('#em-birthday').value = $('.em_birthday').innerText;
    const phoneInput = $('#em-phone').value = $('.em_phone').innerText;
    const emailInput = $('#em-email').value = $('.em_email').innerText;
    const addressInput = $('#em-address').value = $('.em_address').innerText;

    $('.ad_func-save-btn').classList.add('active');
}

function saveChanged() {
    const name = $('.em_name').innerText =$('#em-name').value;
    const kind = $('.em_account-level').innerText = $('#em-account-level').value;
    const part = $('.em_part').innerText = $('#em-part').value;
    const position = $('.em_position').innerText = $('#em-position').value;
    const branch = $('.em_branch').innerText = $('#em-branch').value;
    const birthday = $('.em_birthday').innerText = $('#em-birthday').value;
    const phone = $('.em_phone').innerText = $('#em-phone').value;
    const email = $('.em_email').innerText = $('#em-email').value;
    const address = $('.em_address').innerText = $('#em-address').value;

    const inputs = $$('.form-container input');
    for(let i of inputs) {
        i.value = "";
    }

    $('.ad_func-save-btn').classList.remove('active');
}

function  init() {
    if($('.disabled-showing').classList.contains('active')){
        $('.btn-up-em').style.display='none';
        $('.btn-stop-em').style.display='none';
        $('.btn-pwd-em').style.display='none';
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

function sendPwd() {
    alert("Đã gửi mật khẩu tới email " + $('.em_email').innerText);
}
