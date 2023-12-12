const gpkdBtn = document.querySelector('.js-gpkd')
const gpkd = document.querySelector('.gpkd')
const gpkdContainer = document.querySelector('.gpkd-container')
const gpkdClose= document.querySelector('.js-gpkd-close')

// Hàm hiển thị update ( Thêm class vào update)
function showUpdate(){
  gpkd.classList.add('open')
}

// hàm ẩn modal mua vé ( Gỡ bỏ class open của modal)
function hideUpdate(){
  gpkd.classList.remove('open')
}

// Lặp qua từng thẻ button và nghe hành vi và click
gpkdBtn.addEventListener('click', showUpdate)

// Nghe hành vi và click button close
gpkdClose.addEventListener('click', hideUpdate)

gpkd.addEventListener('click', hideUpdate)

gpkdContainer.addEventListener('click', function (event){
  event.stopPropagation()
})