// to get current year
function getYear() {
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    document.querySelector("#displayYear").innerHTML = currentYear;
}

getYear();


// client section owl carousel
$(".client_owl-carousel").owlCarousel({
    loop: true,
    margin: 0,
    dots: false,
    nav: true,
    navText: [],
    autoplay: true,
    autoplayHoverPause: true,
    navText: [
        '<i class="fa fa-angle-left" aria-hidden="true"></i>',
        '<i class="fa fa-angle-right" aria-hidden="true"></i>'
    ],
    responsive: {
        0: {
            items: 1
        },
        768: {
            items: 2
        },
        1000: {
            items: 2
        }
    }
});



/** google_map js **/
function myMap() {
    var mapProp = {
        center: new google.maps.LatLng(40.712775, -74.005973),
        zoom: 18,
    };
    var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
}
function undo() {
    document.execCommand('undo', false, null);
}

function redo() {
    document.execCommand('redo', false, null);
}

function changeFontSize(size) {
    document.execCommand('fontSize', false, size);
}

function changeFontFamily(family) {
    document.execCommand('fontName', false, family);
}

function boldText() {
    document.execCommand('bold', false, null);
}

function italicText() {
    document.execCommand('italic', false, null);
}

function underlineText() {
    document.execCommand('underline', false, null);
}

function pasteText() {
    navigator.clipboard.readText().then(text => {
        document.getElementById('editorText').value = text;
    });
}

function uploadDocument() {
    // Logic để upload tài liệu
}