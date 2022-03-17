var pre = window.pageYOffset;
$(window).on("scroll", function() {
    if (window.pageYOffset > 50) {
        $(".nav,.nav img,#back").addClass("act")
    } else {
        $(".nav,.nav img,#back").removeClass("act");
    }
    var cur = window.pageYOffset;
    if(pre > cur){
       $(".nav").css("top","0");
    }else{
       $(".nav").css("top","-55px");
    }
    pre = cur;
}).on("load", function() {
    setTimeout(() => {
        $(".loader").hide();
    }, 1500)
    console.clear()
});
$("#back").click(() => {
    $("html,body").animate({
        scrollTop: 0
    }, 'slow')
});
// smooth scrolling
$("a").click(function() {
    $("html,body").animate({
        scrollTop: $($(this).attr("href")).offset().top
    }, 'slow')
});
// scrollreval
const sr = ScrollReveal({
    distance: '100px'
});
ScrollReveal().reveal('.header .flex img', {
    duration: 2000,
    origin: 'top',
    reset: true
});
ScrollReveal().reveal('.header .flex .text', {
    duration: 2000,
    origin: 'bottom',
    reset: true
});
ScrollReveal().reveal('.about-section', {
    duration: 2000,
    origin: 'bottom',
    reset: true
});
ScrollReveal().reveal('.gallary .img1', {
    duration: 2000,
    origin: 'left',
    reset: true
});
ScrollReveal().reveal('.gallary .img2', {
    duration: 2000,
    origin: 'bottom',
    reset: true
});
ScrollReveal().reveal('.gallary .img3', {
    duration: 2000,
    origin: 'right',
    reset: true
});
ScrollReveal().reveal('.gallary .img4', {
    duration: 2000,
    origin: 'left',
    reset: true
});
ScrollReveal().reveal('.gallary .img5', {
    duration: 2000,
    origin: 'top',
    reset: true
});
ScrollReveal().reveal('.gallary .img6', {
    duration: 2000,
    origin: 'right',
    reset: true
});
ScrollReveal().reveal('.footer .social', {
    duration: 2000,
    origin: 'top',
    reset: true
});
