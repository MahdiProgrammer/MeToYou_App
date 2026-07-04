// Loader
window.addEventListener('load', function () {
    setTimeout(function () {
        document.querySelector('.loader').classList.add('hide');
    }, 1200);
    console.clear();
});

// Navbar scroll behavior
var lastScroll = 0;
window.addEventListener('scroll', function () {
    var currentScroll = window.pageYOffset;

    if (currentScroll > 60) {
        document.querySelector('.nav').classList.add('scrolled');
        document.getElementById('back').classList.add('show');
    } else {
        document.querySelector('.nav').classList.remove('scrolled');
        document.getElementById('back').classList.remove('show');
    }

    if (currentScroll > lastScroll && currentScroll > 200) {
        document.querySelector('.nav').style.top = '-80px';
    } else {
        document.querySelector('.nav').style.top = '0';
    }
    lastScroll = currentScroll;
});

// Back to top
document.getElementById('back').addEventListener('click', function () {
    window.scrollTo({ top: 0, behavior: 'smooth' });
});

// Mobile nav toggle
var navToggle = document.getElementById('navToggle');
var navMenu = document.getElementById('navMenu');

navToggle.addEventListener('click', function () {
    navMenu.classList.toggle('open');
});

navMenu.querySelectorAll('a').forEach(function (link) {
    link.addEventListener('click', function () {
        navMenu.classList.remove('open');
    });
});

// Smooth scroll for nav links
document.querySelectorAll('a[href^="#"]').forEach(function (anchor) {
    anchor.addEventListener('click', function (e) {
        var target = document.querySelector(this.getAttribute('href'));
        if (target) {
            e.preventDefault();
            target.scrollIntoView({ behavior: 'smooth', block: 'start' });
        }
    });
});

// Reveal on scroll (Intersection Observer)
var revealEls = document.querySelectorAll('.reveal-up, .reveal-left, .reveal-right');

var observer = new IntersectionObserver(function (entries) {
    entries.forEach(function (entry) {
        if (entry.isIntersecting) {
            entry.target.classList.add('visible');
        }
    });
}, { threshold: 0.12 });

revealEls.forEach(function (el) {
    observer.observe(el);
});
