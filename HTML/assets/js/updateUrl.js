const updateBtn = document.querySelector('.js-update-url')
const update = document.querySelector('.update')
const updateContainer = document.querySelector('.update-container')
const updateClose= document.querySelector('.js-update-close')

// Hàm hiển thị update ( Thêm class vào update)
function showUpdate(){
  update.classList.add('open')
}

// hàm ẩn modal mua vé ( Gỡ bỏ class open của modal)
function hideUpdate(){
  update.classList.remove('open')
}

// Lặp qua từng thẻ button và nghe hành vi và click
updateBtn.addEventListener('click', showUpdate)

// Nghe hành vi và click button close
updateClose.addEventListener('click', hideUpdate)

update.addEventListener('click', hideUpdate)

updateContainer.addEventListener('click', function (event){
  event.stopPropagation()
})