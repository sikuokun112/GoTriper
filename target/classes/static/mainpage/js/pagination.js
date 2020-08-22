(function($) {
    'use strict';
    // =========PAGINATION FOR REVIEWERS==========//
    var numberOfProducts = $('.booking-checkbox_wrap .customer-review_wrap').length;
    console.log(numberOfProducts);
    //SO LUONG ITEM CUA SAN PHAM TRONG 1 TRANG NEN DE LA 3 6 Hoac 9
    var limitPerPageProduct = 3;
    $('.booking-checkbox_wrap .customer-review_wrap:gt(' + (limitPerPageProduct - 1) + ')').hide();
    var totalPagesProduct = Math.ceil(numberOfProducts / limitPerPageProduct);

    $('#review_pagination').append(
        "<div class='current-page'><a href='javascript:void(0)'>" +
        1 +
        '</a></div>'
    );
    for (var i = 2; i <= totalPagesProduct; i++) {
        $('#review_pagination').append(
            "<div class='current-page'><a href='javascript:void(0)'>" +
            i +
            '</a></div>'
        );
    }
    $('#review_pagination div.current-page').on('click', function() {
        var currentPage = $(this).index() +1;
        $('.booking-checkbox_wrap .customer-review_wrap').hide();
        var grandTotal = limitPerPageProduct * currentPage;
        for (var i = grandTotal - limitPerPageProduct; i < grandTotal; i++) {
            $('.booking-checkbox_wrap .customer-review_wrap:eq(' + i + ')').show();
        }
    });
})(jQuery);