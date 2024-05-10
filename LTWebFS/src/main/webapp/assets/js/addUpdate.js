const p$ = document.querySelector.bind(document);
const p$$ = document.querySelectorAll.bind(document);

const confirmStopModalOverlay =p$('.modal__overlay');
const confirmStopModal =p$('.confirm-stop');
const stopBtn = p$('.btn-stop-pro');
const yesConfirmBtn = p$('.yes-confirm');
const noConfirmBtn = p$('.no-confirm');

stopBtn.addEventListener('click',showModal);
confirmStopModalOverlay.addEventListener('click',hideModal);
noConfirmBtn.addEventListener('click',hideModal);

function showModal() {
    confirmStopModal.classList.add('active');
}

function hideModal(){
    confirmStopModal.classList.remove('active');
}
