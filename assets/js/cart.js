$('.sub').click(function () {
    if ($(this).next().val() > 1) $(this).next().val(+$(this).next().val() - 1);
    if ($(this).next().val() >= 1) $(this).next().attr('value', $(this).next().val());

});

$('.add').click(function () {
    $(this).prev().val(+$(this).prev().val() + 1);
    $(this).prev().attr('value', +$(this).prev().val())
});
var quantity = $('.quantity').val()
var price = $('.price').text().parseInt()
console.log(price)
// $('.totalTemp').html(quantity * price)