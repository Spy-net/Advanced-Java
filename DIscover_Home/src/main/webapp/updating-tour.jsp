<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Discover Home | Updating Form</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/booking-form.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<div class="container">
		<h2 style="margin-bottom: 50px;">
			<i class="fas fa-calendar-plus"></i> Enter Updated Details
		</h2>
		<form id="tourupdatingForm" action="${pageContext.request.contextPath}/tour-update?id=${param.id}" method="post">
			<label for="meetingPoint"><i class="fas fa-map-marked"></i>
				Meeting Point (Address):</label> <input type="text" id="meetingPoint"
				name="meetingPoint" required> <label for="tourPlaces"><i
				class="fas fa-location-arrow"></i> Tour Places:</label> <select
				id="tourPlaces" name="tourPlaces" multiple></select>

			<!-- Display selected tour places -->
			<div id="selectedTourPlaces" class="selected-tour-places"></div>

			<label for="arrivalTime"><i class="far fa-clock"></i> Arrival
				Time:</label> <input type="datetime-local" id="arrivalTime"
				name="arrivalTime" required> <label for="departureTime"><i
				class="far fa-clock"></i> Departure Time:</label> <input
				type="datetime-local" id="departureTime" name="departureTime"
				required> <label for="noOfPersons"><i
				class="fas fa-users"></i> No of Persons:</label> <select id="noOfPersons"
				name="noOfPersons">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select>
			<input type="hidden" id="status" name="status" value="Booked">

			<label for="price"><i class="fas fa-dollar-sign"></i> Price:</label>
			
			<input type="number" id="price" name="price" step="0.01" required>

			<input type="hidden" id="tourPlacesHidden" name="tourPlacesHidden" required>

			<input type="submit" value="Save">
		</form>
	</div>

	<script>
        var tourPlacesData = [
        	"Taj Mahal",
            "Jaipur City Palace",
            "Kerala Backwaters",
            "Goa Beaches",
            "Agra Fort",
            "Varanasi Ghats",
            "Hampi Ruins",
            "Mysore Palace",
            "Darjeeling Tea Gardens",
            "Ranthambore National Park",
            "Khajuraho Temples",
            "Jaisalmer Desert Safari",
            "Leh-Ladakh",
            "Amritsar Golden Temple",
            "Kashmir Dal Lake",
            "Ajanta and Ellora Caves",
            "Kolkata Victoria Memorial",
            "Udaipur Lake Palace",
            "Andaman Islands",
            "Jim Corbett National Park",
            "Shimla Mall Road",
            "Mahabalipuram Temples",
            "Gwalior Fort",
            "Pushkar Camel Fair",
            "Rajasthan Jodhpur Fort",
            "Rishikesh River Rafting",
            "Ooty Botanical Garden",
            "Mahabaleshwar Hill Station",
            "Pondicherry Beaches",
            "Mount Abu Trekking"
        ];

        function populateTourPlaces() {
            var tourPlacesDropdown = document.getElementById("tourPlaces");

            tourPlacesData.forEach(function(place) {
                var option = new Option(place, place);
                tourPlacesDropdown.add(option);
            });
        }

        window.onload = function() {
            populateTourPlaces();
        };

        document.getElementById("tourPlaces").addEventListener("change", function() {
            var selectedPlace = this.value;
            if (selectedPlace) {
                addSelectedTourPlace(selectedPlace);
                this.value = '';
            }
        });

        function addSelectedTourPlace(place) {
            var selectedPlacesDiv = document.getElementById("selectedTourPlaces");
            var selectedPlaceElem = document.createElement("span");
            selectedPlaceElem.classList.add("selected-tour-place");
            selectedPlaceElem.textContent = place;
            var removeIcon = document.createElement("span");
            removeIcon.classList.add("remove-tour-place");
            removeIcon.textContent = "x";
            removeIcon.addEventListener("click", function() {
                selectedPlacesDiv.removeChild(selectedPlaceElem);
            });
            selectedPlaceElem.appendChild(removeIcon);
            selectedPlacesDiv.appendChild(selectedPlaceElem);
        }

        document.getElementById("tourBookingForm").addEventListener("submit", function() {
            var selectedPlaces = Array.from(document.querySelectorAll(".selected-tour-place")).map(elem => elem.textContent.replace("X", "").trim());
            document.getElementById("tourPlacesHidden").value = selectedPlaces.join(", ");
        });
    </script>
</body>
</html>
