var page = 0;
var category = "";
var loadProductInProgress = false;

function getPagination() {
    $(".pagination").remove()
    $(".pagination_product").append('<div class="pagination center"><img class="img-pagination-loader" width="30px" src="' + urlImages + 'loader.gif"/></div>')
    $.ajax({
        url: url + "json/product/count/" + category,
        context: document.body
    }).done(function (result) {
        var listPagination = result.COUNT_PAGE;
        $(".pagination").remove()
        for (page = 1; page <= listPagination; page++) {
            var classPagination = "pagination";
            if (page == 1) {
                classPagination = classPagination + " active";
            }
            $(".pagination_product").append('<li class="' + classPagination + '"><a href="Javascript:void(0);" class="change-page" data-id="' + page + '">' + page + '</a></li>')
        }
        loadActonChangePage();
    });
}

function getListProduct() {
    $("#listeProduct").html('<div class="center"><img src="' + urlImages + 'loader.gif" width="200px"/></div>')
    loadProductInProgress = true;
    $.ajax({
        url: url + "json/product/" + page + "/page/" + category,
        context: document.body
    }).done(function (result) {
        var listProduct = result.products;
        $("#listeProduct").empty();
        if (result.products.length === 0) {
            $("#listeProduct").html("Aucun produit n'est disponible.");
        } else {
            showProduct(listProduct);
        }
        loadProductInProgress = false;
    });
}


function showProduct(listProduct) {
    $.each(listProduct, function (key, product) {
        $("#listeProduct").append(
            '<li>' +
            '<div class="simpleCart_shelfItem">' +
            '<a class="cbp-vm-image" href="product?id=' + product.id + '">' +
            '<div class="view view-first">' +
            '<div class="inner_content clearfix">' +
            '<div class="product_image">' +
            '<div class="relative">' +
            '<div class="mask">' +
            '<div class="info">Afficher</div>' +
            '</div>' +
            '<img src="' + urlImages + product.urlPicture + '" class="img-responsive" alt=""/>' +
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
            '</a>' +
            '<div class="cbp-vm-details">' +
            product.description +
            '</div>' +
            '<a class="cbp-vm-icon cbp-vm-add item_add add_item_to_cart" href="Javascript:void(0);" data-id="' + product.id + '">Ajouter au panier</a>' +
            '</div>' +
            '</div>' +
            '</li>');
    });
    loadActonChangeAddItemToCart();
}

function loadActonChangePage() {
    $(".change-page").on("click", function () {
        if (!loadProductInProgress) {
            var pageInput = $(this).data("id") - 1;
            if (pageInput != page) {
                $(".pagination.active").removeClass("active");
                $(".pagination-" + $(this).data("id")).parent().addClass("active");
                page = pageInput;
                getListProduct();
            }
        }
    })
}




$(function () {
    loadActonChangePage();
    getListProduct();

    $(".change-category").on("click", function () {
        if (!loadProductInProgress) {
            var categoryInput = $(this).data("id");
            if ((categoryInput + "/category") != category) {
                $(".change-category.active").removeClass("active");
                $(this).addClass("active");
                category = categoryInput + "/category";
                page = 0;
            } else {
                $(this).removeClass("active");
                category = "";
                page = 0;
            }
            getPagination();
            getListProduct();
        }
    })
});