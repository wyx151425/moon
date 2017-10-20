/**
 * Created by WangZhenqi on 2016/11/21.
 */
$(document).ready(function() {
    $(window).scroll(function(b) {
        var a = $(this).scrollTop();
        if (a > 0) {
            $("#header").removeClass("nobg")
        } else {
            $("#header").addClass("nobg")
        }
    });

    // $(".section1").css('height', ht);
});