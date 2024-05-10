<!DOCTYPE html>
<html>
<head>
    <title>查重</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="applicable-device" content="pc,mobile">
    <script src="/webjars/jquery/3.1.1-1/jquery.min.js"></script>
    <script src="/static/js/index.js"></script>

    <!-- Fonts -->
    <#--    <link href="https://fonts.googleapis.com/css?family=Nunito:300,300i,400,600,700" rel="stylesheet">-->
    <link href="/static/css/googlefont.css" rel="stylesheet">
    <link href="/static/css/all.css" rel="stylesheet">
    <#--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">-->

    <!-- CSS -->
    <link href="/static/anchor/assets/css/main.css" rel="stylesheet"/>
    <link href="/static/anchor/assets/css/vendor/aos.css" rel="stylesheet"/>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
        enabled. Please enable
        Javascript and reload this page!</h2></noscript>
<#include "common/topbar.ftl">

<script src="/static/anchor/assets/js/vendor/jquery.min.js" type="text/javascript"></script>
<script src="/static/anchor/assets/js/vendor/popper.min.js" type="text/javascript"></script>
<script src="/static/anchor/assets/js/vendor/bootstrap.min.js" type="text/javascript"></script>
<script src="/static/anchor/assets/js/functions.js" type="text/javascript"></script>

<!-- Animation -->
<script src="/static/anchor/assets/js/vendor/aos.js" type="text/javascript"></script>
<noscript>
    <style>
        *[data-aos] {
            display: block !important;
            opacity: 1 !important;
            visibility: visible !important;
        }
    </style>
</noscript>
<script>
    AOS.init({
        duration: 700
    });
</script>

<!-- Disable animation on less than 1200px, change value if you like -->
<script>
    AOS.init({
        disable: function () {
            var maxWidth = 1200;
            return window.innerWidth < maxWidth;
        }
    });
</script>

<!-- Carousel Height Smooth -->
<script>
    $('.carousel').on('slide.bs.carousel', function (event) {
        var height = $(event.relatedTarget).height();
        var $innerCarousel = $(event.target).find('.carousel-inner');
        $innerCarousel.animate({
            height: height
        });
    });
</script>

<!-- Popovers -->
<script>
    $(function () {
        $('[data-toggle="popover"]').popover()
    })
    $('.popover-dismiss').popover({
        trigger: 'focus'
    })
</script>

<!-- Tooltips -->
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
</script>
</body>
</html>