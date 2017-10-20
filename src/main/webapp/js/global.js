/**
 * Created by WangZhenqi on 2016/11/21.
 */
$(document).ready(function() {
    var a = $(window);
    setRootFontSize = function() {
        var c = a.width();
        var b;
        if (c < 320) {
            b = 20
        } else {
            if (640 <= c && c < 1200) {
                b = c * 40 / 1200
            } else {
                if (1200 <= c) {
                    b = 40
                }
            }
        }
        $("html").css("font-size", b + "px")
    };
    a.resize(setRootFontSize).load(setRootFontSize).resize()
});