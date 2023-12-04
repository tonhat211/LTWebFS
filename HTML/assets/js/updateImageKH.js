const khBtn = document.querySelector('.js-image-kh')
const kh = document.querySelector('.kh')
const khContainer = document.querySelector('.kh-container')
const khClose= document.querySelector('.js-kh-close')

// Hàm hiển thị update ( Thêm class vào update)
function showUpdate(){
  kh.classList.add('open')
}

// hàm ẩn modal mua vé ( Gỡ bỏ class open của modal)
function hideUpdate(){
  kh.classList.remove('open')
}

// Lặp qua từng thẻ button và nghe hành vi và click
khBtn.addEventListener('click', showUpdate)

// Nghe hành vi và click button close
khClose.addEventListener('click', hideUpdate)

kh.addEventListener('click', hideUpdate)

khContainer.addEventListener('click', function (event){
  event.stopPropagation()
})