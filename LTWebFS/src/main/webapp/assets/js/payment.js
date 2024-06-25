function showEditAddress() {
    let addressItem = this.parentNode.parentNode.parentNode;
    let id = addressItem.querySelector('.id').innerText;
    let receiver = addressItem.querySelector('.receiver').innerText;
    let phone = addressItem.querySelector('.receiver-phone').innerText;
    let address = addressItem.querySelector('.address').innerText;

    let html = '<form class="editAddressForm">'+
        '<div class="show-flex-row" style="margin-top: 10px">'+
        '   <input type="text"  class="form-control w-80" id="receiver" name="receiver"  aria-describedby="" placeholder="Nhập ten nguoi nhan" value="'+ receiver + '">'+
        '   <div style="margin:0 10px"></div>'+
        '   <input type="text" class="form-control w-80" id="phone" name="phone"  aria-describedby="" placeholder="Nhập ten nguoi nhan" value="'+ phone +'">'+
        '</div>'+
        '<input type="text" style="margin-top: 10px" class="form-control w-100" id="address" name="address"  aria-describedby="" placeholder="Nhập ten nguoi nhan" value="'+ address +'">'+
        '<input type="text" class="form-control w-80" id="id" name="id"  aria-describedby="" placeholder="Nhập ten nguoi nhan" value="'+ id + '" hidden>'+
        '<div class="show-flex-row" style="margin-top:20px">'+
        '   <div class="ad_func-container">'+
        '       <button class="btn btn-third" type="button" onclick="canelEditAddress()">Hủy</button>'+
        '   </div>'+
        '   <div class="ad_func-container">'+
        '       <button class="btn btn-primary" type="submit">Lưu</button>'+
        '   </div>'+
        '</div>'+
        '</form>'+
        '<div class="seperate-horizontal-linear"></div>';

    // reset hien thi danh sach item
    canelEditAddress();

    // hien thi edit box
    const edits = document.querySelector('#modal_content').querySelectorAll('.address-item');
    for(let i=0;i<edits.length;i++) {
        if(edits[i].querySelector('.id').innerText===id) {
            edits[i].innerHTML = html;
        }
    }

    const forms = document.querySelectorAll(".editAddressForm");
    console.log(forms);
    for(let i =0;i<forms.length;i++) {
        forms[i].addEventListener('submit',function (event) {
            event.preventDefault();
            const data = new FormData(forms[i]);
            const id = data.get("id");
            const receiver = data.get("receiver");
            const phone = data.get("phone");
            const address = data.get("address");
            const action = "update";
            executeAddress(action,id,receiver,phone,address);
        });
    }

    const addressItems = document.querySelector('#address-container .address-item');
    addressItems[i].addEventListener('click', function () {
        let receiver = this.querySelector('.receiver');
        let phone = this.querySelector('.phone');
        let address = this.querySelector('.address');
        changeAddress(receiver,phone,address);
        hideModal();
    });


    function changeAddress(receiver, phone, address) {
        const container = document.querySelector('.address-info');
        container.querySelector('.sp-receiver').innerText = receiver;
        container.querySelector('.sp-phone').innerText = phone;
        container.querySelector('.sp-address').innerText = address;

        container.querySelector('.ip-receiver').value = receiver;
        container.querySelector('.ip-phone').value = phone;
        container.querySelector('.ip-address').value = address;
    }
}

function canelEditAddress() {
    // reset hien thi danh sach item
    let modal = document.querySelector('#modal_content');
    modal.innerHTML= addressListHtml;
}

function showAddAddress() {
    let html = ' <div class="address-item">' +
        '<form class="editAddressForm">'+
        '<div class="show-flex-row" style="margin-top: 10px">'+
        '   <input type="text"  class="form-control w-80" id="receiver" name="receiver"  aria-describedby="" placeholder="Nhập tên người nhận" value="">'+
        '   <div style="margin:0 10px"></div>'+
        '   <input type="text" class="form-control w-80" id="phone" name="phone"  aria-describedby="" placeholder="Nhập số điện thoại người nhận" value="">'+
        '</div>'+
        '<input type="text" style="margin-top: 10px" class="form-control w-100" id="address" name="address"  aria-describedby="" placeholder="Nhập địa chỉ nhận hàng" value="">'+
        '<input type="text" class="form-control w-80" id="id" name="id"  aria-describedby="" placeholder="Nhập id người nhận" value="" hidden>'+
        '<div class="show-flex-row" style="margin-top:20px">'+
        '   <div class="ad_func-container">'+
        '       <button class="btn btn-third" type="button" onclick="canelEditAddress()">Hủy</button>'+
        '   </div>'+
        '   <div class="ad_func-container">'+
        '       <button class="btn btn-primary" type="submit">Thêm</button>'+
        '   </div>'+
        '</div>'+
        '</form>'+
        '<div class="seperate-horizontal-linear"></div>'+
        '</div>';

    canelEditAddress();

    const addressContainer = document.querySelector('#add-address-container');
    addressContainer.innerHTML = html;

    const showAddAddressBtn = document.querySelector('.confirm__message .yes-confirm');
    showAddAddressBtn.disabled = true;

    const form = document.querySelector('#add-address-container .editAddressForm');
    console.log(form);
    form.addEventListener('submit',function (event) {
        event.preventDefault();
        const data = new FormData(this);
        const id = data.get("id");
        console.log(id);
        const receiver = data.get("receiver");
        const phone = data.get("phone");
        const address = data.get("address");
        console.log(address);
        const action = "add";
        executeAddress(action,id,receiver,phone,address);
    });

}

function executeAddress(action, id, receiver, phone, address) {
    $.ajax({
        url: "/LTWebFS/address-management",
        method: "POST",
        data: {action: action, id: id, receiver: receiver, phone: phone, address: address},
        success: function(data) {
            if(action==="update")
                showSuccessToast("Cập nhật địa chỉ thành công");
            else if(action==="add")
                showSuccessToast("Thêm địa chỉ thành công");
            $('#modal_content').html(data);
        }
    });
}



//     gan xu kien cho cac element

const confirmStopModalOverlay =$('.modal__overlay');
const confirmStopModal =$('.confirm-stop');
const yesConfirmBtn = $('.yes-confirm');
const noConfirmBtn = $('.no-confirm');

confirmStopModalOverlay.click(hideModal);
noConfirmBtn.click(hideModal);
// confirmStopModalOverlay.click(hideModalUnlock);
// noConfirmBtn.click(hideModalUnlock);

function showModal() {
    $('.confirm-stop').addClass('active');
}

function hideModal(){
    console.log(this.parentNode);
    var html = this.parentNode.innerHTML;
    this.parentNode.classList.remove("active");
    // $('.confirm-stop').removeClass('active');
}


function showAddresses() {
    console.log("Add");
    $('.sub-nav').addClass('active');
}

function hideAddresses(){
    console.log("remove");
    $('.sub-nav').removeClass('active');
}

$(document).click(hideAddresses);
$('#changeAddress').click(showAddresses);

$('.add-address').click(showModal);


const addressItems = document.querySelectorAll(".address-item");
for (let i = 0; i < addressItems.length; i++) {
    var id = addressItems[i].querySelector('.id').innerText;
    var receiver = addressItems[i].querySelector('.receiver').innerText;
    var phone = addressItems[i].querySelector('.receiver-phone').innerText;
    var address = addressItems[i].querySelector('.address').innerText;

}

const editIs = document.querySelectorAll(".editBtn i");
for(let i=0; i< editIs.length;i++) {
    editIs[i].addEventListener('click',function (event){
        event.preventDefault();
    });
}


const editAddressBtns = document.querySelectorAll(".editBtn");
for(let i=0; i< editAddressBtns.length;i++) {
    editAddressBtns[i].addEventListener('click', function () {

    });
}

$('#changeAddress').click(showAddressList);
let addressListHtml;
function showAddressList() {

    $.ajax({
        url: "/LTWebFS/address-management?action=init",
        method: "POST",
        // data: { action: action},
        success: function(data) {
            // console.log(action);
            $('.modal').addClass("active");
            $('#modal_content').html(data);
            addressListHtml = data;
        }
    });


}