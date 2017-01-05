/**
 * Created by Steven Dieu on 05/01/2017.
 */
var loadProductInProgress = false;

function addItemToCart(id){
    $.ajax({
        method: "POST",
        url: url + "addCart",
        data: {id : id}
    }).done(function (result) {
        $("#blockPopUpAddToCart").html(result);
        $("#modalAddCart").modal('show')
    });
}

function loadActonChangeAddItemToCart() {
    $(".add_item_to_cart").on("click", function () {
        if (!loadProductInProgress) {
            addItemToCart($(this).data('id'));
        }
        return false;
    });
}

$(function () {
    loadActonChangeAddItemToCart();
});