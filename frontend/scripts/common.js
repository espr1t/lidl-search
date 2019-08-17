API_ENDPOINT_AUTOCOMPLETE = 'http://localhost:4567/complete';
API_ENDPOINT_RESULTS = 'http://localhost:4567/results';

function ajaxCall(url, data, callback) {
    var first = true;
    var args = '';
    for (var field in data) {
        if (data.hasOwnProperty(field)) {
            args += (first ? '' : '&') + field + '=' + encodeURIComponent(data[field]);
            first = false;
        }
    }

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4) {
            callback(xhttp.status == 200 ? xhttp.responseText : '{"status" : "ERROR"}');
        }
    }
    xhttp.open('POST', url, true);
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhttp.send(args);
}

function redirect(url) {
    window.location = url;
}

function focusSearch() {
    var searchBox = document.getElementById('search-input');
    searchBox.value = '';
    searchBox.onkeypress = function(event) {checkEnter(event);};
    searchBox.oninput = function(event) {inputEvent();};
    searchBox.focus();
}

var scheduledEvent = null;
function inputEvent() {
    if (scheduledEvent !== null) {
        window.clearTimeout(scheduledEvent);
    }
    scheduledEvent = window.setTimeout(updateSuggestions, 100);
}

function checkEnter(event) {
    var keyCode = event.keyCode || event.which || 0;
    if (keyCode == 13) {
        event.preventDefault();
        event.stopPropagation();
        getResults();
    }
}

function updateSuggestions() {
    scheduledEvent = null;
    var searchBox = document.getElementById('search-input');
    console.log(searchBox.value);
    var suggestions = ajaxCall(
        API_ENDPOINT_AUTOCOMPLETE,
        {"text": searchBox.value},
        showSuggestions
    );
}

function showSuggestions(response) {
    responseObject = JSON.parse(response);
    var suggestions = document.getElementById('suggestions');
    suggestions.innerHTML = '';
    var items = responseObject["items"];
    for (var item of items) {
        console.log(item);
        var suggestion = document.createElement("div");
        suggestion.innerHTML = '<b>' + item['name'] + '</b>' + ' by ' + '<b>' + item['vendor'] + '</b>';
        suggestion.className = 'suggestion';
        suggestions.appendChild(suggestion);
    }
}

function getResults() {
    var searchBox = document.getElementById('search-input');

    var url = "file:///Users/alexgeorgiev/Desktop/lidl/lidl-search/frontend/results.html";
    url += "?" + encodeURI(encodeURI(searchBox.value));

    console.log(url);
    redirect(url);
}
