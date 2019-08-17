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

function focusSearch() {
    var searchBox = document.getElementById('search-input');
    searchBox.value = '';
    searchBox.focus();
}

var scheduledEvent = null;
function inputEvent() {
    if (scheduledEvent !== null) {
        window.clearTimeout(scheduledEvent);
    }
    scheduledEvent = window.setTimeout(updateSuggestions, 100);
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
    console.log("Response: " + response)
}

function getResults() {
    // TODO
}
