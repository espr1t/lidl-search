function focusSearch() {
    var searchBox = document.getElementById("search-input");
    searchBox.value = "";
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
    var searchBox = document.getElementById("search-input");
    console.log(searchBox.value);
}
