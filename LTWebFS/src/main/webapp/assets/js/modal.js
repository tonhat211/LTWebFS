const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const modalOverlays =$$('.modal__overlay');
const yesStopBtn =$('.yes-confirm');
const cancelStopBtn =$('.no-confirm');

stopBtn.addEventListener('click',confirmStop);
yesStopBtn.addEventListener('click',yesStop);
for(let i of modalOverlays){
    i.addEventListener('click',noStop);

}
cancelStopBtn.addEventListener('click',noStop);