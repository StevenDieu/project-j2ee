var page = 0;
var category = "";


function getListProduct(){
    $.ajax({
        url: "http://localhost:8080/steven-1.0.0-SNAPSHOT/json/product/" + page + "/page",
        context: document.body
    }).done(function(result) {
        var listProduct = result.products;
        if(result.products.length === 0){
            $("#listeProduct").html("Aucun produit n'est disponible.");
        }else{
            showProduct(listProduct);
        }
    });
}

/*

 <li>
 <div class="simpleCart_shelfItem">
 <a class="cbp-vm-image" href="single.html">

 <div class="view view-first">
 <div class="inner_content clearfix">
 <div class="product_image">
 <img src="images/p2.jpg" class="img-responsive" alt=""/>
 <div class="mask">
 <div class="info">Quick View</div>
 </div>
 <div class="product_container">
 <div class="cart-left">
 <p class="title">Great Explorer</p>
 </div>
 <div class="pricey"><span class="item_price">$189.00</span>
 </div>
 <div class="clearfix"></div>
 </div>
 </div>
 </div>
 </div>
 </a>
 <div class="cbp-vm-details">
 Wattle seed bunya nuts spring onion okra garlic bitterleaf zucchini.
 </div>
 <a class="cbp-vm-icon cbp-vm-add item_add" href="#">Add to cart</a>
 </div>
 </li>
 */

function showProduct(listProduct){
    $.each(listProduct, function(key,product) {
        var blocDescription = ""
        if(product.description !== undefined){
            blocDescription = '<div class="cbp-vm-details">' +
                listProduct.description +
                '</div>';
        }
        $("#listeProduct").append('' +
            '<li>' +
            '<div class="simpleCart_shelfItem">' +
            '<a class="cbp-vm-image" href="single.html">' +
            '<div class="view view-first">' +
            '<div class="inner_content clearfix">' +
            '<div class="product_image">' +
            '<img src="' + urlImages + product.urlPicture + '.jpg" class="img-responsive" alt=""/>' +
            '<div class="mask">' +
            '<div class="info">Afficher</div>' +
            '</div>' +
            '<div class="product_container">' +
            '<div class="cart-left">' +
            '<p class="title">' + product.name + '</p>' +
            '</div>' +
            '<div class="pricey"><span class="item_price">' + product.price + ' €</span>' +
            '</div>' +
            '<div class="clearfix"></div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            blocDescription +
            '<a class="cbp-vm-icon cbp-vm-add item_add" href="#">Ajouter au panier</a>' +
            '</div>' +
            '</li>');
    });
}

$(function() {
    getListProduct();
});