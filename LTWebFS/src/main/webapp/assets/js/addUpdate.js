const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const confirmStopModalOverlay =$('.modal__overlay');
const confirmStopModal =$('.confirm-stop');
const stopBtn = $('.btn-stop-pro');
const yesConfirmBtn = $('.yes-confirm');
const noConfirmBtn = $('.no-confirm');

stopBtn.addEventListener('click',showModal);
confirmStopModalOverlay.addEventListener('click',hideModal);
noConfirmBtn.addEventListener('click',hideModal);

function showModal() {
    confirmStopModal.classList.add('active');
}

function hideModal(){
    confirmStopModal.classList.remove('active');
}
